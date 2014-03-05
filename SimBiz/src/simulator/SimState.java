package simulator;

import java.util.Observable;

public class SimState extends Observable {
	protected EventQueue queue;
	
	public void setQueue(EventQueue queue)
	{
		this.queue = queue;
	}
	
	public void addEvent(Event event)
	{
		this.queue.add(event);
	}
}
