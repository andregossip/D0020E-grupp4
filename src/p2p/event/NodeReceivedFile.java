package p2p.event;

import simulator.Event;
import p2p.state.Node;
import p2p.state.P2PState;


public class NodeReceivedFile extends Event {
	
	private Node source;
	private Node destination;
	
	public NodeReceivedFile(P2PState state, Node source, Node destination, double time) {
		super(state);
		super.eventDescription = "Node received file";
		this.source = source;
		this.destination = destination;
		super.eventUserDescription = destination.toString();
		super.executeTime = time;
	}
    @Override
    public void runEvent() {
    	P2PState s = (P2PState) state;
    	s.updateState(this);
    	//eventQueue.addEvent(new NodeSendFile(s, node, 0.01));
    }
}
