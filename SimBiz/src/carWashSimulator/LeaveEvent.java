package carWashSimulator;

import simulator.Event;

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
	/**
	 * If the queue is not empty, the first car in line is removed from the queue
	 * and the LeaveEvent for that car is added to the EventQueue.
	 * Updates the washes' idle time and the total queue time for the cars.
	 */
	public void execute() {
		CarWashState s = (CarWashState) state;
		int fastWashes = s.getAvailableFastWashes();
		int slowWashes = s.getAvailableSlowWashes();
		//Update idle time.
		s.setIdleTime(s.getIdleTime() + (priority - s.getLatestUpdateTime()) * (slowWashes + fastWashes));
		//Puts the first car in line in the wash a car just left.
		if (s.getCarQueueSize() > 0) {
			if (wash == Washes.FAST) {
				//Update queue time.
				s.setQueueTime(s.getQueueTime() + (priority - s.getLatestUpdateTime()) * (s.getCarQueueSize()));
				//Add the car's LeaveEvent.
				s.addEvent(new LeaveEvent(priority + s.getFastWashTime(), s.getFirstCarInLine(), Washes.FAST));
			} else {
				s.setQueueTime(s.getQueueTime() + (priority - s.getLatestUpdateTime()) * (s.getCarQueueSize()));
				s.addEvent(new LeaveEvent(priority + s.getSlowWashTime(), s.getFirstCarInLine(), Washes.SLOW));
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
		s.setLatestUpdateTime(priority);
		s.setCurrentCWSEvent(this);
	}
}
