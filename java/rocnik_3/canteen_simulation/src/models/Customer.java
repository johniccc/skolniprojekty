package models;

import generators.ServiceGenerator;

public class Customer {
  private final int id;

  private boolean hasSoup;
  private boolean hasMainCourse;
  private boolean hasDessert;

  private int soupDuration = 0;
  private int mainCourseDuration = 0;
  private int dessertDuration = 0;
  private int totalDuration = 0;

  private final long creationTime;
  private long queueEntryTime;
  private long serviceStartTime;
  private long serviceEndTime;

  public Customer(int id) {
    this.id = id;
    this.creationTime = System.currentTimeMillis();
    initCustomer();
  }

  private void initCustomer() {
    ServiceGenerator serviceGenerator = new ServiceGenerator();

    if (this.hasSoup = serviceGenerator.hasSoup()) {
      this.soupDuration = serviceGenerator.generateSoupDuration();
    }

    if (this.hasMainCourse = serviceGenerator.hasMainCourse()) {
      this.mainCourseDuration = serviceGenerator.generateMainCourseDuration();
    }

    if (this.hasDessert = serviceGenerator.hasDessert()) {
      this.dessertDuration = serviceGenerator.generateDessertDuration();
    }

    this.totalDuration = this.soupDuration + this.mainCourseDuration + this.dessertDuration;
  }

  public boolean hasSoup() {
    return this.hasSoup;
  }

  public boolean hasMainCourse() {
    return this.hasMainCourse;
  }

  public boolean hasDessert() {
    return this.hasDessert;
  }

  public int getSoupDuration() {
    return this.soupDuration;
  }

  public int getMainCourseDuration() {
    return this.mainCourseDuration;
  }

  public int getDessertDuration() {
    return this.dessertDuration;
  }

  public int getTotalDuration() {
    return this.totalDuration;
  }

  // Timestamp methods
  public void setQueueEntryTime(long queueEntryTime) {
    this.queueEntryTime = queueEntryTime;
  }

  public void setServiceStartTime(long serviceStartTime) {
    this.serviceStartTime = serviceStartTime;
  }

  public void setServiceEndTime(long serviceEndTime) {
    this.serviceEndTime = serviceEndTime;
  }

  public long getCreationTime() {
    return creationTime;
  }

  public long getQueueEntryTime() {
    return queueEntryTime;
  }

  public long getServiceStartTime() {
    return serviceStartTime;
  }

  public long getServiceEndTime() {
    return serviceEndTime;
  }

  // Stats methods
  public long getQueueWaitTime() {
    if (queueEntryTime == 0 || serviceStartTime == 0) {
      return 0;
    }
    return serviceStartTime - queueEntryTime;
  }

  public long getActualServiceTime() {
    if (serviceStartTime == 0 || serviceEndTime == 0) {
      return 0;
    }
    return serviceEndTime - serviceStartTime;
  }

  @Override
  public String toString() {
    return "Customer #" + id + "(" +
        "hasSoup=" + hasSoup +
        ", hasMainCourse=" + hasMainCourse +
        ", hasDessert=" + hasDessert +
        ", totalDuration=" + totalDuration +
        ')';
  }
}
