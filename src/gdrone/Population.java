/*
 * ParcelList.java
 * Manages a list of candidate routes
 */

package gdrone;

public class Population {

	// Holds list of routes
	Route[] routes;
	
	// Holds values for statics of population
	private double max = 0;
	private double min = 0;
	private double mean = 0;

	// Construct a population
	public Population(int populationSize, boolean initialise) {
		routes = new Route[populationSize];
		// If we need to initialize a population of routes do so
		if (initialise) {
			// Loop and create individuals
			for (int i = 0; i < populationSize(); i++) {
				Route newRoute = new Route();
				newRoute.generateIndividual();
				saveRoute(i, newRoute);
			}
		}
	}

	// Saves a route
	public void saveRoute(int index, Route route) {
		routes[index] = route;
	}

	// Gets a route from population
	public Route getRoute(int index) {
		return routes[index];
	}

	// Gets the best route in the population
	public Route getFittest() {
		Route fittest = routes[0];
		// Loop through individuals to find fittest
		for (int i = 1; i < populationSize(); i++) {
			if (fittest.getFitness() <= getRoute(i).getFitness()) {
				fittest = getRoute(i);
			}
		}
		return fittest;
	}

	// Gets population size
	public int populationSize() {
		return routes.length;
	}
	
	public double getMaxDistance() {
		if(max == 0) {
			calcStats();
		}
		return max;
	}
	
	public double getMinDistance() {
		if(max == 0) {
			calcStats();
		}
		return min;
	}
	
	public double getMeanDistance() {
		if(max == 0) {
			calcStats();
		}
		return mean;
	}
	
	public void calcStats(){
		//Set initial Value
		this.max = this.routes[0].getDistance();
		this.min = this.routes[0].getDistance();
		double sum = this.routes[0].getDistance();
		
		for (int i = 1; i < routes.length; i++){
			double dist = routes[i].getDistance();
			
			//Find if it is max or min
			if(dist < this.max){
				
			} else if(dist > this.min){
				
			}
			
			//Add it to the sum
			sum += dist;
		}
		
		this.mean = sum / this.routes.length;
	}
}