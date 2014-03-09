package carWashSimulator;

import simulator.Event;

public class ArriveEvent extends Event {
	private Car car;
	private CarWashState s = ((CarWashState) state);
	private int slowWashes = s.getAvailableSlowWashes();
	private int fastWashes = s.getAvailableFastWashes();
	public ArriveEvent(double priority, Car car) {
		super(priority);
		this.car = car;
	}
	
	public void execute() {
		updateIdleTime();
		if (fastWashes != 0) {
			s.setAvailableFastWashes(fastWashes-1);
			s.addEvent(new LeaveEvent(priority + s.getFastWashTime(), car, Washes.FAST));
		} else if (slowWashes != 0) {
			s.setAvailableSlowWashes(slowWashes-1);
			s.addEvent(new LeaveEvent(priority + s.getSlowWashTime(), car, Washes.SLOW));
		} else {
			s.setQueueTime(s.getQueueTime() + (priority - s.getLatestUpdateTime()) * (s.getCarQueueSize()));
			s.addCarToLine(car);
		}
		s.addEvent(new ArriveEvent(s.getNewArrivalTime(), s.getCarFactory().createNewCar()));
		s.setLatestUpdateTime(priority);
		s.setCurrentCWSEvent(this);
	}
	private void updateIdleTime() {
		s.setIdleTime(s.getIdleTime() + (priority - s.getLatestUpdateTime()) * (slowWashes + fastWashes));
	}
}
