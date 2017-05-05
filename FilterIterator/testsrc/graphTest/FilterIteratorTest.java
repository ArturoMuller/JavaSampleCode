package graphTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import java.util.Iterator;
import org.junit.Before;
import org.junit.Test;
import java.util.NoSuchElementException;
import graph.FilterIterator;
import graph.Graph;
import graph.Node;
import graph.Predicate;
import graph.PredicateAnd;
import graph.PredicateNot;
import graph.PredicateOr;

public class FilterIteratorTest {
	Graph<String> p;
	Node<String> r, s, t, u, v, w, x, y, z;

	Predicate<String> lessThanEight = new Predicate<String>() {
		@Override
		public boolean accept(String city) {
			return city.length() < 8;
		}
	};
	Predicate<String> containsE = new Predicate<String>() {
		@Override
		public boolean accept(String city) {
			return city.contains("e");
		}
	};

	Predicate<String> isRaleigh = new Predicate<String>() {
		@Override
		public boolean accept(String city) {
			return city.equals("raleigh");
		}
	};

	@SuppressWarnings("unchecked")
	public void addNodes(Graph<String> g, Node<String>... nodes) {
		for (Node<String> n : nodes) {
			g.addNode(n);
		}
	}

	@SuppressWarnings("unchecked")
	@Before
	public void setup() {
		s = new Node<String>("sarasota");
		r = new Node<String>("raleigh");
		t = new Node<String>("toledo");
		u = new Node<String>("usuahia");
		v = new Node<String>("venice");
		w = new Node<String>("washington d.c.");
		x = new Node<String>("xalapa");
		y = new Node<String>("yokohama");
		z = new Node<String>("zagreb");
		p = new Graph<String>(s);
		addNodes(p, r, t, u, v, w, x, y, z);
		p.addEdge(s, w, 20);
		p.addEdge(s, r, 15);
		p.addEdge(w, t, 5);
		p.addEdge(w, x, 10);
		p.addEdge(t, u, 5);
		p.addEdge(t, x, 10);
		p.addEdge(x, u, 10);
		p.addEdge(x, y, 10);
		p.addEdge(u, y, 10);
		p.addEdge(r, v, 10);
	}
	
	@Test //to complete code coverage
	public void twoHasNextTest(){
		Iterator<String> onePred = new FilterIterator<String>(p.BFSIterator(), lessThanEight);
		assertTrue(onePred.hasNext());
		assertTrue(onePred.hasNext());
	}
	
	@Test(expected = NoSuchElementException.class)
	public void nothingLeftTest(){
		Iterator<String> onePred = new FilterIterator<String>(p.BFSIterator(), lessThanEight);
		while(onePred.hasNext()){
			onePred.next();
		}
		onePred.next();
	}
	
	
	@Test
	public void onePredicateBFSTest() {
		Iterator<String> onePred = new FilterIterator<String>(p.BFSIterator(), lessThanEight);
		assertEquals("raleigh", onePred.next());
		assertEquals("toledo", onePred.next());
		assertEquals("xalapa", onePred.next());
		assertEquals("venice", onePred.next());
		assertEquals("usuahia", onePred.next());
		assertFalse(onePred.hasNext());
	}

	@Test
	public void onePredicateDFSTest() {
		Iterator<String> onePred = new FilterIterator<String>(p.DFSIterator(), lessThanEight);
		assertEquals("xalapa", onePred.next());
		assertEquals("usuahia", onePred.next());
		assertEquals("toledo", onePred.next());
		assertEquals("venice", onePred.next());
		assertEquals("raleigh", onePred.next());
		assertTrue(onePred.hasNext());
		assertEquals("zagreb", onePred.next());
		assertFalse(onePred.hasNext());
	}

	@Test
	public void AndPredicateBFSTest() {
		Iterator<String> twoAndPred = new FilterIterator<String>(p.BFSIterator(),
				new PredicateAnd<String>(containsE, lessThanEight));
		assertEquals("raleigh", twoAndPred.next());
		assertEquals("toledo", twoAndPred.next());
		assertEquals("venice", twoAndPred.next());
		assertFalse(twoAndPred.hasNext());
	}

	@Test
	public void NotAndPredicateBFSTest() {
		Iterator<String> notAndPred = new FilterIterator<String>(p.BFSIterator(),
				new PredicateNot<String>(new PredicateAnd<String>(containsE, lessThanEight)));
		assertEquals("sarasota", notAndPred.next());
		assertEquals("washington d.c.", notAndPred.next());
		assertEquals("xalapa", notAndPred.next());
		assertEquals("usuahia", notAndPred.next());
		assertEquals("yokohama", notAndPred.next());
	}

	@Test
	public void NotAndOrPredicateBFSTest() {
		Iterator<String> notAndorPred = new FilterIterator<String>(p.BFSIterator(), new PredicateOr<String>(
				new PredicateNot<String>(new PredicateAnd<String>(containsE, lessThanEight)), isRaleigh));
		assertEquals("sarasota", notAndorPred.next());
		assertEquals("washington d.c.", notAndorPred.next());
		assertEquals("raleigh", notAndorPred.next());
		assertEquals("xalapa", notAndorPred.next());
		assertEquals("usuahia", notAndorPred.next());
		assertEquals("yokohama", notAndorPred.next());
	}
}
