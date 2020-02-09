package main;

import simulator.EventQueue;
import simulator.Simulator;
import simulator.StopEvent;
import p2p.event.P2PStartEvent;
import p2p.state.P2PState;
import p2p.view.P2PView;

public class Simulation {
    public static void main(String[] args) {
        int nrOfNodes = 5;
        double TIME_SIM_STOP = 0.5;
        long TIME_SEED = 1234; 
        double ARRIVAL_SPEED = 32;
        
        P2PView view = new P2PView("/Users/andre/Documents/D0020E/Output/text.txt");
        EventQueue eventQueue = new EventQueue();	
        P2PState state = new P2PState(ARRIVAL_SPEED, TIME_SEED, nrOfNodes,eventQueue, TIME_SIM_STOP);

        //Create and add events
        eventQueue.addEvent(new P2PStartEvent(state));
        eventQueue.addEvent(new StopEvent(state, TIME_SIM_STOP));
        
        //Add Observer
        state.addObserver(view);

        //Run Simulator
        new Simulator(state, eventQueue).run();

    }
}