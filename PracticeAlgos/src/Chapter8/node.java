package Chapter8;


public class node<T>{
	T value;
	node<T> next = null;
	
	node(T value){
		this.value = value;
	}
	
	node(T value, node<T> next){
		this.value = value;
		this.next = next;
	}
	
	public void addNext(node<T> next){
		this.next = next;
	}



}
