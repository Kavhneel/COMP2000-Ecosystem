package comp2000.ecosystem;

import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Graphics;
import java.util.List;

public class SimulationPanel extends JPanel {

    private final EcosystemSimulation simulation;
    private final Grid grid;

    public SimulationPanel(EcosystemSimulation simulation, Grid grid) {
        this.simulation = simulation;
        this.grid = grid;
        setBackground(Color.BLACK);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        int cellWidth = getWidth() / grid.getWidth();
        int cellHeight = getHeight() / grid.getHeight();

        List<Agent> agents = simulation.getAgents();
        for (Agent agent : agents) {
            g.setColor(agent.getSpecies() == Species.PREY ? Color.GREEN : Color.RED);
            g.fillRect(agent.getX() * cellWidth, agent.getY() * cellHeight, cellWidth, cellHeight);
        }
    }
}
