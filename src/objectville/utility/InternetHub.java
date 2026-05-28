package objectville.utility;

import objectville.CellType;

public class InternetHub extends Utility{

    public InternetHub(int line, int column) {
        super(line, column, CellType.InternetHub);
    }
    @Override
    public String getProvidedUtilityType() {
        return "INTERNET";
    }
}
