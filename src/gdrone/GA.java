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
		

		return newPopulation;
	}

	// Applies crossover to a set of parents and creates offspring
	public static Route crossover(Route parent1, Route parent2) {
		// Create new child route
		Route child = new Route();

		// Get start and end sub route positions for parent1's route
		int startPos = (int) (Math.random() * parent1.routeSize());
		int endPos = (int) (Math.random() * parent1.routeSize());

		// Loop and add the sub route from parent1 to our child
		for (int i = 0; i < child.routeSize(); i++) {
			// If our start position is less than the end position
			if (startPos < endPos && i > startPos && i < endPos) {
				child.setParcel(i, parent1.getParcel(i));
			} // If our start position is larger
			else if (startPos > endPos) {
				if (!(i < startPos && i > endPos)) {
					child.setParcel(i, parent1.getParcel(i));
				}
			}
		}

		// Loop through parent2's parcel route
		for (int i = 0; i < parent2.routeSize(); i++) {
			// If child doesn't have the parcel add it
			if (!child.containsParcel(parent2.getParcel(i))) {
				// Loop to find a spare position in the child's route
				for (int ii = 0; ii < child.routeSize(); ii++) {
					// Spare position found, add parcel
					if (child.getParcel(ii) == null) {
						child.setParcel(ii, parent2.getParcel(i));
						break;
					}
				}
			}
		}
		return child;
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