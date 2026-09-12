package comp2000.ecosystem;

import java.util.List;

public abstract class Simulation {

    protected final Grid grid;
    protected final List<Agent> agents;

    public Simulation(Grid grid, List<Agent> agents) {
        this.grid = grid;
        this.agents = agents;
    }

    public void step() {
        for (Agent agent : agents) {
            agent.move(grid);
        }
        applyRules();
    }

    protected abstract void applyRules();

    public List<Agent> getAgents() {
        return agents;
    }

    public Grid getGrid() {
        return grid;
    }
}
