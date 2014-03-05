package simulator;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class EventQueue implements Iterable<Event> {
	private SortedSequence store;
	
	public EventQueue()
	{
		this.store = new SortedSequence(true);
	}
	
	public Event peek() {
		return this.store.getFirst();
	}
	
	public void add(Event event)
	{
		this.store.insertAndPush(event);
	}
	
	public Iterator<Event> iterator() {
		return new EventQueueIterator();
	}

	private class EventQueueIterator implements Iterator<Event>
	{
		public boolean hasNext() {
			return this.store.size() > 0;
		}

		public Event next() {
			if(this.hasNext())
			{
				return this.store.getFirst();
			}
			else
			{
				throw new NoSuchElementException();
			}
		}

		public void remove() {
			if(this.hasNext())
			{
				return this.store.removeFirst();
			}
			else
			{
				throw new NoSuchElementException();
			}
		}
	}
}
