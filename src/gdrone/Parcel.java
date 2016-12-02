/*
 * Parcel.java
 * Models a parcel
 */

package gdrone;

public class Parcel {
	int x;
	int y;

	// Constructs a randomly placed parcel
	public Parcel() {
		this.x = (int) (Math.random() * 200);
		this.y = (int) (Math.random() * 200);
	}

	// Constructs a parcel at chosen x, y location
	public Parcel(int x, int y) {
		this.x = x;
		this.y = y;
	}

	// Gets parcel's x coordinate
	public int getX() {
		return this.x;
	}

	// Gets parcel's y coordinate
	public int getY() {
		return this.y;
	}

	// Gets the distance to given parcel
	public double distanceTo(Parcel parcel) {
		int xDistance = Math.abs(getX() - parcel.getX());
		int yDistance = Math.abs(getY() - parcel.getY());
		double distance = Math.sqrt((xDistance * xDistance)
				+ (yDistance * yDistance));

		return distance;
	}

	@Override
	public String toString() {
		return getX() + ", " + getY();
	}
}