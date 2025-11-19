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

    // Colors
    private static final Color SEAT_FREE = new Color(144, 238, 144);
    private static final Color SEAT_BUSY = new Color(255, 99, 71);
    private static final Color EFFICIENT_ZONE = new Color(76, 175, 80);
    private static final Color INEFFICIENT_ZONE = new Color(255, 152, 0);
    private static final Color CUSTOMER_COLOR = new Color(33, 150, 243);
    private static final Color BG_COLOR = new Color(245, 245, 245);
    private static final Color TEXT_COLOR = new Color(33, 33, 33);

    // Sizes and Layout
    private int seatsPerRow = 5;
    private int rowCount;

    private int panelPaddingLeftRight = 50;
    private int titleBottomY = 40;

    private int seatWidth = 150;
    private int seatHeight = 100;
    private int seatSpacing = 20;
    private int seatPanelMarginTop = 50;
    private int seatPanelMarginBottom = 50;

    private int utilBarHeight = 8;
    private int utilPercHeight = 20;

    private int customerWidth = 40;
    private int customerHeight = 80;
    private int customerSpacing = 15;
    private int customerMarginTop = 30;

    private int statsWidth = 400;
    private int statsHeight = 420;
    private int statsPadding = 20;
    private int statsTitleMarginBottom = 35;

    public SimulationPanel(CanteenSimulation simulation, SeatManager seatManager, java.util.List<Seat> seats) {
        this.simulation = simulation;
        this.seatManager = seatManager;
        this.seats = seats;
        this.rowCount = (int) Math.ceil(seats.size() / (double) seatsPerRow);

        int headerHeight = titleBottomY;
        int seatsHeight = seatPanelMarginTop + rowCount * (seatHeight + utilBarHeight + utilPercHeight + seatSpacing)
                + seatPanelMarginBottom;
        int queueHeight = customerMarginTop + customerHeight + 40; // 40 for title
        int totalHeight = headerHeight + seatsHeight + queueHeight;

        int panelWidth = panelPaddingLeftRight * 2
                + Math.min(seatsPerRow, seats.size()) * (seatWidth + seatSpacing) - seatSpacing;
        int totalWidth = panelWidth + statsWidth + panelPaddingLeftRight;

        setPreferredSize(new Dimension(Math.max(1200, totalWidth), Math.max(700, totalHeight)));
        setBackground(BG_COLOR);

        Timer repaintTimer = new Timer(100, e -> repaint());
        repaintTimer.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

        drawTitle(g2d);
        drawSeats(g2d);
        drawQueue(g2d);
        drawStatistics(g2d);
    }

    private void drawTitle(Graphics2D g2d) {
        g2d.setFont(new Font("Arial", Font.BOLD, 28));
        g2d.setColor(TEXT_COLOR);
        String title = "Simulace jídelny";
        FontMetrics fm = g2d.getFontMetrics();

        int x = (getWidth() - fm.stringWidth(title)) / 2;
        int y = titleBottomY;
        g2d.drawString(title, x, y);
    }

    private void drawSeats(Graphics2D g2d) {
        int startX = panelPaddingLeftRight;
        int startY = titleBottomY + seatPanelMarginTop;

        g2d.setFont(new Font("Arial", Font.BOLD, 14));

        for (int i = 0; i < seats.size(); i++) {
            Seat seat = seats.get(i);
            int row = i / seatsPerRow;
            int col = i % seatsPerRow;

            int utilsOffset = utilBarHeight + utilPercHeight;

            int x = startX + col * (seatWidth + seatSpacing);
            int y = startY + row * (seatHeight + utilsOffset + seatSpacing);

            // Seat BG
            Color seatColor = seat.isBusy() ? SEAT_BUSY : SEAT_FREE;
            g2d.setColor(seatColor);
            g2d.fillRoundRect(x, y, seatWidth, seatHeight, 15, 15);

            // Stroke
            g2d.setColor(new Color(100, 100, 100));
            g2d.setStroke(new BasicStroke(2));
            g2d.drawRoundRect(x, y, seatWidth, seatHeight, 15, 15);

            // Seat names
            g2d.setColor(Color.BLACK);
            g2d.setFont(new Font("Arial", Font.BOLD, 12));
            String seatName = seat.getName();
            FontMetrics fm = g2d.getFontMetrics();

            int seatNameMarginTop = 20;

            int textX = x + (seatWidth - fm.stringWidth(seatName)) / 2;
            int textY = y + seatNameMarginTop;
            g2d.drawString(seatName, textX, textY);

            // Seat content text (customer ID or "Free")
            if (seat.isBusy() && seat.getCurrentCustomer() != null) {
                g2d.setFont(new Font("Arial", Font.BOLD, 24));
                String customerText = "#" + seat.getCurrentCustomer().getId();
                fm = g2d.getFontMetrics();

                textX = x + (seatWidth - fm.stringWidth(customerText)) / 2;
                textY = y + (seatHeight + fm.getAscent()) / 2;
                g2d.drawString(customerText, textX, textY);
            } else {
                g2d.setFont(new Font("Arial", Font.PLAIN, 16));
                g2d.setColor(new Color(100, 100, 100));
                String freeText = "Volné";
                fm = g2d.getFontMetrics();

                textX = x + (seatWidth - fm.stringWidth(freeText)) / 2;
                textY = y + (seatHeight + fm.getAscent()) / 2;
                g2d.drawString(freeText, textX, textY);
            }

            // Seat utilization bar
            double utilization = simulation.getStatsManager().getSeatUtilization(seat.getName());
            int utilBarTopMargin = 5;
            drawUtilizationBar(g2d, x, y + seatHeight + utilBarTopMargin, seatWidth, utilBarHeight, utilization);

            // Utilization percentage
            g2d.setFont(new Font("Arial", Font.PLAIN, 11));
            g2d.setColor(Color.BLACK);
            String utilizationText = String.format("%.1f%%", utilization);
            fm = g2d.getFontMetrics();

            textX = x + (seatWidth - fm.stringWidth(utilizationText)) / 2;
            textY = y + seatHeight + utilBarHeight + utilPercHeight;
            g2d.drawString(utilizationText, textX, textY);
        }
    }

    private void drawUtilizationBar(Graphics2D g2d, int x, int y, int width, int height, double utilization) {
        // BG
        g2d.setColor(new Color(220, 220, 220));
        g2d.fillRoundRect(x, y, width, height, 4, 4);

        // Fill depending on utilization
        int fillWidth = (int) (width * utilization / 100.0);
        Color barColor = utilization >= 60 && utilization <= 85 ? EFFICIENT_ZONE : INEFFICIENT_ZONE;
        g2d.setColor(barColor);
        g2d.fillRoundRect(x, y, fillWidth, height, 4, 4);

        // Stroke
        g2d.setColor(new Color(150, 150, 150));
        g2d.setStroke(new BasicStroke(1));
        g2d.drawRoundRect(x, y, width, height, 4, 4);
    }

    private void drawQueue(Graphics2D g2d) {
        int queueTitleHeight;

        int seatsBottomY = titleBottomY + seatPanelMarginTop
                + rowCount * (seatHeight + seatSpacing + utilBarHeight + utilPercHeight);

        int queueStartX = panelPaddingLeftRight;
        int queueStartY = seatsBottomY + seatPanelMarginBottom;

        // Title
        g2d.setFont(new Font("Arial", Font.BOLD, 18));
        queueTitleHeight = g2d.getFontMetrics().getAscent();
        g2d.setColor(TEXT_COLOR);
        g2d.drawString("Fronta:", queueStartX, queueStartY);

        QueueManager queueManager = simulation.getQueueManager();
        java.util.List<Queue<Customer>> queues = queueManager.getQueues();

        // Queue content
        if (queues.isEmpty() || queues.get(0).isEmpty()) {
            g2d.setFont(new Font("Arial", Font.ITALIC, 14));
            g2d.setColor(new Color(150, 150, 150));
            g2d.drawString("Fronta je prázdná", queueStartX, queueStartY + queueTitleHeight + customerMarginTop);
            return;
        }

        Queue<Customer> mainQueue = queues.get(0);
        Customer[] customers = mainQueue.toArray(new Customer[0]);

        // Ordering from newest to oldest
        for (int i = customers.length - 1; i >= 0; i--) {
            Customer customer = customers[i];
            int x = queueStartX + (customers.length - 1 - i) * (customerWidth + customerSpacing);
            int y = queueStartY + customerMarginTop;

            drawCustomer(g2d, customer, x, y, customerWidth, customerHeight, i == 0);
        }
    }

    private void drawCustomer(Graphics2D g2d, Customer customer, int x, int y, int width, int height, boolean isFirst) {
        int borderRadius = 15;

        Color customerColor = isFirst ? new Color(255, 193, 7) : CUSTOMER_COLOR;
        g2d.setColor(customerColor);

        // Head
        int headSize = (int) Math.round(width / 1.5);
        g2d.fillOval(x + (width - headSize) / 2, y, headSize, headSize);

        // Body
        g2d.fillRoundRect(x, y + headSize - 5, width, height - headSize - 5, borderRadius, borderRadius);

        // Stroke
        g2d.setColor(customerColor.darker());
        g2d.setStroke(new BasicStroke(2));
        g2d.drawOval(x + (width - headSize) / 2, y, headSize, headSize);
        g2d.drawRoundRect(x, y + headSize - 5, width, height - headSize - 5, borderRadius, borderRadius);

        // Customer number
        g2d.setFont(new Font("Arial", Font.BOLD, 14));
        g2d.setColor(Color.WHITE);
        String idText = "#" + customer.getId();
        FontMetrics fm = g2d.getFontMetrics();
        int textX = x + (width - fm.stringWidth(idText)) / 2;
        int textY = y + headSize + (height - headSize) / 2 + 5;
        g2d.drawString(idText, textX, textY);

        // Indicator for first in queue
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
        FontMetrics fm;

        int statsX = panelPaddingLeftRight * 2
                + Math.min(seatsPerRow, seats.size()) * (seatWidth + seatSpacing) - seatSpacing;
        int statsY = titleBottomY + seatPanelMarginTop;

        // BG
        g2d.setColor(Color.WHITE);
        g2d.fillRoundRect(statsX, statsY, statsWidth, statsHeight, 15, 15);
        g2d.setColor(new Color(200, 200, 200));
        g2d.setStroke(new BasicStroke(2));
        g2d.drawRoundRect(statsX, statsY, statsWidth, statsHeight, 15, 15);

        // Title
        g2d.setFont(new Font("Arial", Font.BOLD, 18));
        fm = g2d.getFontMetrics();
        g2d.setColor(new Color(33, 33, 33));
        g2d.drawString("Statistiky", statsX + statsPadding, statsY + fm.getAscent() + statsPadding);

        StatsManager stats = simulation.getStatsManager();

        g2d.setFont(new Font("Arial", Font.PLAIN, 16));
        int lineHeight = 30;
        int textY = statsY + statsPadding + fm.getAscent() + statsTitleMarginBottom;

        // Number of customers overall
        g2d.drawString("Celkem zákazníků: " + stats.getTotalCustomersOverall(), statsX + statsPadding, textY);
        textY += lineHeight;

        // Number of served customers
        g2d.drawString("Obslouženo: " + stats.getTotalCustomersServed(), statsX + statsPadding, textY);
        textY += lineHeight;

        // Average queue wait time
        g2d.drawString(String.format("Průměrná doba čekání: %.2f s", stats.getAverageQueueWaitTime()),
                statsX + statsPadding, textY);
        textY += lineHeight;

        // Current queue size
        int queueSize = simulation.getQueueManager().getTotalQueueSize();
        g2d.drawString("Aktuálně ve frontě: " + queueSize, statsX + statsPadding, textY);
    }
}