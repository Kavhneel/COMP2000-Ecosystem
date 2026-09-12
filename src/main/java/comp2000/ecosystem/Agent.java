package comp2000.ecosystem;

import java.util.List;
import java.util.Random;

public class Agent {

    private int x;
    private int y;
    private final Species species;
    private final Random random = new Random();

    public Agent(int x, int y, Species species) {
        this.x = x;
        this.y = y;
        this.species = species;
    }

    public void move(Grid grid) {
        List<Cell> neighbours = grid.getNeighbours(x, y);
        if (neighbours.isEmpty()) return;

        Cell target = neighbours.get(random.nextInt(neighbours.size()));
        this.x = target.getX();
        this.y = target.getY();
    }

    public boolean isNeighbourOf(Agent other) {
        int dx = Math.abs(this.x - other.x);
        int dy = Math.abs(this.y - other.y);
        return dx <= 1 && dy <= 1 && !(dx == 0 && dy == 0);
    }

    public Species getSpecies() { return species; }
    public int getX() { return x; }
    public int getY() { return y; }
}
