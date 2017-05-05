package LeetCodeList;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class SolutionTest {
	ListNode head;
	@Before
	public void initialize(){
		ListNode one = new ListNode(1);
		ListNode two = new ListNode(2);
		ListNode three = new ListNode(3);
		one.next = three;
		three.next = two;
		head = one;
	}
	@Test
	public void test() {
		ListNode test = Solution.sortList(head);
		assertEquals(test.val, 1);
		test = test.next;
		assertEquals(test.val, 2);
		test = test.next;
		assertEquals(test.val, 3);
	}

}
