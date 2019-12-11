package simulator;

/**
 * @author Nour Aldein Bahtite
 * @author Philip Eriksson
 * @author Rickard Bemm
 * @author André Christofferson
 */
public class Simulator {

	private SimState state;
	private EventQueue queue;

	/**
	 * Create a new instance of Simulator
	 * 
	 * @param state A simulator state
	 */
	public Simulator(SimState state, EventQueue queue) {
		this.state = state;
		this.queue = queue;
	}

	/**
	 * Run simulator
	 */
	public void run(){
		state.runSim();

		while (state.simulatorIsRunning) {
			queue.getEvent().runEvent();
			queue.removeEvent();
		}
	}

}
