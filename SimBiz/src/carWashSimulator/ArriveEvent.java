package carWashSimulator;

import simulator.Event;

/**
 * Describes an event when a car arrives at the car wash station.
 */
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
	public Car getCar() {
		return car;
	}
	/**
	 * If there are available washes the car enters the wash and it's LeaveEvent is added to the EventQueue,
	 * otherwise the car is put in the waiting line.
	 * Updates the washes' idle time and the total queue time for the cars.
	 */
	public void execute() {
		CarWashState s = ((CarWashState) state);
		double timeBefore = s.getLatestUpdateTime();
		
		int slowWashes = s.getAvailableSlowWashes();
		int fastWashes = s.getAvailableFastWashes();
		
		//There might be cars in line, update the queue time.
		s.setQueueTime(s.getQueueTime() + (priority - timeBefore) * (s.getCarQueueSize()));
		//Update the idle time.
		s.setIdleTime(s.getIdleTime() + (priority - timeBefore) * (slowWashes + fastWashes));
		
		s.setLatestUpdateTime(priority);
		s.setCurrentCWSEvent(this);

		//Add a LeaveEvent for a fast wash if there is an empty fast wash.
		if (fastWashes != 0) {
			//Decrease the number of available washes.
			s.setAvailableFastWashes(fastWashes-1);
			//Pass car through empty line
			s.passCarThroughEmptyLine();
			//Add the car's LeaveEvent.
			LeaveEvent e = new LeaveEvent(priority + s.getFastWashTime(), car, Washes.FAST);
			e.setState(s);
			s.addEvent(e);
		} 
		//Add a LeaveEvent for a slow wash if there are no empty fast washes but an empty slow wash.
		else if (slowWashes != 0) {
			s.setAvailableSlowWashes(slowWashes-1);
			s.passCarThroughEmptyLine();
			LeaveEvent e = new LeaveEvent(priority + s.getSlowWashTime(), car, Washes.SLOW);
			e.setState(s);
			s.addEvent(e);
		} 
		//Add the car to the queue if there are no empty washes.
		else {
			s.addCarToLine(car);
		}
		//Add the next ArriveEvent to the EventQueue.
		ArriveEvent e = new ArriveEvent(s.getNewArrivalTime(), s.getCarFactory().createNewCar());
		e.setState(s);
		s.addEvent(e);
	}
}
