package simulator;

import java.util.ArrayList;
import java.util.NoSuchElementException;

public class SortedSequence {
	private ArrayList<Event> store;
	private boolean ascending;
	
	public SortedSequence(boolean ascending)
	{
		this.ascending = ascending;
	}
	
	public void insertAndPush(Event ev)
	{
		//This algorithm supposes that the store is always ordered (which it will always be since it is declared private)
		for(int i = 0; i < this.store.size(); i++)
		{
			boolean AtRightIndex = false;
			
			if(this.ascending)
			{
				AtRightIndex = (this.store.get(i).getPriority() > ev.getPriority());//Orders in ASCENDING order
			}
			else
			{
				AtRightIndex = (this.store.get(i).getPriority() < ev.getPriority());//Orders in DESCENDING order
			}
			
			if(AtRightIndex)
			{
				this.store.add(i, ev);
				break;
			}
		}
	}

	public Event getFirst()
	{
		if(this.size() > 0)
		{
			return this.store.get(0);
		}
		else
		{
			throw new NoSuchElementException();
		}
	}
	
	public void removeFirst()
	{
		if(this.size() > 0)
		{
			this.store.remove(0);
		}
		else
		{
			throw new NoSuchElementException();
		}
	}
	
	public int size() {
		return this.store.size();
	}
}
