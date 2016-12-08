/*
 * GA.java
 * Manages algorithms for evolving population
 */

package gdrone;

public class GA {

	/* GA parameters */
	private static final double mutationRate = 0.015;
	private static final int tournamentSize = 5;
	private static final boolean elitism = true;

	// Evolves a population over one generation
	public static Population evolvePopulation(Population pop) {
		Population newPopulation = new Population(pop.populationSize(), false);

		// Keep our best individual if elitism is enabled
		int elitismOffset = 0;
		if (elitism) {
			newPopulation.saveRoute(0, pop.getFittest());
			elitismOffset = 1;
		}

		// Crossover population
		// Loop over the new population's size and create individuals from
		// Current population
		for (int i = elitismOffset; i < newPopulation.populationSize(); i++) {
			// Select parents
			Route parent1 = tournamentSelection(pop);
			Route parent2 = tournamentSelection(pop);
			// Crossover parents
			Route child = crossover(parent1, parent2);
			// Add child to new population
			newPopulation.saveRoute(i, child);
		}

		// Mutate the new population a bit to add some new genetic material
		for (int i = elitismOffset; i < newPopulation.populationSize(); i++) {
			mutate(newPopulation.getRoute(i));
		}
		
		if (elitism) {
			newPopulation.saveRoute(0, pop.getFittest());
			elitismOffset = 1;
		}
		

		return newPopulation;
	}

	// Applies crossover to a set of parents and creates offspring
	public static Route crossover(Route parent1, Route parent2) {
		// Create new child route
		if(parent1.getFitness() > parent2.getFitness()) {
			return parent1;
		} else {
			return parent2;
		}
	}

	// Mutate a route using swap mutation
	private static void mutate(Route route) {
		// Loop through route parcels
		for (int routePos1 = 0; routePos1 < route.routeSize(); routePos1++) {
			// Apply mutation rate
			if (Math.random() < mutationRate) {
				// Get a second random position in the route
				int routePos2 = (int) (route.routeSize() * Math.random());

				// Get the parcels at target position in route
				Parcel parcel1 = route.getParcel(routePos1);
				Parcel parcel2 = route.getParcel(routePos2);

				// Swap them around
				route.setParcel(routePos2, parcel1);
				route.setParcel(routePos1, parcel2);
			}
		}
	}

	// Selects candidate route for crossover
	private static Route tournamentSelection(Population pop) {
		// Create a tournament population
		Population tournament = new Population(tournamentSize, false);
		// For each place in the tournament get a random candidate route and
		// add it
		for (int i = 0; i < tournamentSize; i++) {
			int randomId = (int) (Math.random() * pop.populationSize());
			tournament.saveRoute(i, pop.getRoute(randomId));
		}
		// Get the fittest route
		Route fittest = tournament.getFittest();
		return fittest;
	}
}