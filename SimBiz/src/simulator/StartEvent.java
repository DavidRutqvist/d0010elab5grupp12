package simulator;

/**
 * Defines a general start event for a simulation.
 *
 */
public class StartEvent extends Event {
	/**
	 * 
	 * @param priority The priority of the start event.
	 */
	protected StartEvent(double priority) {
		super(priority);
	}
	/**
	 * Starts the simulation
	 */
	public void execute() {
	}
}
