package graph;

/**
 * This method implements predicate and can be chained with any
 * other predicate implementing classes it takes two predicate objects
 * and when something is tested returns its predicates result in logical OR form 
 * @author Arturo Muller
 *
 * @param <T>
 */
public class PredicateOr<T> implements Predicate<T>{
	
	Predicate<T> a;
	Predicate<T> b;
	
	/**
	 * Class Constructor Takes any predicate object
	 * @param a any predicate object
	 * @param b any predicate object
	 */
	public PredicateOr(Predicate<T> a, Predicate<T> b){
		if(a != null && b != null){
			this.a = a;
			this.b = b;
			}
			else{
				throw new NullPointerException("Predicate cannot be null");
			}
	}
	
	/**
	 * returns logical OR of the item inserted and its test result from
	 * the predicates
	 * @param item any item of type T
	 * @return boolean
	 */
	@Override
	public boolean accept(T item) {
		return a.accept(item) || b.accept(item);
	}
	
	
}