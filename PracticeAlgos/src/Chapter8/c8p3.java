package Chapter8;

import java.util.Iterator;

public class c8p3 {
	
			public static node hasCycle(node head){
				NodeIterator slow  = new NodeIterator(head);
				NodeIterator fast = new NodeIterator(head);

				while(fast.hasNext() && slow.hasNext()){
					node slowNode = slow.next();
					node fastNode = fast.next();
					
							fastNode = fast.next();
						if(slowNode == fastNode){
							int cycleLen = 0;
							do{
								++cycleLen;
								fastNode = fast.next();
								
							}while(slowNode != fastNode);
							
							node cycleLenAdvItr = head;
							
							while(cycleLen-- > 0){
								cycleLenAdvItr = cycleLenAdvItr.next;
							}
							node iter = head;
							
							while(iter != cycleLenAdvItr){
								iter = iter.next;
								cycleLenAdvItr = cycleLenAdvItr.next;
							}
							return iter;
							
							
							
							
							
							
							
						}
					}
				return null;
			}
			
			private static void check(node L, node expectedValue) {
			    node computedValue = hasCycle(L);
			    if (computedValue == null && expectedValue != null
			        || computedValue != null && !computedValue.equals(expectedValue)) {
			      System.err.println("Your program failed on input " + L);
			      System.err.println("Expected " + expectedValue);
			      System.err.println("Your value " + computedValue);
			      System.exit(-1);
			    }
			  }

			  private static void simpleTest() {
			    node<Integer> l0 = new node<>(42, null);
			    l0.next = l0;
			    check(l0, l0);

			    node<Integer> l1 = new node<>(42, null);
			    node<Integer> l2 = new node<>(42, null);
			    l1.next = l2;
			    l2.next = l1;
			    check(l1, l1);
			    check(l2, l2);

			    l2.next = null;
			    check(l1, null);
			    check(l2, null);
			    System.out.println("All tests passed!");
			  }

			  public static void main(String[] args) {
			    simpleTest();
			    node<Integer> l3 = new node<>(3, null);
			    node<Integer> l2 = new node<>(2, l3);
			    node<Integer> l1 = new node<>(1, l2);

			    // should output "l1 does not have cycle."
			    check(l1, null);
			    // make it a cycle
			    l3.next = l2;
			    check(l1, l2);
			    System.out.println("You passed all tests.");
			  }
}
