package comp2000.ecosystem;

import java.util.List;

public class PredatorHuntingStrategy implements MovementStrategy {

    @Override
    public Cell chooseNextCell(Agent agent, Grid grid, List<Agent> agents) {
        List<Cell> neighbours = grid.getNeighbours(agent.getX(), agent.getY());

        if (neighbours.isEmpty()) {
            return null;
        }

        Agent nearestPrey = null;
        int shortestDistance = Integer.MAX_VALUE;

        // Find the nearest prey
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

        // If no prey exists, move randomly
        if (nearestPrey == null) {
            return neighbours.get(0);
        }

        // Choose the neighbouring cell closest to the prey
        Cell bestCell = neighbours.get(0);
        int bestDistance = Integer.MAX_VALUE;

        for (Cell cell : neighbours) {
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