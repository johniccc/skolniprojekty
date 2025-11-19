package views;

import javax.swing.*;
import java.awt.*;
import simulation.CanteenSimulation;
import managers.SeatManager;
import models.Seat;
import java.util.List;
import java.util.ArrayList;
import java.lang.reflect.Field;

public class SimulationFrame extends JFrame {
    private final CanteenSimulation simulation;
    private SimulationPanel simulationPanel;
    private JButton startButton;
    private JButton stopButton;

    public SimulationFrame(CanteenSimulation simulation) {
        this.simulation = simulation;

        setTitle("Simulace fronty v jídelně");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        initComponents();

        pack();
        setLocationRelativeTo(null);
        setResizable(true);
    }

    private void initComponents() {
        // Získání SeatManager a seznamu seats pomocí reflexe
        SeatManager seatManager = null;
        List<Seat> seats = new ArrayList<>();

        try {
            Field seatManagerField = simulation.getClass().getDeclaredField("seatManager");
            seatManagerField.setAccessible(true);
            seatManager = (SeatManager) seatManagerField.get(simulation);

            Field seatsField = seatManager.getClass().getDeclaredField("seats");
            seatsField.setAccessible(true);
            seats = (List<Seat>) seatsField.get(seatManager);
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this,
                    "Chyba při inicializaci GUI: " + e.getMessage(),
                    "Chyba",
                    JOptionPane.ERROR_MESSAGE);
        }

        // Hlavní panel se simulací
        simulationPanel = new SimulationPanel(simulation, seatManager, seats);
        add(simulationPanel, BorderLayout.CENTER);

        // Dolní panel s ovládacími tlačítky
        JPanel controlPanel = new JPanel();
        controlPanel.setBackground(new Color(250, 250, 250));
        controlPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        startButton = new JButton("Spustit simulaci");
        startButton.setFont(new Font("Arial", Font.BOLD, 14));
        startButton.setPreferredSize(new Dimension(150, 40));
        startButton.setBackground(new Color(76, 175, 80));
        startButton.setForeground(Color.WHITE);
        startButton.setFocusPainted(false);
        startButton.addActionListener(e -> startSimulation());

        stopButton = new JButton("Zastavit simulaci");
        stopButton.setFont(new Font("Arial", Font.BOLD, 14));
        stopButton.setPreferredSize(new Dimension(150, 40));
        stopButton.setBackground(new Color(244, 67, 54));
        stopButton.setForeground(Color.WHITE);
        stopButton.setFocusPainted(false);
        stopButton.setEnabled(false);
        stopButton.addActionListener(e -> stopSimulation());

        JLabel infoLabel = new JLabel("  |  Efektivní vytížení: 60-85%");
        infoLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        infoLabel.setForeground(new Color(100, 100, 100));

        controlPanel.add(startButton);
        controlPanel.add(Box.createHorizontalStrut(20));
        controlPanel.add(stopButton);
        controlPanel.add(infoLabel);

        add(controlPanel, BorderLayout.SOUTH);
    }

    private void startSimulation() {
        startButton.setEnabled(false);
        stopButton.setEnabled(true);

        // Spuštění simulace v samostatném vlákně
        new Thread(() -> {
            simulation.start();
        }).start();

        System.out.println("Simulace spuštěna z GUI");
    }

    private void stopSimulation() {
        stopButton.setEnabled(false);

        new Thread(() -> {
            simulation.stop();
            SwingUtilities.invokeLater(() -> {
                startButton.setEnabled(true);
                JOptionPane.showMessageDialog(this,
                        "Simulace ukončena. Statistiky jsou vypsány v konzoli.",
                        "Simulace ukončena",
                        JOptionPane.INFORMATION_MESSAGE);
            });
        }).start();

        System.out.println("Zastavování simulace...");
    }
}