package managers;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import models.Customer;

public class QueueManager {
  private final List<Queue<Customer>> queues;

  public QueueManager(int numberOfQueues) {
    this.queues = new ArrayList<>();

    for (int i = 0; i < numberOfQueues; i++) {
      this.queues.add(new LinkedList<>());
    }
  }

  public synchronized void addCustomerToQueue(Customer customer) {
    customer.setQueueEntryTime(System.currentTimeMillis());

    int shortestQueueIndex = findShortestQueueIndex();
    queues.get(shortestQueueIndex).offer(customer);

    notifyAll();
  }

  public synchronized Customer removeCustomerFromQueue() {
    for (Queue<Customer> queue : queues) {
      if (!queue.isEmpty()) {
        Customer customer = queue.poll();

        if (customer != null) {
          customer.setServiceStartTime(System.currentTimeMillis());
        }

        return customer;
      }
    }
    return null;
  }

  public synchronized Customer waitForCustomer() throws InterruptedException {
    while (true) {
      Customer customer = removeCustomerFromQueue();
      if (customer != null) {
        return customer;
      }

      wait();
    }
  }

  private int findShortestQueueIndex() {
    int shortestQueueIndex = -1;
    int shortestQueueSize = Integer.MAX_VALUE;

    for (int i = 0; i < queues.size(); i++) {
      Queue<Customer> queue = queues.get(i);
      if (queue.size() < shortestQueueSize) {
        shortestQueueSize = queue.size();
        shortestQueueIndex = i;
      }
    }

    return shortestQueueIndex;
  }

  public List<Queue<Customer>> getQueues() {
    return queues;
  }
}
