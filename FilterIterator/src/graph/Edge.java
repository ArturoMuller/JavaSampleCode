package graph;

/**
 * The Edge class is part of an application that creates a graph
 * it is internal to the graph package for safety
 * Edge attribute weight is a feature that can be used in future versions.  
 * 
 * @author Arturo Muller
 * @version 1.0 July 26, 2016
 * @param <T> type of the Node that this edge connects
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
}
