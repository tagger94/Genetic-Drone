/*
 * TSP_GA.java
 * Create a tour and evolve a solution
 */

package gdrone;

public class GeneticDrone {

	public static void main(String[] args) {

		// Create and add our parcels		
		for (int i = 0; i < 1000; i++) {
			ParcelList.addParcel(new Parcel());
		}

		// Initialize population
		Population pop = new Population(50, true);

		// Evolve population for several generations
		WorkerManager wm = new WorkerManager(pop,50,false);
		wm.run_workermanager(0, 1000);

		// Print final results
		System.out.println("Finished");
		System.out.println("Final distance: " + pop.getFittest().getDistance());
		System.out.println("Solution:");
		System.out.println(pop.getFittest());
	}
}