package LeetCodeList;
import java.util.PriorityQueue;
import java.util.Comparator;


public class Solution {
    /**
     * @param head: The head of linked list.
     * @return: You should return the head of the sorted linked list,
                    using constant space complexity.
     */
    public static ListNode sortList(ListNode head) {
        
        int countNodes = 1;
        ListNode current = head;
        while(current.next != null){

        current = current.next;
        
        countNodes++;
        if(current.next == null){
        System.out.println("null");
        break;
        }
        }
        
        
        

        
        
        PriorityQueue<ListNode> sorter = new PriorityQueue<ListNode>(countNodes, new Comparator<ListNode>() {
			@Override
			public int compare(ListNode v1, ListNode v2) {
				if(v1.val < v2.val){
				    return -1;
				}
				else if(v1.val > v2.val){
				    return 1;
				}
				else{
				    return 0;
				}
			}
		});
        
        
        current = head;
        for(int i = 0; i < countNodes; i++){
        sorter.add(current);
        current = current.next;
        }
        
        head = sorter.poll();
        current = head;
        while(!sorter.isEmpty()){
            current.next = sorter.poll();
            current = current.next;
        }
        current.next = null;
        return head;
   }
}
