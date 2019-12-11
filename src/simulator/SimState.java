package simulator;

import java.util.Observable;

/**
 * 
 * @author Nour Aldein Bahtite
 * @author Philip Eriksson
 * @author Rickard Bemm
 * @author André Christofferson
 *
 * @version 1.0
 */
public abstract class SimState extends Observable {
	protected double elapsedTime;
	protected boolean simulatorIsRunning;
	protected EventQueue eventQueue;

	public SimState(EventQueue eventQueue) {
		this.eventQueue = eventQueue;
	}
	
	/**
	 *  Run the simulator and set the simulatorIsRunning variabel to true. 
	 */
	public final void startSimulator() {
		simulatorIsRunning = true;
	}
	
	/**
	 * Stop the simulator and set the simulatorIsRunning variabel to false. 
	 */
	public final void stopSimulator() {
		simulatorIsRunning = false;
		setChanged();
		notifyObservers();
	}
	
	/**
	 * 
	 * @return simulatorIsRunning when this method is called 
	 */
	public final boolean simulatorIsRunning() {
		return simulatorIsRunning;
	}

	/**
	 * 
	 * @return the elaspsed time for the simulator from the start to the end.
	 */
	public double getElapsedTime() {
		return elapsedTime;
	}
	
	/**
	 * 
	 * @return event queue there all events are saved.
	 */
	public final EventQueue getEventQueue() {
		return eventQueue;
	}
	abstract public void updateState(Event event);
	
	
	abstract public void runSim();
}
