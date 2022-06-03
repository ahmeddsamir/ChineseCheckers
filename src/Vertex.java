public class Vertex {
    Point point;
    int occupant;
    boolean currentlyValid;

    Vertex(int xCoordinate, int yCoordinate, int occupant){
        this.currentlyValid = false;
        this.point = new Point(xCoordinate, yCoordinate);
        this.occupant = occupant;
    }

    public boolean isCurrentlyValid() {
        return currentlyValid;
    }

    public void setCurrentlyValid(boolean currentlyValid){
        this.currentlyValid = currentlyValid;
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
