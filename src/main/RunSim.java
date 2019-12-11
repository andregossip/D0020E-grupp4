package main;

import simulator.EventQueue;
import simulator.Simulator;
import simulator.StopEvent;
import store.event.StoreCloseEvent;
import store.event.StoreStartEvent;
import store.state.StoreState;
import store.view.StoreView;;

public class RunSim {

	public static void main(String[] args) {

		///////////////////
		//	 Example 1	 //
		///////////////////
		
		// Constants
		long TIME_SEED = 1234; 			// Seed to generate random number
		int MAX_CUSTOMERS = 5; 			// Maximum number of costumers allowed in store at once
		int MAX_REGISTERS = 2; 			// Maximum number of registers available in store
		int TIME_STORE_CLOSE = 10; 		// At what time store closes
		double ARRIVAL_SPEED = 1.0d; 	// Speed of which costumers arrive at
		double MIN_PICKING_TIME = 0.5d;	// Minimum time a costumer can pick items in
		double MAX_PICKING_TIME = 1d; 	// Maximum time a costumer can pick items in
		double MIN_CHECKOUT_TIME = 2d; 	// Minimum time a costumer can checkout in
		double MAX_CHECKOUT_TIME = 3d; 	// Time a costumer can checkout in
		double TIME_SIM_STOP = 999;

		// Create instances of various objects
		StoreView view = new StoreView("C:/temp/Spermarket_DEDS.log"); // Print result to file only!
		EventQueue eventQueue = new EventQueue();
		StoreState state = new StoreState(TIME_SEED, MAX_CUSTOMERS, MAX_REGISTERS,
				TIME_STORE_CLOSE, ARRIVAL_SPEED, MIN_PICKING_TIME, MAX_PICKING_TIME,
				MIN_CHECKOUT_TIME, MAX_CHECKOUT_TIME, eventQueue, TIME_SIM_STOP);

		// Create and add events
		eventQueue.addEvent(new StoreStartEvent(state));
		eventQueue.addEvent(new StoreCloseEvent(state, TIME_STORE_CLOSE));
		eventQueue.addEvent(new StopEvent(state, TIME_SIM_STOP));
		
		// Add observer
		state.addObserver(view);

		// Run simulator
		new Simulator(state, eventQueue).run();

		
		///////////////////
		//	 Example X	 //
		///////////////////
		long TIME_SEED_X = 13;				// Seed to generate random number
		int MAX_CUSTOMERS_X = 7; 			// Maximum number of costumers allowed in store at once
		int MAX_REGISTERS_X = 2; 			// Maximum number of registers available in store
		int TIME_STORE_CLOSE_X = 8;			// At what time store closes
		double ARRIVAL_SPEED_X = 3.0d; 		// Speed of which costumers arrive at
		double MIN_PICKING_TIME_X = 0.6d;	// Minimum time a costumer can pick items in
		double MAX_PICKING_TIME_X = 0.9d; 	// Maximum time a costumer can pick items in
		double MIN_CHECKOUT_TIME_X = 0.35d; // Minimum time a costumer can checkout in
		double MAX_CHECKOUT_TIME_X = 0.6d;	// Time a costumer can checkout in
		double TIME_SIM_STOP_X = 999;
		
		StoreView viewX = new StoreView();
		EventQueue eventQueueX = new EventQueue();
		StoreState stateX = new StoreState(TIME_SEED_X, MAX_CUSTOMERS_X, MAX_REGISTERS_X,
				TIME_STORE_CLOSE_X, ARRIVAL_SPEED_X, MIN_PICKING_TIME_X,
				MAX_PICKING_TIME_X, MIN_CHECKOUT_TIME_X, MAX_CHECKOUT_TIME_X,
				eventQueueX,TIME_SIM_STOP_X);

		eventQueueX.addEvent(new StoreStartEvent(stateX));
		eventQueueX.addEvent(new StoreCloseEvent(stateX, TIME_STORE_CLOSE_X));
		eventQueueX.addEvent(new StopEvent(stateX, TIME_SIM_STOP_X));

		stateX.addObserver(viewX);

		new Simulator(stateX, eventQueueX).run();

	}

}
