package carWashSimulator;

import java.util.ArrayList;

import simulator.Event;
import simulator.Simulator;
import simulator.StopEvent;

/**
 * A class containing the main method starting the program.
 * @author Emil
 *
 */
public class MainSim {

	/**
	 * The main method of the program, setting the initial parameters and starting the
	 * simulator.
	 * @param args is not used.
	 */
	public static void main(String[] args) {
		final double STARTPRIORITY = 0;
		final double STOPPRIORITY = 15;
		
		ArrayList<Event> events = new ArrayList<Event>();
		CarWashState carState = new CarWashState();
		CarWashView carView = new CarWashView(carState);
		
		events.add(new CarWashStartEvent(STARTPRIORITY));
		events.add(new CarWashStopEvent(STOPPRIORITY));
		
		new Simulator(carState, events, carView);
		
	}

}
