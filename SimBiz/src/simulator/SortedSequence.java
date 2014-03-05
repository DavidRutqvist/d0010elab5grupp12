package simulator;

import java.util.ArrayList;
import java.util.NoSuchElementException;

/**
 * A sorted sequence is a sequence of Events which always is ordered. Could be ordered ascending or descending. The events are ordered by their priority.
 * @author david.rutqvist
 *
 */
public class SortedSequence {
	private ArrayList<Event> store;	//Holds the sequence
	private boolean ascending;		//Holds the sorting order
	
	/**
	 * Initializes a new sequence ordered in the specified direction.
	 * @param ascending Supply true to set order direction to ascending, otherwise (false) it will be ordered descending.
	 */
	public SortedSequence(boolean ascending)
	{
		this.ascending = ascending;
	}
	
	/**
	 * Inserts the event at the correct position and pushes all other events to the right (updates the order of the sequence).
	 * @param ev Event to be inserted in sequence.
	 */
	public void insertAndPush(Event ev)
	{
		//This algorithm supposes that the store is always ordered (which it will always be since it is declared private)
		for(int i = 0; i < this.store.size(); i++)//Loops trough the sequence until the correct position is found (then calls break)
		{
			boolean AtRightIndex = false;//Used since different sorting orders requires different operators
			
			if(this.ascending)
			{
				AtRightIndex = (this.store.get(i).getPriority() > ev.getPriority());//Orders in ASCENDING order
			}
			else
			{
				AtRightIndex = (this.store.get(i).getPriority() < ev.getPriority());//Orders in DESCENDING order
			}
			
			if(AtRightIndex)//If the correct position was found
			{
				this.store.add(i, ev);//Add at correct position
				break;//Breaks the loop since there is no need to continue iterating over the following events
			}
			else if((i + 1) == this.store.size())//If we have iterated over the whole sequence and not found a correct position, insert at the end.
			{
				this.store.add(ev);//Adds event at the end of the list/sequence
			}
		}
	}

	/**
	 * Gets the first event in the ordered sequence
	 * @return Returns the first Event
	 */
	public Event getFirst()
	{
		//Only return if there exists an event
		if(this.size() > 0)
		{
			return this.store.get(0);
		}
		else
		{
			throw new NoSuchElementException();
		}
	}
	
	/**
	 * Removes the first element from sequence
	 */
	public void removeFirst()
	{
		//Only remove if there exists an event
		if(this.size() > 0)
		{
			this.store.remove(0);
		}
		else
		{
			throw new NoSuchElementException();
		}
	}
	
	/**
	 * Gets the size of the sequence (i.e. number of events in queue)
	 * @return returns the size (integer) of the sequence
	 */
	public int size() {
		return this.store.size();//Just call size of store
	}
}
