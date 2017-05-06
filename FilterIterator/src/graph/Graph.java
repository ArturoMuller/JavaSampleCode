package graph;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;

/**
 * The Graph class uses the Node class and the Edge class to a create a Graph it
 * is public and implements has BFS and DFS method that returns an iterator of
 * the Graph in DFS order or BFS order the first node is the root node and can't
 * be removed. It is up to user to make sure that any node added is only used in
 * one Graph.
 * 
 * @author Arturo Muller
 * @version 1.0 July 26, 2016
 * @param <T>
 *            the type of the value of the nodes that will be in this graph
 */
public class Graph<T> {
	Node<T> root;
	private Set<Node<T>> nodes = new LinkedHashSet<Node<T>>();
	private Set<Edge<T>> edges = new HashSet<Edge<T>>();// for future expansion
														// of the Iterator

	/**
	 * Class constructor specifying the Node that will be the root of the Graph
	 * this Node is not removable once chosen and can't be null.
	 * 
	 * @param rootNode
	 * @throws NullPointerException
	 *             if is the parameter
	 */
	public Graph(Node<T> rootNode) {
		if (rootNode != null) {
			root = rootNode;
			nodes.add(rootNode);
		} else {
			throw new NullPointerException("Can't accept null in Graph.");
		}
	}

	/**
	 * This is how a node is added to the graph
	 * 
	 * @param node
	 * @throws NullPointerException
	 * @throws IlegalArgumentException
	 */
	public void addNode(Node<T> node) {
		if (node == null) {
			throw new NullPointerException("Can't accept null in Graph.");
		} else if (nodes.contains(node)) {
			throw new IllegalArgumentException("Node already exists in graph.");
		} else {
			nodes.add(node);
		}
	}

	/**
	 * checks if the graph contains a node
	 * 
	 * @param node
	 * @return boolean
	 */
	public boolean hasNode(Node<T> node) {
		return nodes.contains(node);
	}

	/**
	 * removes the node from the graph as well as all edges and any reference to
	 * it from any other node.
	 * 
	 * @param source
	 */
	public void removeNode(Node<T> node) {
		if (!hasNode(node)) {
			throw new IllegalArgumentException("Node not in Graph.");
		}
		if (node == root) {
			throw new IllegalArgumentException("Cannot remove the root.");
		}
		Iterator<Node<T>> itrSource = node.iterator();
		while (itrSource.hasNext()) {
			Node<T> destination = itrSource.next();
			destination.removeNode(node);
		}
		nodes.remove(node);
	}
	/**
	 * Connects two nodes with an edge and its respective weight
	 * @param source node to connect
	 * @param destination other node to connect
	 * @param edgeWeight Integer weight of the edge that connects the two nodes
	 * @throws NoSuchElementException  
	 */
	public void addEdge(Node<T> source, Node<T> destination, Integer edgeWeight) {
		if (!nodes.contains(source)) {
			throw new NoSuchElementException("Source Node not in Graph.");
		}
		if (!nodes.contains(destination)) {
			throw new NoSuchElementException("Destination Node not in Graph.");
		}
		Edge<T> currEdge = new Edge<T>(edgeWeight, source, destination);
		edges.add(currEdge);
		source.addEdge(currEdge);
		destination.addEdge(currEdge);
	}

	/**
	 * Removes the edge that connects the two nodes
	 * @param source node
	 * @param destination node
	 * @throws NoSuchElementException
	 */
	public void removeEdge(Node<T> source, Node<T> destination) {
		if (!nodes.contains(source)) {
			throw new NoSuchElementException("Source Node not in Graph.");
		}
		if (!nodes.contains(destination)) {
			throw new NoSuchElementException("Destination Node not in Graph.");
		}
		Edge<T> currEdge = source.getEdge(destination);
		destination.removeNode(source);
		source.removeNode(destination);
		edges.remove(currEdge);
	}

	
	/**
	 * Returns the Weight Integer of the edge theat connects two Nodes
	 * @param source
	 * @param destination
	 * @return Integer 
	 */
	public Integer getEdgeWeight(Node<T> source, Node<T> destination) {
		Edge<T> currEdge = source.getEdge(destination);
		return currEdge.getWeight();
	}
	
	
	/**
	 * Returns a GraphIterator in BFS order
	 * @return Iterator
	 */
	public Iterator<T> BFSIterator() {
		return new BFSIterator(root);
	}
	
	/**
	 * Returns a GraphIterator in DFS order
	 * @return Iterator
	 */
	public Iterator<T> DFSIterator() {
		return new DFSIterator(root);
	}

	
	/**
	 * Iterator for the Graph in BFS order returning the values
	 * if a Node is not connected to the source it's value will not
	 * be returned
	 * @author Arturo Muller
	 */
	private class BFSIterator implements Iterator<T> {
		private Queue<Node<T>> unexplored = new LinkedList<Node<T>>();
		private Set<Node<T>> inQueue = new HashSet<>();

		BFSIterator(Node<T> node) {
			unexplored.add(node);
			inQueue.add(node);
		}

		@Override
		public boolean hasNext() {

			return unexplored.size() > 0;
		}

		@Override
		public T next() {
			if (hasNext()) {
				Node<T> currNode = unexplored.remove();
				Iterator<Node<T>> itr = currNode.iterator();
				while (itr.hasNext()) {
					Node<T> futNode = itr.next();
					if (!inQueue.contains(futNode)) {
						unexplored.add(futNode);
						inQueue.add(futNode);
					}
				}

				return currNode.getValue();

			} else {
				throw new NoSuchElementException("No nodes left.");
			}
		}

	}

	
	/**
	 * Iterator for the Graph in DFS order returning the values when
	 * the node has no unvisited path 
	 * if a Node is not connected to the source its value will not
	 * be returned
	 * @author Arturo Muller
	 */
	private class DFSIterator implements Iterator<T> {
		private Iterator<Node<T>> graphItr = nodes.iterator();
		private Stack<Node<T>> grey = new Stack<>();
		private Stack<Node<T>> black = new Stack<>();
		private HashMap<Integer, Iterator<Node<T>>> iterators = new HashMap<>();

		DFSIterator(Node<T> node) {
			grey.push(node);
		}

		@Override
		public boolean hasNext() {
			if (grey.isEmpty()) {
				while (graphItr.hasNext()) {
					Node<T> temp = graphItr.next();
					if (!grey.contains(temp) && !black.contains(temp)) {
						grey.push(temp);
						break;
					}
				}
			}
			return grey.size() > 0;
		}

		@Override
		public T next() {
			if (grey.isEmpty()) {
				while (graphItr.hasNext()) {
					Node<T> temp = graphItr.next();
					if (!grey.contains(temp) && !black.contains(temp)) {
						grey.push(temp);
						break;
					}
				}
			}
			if (!grey.isEmpty()) {
				return DFSVisit().getValue();
			}
			throw new NoSuchElementException("No nodes left to discover.");
		}

		private Node<T> DFSVisit() {
			Node<T> u = grey.peek();
			if (!iterators.containsKey(u)) {
				iterators.put(u.hashCode(), u.iterator());
			}
			Iterator<Node<T>> nodeItr = iterators.get(u.hashCode());
			while (nodeItr.hasNext()) {
				Node<T> v = nodeItr.next();
				if (!grey.contains(v) && !black.contains(v)) {
					iterators.put(v.hashCode(), v.iterator());
					grey.push(v);
					nodeItr = iterators.get(v.hashCode());
					u = v;
				}
			}
			grey.remove(u);
			black.push(u);
			return u;
		}

	}
}
