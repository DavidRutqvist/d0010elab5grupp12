package carWashSimulator;

import java.util.ArrayList;

import simulator.Event;
import simulator.Simulator;
import simulator.StopEvent;

public class MainSim {

	public static void main(String[] args) {
		ArrayList<Event> events = new ArrayList<Event>();
		CarWashState carState = new CarWashState();
		CarWashView carView = new CarWashView(carState);
		
		events.add(new CarWashStartEvent());
		events.add(new StopEvent());
		
		new Simulator(carState, events, carView);
		
	}

}
