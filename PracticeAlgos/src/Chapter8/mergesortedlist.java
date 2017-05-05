package Chapter8;

public class mergesortedlist {
	node<Integer> head;
	node<Integer> current;
	
	public  node<Integer> mergelist(node<Integer> a, node<Integer> b){
		if(a.value <= b.value){
			this.head =  a;
			this.current = a;
			a = a.next;
		}
		else{
			this.head = b;
			this.current = b;
			b = b.next;
		}
		while(a != null && b != null){
			if(a.value <= b.value){
				current.next = a;
				current = a;
				a = a.next;
			}
			else{
				current.next = b;
				current = b;
				b = b.next;
			}
		}
		if(a == null){
			current.next = b;
		}
		else if(b == null){
			current.next = a;
		}
		return head;
		
	}
}
