package objectville.manager;

import objectville.Cell;

public class DisplayManager {
    public void printGrid(Cell[][] grid) {
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                System.out.print(grid[i][j].getType());
            }
            System.out.println();
        }
    }
}
