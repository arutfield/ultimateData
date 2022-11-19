package data;

public class Coordinate {
	private final Double x;
	private final Double y;
	public Coordinate(Double x, Double y) {
		this.x = x;
		this.y = y;
	}
	public Double getX() {
		return x;
	}
	public Double getY() {
		return y;
	}
	
	public Double getMagnitude() {
		if (x == null || y == null)
			return null;
		return Math.sqrt(Math.pow(x, 2) + Math.pow(y,  2));
	}
}
