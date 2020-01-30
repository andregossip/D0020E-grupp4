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
        //hard-coded for now
        for(int i=0; i<2; i++){
            s.nodesList[i] = s.createNewNode();
        }
        /*
         * TODO
         * Fix nextExecuteTime
         */
        double nextExecuteTime = s.getElapsedTime() + 0.01;
        eventQueue.addEvent(new NodeSendFile(s, s.nodesList[0],s.nodesList[1], nextExecuteTime));
    }
}