package comp2000.ecosystem;

import java.util.List;

public interface MovementStrategy {
    Cell chooseNextCell(Agent agent, Grid grid, List<Agent> agents);
}