package managers;

import java.util.ArrayList;
import java.util.List;
import models.Seat;
import simulation.CanteenSimulation;

public class SeatManager {
  private final List<Seat> seats;

  public SeatManager(int totalSeats, CanteenSimulation simulation) {
    this.seats = new ArrayList<>();

    for (int i = 0; i < totalSeats; i++) {
      Seat seat = new Seat(simulation);
      seat.setName("Seat-" + i);
      seats.add(seat);
    }
  }

  public void startAllSeats() {
    for (Seat seat : seats) {
      seat.start();
    }
  }

  public void stopAllSeats() {
    for (Seat seat : seats) {
      seat.interrupt();
    }
  }

  public void waitForAllSeats() throws InterruptedException {
    for (Seat seat : seats) {
      seat.join();
    }
  }

  public List<Seat> getSeats() {
    return seats;
  }
}
