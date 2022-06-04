import java.awt.Point;

public class Vertex {

	public int occupant;
	private Point point;
	private boolean visited;
	private boolean allowedToMove;

	public Vertex(Point l) {
		this(l, 0);
	}

	public Vertex(Point point, int occupant) {
		this.point = point;
		this.occupant = occupant;
		this.visited = false;
		allowedToMove = true;
	}

	public Point getPoint() {
		return this.point;
	}

	public int getOccupant() {
		return this.occupant;
	}

	public void setOccupant(int occupant) {
		this.occupant = occupant;
	}

	public boolean isAllowedToMove(){
		return allowedToMove;
	}

	public void setAllowedToMove(boolean allowedToMove) {
		this.allowedToMove = allowedToMove;
	}

	public boolean isVisited() {
		return visited;
	}

	public void setVisited(boolean visited) {
		this.visited = visited;
	}

}
