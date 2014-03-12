package simulator;

/**
 * Describes an event in an abstract way.
 *
 */
public abstract class Event {
	protected double priority;
	protected SimState state;
	/**
	 * 
	 * @param priority The priority of the event.
	 */
	public Event (double priority){
		this.priority = priority;
	}
	/**
	 * Sets the state.
	 * @param state The state of the simulation.
	 */
	public void setState (SimState state) {
		this.state = state;
	}
	/**
	 * 
	 * @return Returns the priority of the event
	 */
	public double getPriority () {
		return priority;
	}
	/**
	 * 'Runs' the event.
	 */
	public abstract void execute();
}
