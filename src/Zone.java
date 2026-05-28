public abstract class Zone extends Cell {
    protected int level;

    public Zone(int line, int colon, String type) {
        super(line, colon, type);
        this.level = 0;
    }

    public int getLevel() {return level;}

}
class Housing extends Zone{

    public Housing(int line, int colon) {
        super(line, colon, CellType.Housing);
    }
}
class Industrial extends Zone{

    public Industrial(int line, int colon) {
        super(line, colon, CellType.Industrial);
    }
}
class Commercial extends Zone{

    public Commercial(int line, int colon) {
        super(line, colon, CellType.Commercial);
    }
}