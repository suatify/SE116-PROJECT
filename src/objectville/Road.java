package objectville;

public class Road extends Cell {
    public Road(int line, int column) {
        super(line, column, CellType.Road);
        this.setPassable(true);
    }
}