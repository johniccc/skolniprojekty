package views;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.*;
import java.util.Queue;
import models.Customer;
import models.Seat;
import managers.SeatManager;
import managers.QueueManager;
import managers.StatsManager;
import simulation.CanteenSimulation;

public class SimulationPanel extends JPanel {
    private final CanteenSimulation simulation;
    private final SeatManager seatManager;
    private final java.util.List<Seat> seats;

    // Barvy
    private static final Color SEAT_FREE = new Color(144, 238, 144);
    private static final Color SEAT_BUSY = new Color(255, 99, 71);
    private static final Color EFFICIENT_ZONE = new Color(76, 175, 80);
    private static final Color INEFFICIENT_ZONE = new Color(255, 152, 0);
    private static final Color CUSTOMER_COLOR = new Color(33, 150, 243);
    private static final Color BG_COLOR = new Color(245, 245, 245);

    public SimulationPanel(CanteenSimulation simulation, SeatManager seatManager, java.util.List<Seat> seats) {
        this.simulation = simulation;
        this.seatManager = seatManager;
        this.seats = seats;

        // Dynamicky vypočítáme výšku okna podle počtu stolů
        int seatHeight = 100;
        int spacing = 20;
        int seatsPerRow = 5;
        int rowCount = (int) Math.ceil(seats.size() / (double) seatsPerRow);
        int seatsBottomY = 80 + rowCount * (seatHeight + spacing + 50);
        int queueHeight = 120;
        int totalHeight = seatsBottomY + queueHeight + 100; // +100 pro nadpis fronty a mezery

        setPreferredSize(new Dimension(1200, Math.max(700, totalHeight)));
        setBackground(BG_COLOR);

        // Timer pro překreslování
        Timer repaintTimer = new Timer(100, e -> repaint());
        repaintTimer.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        // Antialiasing pro hladší vykreslování
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

        drawTitle(g2d);
        drawSeats(g2d);
        drawQueue(g2d);
        drawStatistics(g2d);
    }

    private void drawTitle(Graphics2D g2d) {
        g2d.setFont(new Font("Arial", Font.BOLD, 28));
        g2d.setColor(new Color(33, 33, 33));
        String title = "Simulace jídelny";
        FontMetrics fm = g2d.getFontMetrics();
        int x = (getWidth() - fm.stringWidth(title)) / 2;
        g2d.drawString(title, x, 40);
    }

    private void drawSeats(Graphics2D g2d) {
        int seatWidth = 120;
        int seatHeight = 100;
        int startX = 50;
        int startY = 80;
        int spacing = 20;
        int seatsPerRow = 5;

        g2d.setFont(new Font("Arial", Font.BOLD, 14));

        for (int i = 0; i < seats.size(); i++) {
            Seat seat = seats.get(i);
            int row = i / seatsPerRow;
            int col = i % seatsPerRow;

            int x = startX + col * (seatWidth + spacing);
            int y = startY + row * (seatHeight + spacing + 50);

            // Pozadí sedadla
            Color seatColor = seat.isBusy() ? SEAT_BUSY : SEAT_FREE;
            g2d.setColor(seatColor);
            g2d.fillRoundRect(x, y, seatWidth, seatHeight, 15, 15);

            // Obrys
            g2d.setColor(new Color(100, 100, 100));
            g2d.setStroke(new BasicStroke(2));
            g2d.drawRoundRect(x, y, seatWidth, seatHeight, 15, 15);

            // Název sedadla
            g2d.setColor(Color.BLACK);
            g2d.setFont(new Font("Arial", Font.BOLD, 12));
            String seatName = seat.getName();
            FontMetrics fm = g2d.getFontMetrics();
            int textX = x + (seatWidth - fm.stringWidth(seatName)) / 2;
            g2d.drawString(seatName, textX, y + 20);

            // Číslo zákazníka nebo "Volné"
            if (seat.isBusy() && seat.getCurrentCustomer() != null) {
                g2d.setFont(new Font("Arial", Font.BOLD, 24));
                String customerText = "#" + getCustomerId(seat.getCurrentCustomer());
                fm = g2d.getFontMetrics();
                textX = x + (seatWidth - fm.stringWidth(customerText)) / 2;
                g2d.drawString(customerText, textX, y + 55);
            } else {
                g2d.setFont(new Font("Arial", Font.PLAIN, 16));
                g2d.setColor(new Color(100, 100, 100));
                String freeText = "Volné";
                fm = g2d.getFontMetrics();
                textX = x + (seatWidth - fm.stringWidth(freeText)) / 2;
                g2d.drawString(freeText, textX, y + 55);
            }

            // Vytížení sedadla
            double utilization = simulation.getStatsManager().getSeatUtilization(seat.getName());
            drawUtilizationBar(g2d, x, y + seatHeight + 5, seatWidth, 8, utilization);

            // Procento vytížení
            g2d.setFont(new Font("Arial", Font.PLAIN, 11));
            g2d.setColor(Color.BLACK);
            String utilizationText = String.format("%.1f%%", utilization);
            fm = g2d.getFontMetrics();
            textX = x + (seatWidth - fm.stringWidth(utilizationText)) / 2;
            g2d.drawString(utilizationText, textX, y + seatHeight + 25);
        }
    }

    private void drawUtilizationBar(Graphics2D g2d, int x, int y, int width, int height, double utilization) {
        // Pozadí
        g2d.setColor(new Color(220, 220, 220));
        g2d.fillRoundRect(x, y, width, height, 4, 4);

        // Výplň podle vytížení
        int fillWidth = (int) (width * utilization / 100.0);
        Color barColor = utilization >= 60 && utilization <= 85 ? EFFICIENT_ZONE : INEFFICIENT_ZONE;
        g2d.setColor(barColor);
        g2d.fillRoundRect(x, y, fillWidth, height, 4, 4);

        // Obrys
        g2d.setColor(new Color(150, 150, 150));
        g2d.setStroke(new BasicStroke(1));
        g2d.drawRoundRect(x, y, width, height, 4, 4);
    }

    private void drawQueue(Graphics2D g2d) {
        // Dynamicky vypočítáme pozici fronty podle počtu stolů
        int seatHeight = 100;
        int spacing = 20;
        int seatsPerRow = 5;
        int rowCount = (int) Math.ceil(seats.size() / (double) seatsPerRow);
        int seatsBottomY = 80 + rowCount * (seatHeight + spacing + 50);

        int queueStartX = 50;
        int queueStartY = seatsBottomY + 30; // 30px mezera pod stoly
        int customerWidth = 60;
        int customerHeight = 80;
        int overlap = 15;

        g2d.setFont(new Font("Arial", Font.BOLD, 18));
        g2d.setColor(new Color(33, 33, 33));
        g2d.drawString("Fronta:", queueStartX, queueStartY - 10);

        QueueManager queueManager = simulation.getQueueManager();
        java.util.List<Queue<Customer>> queues = queueManager.getQueues();

        if (queues.isEmpty() || queues.get(0).isEmpty()) {
            g2d.setFont(new Font("Arial", Font.ITALIC, 14));
            g2d.setColor(new Color(150, 150, 150));
            g2d.drawString("Fronta je prázdná", queueStartX + 100, queueStartY + 40);
            return;
        }

        Queue<Customer> mainQueue = queues.get(0);
        Customer[] customers = mainQueue.toArray(new Customer[0]);

        // Kreslíme od konce (nejstarší vzadu)
        for (int i = customers.length - 1; i >= 0; i--) {
            Customer customer = customers[i];
            int x = queueStartX + 100 + (customers.length - 1 - i) * (customerWidth - overlap);
            int y = queueStartY;

            drawCustomer(g2d, customer, x, y, customerWidth, customerHeight, i == 0);
        }
    }

    private void drawCustomer(Graphics2D g2d, Customer customer, int x, int y, int width, int height, boolean isFirst) {
        // Stín
        g2d.setColor(new Color(0, 0, 0, 30));
        g2d.fillOval(x + 5, y + height - 5, width - 10, 10);

        // Tělo zákazníka
        Color customerColor = isFirst ? new Color(255, 193, 7) : CUSTOMER_COLOR;
        g2d.setColor(customerColor);

        // Hlava
        int headSize = width / 2;
        g2d.fillOval(x + width / 2 - headSize / 2, y, headSize, headSize);

        // Tělo
        g2d.fillRoundRect(x + 10, y + headSize - 5, width - 20, height - headSize - 5, 15, 15);

        // Obrys
        g2d.setColor(customerColor.darker());
        g2d.setStroke(new BasicStroke(2));
        g2d.drawOval(x + width / 2 - headSize / 2, y, headSize, headSize);
        g2d.drawRoundRect(x + 10, y + headSize - 5, width - 20, height - headSize - 5, 15, 15);

        // Číslo zákazníka
        g2d.setFont(new Font("Arial", Font.BOLD, 14));
        g2d.setColor(Color.WHITE);
        String idText = "#" + getCustomerId(customer);
        FontMetrics fm = g2d.getFontMetrics();
        int textX = x + (width - fm.stringWidth(idText)) / 2;
        int textY = y + headSize + (height - headSize) / 2 + 5;
        g2d.drawString(idText, textX, textY);

        // Indikátor pro prvního ve frontě
        if (isFirst) {
            g2d.setFont(new Font("Arial", Font.BOLD, 12));
            g2d.setColor(new Color(255, 87, 34));
            String firstText = "DALŠÍ";
            fm = g2d.getFontMetrics();
            textX = x + (width - fm.stringWidth(firstText)) / 2;
            g2d.drawString(firstText, textX, y - 5);
        }
    }

    private void drawStatistics(Graphics2D g2d) {
        int statsX = 750;
        int statsY = 80;
        int statsWidth = 400;
        int statsHeight = 420;

        // Pozadí statistik
        g2d.setColor(Color.WHITE);
        g2d.fillRoundRect(statsX, statsY, statsWidth, statsHeight, 15, 15);
        g2d.setColor(new Color(200, 200, 200));
        g2d.setStroke(new BasicStroke(2));
        g2d.drawRoundRect(statsX, statsY, statsWidth, statsHeight, 15, 15);

        // Nadpis
        g2d.setFont(new Font("Arial", Font.BOLD, 18));
        g2d.setColor(new Color(33, 33, 33));
        g2d.drawString("Statistiky", statsX + 20, statsY + 30);

        StatsManager stats = simulation.getStatsManager();

        g2d.setFont(new Font("Arial", Font.PLAIN, 16));
        int lineHeight = 30;
        int textY = statsY + 60;

        // Celkový počet zákazníků
        g2d.drawString("Celkem zákazníků: " + stats.getTotalCustomersOverall(), statsX + 20, textY);
        textY += lineHeight;

        // Obsloužení zákazníci
        g2d.drawString("Obslouženo: " + stats.getTotalCustomersServed(), statsX + 20, textY);
        textY += lineHeight;

        // Průměrná doba čekání
        g2d.drawString(String.format("Průměrná doba čekání: %.2f s", stats.getAverageQueueWaitTime()),
                statsX + 20, textY);
        textY += lineHeight;

        // Počet ve frontě
        int queueSize = simulation.getQueueManager().getTotalQueueSize();
        g2d.drawString("Aktuálně ve frontě: " + queueSize, statsX + 20, textY);
    }

    private int getCustomerId(Customer customer) {
        return Integer.parseInt(customer.toString().split("#")[1].split("\\(")[0]);
    }
}