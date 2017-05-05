package graph;

import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.NoSuchElementException;
import java.util.Set;

/**
 * The Node class is part of an application that creates a graph
 * it is package protected and includes the value and edges of a node
 * the nodes value is generic.    
 * 
 * @author Arturo Muller
 * @version 1.0 July 26, 2016
 * @param <T> type of the Node value of this node
 */
public class Node<T> implements Iterable<Node<T>>{
	private T value;
	private Set<Edge<T>> adjList;
	
	
	
	/**
	 * Constructs and initializes a node with any value
	 * @param value to set the node
	 */
	public Node(T val) {
		setValue(val);
		adjList = new LinkedHashSet<Edge<T>>();
	}
	
	/**
	 * Returns the value of the Node
	 * @return value of the node
	 */
	public T getValue() {
		return value;
	}
	
	/**
	 * Changes the current value of the node to a new value
	 * @param value to set the node
	 */
	public void setValue(T val) {
		if(val != null){
		this.value = val;
		}
		else{
			throw new NullPointerException("Value cannot be null.");
		}
		
	}
	

	void addEdge(Edge<T> e){
		adjList.add(e);
	}
	
	void removeEdge(Edge<T> e){
		adjList.remove(e);
	}
	
	boolean hasEdge(Edge<T> e){
		return adjList.contains(e);
	}
	
    Edge<T> getEdge(Node<T> destination) {
		for(Edge<T> e: adjList){
			if(destination == e.getOppositeNode(this)){
				return e;
			}
		}
		throw new NoSuchElementException("Such an Edge does not Exist");
	}

	/**
	 * 
	 * @param node that connects to this node 
	 */
	public void removeNode(Node<T> node){
		for(Edge<T> e: adjList){
			if(node == e.getOppositeNode(this)){
				removeEdge(e);
				return;
			}
		}
		throw new NoSuchElementException("This Node is not adjacent.");
	}
	
	public boolean hasNode(Node<T> destination){
		for(Edge<T> e: adjList){
			if(destination == e.getOppositeNode(this)){
				return true;
			}
		}
		return false;
	}
	/**
	 * @ return new NodeIterator inner class 
	 */
	@Override
	public Iterator<Node<T>> iterator() {
		return new NodeIterator() ;
	}

	/**
	 * Node Iterator class can only be instantiated from a Node
	 * implements the Iterator method for this nodes adjacency list
	 *
	 */
	private class NodeIterator implements Iterator<Node<T>>{
		Iterator<Edge<T>> itr = adjList.iterator();

		@Override
		public boolean hasNext() {
			return itr.hasNext();
		}

		@Override
		public Node<T> next() {
			Edge<T> e =  itr.next();
			Node<T> r = e.getOppositeNode(Node.this);
			return r;
		}
		
		@Override
		public void remove(){
			itr.remove();
		}
	}




	
}