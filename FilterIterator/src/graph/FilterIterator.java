package graph;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * FilterIterator class uses a Predicate to make sure the next object returned
 * always passes the predicates test conditions
 * @author Michael Schidlowsky
 *
 * @param <T>
 */
public class FilterIterator<T> implements Iterator<T> {

	private Iterator<T> iterator;
	private Predicate<T> predicate;
	private T nextItem;
	private boolean nextIsValid;
	
	/**
	 * Constructor for a FilterIterator
	 * @param iterator of any type
	 * @param predicate of the same type as the iterator returned objects
	 */
	public FilterIterator(Iterator<T> iterator, Predicate<T> predicate) {
		this.iterator = iterator;
		this.predicate = predicate;
	}
	
	/**
	 * @return boolean value if there is an object to be returned that 
	 * passes the predicate test
	 */
	@Override
	public boolean hasNext() {
		if (nextIsValid) {
			return true;
		}
		while (iterator.hasNext()) {
			nextItem = iterator.next();
			if (predicate.accept(nextItem)) {
				nextIsValid = true;
				return true;
			}
		}
		return false;
	}
	
	/**
	 * @return T type value if there is an object to be returned that passes the test
	 * @throws NoSuchElementException if nothing is left in the iterator that passes the test
	 */
	@Override 
	public T next() {
		if (nextIsValid) {
			nextIsValid = false;
			T val = nextItem;
			nextItem = null;
			return val;
		}
		if (hasNext()) {
			return next();
		}
		throw new NoSuchElementException();
	}

}