package simulator;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * An EventQueue is an ordered and iterable queue of events to be executed.
 * @author david.rutqvist
 *
 */
public class EventQueue implements Iterable<Event> {
	private SortedSequence store;//An ordered sequence of events, orders itself automatically when events are inserted trough insertAndPush(Event event)
	
	/**
	 * Initializes a new EventQueue which lets you iterate over a sequence of events ordered by priority.
	 */
	public EventQueue()
	{
		this.store = new SortedSequence(true);//Initialize store
	}
	
	/**
	 * Lets you peek which element is the next one without using an iterator
	 * @return returns the next element without creating a new iterator
	 */
	public Event peek() {//This is not used but it was specified in the design review and should probably remain to pass the assignment
		return this.store.getFirst();//Return first event in sequence without 
	}
	
	/**
	 * Adds event to queue ordered by priority
	 * @param event Event to be inserted
	 */
	public void add(Event event)
	{
		this.store.insertAndPush(event);//Events should be inserted trough this method (and it is in fact the only way to do it) to remain ordered.
	}
	
	/**
	 * Gets an iterator which lets you iterate over the queue
	 */
	public Iterator<Event> iterator() {
		return new EventQueueIterator(this.store);
	}

	/**
	 * An EventQueueIterator allows easy access to the EventQueue in the defined order. This is not an usual iterator since it is allowed (and supposed) for events to add events to the queue while running the simulation.
	 * @author david.rutqvist
	 *
	 */
	private class EventQueueIterator implements Iterator<Event>
	{
		private SortedSequence store;	//Store passed from EventQueue
		
		/**
		 * Creates a new iterator
		 * @param store A SortedSequence used to store events in
		 */
		public EventQueueIterator(SortedSequence store)
		{
			this.store = store;//Store in variable
		}
		
		/**
		 * Checks whether there are events left in queue. <b>Note</b> since this is not an ordinary iterator an event is removed after next() is run because of this this method returns true as long as there are events in queue (i.e. size > 0).
		 */
		public boolean hasNext() {
			return this.store.size() > 0;
		}

		/**
		 * Gets the next event in queue. <b>Note</b> remove() should be called after t
		 */
		public Event next() {
			if(this.hasNext())
			{
				Event event = this.store.getFirst();
				this.store.removeFirst();
				return event;
			}
			else
			{
				throw new NoSuchElementException();
			}
		}

		/**
		 * Removes the first element in queue without executing it
		 */
		public void remove() {//Since this is not an ordinary iterator this is not used
			if(this.hasNext())
			{
				this.store.removeFirst();
			}
			else
			{
				throw new NoSuchElementException();
			}
		}
	}
}
