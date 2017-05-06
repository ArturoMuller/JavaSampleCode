package graph;
/**
 * The Edge class is part of an application that creates a graph it is internal
 * to the graph package for safety it's hash code and equal are the same for a  
 * combination of the same nodes since duplicate edges can create a loop
 * The edge attribute weight is a feature that can be used in future versions.
 * 
 * @author Arturo Muller
 * @version 1.0 July 26, 2016
 * @param <T> type of the Node value that this edge connects
 */
class Edge<T> {
	private Integer weight;
	private Node<T> a;
	private Node<T> b;

	Edge(Integer w, Node<T> a, Node<T> b) {
		this.a = a;
		this.b = b;
		this.weight = w;

	}

	Integer getWeight() {
		return weight;
	}

	Node<T> getOppositeNode(Node<T> node) {
		return (node == a) ? b : a;
	}

	
	
	/**
	 * Suppress here is fine here because its checking if its an edge 
	 * before the cast to edge
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public boolean equals(Object edge) {
		if (edge instanceof Edge) {
			if (b == ((Edge) edge).getOppositeNode(a)) {
				return true;
			} else {
				return false;
			}
		} else {
			throw new IllegalArgumentException("must be a StopWatch object");
		}
	}

	@Override
	public int hashCode() {
		int result = 17;
		int c = a.hashCode() + b.hashCode();
		return 32 * c + result;
	}
}
