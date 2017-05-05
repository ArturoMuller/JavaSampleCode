package graph;

/**
 * This method implements predicate and can be chained with any
 * other predicate implementing classes it takes two predicate objects
 * and when something is tested returns its predicates result in logical AND form  
 * @author Arturo Muller
 *
 * @param <T>
 */
public class PredicateAnd<T> implements Predicate<T>{
	
	Predicate<T> a;
	Predicate<T> b;
	
	/**
	 * Class Constructor takes two predicate objects and in
	 * @param a
	 * @param b
	 */
	public PredicateAnd(Predicate<T> a, Predicate<T> b){
		this.a = a;
		this.b = b;
	}
	
	/**
	 * returns logical AND of the item inserted and its test result from
	 * the predicates
	 * @param item any item of type T
	 * @return boolean
	 */
	@Override
	public boolean accept(T item) {
		return a.accept(item) & b.accept(item);
	}
	
	
}
