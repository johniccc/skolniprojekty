import config.SimulationConfig;
import simulation.CanteenSimulation;
import views.SimulationFrame;
import javax.swing.SwingUtilities;

public class CanteenApp {
    public static void main(String[] args) throws Exception {
        SimulationConfig config = new SimulationConfig();
        CanteenSimulation simulation = new CanteenSimulation(config);

        // Start GUI on EDT
        SwingUtilities.invokeLater(() -> {
            SimulationFrame frame = new SimulationFrame(simulation);
            frame.setVisible(true);
        });
    }
}