package carWashSimulator;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

/**
 * A first in first in first out queue consisting of Car objects.
 * @author Emil
 *
 */
public class FIFO implements Queue<Car> {
	private int maximum;
	private LinkedList<Car> list = new LinkedList<Car>();
	
	/**
	 * Constructor.
	 * @param maximum sets maximum size for the queue.
	 */
	public FIFO(int maximum){
		this.maximum = maximum;
	}
	
	/**
	 * Adds a collection to queue as long as it won't make the queue exceed max size.
	 * Otherwise returns false.
	 */
	public boolean addAll(Collection<? extends Car> c) {
		if((c.size() + list.size()) > maximum || list.isEmpty()){
			return false;
		}
		list.addAll(c);
		return true;
	}

	/**
	 * Clears the queue.
	 */
	public void clear() {
		list.clear();
	}

	/**
	 * Returns true if the queue contains the Object e, otherwise false.
	 */
	public boolean contains(Object e) {
		if (list.contains(e)){
			return true;
		}
		return false;
	}

	/**
	 * Unsupported operation, throws UnsupportedOperationException.
	 */
	public boolean containsAll(Collection<?> c) {
		throw new UnsupportedOperationException("Unsupported operation.");
	}

	/**
	 * Returns true if queue is empty.
	 */
	public boolean isEmpty() {
		return list.size() == 0;
	}
	
	/**
	 * Checks if queue is full.
	 * @return true if queue is full.
	 */
	public boolean isFull() {
		return list.size() == maximum;
	}

	/**
	 * Returns an iterator of the queue.
	 */
	public Iterator<Car> iterator() {
		return list.listIterator();
	}

	/**
	 * Removes Object from queue or returns false if it did not exist in queue.
	 */
	public boolean remove(Object o) {
		if(!(list.contains(o))){
			list.remove(o);
			return true;
		}
		return false;
	}

	/**
	 * Unsupported operation, throws UnsupportedOperationException.
	 */
	public boolean removeAll(Collection<?> c) {
		throw new UnsupportedOperationException("Unsupported operation.");
	}

	/**
	 * Unsupported operation, throws UnsupportedOperationException.
	 */
	public boolean retainAll(Collection<?> c) {
		throw new UnsupportedOperationException("Unsupported operation.");
	}

	/**
	 * Returns an integer with the current size of the queue.
	 */
	public int size() {
		return list.size();
	}
	
	/**
	 * Returns an Array of the queue.
	 */
	public Object[] toArray() {
		return list.toArray();
	}

	/**
	 * Returns an Array of type T of the queue.
	 */
	public <T> T[] toArray(T[] t) {
		return list.toArray(t);
	}

	/**
	 * Adds a Car to the queue.
	 */
	public boolean add(Car c) {
		if (isFull() || list.isEmpty()){
			return false;
		}
		list.add(c);
		return true;
	}

	/**
	 * Returns the first element of the queue, or null if it is empty.
	 */
	public Car element() {
		return list.isEmpty() ? null:list.element();
	}
	
	/**
	 * Offers a Car to add to the queue. Returns false if queue is empty.
	 */
	public boolean offer(Car c) {
		if (isFull() || list.isEmpty()){
			return false;
		}
		list.offer(c);
		return true;
	}

	/**
	 * Returns the next element in the queue. Returns false if queue is empty.
	 */
	public Car peek() {
		return list.isEmpty() ? null:list.peek();
	}

	/**
	 * Returns the first element in the queue and the removes it. Returns false if queue is empty.
	 */
	public Car poll() {
		return list.isEmpty() ? null:list.poll();
	}

	/**
	 * If the list is not empty, removes the first element of the queue. Otherwise returns false.
	 */
	public Car remove() {
		return list.isEmpty() ? null:list.remove();
	}

}
