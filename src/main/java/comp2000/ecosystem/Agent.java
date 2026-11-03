package comp2000.ecosystem;




public class Agent {

    private int x;
    private int y;
    private final Species species;


    private MovementStrategy movementStrategy;

    public Agent(int x, int y, Species species) {
        this.x = x;
        this.y = y;
        this.species = species;
        this.movementStrategy = new RandomMovementStrategy();
    }

    public void move(Grid grid) {
        Cell target = movementStrategy.chooseNextCell(this, grid);

        if (target != null) {
            this.x = target.getX();
            this.y = target.getY();
        }
    }

    public void setMovementStrategy(MovementStrategy movementStrategy) {
        this.movementStrategy = movementStrategy;
    }

    public boolean isNeighbourOf(Agent other) {
        int dx = Math.abs(this.x - other.x);
        int dy = Math.abs(this.y - other.y);
        return dx <= 1 && dy <= 1 && !(dx == 0 && dy == 0);
    }

    public Species getSpecies() {
        return species;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}