package objectville.service;

import objectville.Cell;
import objectville.CellType;

public abstract class Service extends Cell {
    protected int radius;

    public Service(int line, int column, String type, int radius) {
        super(line, column, type);
        this.radius=radius;
    }

    public int getRadius() {return radius;}
}

