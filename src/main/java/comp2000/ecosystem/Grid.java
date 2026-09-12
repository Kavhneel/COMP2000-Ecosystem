package comp2000.ecosystem;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Grid {

    private final int width;
    private final int height;
    private final Cell[][] cells;

    public Grid(int width, int height) {
        if (width <= 0 || height <= 0) {
            throw new IllegalArgumentException("Grid dimensions must be positive");
        }
        this.width = width;
        this.height = height;
        this.cells = new Cell[height][width];
        initialiseCells();
    }

    private void initialiseCells() {
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                cells[y][x] = new Cell(x, y);
            }
        }
    }

    public Optional<Cell> getCellIfInside(int x, int y) {
        if (x < 0 || y < 0 || x >= width || y >= height) {
            return Optional.empty();
        }
        return Optional.of(cells[y][x]);
    }

    public List<Cell> getNeighbours(int x, int y) {
        List<Cell> neighbours = new ArrayList<>();
        int[] dx = {-1, 0, 1};
        int[] dy = {-1, 0, 1};
        for (int ix : dx) {
            for (int iy : dy) {
                if (ix == 0 && iy == 0) continue;
                getCellIfInside(x + ix, y + iy).ifPresent(neighbours::add);
            }
        }
        return neighbours;
    }

    public int getWidth() { return width; }
    public int getHeight() { return height; }
}
