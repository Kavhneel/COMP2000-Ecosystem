package comp2000.ecosystem;

import javax.swing.JFrame;
import javax.swing.Timer;
import java.awt.BorderLayout;

public class App {

    public static void main(String[] args) {
        int width = 40;
        int height = 30;

        Grid grid = new Grid(width, height);
        EcosystemSimulation simulation = new EcosystemSimulation(grid);
        SimulationPanel panel = new SimulationPanel(simulation, grid);

        JFrame frame = new JFrame("COMP2000 Ecosystem Simulation");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        frame.add(panel, BorderLayout.CENTER);
        frame.setSize(800, 600);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        Timer timer = new Timer(150, e -> {
            simulation.step();
            panel.repaint();
        });
        timer.start();
    }
}
