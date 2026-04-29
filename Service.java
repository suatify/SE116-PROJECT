public abstract class Service extends Cell {
    protected int radius;

    public Service(int line, int colon, String type, int radius) {
        super(line, colon, type);
        this.radius=radius;
    }

    public int getRadius() {return radius;}
}
class PoliceStation extends Service{

    public PoliceStation(int line, int colon) {
        super(line, colon, CellType.PoliceStation, 5);
    }
}
class Hospital extends Service{

    public Hospital(int line, int colon) {
        super(line, colon, CellType.Hospital, 3);
    }
}
class School extends Service{

    public School(int line, int colon) {
        super(line, colon, CellType.School, 4);
    }
}
