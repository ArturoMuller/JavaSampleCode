package Chapter8;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class testmergesortedlist {
	node headA;
	node headB;
	@Before
	public void setup(){
		this.headA = new node(2);
		headA.next = new node(5);
		headA.next.next = new node(7);
		
		this.headB = new node(3);
		headB.next = new node(11);
	}
	@Test
	public void test() {
		mergesortedlist a = new mergesortedlist();
		node sorted = a.mergelist(headB, headA);
		assertEquals(sorted.value, 2);
		sorted = sorted.next;
		assertEquals(sorted.value, 3);
		sorted = sorted.next;
		assertEquals(sorted.value, 5);
		sorted = sorted.next;
		assertEquals(sorted.value, 7);
		sorted = sorted.next;
		assertEquals(sorted.value, 11);
	}

}
