package comp2000.ecosystem;

import java.util.List;
import java.util.Random;

public class RandomMovementStrategy implements MovementStrategy {

    private final Random random = new Random();

    @Override
    public Cell chooseNextCell(Agent agent, Grid grid, List<Agent> agents) {
        List<Cell> neighbours = grid.getNeighbours(agent.getX(), agent.getY());

        if (neighbours.isEmpty()) {
            return null;
        }

        int randomIndex = random.nextInt(neighbours.size());
        return neighbours.get(randomIndex);
    }
}