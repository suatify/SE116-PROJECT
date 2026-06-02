package objectville.manager;

import objectville.Cell;
import objectville.zone.Zone;

public class ZoneUpdater {

    public void resetAllZones(Cell[][] grid) {
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] instanceof Zone) {
                    ((Zone) grid[i][j]).resetTick();
                }
            }
        }
    }

    public void updateZones(Cell[][] grid) {
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] instanceof Zone) {
                    Zone zone = (Zone) grid[i][j];
                    zone.setPreviousOutput(zone.calculateOutput());
                    zone.updateLevel();
                }
            }
        }
    }
}
