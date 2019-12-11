package main;

import java.text.DecimalFormat;
import java.text.MessageFormat;
import java.util.Random;
import simulator.EventQueue;
import simulator.Simulator;
import simulator.StopEvent;
import store.event.StoreCloseEvent;
import store.event.StoreStartEvent;
import store.state.StoreState;

public class Optimize implements OptimizeTesting {
	int missedCustomers;
	public static void main(String[] args) {

		new Optimize().metod3(1234);
	}
	
	private static int example = 0;

	public StoreState metod1(long timeSeed, int maxRegisters) {

		EventQueue eventQueue = new EventQueue();
		StoreState state = new StoreState(TIME_SEED,MAX_CUSTOMERS,maxRegisters,TIME_STORE_CLOSE,
				  ARRIVAL_SPEED,MIN_PICKING_TIME,MAX_PICKING_TIME,MIN_CHECKOUT_TIME,
				  MAX_CHECKOUT_TIME,eventQueue,SIM_STOP_TIME);
		
		eventQueue.addEvent(new StoreStartEvent(state));
		eventQueue.addEvent(new StoreCloseEvent(state, state.getTIME_STORE_CLOSE()));
		eventQueue.addEvent(new StopEvent(state, state.getTIME_SIM_STOP()));
		new Simulator(state, eventQueue).run();
		return state;
	}
	
	public int metod2(long timeSeed) {
		missedCustomers = metod1(timeSeed, MAX_CUSTOMERS).getCustomersDeniedEntry();
		int max_Registers = 1;
		while (metod1(timeSeed, max_Registers).getCustomersDeniedEntry() > missedCustomers) {
			max_Registers ++;
		}
		return max_Registers;
	}

	public void metod3(long seed) {
		Random random = new Random(seed);
		int maxLeastRegisters = 0;
		int leastRegistersIteration = 0;
		long timeSeed = 0;
		for(int i = 0; i < 100; i++) {
			timeSeed = random.nextLong();
			leastRegistersIteration = metod2(timeSeed);
			if(leastRegistersIteration > maxLeastRegisters) {
				maxLeastRegisters = leastRegistersIteration;
				i=0;
			}
		}
		
		StoreState s = metod1(timeSeed, maxLeastRegisters);
		System.out.println(resultBody(s));
		System.out.println(resultsEnd(s));
	}

	/// FOR RESULTS VIEW

	private String resultBody(StoreState state) {
		String result = "Exampel " + (example++) + "\n";
		result += MessageFormat.format("Max som ryms, M...........: {0} \n", state.getMAX_CUSTOMERS());
		result += MessageFormat.format("Ankomshastighet, lambda...: {0} \n", state.getARRIVAL_SPEED());
		result += MessageFormat.format("Plocktider, [P_min, P_max]: [{0}..{1}] \n", state.getMIN_PICKING_TIME(),
				state.getMAX_PICKING_TIME());
		result += MessageFormat.format("Betaltider, [K_min, K_max]: [{0}..{1}] \n", state.getMIN_CHECKOUT_TIME(),
				state.getMAX_CHECKOUT_TIME());
		result += MessageFormat.format("Frö, F....................: {0} \n", state.getTIME_SEED());
		return result;

	}

	private String resultsEnd(StoreState state) {
		String result = "";
		result += MessageFormat.format("Stängning sker tiden {0} och stophändelsen sker tiden {1} \n",
				state.getTIME_STORE_CLOSE(), state.getTIME_SIM_STOP());
		result += MessageFormat.format("Minsta antal kassor som ger minimalt antal missade ({0}): {1} \n ",
				state.getCustomersDeniedEntry(), state.getMAX_REGISTERS());
		if (state.getCustomersDeniedEntry() != 0) {
			result += MessageFormat.format("(OBS! Missar som minst {0} kunder.)", "<ANTAL MISSADE>");
		}
		return result;

	}

	private void printResultsMetod2(StoreState state) {
		String result = resultBody(state) + resultsEnd(state);
		System.out.println(result);

	}
	
	private void printResultsMetod3(StoreState state){
		String result = resultBody(state);
		result += MessageFormat.format("Lambda = {0} \n", state.getARRIVAL_SPEED());
		result += resultsEnd(state);
		System.out.println(result);
	}
	
	private String cutDecimals(double d) {
		return new DecimalFormat("#.##").format(d);
	}
}
