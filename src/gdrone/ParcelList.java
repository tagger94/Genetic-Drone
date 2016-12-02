/*
 * RouteManager.java
 * Holds the parcels of a route
 */

package gdrone;

import java.util.ArrayList;

public class ParcelList {

	// Holds our parcels
	private static ArrayList<Parcel> destinationParcels = new ArrayList<Parcel>();

	// Adds a destination parcel
	public static void addParcel(Parcel city) {
		destinationParcels.add(city);
	}

	// Get a parcel
	public static Parcel getParcel(int index) {
		return (Parcel) destinationParcels.get(index);
	}

	// Get the number of destination parcels
	public static int numberOfParcels() {
		return destinationParcels.size();
	}
}