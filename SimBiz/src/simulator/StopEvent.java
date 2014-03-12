package simulator;

/**
 * Describes a general stop event for a simulation. A simulation is run until no event are left or until a stop event is fired.
 *
 */
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
