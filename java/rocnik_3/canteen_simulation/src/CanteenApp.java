import config.SimulationConfig;
import simulation.CanteenSimulation;

public class CanteenApp {
    public static void main(String[] args) throws Exception {
        SimulationConfig config = new SimulationConfig();

        CanteenSimulation simulation = new CanteenSimulation(config);
        simulation.start();
    }
}
