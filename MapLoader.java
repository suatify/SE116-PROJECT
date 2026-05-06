import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class MapLoader {
    public static Cell[][] loadMap(String fileName) {
        ArrayList<String> lines = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (!line.trim().isEmpty()) {
                    lines.add(line);
                }
            }
        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
            return null;
        }
        return convertToGrid(lines);
    }

    private static Cell[][] convertToGrid(ArrayList<String> lines) {
        if (lines.isEmpty()) return null;

        int rowCount = lines.size();
        int colCount = lines.get(0).length();
        Cell[][] grid = new Cell[rowCount][colCount];

        for (int i = 0; i < rowCount; i++) {
            String line = lines.get(i);
            for (int j = 0; j < colCount; j++) {
                char symbol = line.charAt(j);
                grid[i][j] = createCell(symbol, i, j);
            }
        }
        return grid;
    }

    private static Cell createCell(char symbol, int r, int c) {
        String s = String.valueOf(symbol);
        switch (s) {
            case CellType.Housing: return new Housing(r, c);
            case CellType.Industrial: return new Industrial(r, c);
            case CellType.Commercial: return new Commercial(r, c);
            case CellType.Road: return new Road(r, c);
            case CellType.PowerPlant: return new PowerPlant(r, c);
            case CellType.WaterPumpingStation: return new WaterPumpingStation(r, c);
            case CellType.InternetHub: return new InternetHub(r, c);
            case CellType.PoliceStation: return new PoliceStation(r, c);
            case CellType.Hospital: return new Hospital(r, c);
            case CellType.School: return new School(r, c);
            default: return new EmptyCell(r, c);
        }
    }
}