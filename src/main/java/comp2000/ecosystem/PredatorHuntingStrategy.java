
package comp2000.ecosystem;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class PredatorHuntingStrategy implements MovementStrategy {

    private final Random random = new Random();

    @Override
    public Cell chooseNextCell(Agent agent, Grid grid, List<Agent> agents) {

        List<Cell> neighbours = grid.getNeighbours(
                agent.getX(), agent.getY()
        );

        if (neighbours.isEmpty()) {
            return null;
        }

        // Find the nearest prey
        Agent nearestPrey = null;
        int shortestDistance = Integer.MAX_VALUE;

        for (Agent other : agents) {
            if (other.getSpecies() == Species.PREY) {
                int dx = Math.abs(agent.getX() - other.getX());
                int dy = Math.abs(agent.getY() - other.getY());
                int distance = dx + dy;

                if (distance < shortestDistance) {
                    shortestDistance = distance;
                    nearestPrey = other;
                }
            }
        }

        // Find neighbouring cells not occupied by another predator
        List<Cell> availableCells = new ArrayList<>();

        for (Cell cell : neighbours) {
            boolean occupied = false;

            for (Agent other : agents) {
                if (other != agent
                        && other.getSpecies() == Species.PREDATOR
                        && other.getX() == cell.getX()
                        && other.getY() == cell.getY()) {
                    occupied = true;
                    break;
                }
            }

            if (!occupied) {
                availableCells.add(cell);
            }
        }

        // Stay still if every neighbouring cell is occupied
        if (availableCells.isEmpty()) {
            return null;
        }

        // If no prey exists, move randomly instead of towards one corner
        if (nearestPrey == null) {
            int randomIndex = random.nextInt(availableCells.size());
            return availableCells.get(randomIndex);
        }

        // Choose the available cell closest to the prey
        Cell bestCell = availableCells.get(0);
        int bestDistance = Integer.MAX_VALUE;

        for (Cell cell : availableCells) {
            int dx = Math.abs(cell.getX() - nearestPrey.getX());
            int dy = Math.abs(cell.getY() - nearestPrey.getY());
            int distance = dx + dy;

            if (distance < bestDistance) {
                bestDistance = distance;
                bestCell = cell;
            }
        }

        return bestCell;
    }
}
