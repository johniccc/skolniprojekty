package generators;

import models.Customer;
import simulation.CanteenSimulation;

public class CustomerGenerator extends Thread {
  private final CanteenSimulation simulation;

  public CustomerGenerator(CanteenSimulation simulation) {
    this.simulation = simulation;
  }

  private int generateInterval() {
    return 2000;
  }

  @Override
  public void run() {
    while (simulation.isRunning()) {
      try {
        int currentId = simulation.getStatsManager().incrementCustomersOverall();
        Customer customer = new Customer(currentId);
        simulation.getQueueManager().addCustomerToQueue(customer);

        System.out.println("New customer added to queue: " + customer.toString());

        Thread.sleep(generateInterval());
      } catch (InterruptedException e) {
        Thread.currentThread().interrupt();
        break;
      }
    }
  }
}
