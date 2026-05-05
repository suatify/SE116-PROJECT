package objectville.zone;

import objectville.CellType;

public class Industrial extends Zone {
    public Industrial(int line, int column) {
        super(line, column, CellType.Industrial);
    }

    @Override
    // get the lowest utility
    public int calculateM() {
        return Math.min(receivedElectricity, receivedWater);
    }

    @Override
    public int calculateOutput() {
        int m = this.calculateM();
        if (level == 0) {
            return 0;
        } else if (level == 1) {
            return m;
        } else if (level == 2) {
            return 2 * m;
        } else if (level == 3) {
            return (2 * m) + receivedPopulation;
        } else {
            throw new IllegalArgumentException("Error: Industrial level must be 0-3. Given: " + level);
        }
    }

    @Override
    public void updateLevel() {
        try {
            //check if any resource is negative
            if (receivedPopulation < 0 || receivedElectricity < 0 || receivedWater < 0) {
                throw new IllegalStateException("Negative resources detected!");
            }

            // If all utilities are 0, return the level to 0
            if (receivedElectricity == 0 && receivedWater == 0) {
                setLevel(0);
                return;
            }

            int targetLevel = 0;
            boolean hasLevel1Req = (receivedPopulation > 0 && receivedElectricity > 0 && receivedWater > 0);
            boolean hasLevel2Req = (hasLevel1Req && hasSecurity);
            boolean hasLevel3Req = (hasLevel2Req && receivedPopulation > 10); // assumed that excess population <10

            if (hasLevel3Req) {
                targetLevel = 3;
            } else if (hasLevel2Req) {
                targetLevel = 2;
            } else if (hasLevel1Req) {
                targetLevel = 1;
            }

            // Level changes gradually
            if (targetLevel > level) {
                setLevel(level + 1);
            } else if (targetLevel < level) {
                setLevel(level - 1);
            }
        } catch (IllegalArgumentException | IllegalStateException e) {
            // catch negative resources or invalid level numbers
            System.out.println("Error: Updating Industrial level. " + e.getMessage());
            this.level = 0;
        }
    }
}