package carWashSimulator;

import simulator.Event;

public class LeaveEvent extends Event {
	private Car car;
	private Washes wash;
	private CarWashState s = (CarWashState) state;
	private int fastWashes = s.getAvailableFastWashes();
	private int slowWashes = s.getAvailableSlowWashes();
	LeaveEvent(double priority, Car car, Washes wash) {
		super(priority);
		this.car = car;
		this.wash = wash;
	}

	public void execute() {
		updateIdleTime();
		if (s.getCarQueueSize() > 0) {
			if (wash == Washes.FAST) {
				s.setQueueTime(s.getQueueTime() + (priority - s.getLatestUpdateTime()) * (s.getCarQueueSize()));
				s.addEvent(new LeaveEvent(priority + s.getFastWashTime(), s.getFirstCarInLine(), Washes.FAST));
			} else {
				s.setQueueTime(s.getQueueTime() + (priority - s.getLatestUpdateTime()) * (s.getCarQueueSize()));
				s.addEvent(new LeaveEvent(priority + s.getSlowWashTime(), s.getFirstCarInLine(), Washes.SLOW));
			}
		} else {
			if (wash == Washes.FAST) {
				s.setAvailableFastWashes(fastWashes+1);
			} else {
				s.setAvailableSlowWashes(slowWashes+1);
			}
		}
		s.setLatestUpdateTime(priority);
		s.setCurrentCWSEvent(this);
	}
	private void updateIdleTime() {
		s.setIdleTime(s.getIdleTime() + (priority - s.getLatestUpdateTime()) * (slowWashes + fastWashes));
	}
}
