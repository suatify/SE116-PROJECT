package objectville.utility;

import objectville.CellType;

public class WaterPumpingStation extends  Utility{

    public WaterPumpingStation(int line, int column) {
        super(line, column, CellType.WaterPumpingStation);
    }
    @Override
    public String getProvidedUtilityType() {
        return "WATER";
    }
}
