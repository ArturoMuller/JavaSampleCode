package graph;

/**
 * This method implements predicate and can be chained with any
 * other predicate implementing classes it takes any predicate object
 * and when something is tested returns its predicates result in logical NOT form 
 * @author Arturo Muller
 *
 * @param <T>
 */
public class PredicateNot<T> implements Predicate<T>{

	Predicate<T> a;
	

	/**
	 * Class Constructor Takes any predicate object
	 * @param a any predicate
	 */
	public PredicateNot(Predicate<T> a){
		this.a = a;
	}
	
	/**
	 * Negates the predicate that is being returned from this predicate
	 * @param any item of type T that is to be tested with the predicate
	 */
	@Override
	public boolean accept(T item) {
		return !a.accept(item);
	}
	
	
}
