package p2p.event;

import simulator.Event;
import p2p.state.Node;
import p2p.state.P2PState;

public class NodeSendFile extends Event {
    public NodeSendFile(P2PState state, double time){
        super(state);
        super.eventDescription = "Node send file";
        // Implementera node.toString
        super.eventUserDescription = node.toString();
        super.executeTime = time;
    }
}