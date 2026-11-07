
package comp2000.ecosystem;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class EcosystemSimulation extends Simulation {

    private final Random random = new Random();

    public EcosystemSimulation(Grid grid) {
        super(grid, new ArrayList<>());
        initialiseAgents();
    }

    private void initialiseAgents() {
        // Create prey that move randomly
        for (int i = 0; i < 60; i++) {
            agents.add(new Agent(
                    random.nextInt(grid.getWidth()),
                    random.nextInt(grid.getHeight()),
                    Species.PREY
            ));
        }

        // Create predators that actively hunt prey
        for (int i = 0; i < 15; i++) {
            Agent predator = new Agent(
                    random.nextInt(grid.getWidth()),
                    random.nextInt(grid.getHeight()),
                    Species.PREDATOR
            );

            predator.setMovementStrategy(new PredatorHuntingStrategy());
            agents.add(predator);
        }
    }

    @Override
    protected void applyRules() {
        List<Agent> snapshot = new ArrayList<>(agents);

        for (Agent predator : snapshot) {
            if (predator.getSpecies() != Species.PREDATOR) {
                continue;
            }

            for (Agent prey : snapshot) {
                if (prey.getSpecies() == Species.PREY
                        && predator.isNeighbourOf(prey)) {
                    agents.remove(prey);
                    break;
                }
            }
        }
    }

    // Count the remaining prey using Streams
    public long getPreyCount() {
        return agents.stream()
                .filter(agent -> agent.getSpecies() == Species.PREY)
                .count();
    }

    // Count the remaining predators using Streams
    public long getPredatorCount() {
        return agents.stream()
                .filter(agent -> agent.getSpecies() == Species.PREDATOR)
                .count();
    }

    // Count the total population using Streams
    public long getTotalPopulation() {
        return agents.stream().count();
    }
}
