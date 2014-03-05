package carWashSimulator;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

public class FIFO implements Queue {
	private int maximum;
	private LinkedList<Car> list = new LinkedList<Car>();
	
	public FIFO(int maximum){
		this.maximum = maximum;
	}
	
	public boolean addAll(Collection c) {
		if((c.size() + list.size()) > maximum){
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

	@Override
	public boolean containsAll(Collection arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return false;
	}

	public Iterator<Car> iterator() {
		return list.listIterator();
	}

	@Override
	public boolean remove(Object arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean removeAll(Collection arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean retainAll(Collection arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Object[] toArray() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object[] toArray(Object[] arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean add(Object e) {
		if(list.add((Car)e)){
			return true;
		}
		return false;
	}

	public Object element() {
		return list.element();
	}
	
	// TODO: Fix typecasting.
	public boolean offer(Object c) {
		if (list.size() > maximum){
			return false;
		}
		list.add((Car) c);
		return true;
	}

	public Object peek() {
		return list.peek();
	}

	public Object poll() {
		return list.poll();
	}

	public Object remove() {
		return list.remove();
	}

}
