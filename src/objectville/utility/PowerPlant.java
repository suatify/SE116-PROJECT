package objectville.utility;

import objectville.CellType;

public class PowerPlant extends Utility{

    public PowerPlant(int line, int column) {
        super(line, column, CellType.PowerPlant);
    }
    @Override
    public String getProvidedUtilityType() {
        return "POWER";
    }
}
