package objectville;

public abstract class Cell {
    private int line;
    private int column;
    private String type;
    private boolean isPassable;
    public Cell(int line, int column, String type){
        this.line=line;
        this.column = column;
        this.type=type;
        this.isPassable = false; //Varsayılan
    }

    public int getLine() {return line;}
    public int getColumn() {return column;}
    public String getType() {return type;}
    public boolean isPassable() {
        return isPassable;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    public void setLine(int line) {
        this.line = line;
    }

    public void setType(String type) {
        this.type = type;
    }

    protected void setPassable(boolean passable) {
        this.isPassable = passable; // kritik olduğundan protected
    }
}