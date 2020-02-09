package p2p.state;

import simulator.Event;
import simulator.EventQueue;
import java.util.LinkedList;
import p2p.time.P2PTime;

public class P2PState extends simulator.SimState{
	
    //Constants
    private final int nrOfNodes;
	private final double TIME_SIM_STOP;
	//private final double ARRIVAL_SPEED;
	private final long TIME_SEED;

    public Node[] nodesList;
    private CreateNode nodeSpawn;

	// Event descriptions
	private String eventDescription;
	private String nodeWhoPerformedEvent;
	
	private P2PTime p2pTime;

	/**
	 * Construct an instance of P2PTime
	 * 
	 * @param ARRIVAL_SPEED
	 * @param TIME_SEED
	 * @param nrOfNodes
	 * @param eventQueue
	 * @param TIME_SIM_STOP
	 */
    public P2PState (double ARRIVAL_SPEED, long TIME_SEED, int nrOfNodes, EventQueue eventQueue, double TIME_SIM_STOP){
    	super(eventQueue);
    	//this.ARRIVAL_SPEED = ARRIVAL_SPEED;
        this.TIME_SEED = TIME_SEED;
        this.nodesList = new Node[nrOfNodes];
        this.TIME_SIM_STOP = TIME_SIM_STOP;
        //this.nodesList = new Node[nrOfNodes];
        this.nodeSpawn = new CreateNode();
        this.nrOfNodes = nodesList.length;
		this.p2pTime = new P2PTime(ARRIVAL_SPEED,TIME_SEED);
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

		// Updates which node who performed the event.
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
	
	public Node getNode(int id) {
		return nodesList[id];
	}
	/**
	 * @return timestamp for events
	 */
	public double getElapsedTime() {
		return elapsedTime;
	}
	public int getNrOfNodes() {
		return nrOfNodes;
	}
}