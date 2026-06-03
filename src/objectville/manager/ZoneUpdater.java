package objectville.manager;

import objectville.Cell;
import objectville.zone.Commercial;
import objectville.zone.Housing;
import objectville.zone.Industrial;
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

                    String zoneName = "";
                    String generatedResource = "";
                    if (zone instanceof Housing) {
                        zoneName = "House";
                        generatedResource = "population";
                    } else if (zone instanceof Commercial) {
                        zoneName = "Commercial";
                        generatedResource = "lifestyle";
                    } else if (zone instanceof Industrial) {
                        zoneName = "Industrial";
                        generatedResource = "goods";
                    }

                    int oldLevel = zone.getLevel();

                    zone.updateLevel();

                    int newLevel = zone.getLevel();

                    int generatedAmount = zone.calculateOutput();
                    zone.setPreviousOutput(generatedAmount);

                    System.out.println(zoneName + " at (" + zone.getLine() + "," + zone.getColumn() + ") generated " + generatedAmount + " " + generatedResource);

                    if (newLevel > oldLevel) {
                        System.out.println(zoneName + " at (" + zone.getLine() + "," + zone.getColumn() + ") levels up from " + oldLevel + " to " + newLevel);
                    } else if (newLevel < oldLevel) {
                        System.out.println(zoneName + " at (" + zone.getLine() + "," + zone.getColumn() + ") levels down from " + oldLevel + " to " + newLevel);
                    }

                }
            }
        }
    }
}
