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
class PoliceStation extends Service{

    public PoliceStation(int line, int column) {
        super(line, column, CellType.PoliceStation, 5);
    }
}
class Hospital extends Service{

    public Hospital(int line, int column) {
        super(line, column, CellType.Hospital, 3);
    }
}
class School extends Service{

    public School(int line, int column) {
        super(line, column, CellType.School, 4);
    }
}
