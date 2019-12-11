package simulator;




/**
 * @author Nour Aldein Bahtite
 * @author Philip Eriksson
 * @author Rickard Bemm
 * @author André Christofferson
 */
public abstract class Event {

	protected double executeTime;
	protected SimState state;
	protected String eventDescription;
	protected String eventUserDescription;
	protected EventQueue eventQueue;
	
	
	public Event(SimState state) {
		this.state = state;
		this.eventQueue = state.getEventQueue();
	}

	/**
	 * Get time for event to execute.
	 * 
	 * @return time for event to execute
	 */
	public double getExTime() {
		return executeTime;
	}
	
	/**
	 * Get event description.
	 * 
	 * @return event description
	 */
	public String getEventDescription() {
		return eventDescription;
	}
	
	/**
	 *  Get event user description 
	 * 
	 * @return event user description
	 */
	public String getEventUserDescription() {
		return eventUserDescription;
	}

	/**
	 * Adds an event to the event queue for the specific state.
	 * 
	 * @param event adds this to the event queue
	 */
	public void addEventToQueue(Event event) {
		this.eventQueue.addEvent(event);
	}
	
	/**
	 * @return String that tells type of event
	 */
	public String toString() {
		return eventDescription + eventUserDescription;
		
	}

	/**
	 * Abstract method which is designed for each event.
	 */
	public abstract void runEvent();

}
