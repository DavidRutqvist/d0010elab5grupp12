package simulator;

import java.util.Observable;

/**
 * A SimState which is observable, holds all necessary information. <b>Should be extended to application specific state.</b>
 * @author david.rutqvist
 *
 */
public class SimState extends Observable {
	protected EventQueue queue;//The queue which hold all events is used to be able to add events an easy way from events' execute()-method
	
	/**
	 * Sets the queue which the states holds
	 * @param queue
	 */
	public void setQueue(EventQueue queue)
	{
		this.queue = queue;
	}
	
	/**
	 * Adds an event to queue. (Basically just maps the insert call down to the right object)
	 * @param event Event to be inserted
	 */
	public void addEvent(Event event)
	{
		this.queue.add(event);
	}
}
