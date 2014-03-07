package carWashSimulator;

import java.util.ArrayList;

import simulator.Event;
import simulator.Simulator;
import simulator.StopEvent;

public class MainSim {

	public static void main(String[] args) {
		final double STARTPRIORITY = 0;
		final double STOPPRIORITY = 15;
		
		ArrayList<Event> events = new ArrayList<Event>();
		CarWashState carState = new CarWashState();
		CarWashView carView = new CarWashView(carState);
		
		events.add(new CarWashStartEvent(STARTPRIORITY));
		events.add(new StopEvent(STOPPRIORITY));
		
		new Simulator(carState, events, carView);
		
	}

}
