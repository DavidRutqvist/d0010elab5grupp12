package simulator;

import java.util.ArrayList;
import java.util.Iterator;

public class Simulator {
	private SimState state;
	private EventQueue queue;
	private SimView view;
	
	public Simulator(SimState state, ArrayList<Event> events, SimView view)
	{
		this.state = state;
		this.view = view;
		this.queue = new EventQueue();
		
		for(Event ev : events)
		{
			ev.setState(this.state);
			this.queue.add(ev);
		}
		
		Iterator<Event> eventIterator = this.queue.iterator();
		
		while(eventIterator.hasNext())
		{
			Event event  = eventIterator.next();
			event.execute();
		}
	}
}
