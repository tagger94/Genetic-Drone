/*
 * Route.java
 * Stores a candidate route
 */

package gdrone;

import java.util.ArrayList;
import java.util.Collections;

public class Route {

	// Holds our route of parcels
	private ArrayList<Parcel> route = new ArrayList<Parcel>();
	// Cache
	private double fitness = 0;
	private int distance = 0;

	// Constructs a blank route
	public Route() {
		for (int i = 0; i < ParcelList.numberOfParcels(); i++) {
			route.add(null);
		}
	}

	public Route(ArrayList<Parcel> route) {
		this.route = route;
	}

	// Creates a random individual
	public void generateIndividual() {
		// Loop through all our destination parcels and add them to our route
		for (int parcelIndex = 0; parcelIndex < ParcelList.numberOfParcels(); parcelIndex++) {
			setParcel(parcelIndex, ParcelList.getParcel(parcelIndex));
		}
		// Randomly reorder the route
		Collections.shuffle(route);
	}

	// Gets a parcel from the route
	public Parcel getParcel(int routePosition) {
		return (Parcel) route.get(routePosition);
	}

	// Sets a parcel in a certain position within a route
	public void setParcel(int routePosition, Parcel parcel) {
		route.set(routePosition, parcel);
		// If the routes been altered we need to reset the fitness and distance
		fitness = 0;
		distance = 0;
	}

	// Gets the routes fitness
	public double getFitness() {
		if (fitness == 0) {
			fitness = 1 / (double) getDistance();
		}
		return fitness;
	}

	// Gets the total distance of the route
	public int getDistance() {
		if (distance == 0) {
			int routeDistance = 0;
			// Loop through our route's parcels
			for (int parcelIndex = 0; parcelIndex < routeSize(); parcelIndex++) {
				// Get parcel we're traveling from
				Parcel fromParcel = getParcel(parcelIndex);
				// Parcel we're traveling to
				Parcel destinationParcel;
				// Check we're not on our route's last parcel, if we are set our
				// route's final destination parcel to our starting parcel
				if (parcelIndex + 1 < routeSize()) {
					destinationParcel = getParcel(parcelIndex + 1);
				} else {
					destinationParcel = getParcel(0);
				}
				// Get the distance between the two parcels
				routeDistance += fromParcel.distanceTo(destinationParcel);
			}
			distance = routeDistance;
		}
		return distance;
	}

	// Get number of parcels on our route
	public int routeSize() {
		return route.size();
	}

	// Check if the route contains a parcel
	public boolean containsParcel(Parcel parcel) {
		return route.contains(parcel);
	}

	@Override
	public String toString() {
		String geneString = "|";
		for (int i = 0; i < routeSize(); i++) {
			geneString += getParcel(i) + "|";
		}
		return geneString;
	}
}