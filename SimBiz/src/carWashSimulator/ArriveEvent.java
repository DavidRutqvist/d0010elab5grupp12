package carWashSimulator;

import simulator.Event;

public class ArriveEvent extends Event {
	private Car car;
	private CarWashState s = ((CarWashState) state);
	private int slowWashes = s.getAvailableSlowWashes();
	private int fastWashes = s.getAvailableFastWashes();
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
