package objectville.service;

import objectville.CellType;

public class Hospital extends Service {

    public Hospital(int line, int column) {
        super(line, column, CellType.Hospital, 3);
    }

    @Override
    public String getServiceType() {
        return CellType.Hospital; // "D" döner
    }
}