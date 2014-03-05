package carWashSimulator;

import java.util.ArrayList;

import simulator.Event;
import simulator.Simulator;

public class MainSim {

	public static void main(String[] args) {
		ArrayList<Event> events = new ArrayList<Event>();
		CarWashState carState = new CarWashState();
		CarWashView carView = new CarWashView(carState);
		
		//TODO: add initial events to ArrayList.
		
		new Simulator(carState, events, carView);
		
	}

}
