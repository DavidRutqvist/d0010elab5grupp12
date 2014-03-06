package carWashSimulator;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

public class FIFO implements Queue<Car> {
	private int maximum;
	private LinkedList<Car> list = new LinkedList<Car>();
	
	public FIFO(int maximum){
		this.maximum = maximum;
	}
	
	public boolean addAll(Collection<? extends Car> c) {
		if((c.size() + list.size()) > maximum || list.isEmpty()){
			return false;
		}
		list.addAll(c);
		return true;
	}

	public void clear() {
		list.clear();
	}

	public boolean contains(Object e) {
		if (list.contains(e)){
			return true;
		}
		return false;
	}

	public boolean containsAll(Collection<?> c) {
		throw new UnsupportedOperationException("Unsupported operation.");
	}

	public boolean isEmpty() {
		return list.size() == 0;
	}

	public Iterator<Car> iterator() {
		return list.listIterator();
	}

	public boolean remove(Object o) {
		if(!(list.isEmpty())){
			list.remove(o);
			return true;
		}
		return false;
	}

	public boolean removeAll(Collection<?> c) {
		throw new UnsupportedOperationException("Unsupported operation.");
	}

	public boolean retainAll(Collection<?> c) {
		throw new UnsupportedOperationException("Unsupported operation.");
	}

	public int size() {
		return list.size();
	}
	
	public Object[] toArray() {
		return list.toArray();
	}

	public <T> T[] toArray(T[] t) {
		return list.toArray(t);
	}

	public boolean add(Car c) {
		if(list.add(c)){
			return true;
		}
		return false;
	}

	public Car element() {
		return list.isEmpty() ? null:list.element();
	}
	
	public boolean offer(Car c) {
		if (list.size() > maximum || list.isEmpty()){
			return false;
		}
		list.add(c);
		return true;
	}

	public Car peek() {
		return list.isEmpty() ? null:list.peek();
	}

	public Car poll() {
		return list.isEmpty() ? null:list.poll();
	}

	public Car remove() {
		return list.isEmpty() ? null:list.remove();
	}

}
