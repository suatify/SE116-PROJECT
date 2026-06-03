package objectville.manager;

import objectville.Cell;
import objectville.zone.Commercial;
import objectville.zone.Housing;
import objectville.zone.Industrial;
import objectville.zone.Zone;

import java.util.ArrayList;

public class ResourceManager {
    private int totalPopulationPool;
    private int totalGoodsPool;
    private int totalLifestylePool;

    public ResourceManager() {
        this.totalPopulationPool = 0;
        this.totalGoodsPool = 0;
        this.totalLifestylePool = 0;
    }

    public int getTotalPopulationPool() {
        return totalPopulationPool;
    }

    public void setTotalPopulationPool(int totalPopulationPool) {
        this.totalPopulationPool = totalPopulationPool;
    }

    public int getTotalGoodsPool() {
        return totalGoodsPool;
    }

    public void setTotalGoodsPool(int totalGoodsPool) {
        this.totalGoodsPool = totalGoodsPool;
    }

    public int getTotalLifestylePool() {
        return totalLifestylePool;
    }

    public void setTotalLifestylePool(int totalLifestylePool) {
        this.totalLifestylePool = totalLifestylePool;
    }

    public int countZonesByCondition(Cell[][] grid, ZoneChecker checker) {
        int count= 0;
        for (int i=0; i< grid.length; i++) {
            for (int j=0; j< grid[0].length; j++) {
                if (grid[i][j] instanceof Zone) {
                    Zone z=(Zone) grid[i][j];
                    if (checker.check(z)) {
                        count++;
                    }
                }
            }
        }
        return count;
    }

    public void  poolResources(Cell[][] grid){
        this.totalPopulationPool= 0;
        this.totalGoodsPool = 0;
        this.totalLifestylePool= 0;

        for (int i=0;i<grid.length; i++) {
            for (int j=0; j<grid[0].length;j++) {
                Cell cell = grid[i][j];
                if (cell instanceof Housing) {
                    this.totalPopulationPool +=((Housing) cell).calculateOutput();
                } else if (cell instanceof Industrial) {
                    this.totalGoodsPool +=((Industrial) cell).calculateOutput();
                } else if (cell instanceof Commercial) {
                    this.totalLifestylePool +=((Commercial) cell).calculateOutput();
                }
            }
        }
    }

    public void distributePreviousTickResources(Cell[][] grid){
        ArrayList<Zone>zones= new ArrayList<>();
        for (int i=0;i<grid.length; i++) {
            for (int j=0; j<grid[0].length; j++) {
                if (grid[i][j] instanceof Zone) {
                    Cell currentCell=grid[i][j];
                    Zone currentZone=(Zone) currentCell;
                    zones.add(currentZone);
                }
            }
        }

        int housingCount = countZonesByCondition(grid, z -> z instanceof Housing);
        int industrialCount = countZonesByCondition(grid, z -> z instanceof Industrial);
        int commercialCount = countZonesByCondition(grid, z -> z instanceof Commercial);

        int populationTargets= industrialCount + commercialCount;
        int goodsTargets =commercialCount;
        int lifestyleTargets =housingCount;

        int popPerZone= 0;
        int goodsPerZone = 0;
        int lifestylePerZone =0;

        if (populationTargets>0) {
            popPerZone=this.totalPopulationPool/ populationTargets;
        }
        if (goodsTargets>0) {
            goodsPerZone= this.totalGoodsPool /goodsTargets;
        }
        if (lifestyleTargets>0) {
            lifestylePerZone= this.totalLifestylePool/ lifestyleTargets;
        }

        for (Zone zone : zones) {
            String zoneName = "";
            if (zone instanceof Housing) zoneName = "House";
            else if (zone instanceof Commercial) zoneName = "Commercial";
            else if (zone instanceof Industrial) zoneName = "Industrial";

            if (zone instanceof Housing) {
                zone.setReceivedLifestyle(lifestylePerZone);
                if (lifestylePerZone > 0) {
                    System.out.println(zoneName + " at (" + zone.getLine() + "," + zone.getColumn() + ") received " + lifestylePerZone + " lifestyle");
                }
            } else if (zone instanceof Industrial) {
                zone.setReceivedPopulation(popPerZone);
                if (popPerZone > 0) {
                    System.out.println(zoneName + " at (" + zone.getLine() + "," + zone.getColumn() + ") received " + popPerZone + " population");
                }
            } else if (zone instanceof Commercial) {
                zone.setReceivedPopulation(popPerZone);
                zone.setReceivedGoods(goodsPerZone);
                if (popPerZone > 0) {
                    System.out.println(zoneName + " at (" + zone.getLine() + "," + zone.getColumn() + ") received " + popPerZone + " population");
                }
                if (goodsPerZone > 0) {
                    System.out.println(zoneName + " at (" + zone.getLine() + "," + zone.getColumn() + ") received " + goodsPerZone + " goods");
                }
            }
        }
    }
}