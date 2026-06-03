package objectville.manager;

import objectville.Cell;
import objectville.EmptyCell;
import objectville.Road;
import objectville.utility.Utility;
import objectville.zone.Commercial;
import objectville.zone.Housing;
import objectville.zone.Industrial;
import objectville.zone.Zone;

import java.util.LinkedList;
import java.util.Queue;

public class UtilityManager {

    public void distributeUtility(Cell[][] grid, Utility provider) {
        int capacity = provider.getCapacity();
        String utilityType = provider.getProvidedUtilityType();

        int rows = grid.length;
        int cols = grid[0].length;
        boolean[][] visited = new boolean[rows][cols];

        // The queue list is being prepared for the algorithm.
        Queue<Cell> queue = new LinkedList<>();

        queue.add(provider);
        visited[provider.getLine()][provider.getColumn()] = true;

        //manhatan format
        int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};


        while (!queue.isEmpty() && capacity > 0) {
            //.poll = It gives you the element (cell) at the very beginning of the queue.
            Cell current = queue.poll();

            // provide as much (utility) as needed.
            if (current instanceof Zone && current != provider) {
                Zone zone = (Zone) current;

                boolean canAbsorb = true;
                if (utilityType.equals("INTERNET") && zone instanceof Industrial) {
                    canAbsorb = false;
                }
                if (canAbsorb){
                    // Min value must be 1
                    int request = zone.getPreviousOutput();
                    if (request < 1) {
                        request = 1;
                    }
                    int currentlyReceived = 0;


                    if (utilityType.equals("POWER")) {
                        currentlyReceived = zone.getReceivedElectricity();
                    } else if (utilityType.equals("WATER")) {
                        currentlyReceived = zone.getReceivedWater();
                    } else if (utilityType.equals("INTERNET")) {
                        currentlyReceived = zone.getReceivedInternet();
                    }

                    int unmetRequest = request - currentlyReceived;
                    if (unmetRequest < 0) {
                        unmetRequest = 0;
                    }
                    int absorbed = 0;
                    if (unmetRequest < capacity) {
                        absorbed = unmetRequest;
                    } else {
                        absorbed = capacity;
                    }

                    if (absorbed > 0) {
                        String utilName = "";
                        if (utilityType.equals("POWER")) {
                            zone.setReceivedElectricity(currentlyReceived + absorbed);
                            utilName = "electricity";
                        } else if (utilityType.equals("WATER")) {
                            zone.setReceivedWater(currentlyReceived + absorbed);
                            utilName = "water";
                        } else if (utilityType.equals("INTERNET")) {
                            zone.setReceivedInternet(currentlyReceived + absorbed);
                            utilName = "internet";
                        }
                        capacity -= absorbed;
                        String zoneName = "";
                        if (zone instanceof Housing) zoneName = "House";
                        else if (zone instanceof Commercial) zoneName = "Commercial";
                        else if (zone instanceof Industrial) zoneName = "Industrial";

                        System.out.println(zoneName + " at (" + zone.getLine() + "," + zone.getColumn() + ") received " + absorbed + " " + utilName);
                    }
                }
            }

            if (capacity <= 0) {break;}

            // Eight sides of the box
            for (int i = 0; i < directions.length; i++) {
                int newRow = current.getLine() + directions[i][0];
                int newCol = current.getColumn() + directions[i][1];

                if (newRow >= 0 && newRow < rows && newCol >= 0 && newCol < cols) {
                    if (visited[newRow][newCol] == false) {
                        Cell neighbor = grid[newRow][newCol];

                        // Infrastructure can flow only through roads and zones.
                        if (neighbor instanceof Road || neighbor instanceof Zone) {
                            visited[newRow][newCol] = true;
                            queue.add(neighbor);
                        }
                    }
                }
            }
        }
    }
}
