package store.event;

import simulator.StartEvent;
import store.state.StoreState;

/**
 * 
 * @author Nour Aldein Bahtite
 * @author Philip Eriksson
 * @author Rickard Bemm
 * @author André Christofferson
 * 
 */
public class StoreStartEvent extends StartEvent {
	/**
	 * Consctructs a new Start event for the store
	 * @param state
	 */
	public StoreStartEvent(StoreState state) {
		super(state);
		super.eventDescription = "Open";
		super.eventUserDescription = "-";
		super.executeTime = 0.0;
	}
	/**
	 * This method executes the startevent and calls for the first customer to 
	 * arrive. It updates the state who notify the observers.
	 */
	@Override
	public void runEvent() {
		StoreState s = (StoreState) state;
		s.updateState(this);
		s.openStore();
		
		double nextExecuteTime = state.getElapsedTime() + s.getTimeNextCustomer();
		eventQueue.addEvent(new CustomerArrivedEvent(s, nextExecuteTime));
	}

}
