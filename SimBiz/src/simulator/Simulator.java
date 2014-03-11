package simulator;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * A Discrete Event Driven Simulator (DEDS) which basically iterates over a sequence of ordered events and executes them.
 * @author david.rutqvist
 *
 */
public class Simulator {
	//None of these variables is needed but since we declared them in the design review they should probably remain to pass the assignment
	private SimState state;		//Holds the state for this simulation
	private EventQueue queue;	//Hold the queue for this simulation
	private SimView view;		//Hold the view for this simulation
	
	/**
	 * Creates a new simulation and runs it until it runs out of events or a StopEvent is reached.
	 * @param state The SimState
	 * @param events Events to add to execution at initialization
	 * @param view The SimView to be used to notify the user about events and the state
	 */
	public Simulator(SimState state, ArrayList<Event> events, SimView view)
	{
		//Store variables
		this.state = state;
		this.view = view;
		this.queue = new EventQueue();
		this.state.setQueue(this.queue);
		
		//Loop trough and add supplied events
		for(Event ev : events)
		{
			ev.setState(this.state);
			this.queue.add(ev);
		}

		//Iterate trough all events (some events may create new events) until there are non left or a StopEvent is reaced
		for(Event event : this.queue)//This will automatically get an iterator and then call next() as long as hasNext() returns true
		{
			event.execute();
			
			if(event instanceof StopEvent)
			{
				break;//Break if instance of StopEvent (could be extended to application specific stop event)
			}
		}
	}
}
