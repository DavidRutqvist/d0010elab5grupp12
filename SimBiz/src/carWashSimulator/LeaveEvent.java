package carWashSimulator;

import simulator.Event;

/**
 * Describes an event when a car leaves the car wash, i.e. washing has finished.
 *
 */
public class LeaveEvent extends Event {
	private Car car;
	private Washes wash; //Fast or slow wash.
	/**
	 * 
	 * @param priority The time at which the LeaveEvent occurs.
	 * @param car The car that leaves the car wash.
	 * @param wash The wash that the car leaves (fast or slow).
	 */
	LeaveEvent(double priority, Car car, Washes wash) {
		super(priority);
		this.car = car;
		this.wash = wash;
	} 
	public Car getCar() {
		return car;
	}
	/**
	 * If the queue is not empty, the first car in line is removed from the queue
	 * and the LeaveEvent for that car is added to the EventQueue.
	 * Updates the washes' idle time and the total queue time for the cars.
	 */
	public void execute() {
		CarWashState s = (CarWashState) state;
		double timeBefore = s.getLatestUpdateTime();//Store time before updating to current time
		
		int fastWashes = s.getAvailableFastWashes();
		int slowWashes = s.getAvailableSlowWashes();
		
		//Update idle time.
		s.setIdleTime(s.getIdleTime() + (priority - timeBefore) * (slowWashes + fastWashes));
		
		//Update queue time, there might be cars in queue
		s.setQueueTime(s.getQueueTime() + (priority - timeBefore) * (s.getCarQueueSize()));
		
		s.setLatestUpdateTime(priority);
		s.setCurrentCWSEvent(this);
		
		//Puts the first car in line in the wash a car just left.
		if (s.getCarQueueSize() > 0) {
			if (wash == Washes.FAST) {
				//Add the car's LeaveEvent.
				LeaveEvent e = new LeaveEvent(priority + s.getFastWashTime(), s.getFirstCarInLine(), Washes.FAST);
				e.setState(s);
				s.addEvent(e);
			} else {
				LeaveEvent e = new LeaveEvent(priority + s.getSlowWashTime(), s.getFirstCarInLine(), Washes.SLOW);
				e.setState(s);
				s.addEvent(e);
			}
		} 
		//If there are no cars in line, increase the number of available washes.
		else {
			if (wash == Washes.FAST) {
				s.setAvailableFastWashes(fastWashes+1);
			} else {
				s.setAvailableSlowWashes(slowWashes+1);
			}
		}
	}
}
