package models;

import managers.QueueManager;
import managers.StatsManager;
import simulation.CanteenSimulation;

public class Seat extends Thread {
  private final CanteenSimulation simulation;
  private final QueueManager queueManager;
  private final StatsManager statsManager;

  public Seat(CanteenSimulation simulation) {
    this.simulation = simulation;
    this.queueManager = simulation.getQueueManager();
    this.statsManager = simulation.getStatsManager();
  }

  @Override
  public void run() {
    while (simulation.isRunning() && !isInterrupted()) {
      try {
        Customer customer = queueManager.waitForCustomer();
        if (customer != null) {
          serveCustomer(customer);
        }
      } catch (InterruptedException e) {
        break;
      }
    }
    System.out.println("Seat " + getName() + " stopped");
  }

  private void serveCustomer(Customer customer) {
    long serviceStartTime = System.currentTimeMillis();

    try {
      long queueWaitTime = customer.getQueueWaitTime();
      if (queueWaitTime > 0) {
        statsManager.addQueueWaitTime(queueWaitTime);
      }

      Thread.sleep(customer.getTotalDuration() * 1000);

      long serviceEndTime = System.currentTimeMillis();
      customer.setServiceEndTime(serviceEndTime);

      long actualServiceTime = serviceEndTime - serviceStartTime;
      statsManager.addSeatServiceTime(getName(), actualServiceTime);

      System.out.println(customer.toString() + " leaving seat " + getName() +
          " after " + customer.getTotalDuration() + " seconds." +
          " (Queue wait: " + String.format("%.1f", queueWaitTime / 1000.0) + "s)");

      statsManager.incrementCustomersServed();

    } catch (InterruptedException e) {
      Thread.currentThread().interrupt();
      System.err.println("Seat " + getName() + " interrupted while serving customer: " + e.getMessage());

      // Partial service time if interrupted also added
      long partialServiceTime = System.currentTimeMillis() - serviceStartTime;
      if (partialServiceTime > 0) {
        statsManager.addSeatServiceTime(getName(), partialServiceTime);
      }
    }
  }
}
