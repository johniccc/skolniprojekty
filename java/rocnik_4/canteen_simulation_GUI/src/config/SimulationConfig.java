package config;

public class SimulationConfig {
  private final int SIMULATION_DURATION = 20 * 1000; // 20 seconds in milliseconds

  private final int CUSTOMER_GENERATION_INTERVAL = 100; // 1 second in milliseconds

  private final int MAX_SEATS = 20;
  private final int QUEUE_COUNT = 1; // NOT IMPLEMENTED YET

  private final double SOUP_PROBABILITY = 0.40;
  private final double MAIN_COURSE_PROBABILITY = 1.00;
  private final double DESSERT_PROBABILITY = 0.60;

  private final int SOUP_MIN_DURATION = 3;
  private final int SOUP_MAX_DURATION = 5;

  private final int MAIN_COURSE_MIN_DURATION = 7;
  private final int MAIN_COURSE_MAX_DURATION = 20;

  private final int DESSERT_MIN_DURATION = 1;
  private final int DESSERT_MAX_DURATION = 3;

  public SimulationConfig() {
    // Default constructor
  }

  public int getCustomerGenerationInterval() {
    return CUSTOMER_GENERATION_INTERVAL;
  }

  public int getQueueCount() {
    return QUEUE_COUNT;
  }

  public double getSoupProbability() {
    return SOUP_PROBABILITY;
  }

  public double getMainCourseProbability() {
    return MAIN_COURSE_PROBABILITY;
  }

  public double getDessertProbability() {
    return DESSERT_PROBABILITY;
  }

  public int getSimulationDuration() {
    return SIMULATION_DURATION;
  }

  public int getMaxSeats() {
    return MAX_SEATS;
  }

  public int getSoupMinDuration() {
    return SOUP_MIN_DURATION;
  }

  public int getSoupMaxDuration() {
    return SOUP_MAX_DURATION;
  }

  public int getMainCourseMinDuration() {
    return MAIN_COURSE_MIN_DURATION;
  }

  public int getMainCourseMaxDuration() {
    return MAIN_COURSE_MAX_DURATION;
  }

  public int getDessertMinDuration() {
    return DESSERT_MIN_DURATION;
  }

  public int getDessertMaxDuration() {
    return DESSERT_MAX_DURATION;
  }
}
