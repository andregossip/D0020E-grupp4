package event;

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

    }
}