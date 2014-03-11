package carWashSimulator;

import simulator.Event;

public class ArriveEvent extends Event {
	private Car car;

	/**
	 * 
	 * @param priority The time at which the LeaveEvent occurs.
	 * @param car The car that enters the car wash.
	 */
	public ArriveEvent(double priority, Car car) {
		super(priority);
		this.car = car;
	}
	/**
	 * If there are available washes the car enters the wash and it's LeaveEvent is added to the EventQueue,
	 * otherwise the car is put in the waiting line.
	 * Updates the washes' idle time and the total queue time for the cars.
	 */
	public void execute() {
		CarWashState s = ((CarWashState) state);
		int slowWashes = s.getAvailableSlowWashes();
		int fastWashes = s.getAvailableFastWashes();
		s.setIdleTime(s.getIdleTime() + (priority - s.getLatestUpdateTime()) * (slowWashes + fastWashes));
		if (fastWashes != 0) {
			s.setAvailableFastWashes(fastWashes-1);
			LeaveEvent e = new LeaveEvent(priority + s.getFastWashTime(), car, Washes.FAST);
			e.setState(s);
			s.addEvent(e);
		} else if (slowWashes != 0) {
			s.setAvailableSlowWashes(slowWashes-1);
			LeaveEvent e = new LeaveEvent(priority + s.getSlowWashTime(), car, Washes.SLOW);
			e.setState(s);
			s.addEvent(e);
		} else {
			s.setQueueTime(s.getQueueTime() + (priority - s.getLatestUpdateTime()) * (s.getCarQueueSize()));
			s.addCarToLine(car);
		}
		ArriveEvent e = new ArriveEvent(s.getNewArrivalTime(), s.getCarFactory().createNewCar());
		e.setState(s);
		s.addEvent(e);
		s.setLatestUpdateTime(priority);
		s.setCurrentCWSEvent(this);
	}
}
