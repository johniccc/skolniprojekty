package views;

import javax.swing.JFrame;

import simulation.CanteenSimulation;

public class SimulationFrame extends JFrame {
    public SimulationFrame(CanteenSimulation simulation) {
        setTitle("Simulace kantýny");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        initComponents(simulation);
    }

    private void initComponents(CanteenSimulation simulation) {
        SimulationPanel simulationPanel = new SimulationPanel(simulation);
        add(simulationPanel);
    }
}
