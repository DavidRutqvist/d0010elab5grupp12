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
			s.addCarToLine(car);
		}
		s.addEvent(new ArriveEvent(s.getNewArrivalTime(), s.createCar()));
		s.setLatestUpdateTime(priority);
	}
	private void updateIdleTime() {
		s.setIdleTime((priority - s.getLatestUpdateTime()) * (slowWashes + fastWashes));
	}
}
