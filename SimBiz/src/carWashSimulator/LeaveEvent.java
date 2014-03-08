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
		
		if (wash == Washes.FAST) {
			s.setAvailableFastWashes(fastWashes+1);
		} else {
			s.setAvailableSlowWashes(slowWashes+1);
		}
		if (s.getCarQueueSize() > 0) {
			if (fastWashes > 0) {
				s.setQueueTime(s.getQueueTime() + (priority - s.getLatestUpdateTime()) * (s.getCarQueueSize()));
				s.addEvent(new LeaveEvent(priority + s.getFastWashTime(), s.getFirstCarInLine(), Washes.FAST));
				s.setAvailableFastWashes(fastWashes-1);
			} else {
				s.setQueueTime(s.getQueueTime() + (priority - s.getLatestUpdateTime()) * (s.getCarQueueSize()));
				s.addEvent(new LeaveEvent(priority + s.getSlowWashTime(), s.getFirstCarInLine(), Washes.SLOW));
				s.setAvailableSlowWashes(slowWashes-1);
			}
		}
		s.setLatestUpdateTime(priority);
	}
}
