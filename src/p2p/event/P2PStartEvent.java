package p2p.event;

import simulator.StartEvent;
import p2p.state.P2PState;

public class P2PStartEvent extends StartEvent{
    public P2PStartEvent(P2PState state){
        super(state);
        super.eventDescription = "Network Open";
		super.eventUserDescription = "-";
		super.executeTime = 0.0;
    }
    @Override
	public void runEvent() {
        P2PState s = (P2PState) state;
        s.updateState(this);
        /**
         * Creates the Nodes in the network
         * TODO
         * Maybe put this in the constructor of P2PState instead
         * Fix so all nodes sends file, the last does not send anything now
         */
        double nextExecuteTime = s.getElapsedTime();
        for(int i=0; i<s.getNrOfNodes(); i++){
            s.nodesList[i] = s.createNewNode();
            nextExecuteTime = s.getElapsedTime() + 0.01;
            if (i != 0) {
            	eventQueue.addEvent(new NodeSendFile(s, s.getNode(i-1),s.getNode(i), nextExecuteTime));
            }
        }
        /*
         * TODO
         * Fix nextExecuteTime
         */
//       nextExecuteTime = s.getElapsedTime() + 0.01;
//       eventQueue.addEvent(new NodeSendFile(s, s.nodesList[0],s.nodesList[1], nextExecuteTime));
    }
}