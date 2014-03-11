package simulator;

public class StopEvent extends Event {
	/**
	 * 
	 * @param priority The priority of the stop event.
	 */
	public StopEvent(double priority) {
		super(priority);
	}
	/**
	 * Stops the simulation
	 */
	public void execute() {
	}
}
