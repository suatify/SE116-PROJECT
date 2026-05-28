package objectville.service;

import objectville.CellType;

public class Hospital extends Service{

    public Hospital(int line, int column) {
        super(line, column, CellType.Hospital, 3);
    }
}
