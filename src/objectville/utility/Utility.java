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
}

class PowerPlant extends Utility{

    public PowerPlant(int line, int column) {
        super(line, column, CellType.PowerPlant);
    }
}
class WaterPumpingStation extends  Utility{

    public WaterPumpingStation(int line, int column) {
        super(line, column, CellType.WaterPumpingStation);
    }
}
class InternetHub extends Utility{

    public InternetHub(int line, int column) {
        super(line, column, CellType.InternetHub);
    }
}