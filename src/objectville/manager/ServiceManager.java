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
                                int mesafe = Math.abs(s.getLine() - x) + Math.abs(s.getColumn() - y);

                                // Radius içindeyse hizmeti ver
                                if (mesafe <= s.getRadius()) {
                                    String tip = s.getServiceType();
                                    if (tip.equals(CellType.PoliceStation)) {
                                        z.setHasSecurity(true);
                                    } else if (tip.equals(CellType.Hospital)) {
                                        z.setHasHealth(true);
                                    } else if (tip.equals(CellType.School)) {
                                        z.setHasEducation(true);
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