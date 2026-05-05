package objectville.zone;

import objectville.Cell;

public abstract class Zone extends Cell {
    protected int level; // Level of the zone (starts at 0, max 3)

    // Resources received in the current tick
    protected int receivedElectricity;
    protected int receivedWater;
    protected int receivedInternet;
    protected int receivedPopulation;
    protected int receivedGoods;
    protected int receivedLifestyle;

    // Services received in the current tick
    protected boolean hasSecurity;
    protected boolean hasHealth;
    protected boolean hasEducation;

    public Zone(int line, int column, String type) {
        super(line, column, type);
        this.level = 0;
        this.receivedElectricity=0;
        this.receivedWater=0;
        this.receivedInternet=0;
        this.receivedPopulation=0;
        this.receivedLifestyle=0;
        this.receivedGoods=0;
        this.hasEducation=false;
        this.hasHealth=false;
        this.hasSecurity=false;
    }

    public int getReceivedElectricity() { return receivedElectricity; }
    public void setReceivedElectricity(int receivedElectricity) { this.receivedElectricity = receivedElectricity; }
    public int getReceivedWater() { return receivedWater; }
    public void setReceivedWater(int receivedWater) { this.receivedWater = receivedWater; }
    public int getReceivedInternet() { return receivedInternet; }
    public void setReceivedInternet(int receivedInternet) { this.receivedInternet = receivedInternet; }
    public int getReceivedPopulation() { return receivedPopulation; }
    public void setReceivedPopulation(int receivedPopulation) { this.receivedPopulation = receivedPopulation; }
    public int getReceivedGoods() { return receivedGoods; }
    public void setReceivedGoods(int receivedGoods) { this.receivedGoods = receivedGoods; }
    public int getReceivedLifestyle() { return receivedLifestyle; }
    public void setReceivedLifestyle(int receivedLifestyle) { this.receivedLifestyle = receivedLifestyle; }
    public boolean isHasSecurity() { return hasSecurity; }
    public void setHasSecurity(boolean hasSecurity) { this.hasSecurity = hasSecurity; }
    public boolean isHasHealth() { return hasHealth; }
    public void setHasHealth(boolean hasHealth) { this.hasHealth = hasHealth; }
    public boolean isHasEducation() { return hasEducation; }
    public void setHasEducation(boolean hasEducation) { this.hasEducation = hasEducation; }

    public void resetTick() {
        this.receivedElectricity = 0;
        this.receivedWater = 0;
        this.receivedInternet = 0;
        this.receivedPopulation = 0;
        this.receivedGoods = 0;
        this.receivedLifestyle = 0;
        this.hasSecurity = false;
        this.hasHealth = false;
        this.hasEducation = false;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        // A zone level can only be between 0 and 3
        if(level<0 || level> 3){
            throw new IllegalArgumentException( "Error: Invalid zone level! It must be between 0 and 3. Given: " + level );
        }
        this.level = level;
    }


    public abstract int calculateM();
    public abstract int calculateOutput();
    public abstract void updateLevel();
}