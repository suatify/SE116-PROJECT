public abstract class Cell {
    protected int line;
    protected int colon;
    protected String type;

    public Cell(int line,int colon,String type){
        this.line=line;
        this.colon=colon;
        this.type=type;
    }

    public int getLine() {return line;}
    public int getColon() {return colon;}
    public String getType() {return type;}
}
