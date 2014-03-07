package carWashSimulator;

import simulator.Event;

public class LeaveEvent extends Event {
	private Car car;
	Washes wash;
	LeaveEvent(double priority, Car car, Washes wash) {
		super(priority);
		this.car = car;
		this.wash = wash;
	}

	public void execute() {
		CarWashState s = (CarWashState) state;
		if (wash == Washes.FAST) {
			s.setAvailableFastWashes(s.getAvailableFastWashes()+1);
		} else {
			s.setAvailableSlowWashes(s.getAvailableSlowWashes()+1);
		}
	}
}
