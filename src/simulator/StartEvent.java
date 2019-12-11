package simulator;

public abstract class StartEvent extends Event {
	
	/**
	 * 
	 * @param state
	 */
	public StartEvent(SimState state) {
		super(state);
		super.eventDescription = "Start";
		super.eventUserDescription = "-";
		this.executeTime = 0;
	}

	public abstract void runEvent();
}
