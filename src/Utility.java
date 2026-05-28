public abstract class Utility extends Cell {
    protected int capacity;

    public Utility(int line, int colon, String type) {
        super(line, colon, type);
        this.capacity = 100;
    }

    public int getCapacity() {return capacity;}
}

class PowerPlant extends Utility{

    public PowerPlant(int line, int colon) {
        super(line, colon, CellType.PowerPlant);
    }
}
class WaterPumpingStation extends  Utility{

    public WaterPumpingStation(int line, int colon) {
        super(line, colon, CellType.WaterPumpingStation);
    }
}
class InternetHub extends Utility{

    public InternetHub(int line, int colon) {
        super(line, colon, CellType.InternetHub);
    }
}