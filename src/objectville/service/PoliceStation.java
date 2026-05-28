package objectville.service;

import objectville.CellType;

public class PoliceStation extends Service{

    public PoliceStation(int line, int column) {
        super(line, column, CellType.PoliceStation, 5);
    }
}
