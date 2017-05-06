package graph;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class EdgeTest {
	Edge<String> edgeHomeToSchool;
	Edge<String> edgeSchoolToHome;
	Edge<String> edgeHometoBar;
	Edge<String> edgeBarToPark;
	Edge<String> edgeParkToBar;
	
	@Before
	public void setup() {
		Node<String> a = new Node<String>("My House");
		Node<String> b = new Node<String>("School");
		Node<String> f = new Node<String>("Bar");
		Node<String> g = new Node<String>("Park");

		edgeHomeToSchool = new Edge(5, a, b);
		edgeSchoolToHome =  new Edge(6, b, a);
		edgeHometoBar = new Edge(4, a, f);
	    edgeBarToPark = new Edge(7, f, g);
		edgeParkToBar = new Edge(4, g, f);
	}

	@Test
	public void equalsTest() {
		assertEquals(edgeHomeToSchool,edgeSchoolToHome);
		assertNotEquals(edgeHomeToSchool,edgeBarToPark );
	}
	
	@Test
	public void hashCodeTest() {
		assertEquals(edgeHomeToSchool.hashCode(),edgeSchoolToHome.hashCode());
	    assertNotEquals(edgeHomeToSchool.hashCode(),edgeBarToPark.hashCode());
	}

}
