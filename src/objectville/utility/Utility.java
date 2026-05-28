package objectville.utility;

import objectville.Cell;
import objectville.CellType;

public abstract class Utility extends Cell {
    protected int capacity;

    public Utility(int line, int column, String type) {
        super(line, column, type);
        this.capacity = 100;
    }

    public int getCapacity() {return capacity;}
    public abstract String getProvidedUtilityType();
}