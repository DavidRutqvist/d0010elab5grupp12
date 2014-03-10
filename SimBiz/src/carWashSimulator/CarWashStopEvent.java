package carWashSimulator;

import simulator.StopEvent;

public class CarWashStopEvent extends StopEvent {
	private CarWashState s = (CarWashState) state;
	private int fastWashes = s.getAvailableFastWashes();
	private int slowWashes = s.getAvailableSlowWashes();
	public CarWashStopEvent(double priority) {
		super(priority);
	}
	/**
	 * Updates the washes' idle time and the total queue time for the cars.
	 */
	public void execute(){
		updateIdleTime();
		s.setQueueTime(s.getQueueTime() + (priority - s.getLatestUpdateTime()) * (s.getCarQueueSize()));
		s.setCurrentCWSEvent(this);
	}
	private void updateIdleTime() {
		s.setIdleTime(s.getIdleTime() + (priority - s.getLatestUpdateTime()) * (slowWashes + fastWashes));
	}
}
