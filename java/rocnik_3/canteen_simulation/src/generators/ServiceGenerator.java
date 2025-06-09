package generators;

import config.SimulationConfig;

public class ServiceGenerator {
  SimulationConfig config;

  public ServiceGenerator() {
    this.config = new SimulationConfig();
  }

  public boolean hasSoup() {
    return Math.random() < config.getSoupProbability();
  }

  public boolean hasMainCourse() {
    return Math.random() < config.getMainCourseProbability();
  }

  public boolean hasDessert() {
    return Math.random() < config.getDessertProbability();
  }

  public int generateSoupDuration() {
    return config.getSoupMinDuration()
        + (int) (Math.random() * (config.getSoupMaxDuration() - config.getSoupMinDuration() + 1));
  }

  public int generateMainCourseDuration() {
    return config.getMainCourseMinDuration()
        + (int) (Math.random() * (config.getMainCourseMaxDuration() - config.getMainCourseMinDuration() + 1));
  }

  public int generateDessertDuration() {
    return config.getDessertMinDuration()
        + (int) (Math.random() * (config.getDessertMaxDuration() - config.getDessertMinDuration() + 1));
  }
}
