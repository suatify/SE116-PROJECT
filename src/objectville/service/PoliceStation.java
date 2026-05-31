package objectville.service;

import objectville.CellType;

public class PoliceStation extends Service {

    public PoliceStation(int line, int column) {
        super(line, column, CellType.PoliceStation, 5);
    }

    @Override
    public String getServiceType() {
        return CellType.PoliceStation; // "F" döner
    }
}
