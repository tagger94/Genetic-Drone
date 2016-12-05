package gdrone;

import java.util.ArrayList;

public class WorkerManager {
	private boolean cont;
	private int step_by;
	private int num_threads;
	private boolean breakmode;
	private volatile Population pop;
	
	private class worker extends Thread{
		private ArrayList<Route> r = new ArrayList<Route>();
		void add_route(Route r){
			this.r.add(r);
		}
		public void start(){
			for(int i = 0; i < r.size(); i++){
				r.get(i).getFitness();
			}
		}
	}
	
	WorkerManager(Population pop,int num_threads, boolean breakmode){
		step_by = pop.populationSize()/num_threads; // number of routes each thread will run
		this.breakmode = breakmode;
		this.pop = pop;
		this.num_threads = num_threads;
		cont = true;
	}
	
	//recursevly do goto_gen number of generations 
	//curr_gen should be 0 when called outside
	public void run_workermanager(int curr_gen, int goto_gen){
		
		//run all workers for current generation
		for(int i = 0; i < num_threads; i++){
			run_workers(i*step_by,(i*step_by)+step_by);
		}
		
		//pause after each generation
		if(breakmode){
			cont = false;
			//call to send stuff to GUI
			while(!cont);
		}
		
		pop = GA.evolvePopulation(pop);
		
		System.out.println("gen number: " + curr_gen + " " + pop.getFittest().getDistance());

		if(curr_gen == goto_gen){
			return;
		}
		else{
			run_workermanager(curr_gen+1,goto_gen);
		}
	}
	
	private void run_workers(int start,int stop){
		worker w = new worker();
		for(int i = start; i < stop; i++){
			if(pop.populationSize() == i){
				break;
			}
			w.add_route(pop.getRoute(i));
			w.start();
		}
	}
	
	public void start_next(){
		cont = true;
	}
}
