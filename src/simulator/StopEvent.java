package simulator;

public class StopEvent extends Event {
	/**
	 * Constructor for a stop event
	 * @param state
	 * @param executeTime
	 */
	public StopEvent(SimState state, double executeTime) {
		super(state);
		super.eventDescription = "Stop";
		super.eventUserDescription = "-";
		this.executeTime = executeTime;
	}
	/**
	 * This method stops the simulator
	 */
	public void runEvent() {
		state.updateState(this);
		state.stopSimulator();
	}


}
