package carWashSimulator;

import simulator.StartEvent;

public class CarWashStartEvent extends StartEvent {
	public CarWashStartEvent(double priority) {
		super(priority);
	}
	
	public void execute() {
		CarWashState s = ((CarWashState) state);
		s.addEvent(new ArriveEvent(s.getNewArrivalTime(), s.getCarFactory().createNewCar()));
	}
}
