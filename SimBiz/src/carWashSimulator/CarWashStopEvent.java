package carWashSimulator;

import simulator.StopEvent;

public class CarWashStopEvent extends StopEvent {
	/**
	 * 
	 * @param priority The time at which the StopEvent occurs.
	 */
	public CarWashStopEvent(double priority) {
		super(priority);
	}
	/**
	 * Updates the washes' idle time and the total queue time for the cars.
	 */
	public void execute(){
		CarWashState s = (CarWashState) state;
		int fastWashes = s.getAvailableFastWashes();
		int slowWashes = s.getAvailableSlowWashes();
		//Update idle time.
		s.setIdleTime(s.getIdleTime() + (priority - s.getLatestUpdateTime()) * (slowWashes + fastWashes));
		//Update queue time.
		s.setQueueTime(s.getQueueTime() + (priority - s.getLatestUpdateTime()) * (s.getCarQueueSize()));
		s.setLatestUpdateTime(priority);
		s.setCurrentCWSEvent(this);
	}
}
