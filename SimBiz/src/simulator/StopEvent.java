package simulator;

import carWashSimulator.CarWashState;

public class StopEvent extends Event {
	public StopEvent(double priority) {
		super(priority);
	}

	public void execute() {
		CarWashState s = ((CarWashState) state);
		s.setHasStopped();
	}
}
