package objectville;

import objectville.manager.ResourceManager;
import objectville.manager.ServiceManager;
import objectville.manager.UtilityManager;
import objectville.manager.ZoneUpdater;
import objectville.utility.Utility;

public class Main {

    public static void main(String[] args) {

        String mapFile;
        int tickCount;

        try {
            mapFile = args[0];
            tickCount = Integer.parseInt(args[1]);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Usage: java objectville.Main <map_file> <tick_count>");
            return;
        } catch (NumberFormatException e) {
            System.out.println("Tick count must be a number.");
            return;
        }

        Cell[][] grid = MapLoader.loadMap(mapFile);

        if (grid == null) {
            System.out.println("Map could not be loaded: " + mapFile);
            return;
        }

        ZoneUpdater zoneUpdater = new ZoneUpdater();
        UtilityManager utilityManager = new UtilityManager();
        ServiceManager serviceManager = new ServiceManager(grid);
        ResourceManager resourceManager = new ResourceManager();

        for (int tick = 1; tick <= tickCount; tick++) {

            System.out.println("Tick " + tick);
            zoneUpdater.resetAllZones(grid);
            serviceManager.distributeServices();

            for (int i = 0; i < grid.length; i++) {
                for (int j = 0; j < grid[0].length; j++) {
                    if (grid[i][j] instanceof Utility) {
                        utilityManager.distributeUtility(grid, (Utility) grid[i][j]);
                    }
                }
            }

            if (tick > 1) {
                resourceManager.distributePreviousTickResources(grid);
            }
            zoneUpdater.updateZones(grid);
            resourceManager.poolResources(grid);
        }
    }
}