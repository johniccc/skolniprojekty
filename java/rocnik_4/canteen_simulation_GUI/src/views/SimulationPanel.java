package views;

import java.awt.*;
import java.awt.geom.*;
import javax.swing.*;
import javax.swing.Timer;

import java.util.*;
import java.util.List;
import models.Customer;
import models.Seat;
import simulation.CanteenSimulation;

public class SimulationPanel extends JPanel {
    private final CanteenSimulation simulation;
    private Timer repaintTimer;

    // Vizuální konstanty
    private static final int SEAT_WIDTH = 120;
    private static final int SEAT_HEIGHT = 80;
    private static final int CUSTOMER_SIZE = 30;
    private static final int CUSTOMER_OVERLAP = 5;

    // Barvy
    private static final Color SEAT_FREE = new Color(144, 238, 144);
    private static final Color SEAT_BUSY = new Color(255, 182, 193);
    private static final Color UTILIZATION_GOOD = new Color(100, 200, 100);
    private static final Color UTILIZATION_BAD = new Color(255, 100, 100);
    private static final Color CUSTOMER_COLOR = new Color(70, 130, 180);

    public SimulationPanel(CanteenSimulation simulation) {
        this.simulation = simulation;
        setPreferredSize(new Dimension(1200, 800));
        setBackground(new Color(240, 240, 245));

        // Pravidelné překreslování každých 100ms
        repaintTimer = new Timer(100, e -> repaint());
        repaintTimer.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        // Antialiasing pro hladší vykreslování
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,
                RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

        drawTitle(g2d);
        drawSeats(g2d);
        drawQueue(g2d);
        drawStatistics(g2d);
    }

    private void drawTitle(Graphics2D g2d) {
        g2d.setColor(new Color(50, 50, 50));
        g2d.setFont(new Font("Arial", Font.BOLD, 24));
        String title = "Simulace kantýny - Vizualizace";
        FontMetrics fm = g2d.getFontMetrics();
        int x = (getWidth() - fm.stringWidth(title)) / 2;
        g2d.drawString(title, x, 40);
    }

    private void drawSeats(Graphics2D g2d) {
        List<Seat> seats = getAllSeats();

        int seatsPerRow = 5;
        int startX = 50;
        int startY = 100;
        int spacingX = SEAT_WIDTH + 30;
        int spacingY = SEAT_HEIGHT + 60;

        g2d.setFont(new Font("Arial", Font.PLAIN, 12));

        for (int i = 0; i < seats.size(); i++) {
            Seat seat = seats.get(i);
            int row = i / seatsPerRow;
            int col = i % seatsPerRow;

            int x = startX + col * spacingX;
            int y = startY + row * spacingY;

            drawSingleSeat(g2d, seat, x, y);
        }
    }

    private void drawSingleSeat(Graphics2D g2d, Seat seat, int x, int y) {
        String seatName = seat.getName();
        boolean isBusy = seat.isBusy();
        Customer currentCustomer = seat.getCurrentCustomer();

        // Získání statistik
        double utilization = simulation.getStatsManager().getSeatUtilization(seatName);

        // Barva podle stavu
        Color seatColor = isBusy ? SEAT_BUSY : SEAT_FREE;

        // Vykreslení obdélníku sedadla
        g2d.setColor(seatColor);
        g2d.fill(new RoundRectangle2D.Double(x, y, SEAT_WIDTH, SEAT_HEIGHT, 10, 10));

        g2d.setColor(Color.DARK_GRAY);
        g2d.setStroke(new BasicStroke(2));
        g2d.draw(new RoundRectangle2D.Double(x, y, SEAT_WIDTH, SEAT_HEIGHT, 10, 10));

        // Název sedadla
        g2d.setColor(Color.BLACK);
        g2d.setFont(new Font("Arial", Font.BOLD, 14));
        String displayName = seatName.replace("Seat-", "Místo ");
        drawCenteredString(g2d, displayName, x + SEAT_WIDTH / 2, y + 25);

        // Číslo zákazníka (pokud je obsazeno)
        if (isBusy && currentCustomer != null) {
            g2d.setFont(new Font("Arial", Font.BOLD, 20));
            g2d.setColor(new Color(150, 0, 0));
            drawCenteredString(g2d, "#" + getCustomerId(currentCustomer),
                    x + SEAT_WIDTH / 2, y + SEAT_HEIGHT / 2 + 5);
        } else if (!isBusy) {
            g2d.setFont(new Font("Arial", Font.ITALIC, 16));
            g2d.setColor(new Color(100, 100, 100));
            drawCenteredString(g2d, "Volné", x + SEAT_WIDTH / 2, y + SEAT_HEIGHT / 2 + 5);
        }

        // Utilization bar
        int barY = y + SEAT_HEIGHT + 5;
        int barHeight = 8;

        // Pozadí
        g2d.setColor(Color.LIGHT_GRAY);
        g2d.fill(new RoundRectangle2D.Double(x, barY, SEAT_WIDTH, barHeight, 4, 4));

        // Výplň podle využití
        int fillWidth = (int) (SEAT_WIDTH * utilization / 100.0);
        Color utilizationColor = utilization >= 60 ? UTILIZATION_GOOD : UTILIZATION_BAD;
        g2d.setColor(utilizationColor);
        g2d.fill(new RoundRectangle2D.Double(x, barY, fillWidth, barHeight, 4, 4));

        // Border
        g2d.setColor(Color.DARK_GRAY);
        g2d.setStroke(new BasicStroke(1));
        g2d.draw(new RoundRectangle2D.Double(x, barY, SEAT_WIDTH, barHeight, 4, 4));

        // Procenta
        g2d.setFont(new Font("Arial", Font.PLAIN, 11));
        g2d.setColor(Color.BLACK);
        String utilText = String.format("%.1f%%", utilization);
        drawCenteredString(g2d, utilText, x + SEAT_WIDTH / 2, barY + barHeight + 14);
    }

    private void drawQueue(Graphics2D g2d) {
        int queueX = 50;
        int queueY = 500;

        g2d.setColor(Color.BLACK);
        g2d.setFont(new Font("Arial", Font.BOLD, 16));
        g2d.drawString("Fronta zákazníků:", queueX, queueY - 10);

        // Získání fronty
        var queues = simulation.getQueueManager().getQueues();
        if (queues.isEmpty())
            return;

        var queue = queues.get(0); // První fronta

        synchronized (queue) {
            List<Customer> customers = new ArrayList<>(queue);

            if (customers.isEmpty()) {
                g2d.setFont(new Font("Arial", Font.ITALIC, 14));
                g2d.setColor(Color.GRAY);
                g2d.drawString("Fronta je prázdná", queueX + 20, queueY + 40);
                return;
            }

            // Vykreslení zákazníků s překrytím (první nahoře)
            for (int i = customers.size() - 1; i >= 0; i--) {
                Customer customer = customers.get(i);
                int x = queueX + 20 + i * CUSTOMER_OVERLAP;
                int y = queueY;

                drawCustomer(g2d, customer, x, y, i == 0);
            }

            // Zobrazení počtu čekajících
            g2d.setFont(new Font("Arial", Font.BOLD, 14));
            g2d.setColor(Color.BLACK);
            g2d.drawString("Počet čekajících: " + customers.size(),
                    queueX + 20 + customers.size() * CUSTOMER_OVERLAP + CUSTOMER_SIZE + 20,
                    queueY + CUSTOMER_SIZE / 2 + 5);
        }
    }

    private void drawCustomer(Graphics2D g2d, Customer customer, int x, int y, boolean isFirst) {
        // Kruh reprezentující zákazníka
        if (isFirst) {
            g2d.setColor(CUSTOMER_COLOR.brighter());
        } else {
            g2d.setColor(CUSTOMER_COLOR);
        }

        g2d.fill(new Ellipse2D.Double(x, y, CUSTOMER_SIZE, CUSTOMER_SIZE));

        g2d.setColor(Color.WHITE);
        g2d.setStroke(new BasicStroke(2));
        g2d.draw(new Ellipse2D.Double(x, y, CUSTOMER_SIZE, CUSTOMER_SIZE));

        // Číslo zákazníka
        g2d.setFont(new Font("Arial", Font.BOLD, 11));
        g2d.setColor(Color.WHITE);
        String id = String.valueOf(getCustomerId(customer));
        drawCenteredString(g2d, id, x + CUSTOMER_SIZE / 2, y + CUSTOMER_SIZE / 2 + 4);
    }

    private void drawStatistics(Graphics2D g2d) {
        int statsX = 650;
        int statsY = 500;
        int lineHeight = 30;

        // Pozadí pro statistiky
        g2d.setColor(new Color(255, 255, 255, 200));
        g2d.fill(new RoundRectangle2D.Double(statsX - 10, statsY - 30, 500, 250, 15, 15));

        g2d.setColor(Color.DARK_GRAY);
        g2d.setStroke(new BasicStroke(2));
        g2d.draw(new RoundRectangle2D.Double(statsX - 10, statsY - 30, 500, 250, 15, 15));

        // Nadpis
        g2d.setColor(Color.BLACK);
        g2d.setFont(new Font("Arial", Font.BOLD, 18));
        g2d.drawString("Statistiky simulace", statsX, statsY);

        statsY += 15;

        // Statistiky
        g2d.setFont(new Font("Arial", Font.PLAIN, 14));

        int totalCustomers = simulation.getStatsManager().getTotalCustomersOverall();
        int servedCustomers = simulation.getStatsManager().getTotalCustomersServed();
        double avgWaitTime = simulation.getStatsManager().getAverageQueueWaitTime();

        g2d.drawString("Celkem zákazníků: " + totalCustomers,
                statsX, statsY += lineHeight);
        g2d.drawString("Obslouženo: " + servedCustomers,
                statsX, statsY += lineHeight);
        g2d.drawString("Čeká: " + (totalCustomers - servedCustomers),
                statsX, statsY += lineHeight);
        g2d.drawString(String.format("Průměrná doba čekání: %.2f s", avgWaitTime),
                statsX, statsY += lineHeight);

        // Poměr času
        double avgServiceTime = calculateAverageServiceTime();
        if (avgWaitTime > 0 && avgServiceTime > 0) {
            double ratio = avgServiceTime / avgWaitTime;
            g2d.drawString(String.format("Poměr (obsluha/čekání): %.2f", ratio),
                    statsX, statsY += lineHeight);
        }
    }

    // Pomocné metody
    private void drawCenteredString(Graphics2D g2d, String text, int x, int y) {
        FontMetrics fm = g2d.getFontMetrics();
        int textWidth = fm.stringWidth(text);
        int textX = x - textWidth / 2;
        g2d.drawString(text, textX, y);
    }

    private List<Seat> getAllSeats() {
        List<Seat> seats = new ArrayList<>();
        ThreadGroup rootGroup = Thread.currentThread().getThreadGroup();
        ThreadGroup parent;
        while ((parent = rootGroup.getParent()) != null) {
            rootGroup = parent;
        }

        Thread[] threads = new Thread[rootGroup.activeCount() * 2];
        int count = rootGroup.enumerate(threads);

        for (int i = 0; i < count; i++) {
            if (threads[i] instanceof Seat && threads[i].isAlive()) {
                seats.add((Seat) threads[i]);
            }
        }

        return seats;
    }

    private int getCustomerId(Customer customer) {
        // Použití reflection pro získání ID
        try {
            var field = Customer.class.getDeclaredField("id");
            field.setAccessible(true);
            return (int) field.get(customer);
        } catch (Exception e) {
            return 0;
        }
    }

    private double calculateAverageServiceTime() {
        List<Seat> seats = getAllSeats();
        double totalTime = 0;
        int count = 0;

        for (Seat seat : seats) {
            double time = simulation.getStatsManager()
                    .getAverageSeatServiceTime(seat.getName());
            if (time > 0) {
                totalTime += time;
                count++;
            }
        }

        return count > 0 ? totalTime / count : 0;
    }

    public void stopTimer() {
        if (repaintTimer != null) {
            repaintTimer.stop();
        }
    }
}