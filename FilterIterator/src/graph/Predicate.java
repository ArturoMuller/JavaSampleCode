package graph;

public interface Predicate<T> {
	/**
	 * this method can take any object or class that has an accept method
	 * to be used with the FilterIterator
	 * for example you could insert the following Predicate Object:	
	 * Predicate<String> lessThanEight = new Predicate<String>() {
	 *	@Override
	 *	public boolean accept(String city) {
	 *		return city.length() < 8;
	 *	}};
	 * into the filter iterator
	 * @param item
	 * @return boolean
	 */
	boolean accept(T item);
}
