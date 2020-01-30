package p2p.state;

import simulator.Event;
import simulator.EventQueue;

import java.util.LinkedList;

import p2p.time.P2PTime;

public class P2PState extends simulator.SimState{
    //Constants
    private final int nrOfNodes;
    private final double TIME_SIM_STOP;

    public Node[] nodesList;
    private CreateNode nodeSpawn;

	// Event descriptions
	private String eventDescription;
	private String nodeWhoPerformedEvent;
    /**
     * 
     * @param nrOfNodes
     * @param TIME_SIM_STOP
     */

    public P2PState(int nrOfNodes, EventQueue eventQueue, double TIME_SIM_STOP){
    	super(eventQueue);
        this.nrOfNodes = nrOfNodes;
        this.nodesList = new Node[nrOfNodes];
        this.TIME_SIM_STOP = TIME_SIM_STOP;
       // this.nodesList = new Node[nrOfNodes];
        this.nodeSpawn = new CreateNode();
    }

    /**
     * 
     * @return New node with incremented id
     */
    public Node createNewNode(){
        return nodeSpawn.newNode();
    }
    @Override
    public void updateState(Event event) {

		// DESCRIPTION OF EVENT

		// Updates event that occured
		eventDescription = event.getEventDescription();

		// Updates which customer who performed the event.
		nodeWhoPerformedEvent = event.getEventUserDescription();

		// Sets time to be the time that the event was executed.
		elapsedTime = event.getExTime();

		setChanged();
		notifyObservers();
    }
    
    @Override
    public void runSim() {
    	startSimulator();
    }
    
	/**
	 * 
	 * @return type of event
	 */
	public String getEventDescription() {
		return eventDescription;
	}
	
	/**
	 * 
	 * @return the customer who did an event
	 */
	public String getNodeWhoPerformedEvent() {
		return nodeWhoPerformedEvent;
	}
	/**
	 * @return timestamp for events
	 */
	public double getElapsedTime() {
		return elapsedTime;
	}
}