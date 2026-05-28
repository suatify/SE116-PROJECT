package objectville.service;

import objectville.CellType;

public class School extends Service{

    public School(int line, int column) {
        super(line, column, CellType.School, 4);
    }
}
