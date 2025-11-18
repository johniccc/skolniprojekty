import config.SimulationConfig;
import java.util.Scanner;

import javax.swing.SwingUtilities;

import simulation.CanteenSimulation;
import views.SimulationFrame;

public class CanteenAppGUI {
    static SimulationFrame frame;

    public static void main(String[] args) throws Exception {
        SimulationConfig config = new SimulationConfig();

        CanteenSimulation simulation = new CanteenSimulation(config);
        simulation.start();

        SwingUtilities.invokeLater(() -> {
            frame = new SimulationFrame(simulation);
            frame.setVisible(true);
        });

        try (Scanner scanner = new Scanner(System.in)) {
            scanner.nextLine();
        }

        simulation.stop();
        frame.dispose();
    }
}
