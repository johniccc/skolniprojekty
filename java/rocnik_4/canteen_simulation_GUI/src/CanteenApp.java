import config.SimulationConfig;
import simulation.CanteenSimulation;
import views.SimulationFrame;
import javax.swing.SwingUtilities;

public class CanteenApp {
    public static void main(String[] args) throws Exception {
        SimulationConfig config = new SimulationConfig();
        CanteenSimulation simulation = new CanteenSimulation(config);

        // Spuštění GUI na Event Dispatch Thread
        SwingUtilities.invokeLater(() -> {
            SimulationFrame frame = new SimulationFrame(simulation);
            frame.setVisible(true);
        });
    }
}