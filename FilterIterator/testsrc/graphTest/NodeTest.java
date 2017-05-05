package graphTest;

import static org.junit.Assert.*;
import graph.*;
import java.util.Iterator;
import java.util.NoSuchElementException;
import org.junit.Before;
import org.junit.Test;


public class NodeTest {
	Graph<String> p;
	Node<String> a, b, c, d, e, f, g;
	
	
	@SuppressWarnings("unchecked")
	public void addNodes(Graph<String> g, Node<String>... nodes){
		for(Node<String> n: nodes){
			g.addNode(n);
		}
	}
	
	@SuppressWarnings("unchecked")
	@Before
	public void setup(){		
		a = new Node<String>("My House");
		b = new Node<String>("School");
		c = new Node<String>("Gym");
		d = new Node<String>("Pharmacy");
		e = new Node<String>("Supermarket");
		f = new Node<String>("Bar");
		g = new Node<String>("Park");
		
		p = new Graph<String>(a);
		
		addNodes(p, b, c, d, e, f, g);	
		p.addEdge(a, b, 20);
		p.addEdge(a, c, 15);
		p.addEdge(a, d,  5);
		p.addEdge(a, f, 10);
		p.addEdge(b, c, 5);
		p.addEdge(b, e, 10);
	}
	
	@Test
	public void GetValueTest(){
		assertEquals(b.getValue(), "School");
		assertEquals(c.getValue(), "Gym");
	}
	
	@Test(expected = NullPointerException.class)
	public void nullValueTest(){
		Node<String> z = new Node<String>(null);
	}
	
	@Test
	public void SetValueTest(){
		assertEquals(b.getValue(), "School");
		b.setValue("Zoo");
		assertEquals(b.getValue(), "Zoo");
	}
	

	@Test
	public void HasNodeTest(){
		assertTrue(b.hasNode(c));
		assertTrue(b.hasNode(e));
		assertFalse(b.hasNode(d));
		assertTrue(a.hasNode(b));	
	}
	
	@Test
	public void RemoveNodeTest(){
		assertTrue(b.hasNode(c));
		b.removeNode(c);
		assertFalse(b.hasNode(c));	
	}
	
	@Test(expected = NoSuchElementException.class)
	public void RemoveNodeNonAdjacentTest(){
		g = new Node<String>("Park");
		b.removeNode(g);	
	}
	
	@Test
	public void NodeIteratorTest() {
			Iterator<Node<String>> test = a.iterator();
			assertTrue(test.hasNext());
			assertEquals(test.next(), b);
			assertTrue(test.hasNext());
			assertEquals(test.next(), c);
			assertTrue(test.hasNext());
			assertEquals(test.next(), d);
			assertTrue(test.hasNext());
			assertEquals(test.next(), f);
			assertFalse(test.hasNext());
	}
	
	
	@Test
	public void NodeIteratorRemoveTest() {
			Iterator<Node<String>> test = a.iterator();
			assertEquals(test.next(), b);
			assertEquals(test.next(), c);
			assertEquals(test.next(), d);
			assertEquals(test.next(), f);
			test.remove();
			assertFalse(a.hasNode(f));
			
	}
	
	

}
