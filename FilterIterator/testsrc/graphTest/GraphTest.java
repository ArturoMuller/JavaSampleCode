package graphTest;

import static org.junit.Assert.*;
import java.util.Iterator;
import java.util.NoSuchElementException;
import org.junit.Before;
import org.junit.Test;
import graph.Graph;
import graph.Node;

public class GraphTest {
	Graph<String> p;
	Node<String> r, s, t, u, v, w, x, y;
	
	
	@SuppressWarnings("unchecked")
	public void addNodes(Graph<String> g, Node<String>... nodes){
		for(Node<String> n: nodes){
			g.addNode(n);
		}
	}
	
	@SuppressWarnings("unchecked")
	@Before
	public void setup(){		
		s = new Node<String>("s");
		r = new Node<String>("r");
		t = new Node<String>("t");
		u = new Node<String>("u");
		v = new Node<String>("v");
		w = new Node<String>("w");
		x = new Node<String>("x");
		y = new Node<String>("y");
		p = new Graph<String>(s);
		addNodes(p, r, t, u, v, w, x, y);	
		p.addEdge(s, w, 20);
		p.addEdge(s, r, 15);
		p.addEdge(w, t,  5);
		p.addEdge(w, x, 10);
		p.addEdge(t, u, 5);
		p.addEdge(t, x, 10);
		p.addEdge(x, u, 10);
		p.addEdge(x, y, 10);
		p.addEdge(u, y, 10);
		p.addEdge(r, v, 10);
	}
	
	@Test(expected = NullPointerException.class)
	public void nullRootNodeTest(){
		Graph<String> y = new Graph<String>(null);
	}
	
	@Test(expected = NullPointerException.class)
	public void addNullNodeTest(){
		p.addNode(null);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void existingNodeTest(){
		p.addNode(y);
	}
	
	@Test
	public void hasNodeTest(){
		assertFalse(p.hasNode(new Node<String> ("S")));
		assertTrue(p.hasNode(t));
	}
	
	@Test
	public void removeNodeTest(){
		assertTrue(p.hasNode(t));
		assertTrue(u.hasNode(t));
		p.removeNode(t);
		assertFalse(p.hasNode(t));
		assertFalse(u.hasNode(t));
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void removeNodeNotInGraphTest(){
		p.removeNode(new Node<String>("S"));
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void removeRootNodeTest(){
		p.removeNode(s);
	}
	
	@Test
	public void addEdge(){
		assertFalse(s.hasNode(t));
		assertFalse(t.hasNode(s));
		p.addEdge(s, t, 3);
		assertTrue(s.hasNode(t));
		assertTrue(t.hasNode(s));
	}
	
	@Test(expected = NoSuchElementException.class)
	public void addNullSourceEdgeTest(){
		p.addEdge(null, t, 3);
	}
	
	
	@Test(expected = NoSuchElementException.class)
	public void addNodeNotinGraphTest(){
		p.addEdge(s,null,3);
	}
	
	@Test
	public void removeEdgeTest(){
		assertTrue(w.hasNode(t));
		assertTrue(t.hasNode(w));
		assertTrue(u.hasNode(t));
		assertTrue(t.hasNode(u));
		assertTrue(x.hasNode(t));
		assertTrue(t.hasNode(x));
		p.removeEdge(t, w);
		assertFalse(w.hasNode(t));
		assertFalse(t.hasNode(w));
		assertTrue(u.hasNode(t));
		assertTrue(t.hasNode(u));
		assertTrue(x.hasNode(t));
		assertTrue(t.hasNode(x));
	}
	
	@Test(expected = NoSuchElementException.class)
	public void removeDestinationEdgeNotinGreaphTest(){
		p.removeEdge(new Node<String>("s"), t);
	}
	
	@Test(expected = NoSuchElementException.class)
	public void removeSourceEdgeNotinGreaphTest(){
		p.removeEdge(t, new Node<String>("s"));
	}
	
	@Test
	public void getEdgeWeightTest(){
		assertEquals(p.getEdgeWeight(w, x) , new Integer(10));
	}
	
	
	@Test(expected = NoSuchElementException.class)
	public void getNonExistentEdgeWeightTest(){
		p.getEdgeWeight(t, s);
	}
	
	@Test
	public void BFSIteratorTest() {
		Iterator<String> itr = p.BFSIterator();
		assertEquals("s", itr.next());
		assertEquals("w", itr.next());
		assertEquals("r", itr.next());
		assertEquals("t", itr.next());
		assertEquals("x", itr.next());
		assertEquals("v", itr.next());
		assertEquals("u", itr.next());
		assertEquals("y", itr.next());
		assertFalse(itr.hasNext());
	}
	
	@Test(expected = NoSuchElementException.class)
	public void BFSIteratorTooMuchTest() {
		Iterator<String> itr = p.BFSIterator();
		assertEquals("s", itr.next());
		assertEquals("w", itr.next());
		assertEquals("r", itr.next());
		assertEquals("t", itr.next());
		assertEquals("x", itr.next());
		assertEquals("v", itr.next());
		assertEquals("u", itr.next());
		assertEquals("y", itr.next());
		itr.next();
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void DFSIteratorTest(){
		Node<String> a = new Node<String>("a");
		addNodes(p,a);
		Iterator<String> itr = p.DFSIterator();
		assertEquals("y", itr.next());
		assertEquals("x", itr.next());
		assertEquals("u", itr.next());
		assertEquals("t", itr.next());
		assertEquals("w", itr.next());
		assertEquals("v", itr.next());
		assertEquals("r", itr.next());
		assertEquals("s", itr.next());
		assertTrue(itr.hasNext());
		assertEquals("a", itr.next());
		assertFalse(itr.hasNext());
	}

	
	@SuppressWarnings("unchecked")
	@Test
	public void DFSIteratorNodeFromListTest(){
		Node<String> a = new Node<String>("a");
		addNodes(p,a);
		Iterator<String> itr = p.DFSIterator();
		assertEquals("y", itr.next());
		assertEquals("x", itr.next());
		assertEquals("u", itr.next());
		assertEquals("t", itr.next());
		assertEquals("w", itr.next());
		assertEquals("v", itr.next());
		assertEquals("r", itr.next());
		assertEquals("s", itr.next());
		assertEquals("a", itr.next());
		assertFalse(itr.hasNext());
	}
	
	@SuppressWarnings("unchecked")
	@Test(expected = NoSuchElementException.class)
	public void DFSIteratorNoNodeLeftTest(){
		Node<String> a = new Node<String>("a");
		addNodes(p,a);
		Iterator<String> itr = p.DFSIterator();
		assertEquals("y", itr.next());
		assertEquals("x", itr.next());
		assertEquals("u", itr.next());
		assertEquals("t", itr.next());
		assertEquals("w", itr.next());
		assertEquals("v", itr.next());
		assertEquals("r", itr.next());
		assertEquals("s", itr.next());
		assertEquals("a", itr.next());
		itr.next();
	}
}
