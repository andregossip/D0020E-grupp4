package main;

import simulator.EventQueue;
import simulator.Simulator;
import simulator.StopEvent;
import p2p.event.P2PStartEvent;
import p2p.state.P2PState;
import p2p.view.P2PView;

public class Simulation {
    public static void main(String[] args) {
        int nrOfNodes = 4;
        double TIME_SIM_STOP = 0.5;

        P2PView view = new P2PView("/Users/andre/Documents/D0020E/Output/text.txt");
        EventQueue eventQueue = new EventQueue();
        P2PState state = new P2PState(nrOfNodes, eventQueue, TIME_SIM_STOP);

        //Create and add events
        eventQueue.addEvent(new P2PStartEvent(state));
        eventQueue.addEvent(new StopEvent(state, TIME_SIM_STOP));
        
        //Add Observer
        state.addObserver(view);

        //Run Simulator
        new Simulator(state, eventQueue).run();

    }
}