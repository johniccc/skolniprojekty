package simulation;

import config.SimulationConfig;
import generators.CustomerGenerator;
import managers.QueueManager;
import managers.SeatManager;
import managers.StatsManager;

public class CanteenSimulation {
  private final SimulationConfig config;
  private final QueueManager queueManager;
  private final SeatManager seatManager;
  private final StatsManager statsManager;

  private volatile boolean isRunning = false;

  private Thread customerGeneratorThread;

  public CanteenSimulation(SimulationConfig config) {
    this.config = config;

    this.statsManager = new StatsManager();
    this.queueManager = new QueueManager(config.getQueueCount());
    this.seatManager = new SeatManager(config.getMaxSeats(), this);
  }

  public void start() {
    System.out.println();
    System.out.println("Canteen Simulation started.");
    System.out.println("Press Enter to stop the simulation...");
    System.out.println("-".repeat(50));

    isRunning = true;

    customerGeneratorThread = new CustomerGenerator(this);
    customerGeneratorThread.start();

    seatManager.startAllSeats();
  }

  public void stop() {
    System.out.println("Stopping simulation, wait for customers to finish...");
    isRunning = false;

    if (customerGeneratorThread != null) {
      customerGeneratorThread.interrupt();
    }

    seatManager.stopAllSeats();

    try {
      if (customerGeneratorThread != null) {
        customerGeneratorThread.join();
      }
      seatManager.waitForAllSeats();

      statsManager.setFinalQueueSize(queueManager.getTotalQueueSize());
    } catch (InterruptedException e) {
      Thread.currentThread().interrupt();
    }

    statsManager.printStats();
  }

  public boolean isRunning() {
    return isRunning;
  }

  public QueueManager getQueueManager() {
    return queueManager;
  }

  public StatsManager getStatsManager() {
    return statsManager;
  }

  public SeatManager getSeatManager() {
    return seatManager;
  }

  public SimulationConfig getConfig() {
    return config;
  }
}
