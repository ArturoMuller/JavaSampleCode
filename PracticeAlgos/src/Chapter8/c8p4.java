package Chapter8;

import java.util.ArrayList;
import java.util.List;

public class c8p4<T> {
	
	
	static node reverseList(node head, int startNum, int endNum){
		if(startNum == endNum){
			return head;
		}
		node tailBefore = head;
		node headAfter;
		node temp;
		int i = 1;
		for(; i < startNum - 1; i++){
			
			tailBefore = tailBefore.next;
		}
		temp = tailBefore.next;
		i++;
		List<node> reverseList = new ArrayList<node>();
		for(;i<= endNum; i++){
			reverseList.add(temp);
			temp = temp.next;
		}
		//now after all this temp is the head after
		tailBefore.addNext(reverseList.get(reverseList.size()-1));
		tailBefore = tailBefore.next;
		for(int j = reverseList.size() -2; j >= 0; j--){
			tailBefore.addNext(reverseList.get(j));
			tailBefore = tailBefore.next;
		}
		tailBefore.addNext(temp);
		return head;
	}
	
/*	static node reverseList(node head, int startNum, int endNum){

		List<node> store = new ArrayList<node>();
		node revHead = head;
		node revTail;
		node begin;
		int curr = 1;
		for(; curr <= startNum; curr++){
			if(curr == startNum - 1){
				head = revHead;
			}
			if(curr == startNum){
			store.add(revHead);
			}
			revHead = revHead.next;
		}
		revTail = revHead;
		for(;curr <= endNum; curr++){
			store.add(revTail);
			revTail = revTail.next;
		}
		
		for(int i = store.size() - 1; i >= 0; i--){
			head.addNext(store.get(i));
			head = head.next;
		}
		
		revHead.addNext(revTail);
		return head;
		
	}*/
	
	public static void printList(node head){
		while(head.next != null){
			System.out.print(head.value + "|");
			head = head.next;
			
		}
		System.out.print(head.value + "|");
		System.out.println();
	}
	
	public static void main(String [] args){
		node head = new node(11);
		head.addNext(new node(3, new node(5, new node( 7, new node(2)))));
		printList(head);
		printList(reverseList(head, 2, 4));
	}
}
