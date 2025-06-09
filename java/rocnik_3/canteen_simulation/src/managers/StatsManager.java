package managers;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class StatsManager {
  private int totalCustomersOverall = 0;
  private int totalCustomersServed = 0;
  private final long simulationStartTime;

  private final Map<String, Long> seatServiceTimes = new ConcurrentHashMap<>(); // in milliseconds
  private final Map<String, Integer> seatCustomerCounts = new ConcurrentHashMap<>(); // count customers per seat

  private long totalQueueWaitTime = 0;
  private int customersWithQueueData = 0;

  public StatsManager() {
    this.simulationStartTime = System.currentTimeMillis();
  }

  public synchronized int incrementCustomersOverall() {
    return ++totalCustomersOverall;
  }

  public synchronized int getTotalCustomersOverall() {
    return totalCustomersOverall;
  }

  public synchronized int incrementCustomersServed() {
    return ++totalCustomersServed;
  }

  public synchronized int getTotalCustomersServed() {
    return totalCustomersServed;
  }

  public synchronized void addQueueWaitTime(long waitTimeMs) {
    totalQueueWaitTime += waitTimeMs;
    customersWithQueueData++;
  }

  public synchronized void addSeatServiceTime(String seatName, long serviceTimeMs) {
    seatServiceTimes.merge(seatName, serviceTimeMs, Long::sum);
    seatCustomerCounts.merge(seatName, 1, Integer::sum);
  }

  public synchronized double getAverageQueueWaitTime() {
    if (customersWithQueueData == 0)
      return 0.0;
    return (double) totalQueueWaitTime / customersWithQueueData / 1000.0;
  }

  public synchronized double getSeatUtilization(String seatName) {
    long totalSimulationTime = System.currentTimeMillis() - simulationStartTime;
    Long serviceTime = seatServiceTimes.get(seatName);

    if (serviceTime == null || totalSimulationTime == 0)
      return 0.0;

    return (double) serviceTime / totalSimulationTime * 100.0;
  }

  public synchronized double getAverageSeatServiceTime(String seatName) {
    Long totalServiceTime = seatServiceTimes.get(seatName);
    Integer customerCount = seatCustomerCounts.get(seatName);

    if (totalServiceTime == null || customerCount == null || customerCount == 0) {
      return 0.0;
    }

    return (double) totalServiceTime / customerCount / 1000.0;
  }

  public synchronized void printStats() {
    long totalSimulationTime = System.currentTimeMillis() - simulationStartTime;
    double simulationTimeSeconds = totalSimulationTime / 1000.0;

    System.out.println("\n" + "=".repeat(60));
    System.out.println("SIMULATION STATISTICS");
    System.out.println("=".repeat(60));

    // Basic statistics
    System.out.println("Total simulation time: " + String.format("%.1f", simulationTimeSeconds) + " seconds");
    System.out.println("Total customers: " + totalCustomersOverall);
    System.out.println("Served customers: " + totalCustomersServed);
    System.out.println("Waiting customers: " + (totalCustomersOverall - totalCustomersServed));

    // Queue statistics
    System.out.println("\nQUEUE STATISTICS:");
    System.out
        .println("Average queue wait time: " + String.format("%.2f", getAverageQueueWaitTime()) + " seconds");

    // Seat statistics
    System.out.println("\nINDIVIDUAL SEAT STATISTICS:");
    System.out.println("-".repeat(80));
    System.out.printf("%-10s | %-12s | %-15s | %-12s | %-10s%n",
        "Seat", "Cus. count", "Tot. time", "Avg. time", "Utilization");
    System.out.println("-".repeat(80));

    for (String seatName : seatServiceTimes.keySet()) {
      int customerCount = seatCustomerCounts.getOrDefault(seatName, 0);
      long totalServiceTime = seatServiceTimes.get(seatName);
      double avgServiceTime = getAverageSeatServiceTime(seatName);
      double utilization = getSeatUtilization(seatName);

      System.out.printf("%-10s | %-12d | %-15.1f | %-12.2f | %-9.1f%%%n",
          seatName,
          customerCount,
          totalServiceTime / 1000.0,
          avgServiceTime,
          utilization);
    }

    System.out.println("-".repeat(80));

    // Overall seat utilization
    double totalUtilization = seatServiceTimes.values().stream()
        .mapToLong(Long::longValue)
        .sum() / (double) (totalSimulationTime * seatServiceTimes.size()) * 100.0;

    System.out.println("Average utilization of all seats: " + String.format("%.1f", totalUtilization) + "%");
    System.out
        .println("The seat count IS" + ((totalUtilization > 60) ? " NOT" : "") + " sufficient for the customers.");
    System.out.println("=".repeat(60));
  }
}