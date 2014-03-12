package carWashSimulator;

import simulator.StartEvent;

/**
 * Describes the start of the car wash simulation.
 */
public class CarWashStartEvent extends StartEvent {
	/**
	 * 
	 * @param priority The time at which the LeaveEvent occurs (0).
	 */
	public CarWashStartEvent(double priority) {
		super(priority);
	}
	/**
	 * Adds the first ArriveEvent to the EventQueue.
	 */
	public void execute() {
		CarWashState s = ((CarWashState) state);
		s.setLatestUpdateTime(priority);
		ArriveEvent e = new ArriveEvent(s.getNewArrivalTime(), s.getCarFactory().createNewCar());
		e.setState(s);
		s.addEvent(e);
		s.setCurrentCWSEvent(this);
	}
}
