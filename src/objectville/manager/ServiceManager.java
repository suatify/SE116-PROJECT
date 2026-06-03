package objectville.manager;

import objectville.Cell;
import objectville.service.*;
import objectville.zone.*;
import objectville.CellType;


public class ServiceManager {
    private Cell[][] grid;

    public ServiceManager(Cell[][] grid) {
        this.grid = grid;
    }

    public void distributeServices() {

        // Grid'i tara, Service binalarını bul
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] instanceof Service) {
                    Service s = (Service) grid[i][j];

                    for (int x = 0; x < grid.length; x++) {
                        for (int y = 0; y < grid[x].length; y++) {

                            // Bu Service'in radius'u içindeki Zone'ları bul
                            if (grid[x][y] instanceof Zone) {
                                Zone z = (Zone) grid[x][y];

                                // Manhattan mesafesi hesapla
                                int distance = Math.abs(s.getLine() - x) + Math.abs(s.getColumn() - y);

                                // Radius içindeyse hizmeti ver
                                if (distance <= s.getRadius()) {
                                    String serviceType = s.getServiceType();
                                    String zoneName = "";
                                    if (z instanceof Housing) {
                                        zoneName = "House";
                                    } else if (z instanceof Commercial) {
                                        zoneName = "Commercial";
                                    } else if (z instanceof Industrial) {
                                        zoneName = "Industrial";
                                    }
                                    if (serviceType.equals(CellType.PoliceStation)) {
                                        if (!z.isHasSecurity()) {
                                            z.setHasSecurity(true);
                                            System.out.println(zoneName + " at (" + x + "," + y + ") received security service");
                                        }
                                    } else if (serviceType.equals(CellType.Hospital)) {
                                        if (z instanceof Housing && !z.isHasHealth()) {
                                            z.setHasHealth(true);
                                            System.out.println(zoneName + " at (" + x + "," + y + ") received health service");
                                        }
                                    } else if (serviceType.equals(CellType.School)) {
                                        if (z instanceof Housing && !z.isHasEducation()) {
                                            z.setHasEducation(true);
                                            System.out.println(zoneName + " at (" + x + "," + y + ") received education service");
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}