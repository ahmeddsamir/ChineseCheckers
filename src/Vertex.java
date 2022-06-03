public class Vertex {
    Point point;
    int occupant;

    Vertex(int xCoordinate, int yCoordinate, int occupant){
        this.point = new Point(xCoordinate, yCoordinate);
        this.occupant = occupant;
    }

    public Point getPoint() {
        return point;
    }

    public int getOccupant() {
        return occupant;
    }

    public void setOccupant(int occupant) {
        this.occupant = occupant;
    }
}
