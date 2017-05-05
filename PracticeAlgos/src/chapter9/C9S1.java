package chapter9;

import java.util.Deque;
import java.util.EmptyStackException;
import java.util.LinkedList;

public class C9S1<T extends Comparable<T>> {
	Deque<num> stack;

	C9S1() {
		stack = new LinkedList<num>();
	}

	public void push(T var){
		num current;
		if(stack.isEmpty()){
			current = new num(var, var);
		}
		else{
			if(var.compareTo(stack.peek().max) >= 0){
					current = new num(var,var);
			}
			else {
					current = new num(var, stack.peek().max);
			}
			}
		stack.push(current);
	}

	public T pop(){
		return stack.pop().num;
	}
	
	public T max(){
		if(stack.isEmpty()){
			throw new EmptyStackException();
		}
		return stack.peek().max;
	}

	public class num {
		public T num;
		public T max;

		num(T num, T max) {
			this.num = num;
			this.max = max;
		}

	}
	
	
	//@exclude
	
	  public static void check(boolean condition, String msg) {
		    if (!condition) {
		      System.err.println(msg);
		      System.exit(-1);
		    }
		  }

		  public static void missedMaxException() {
		    System.err.println("Should have seen an exception, max() on empty stack!");
		    System.exit(-1);
		  }

		  public static void missedPopException() {
		    System.err.println("Should have seen an exception, pop() on empty stack!");
		    System.exit(-1);
		  }
		  
		  
	public static void main(String[] args) {
	    C9S1<Integer> s = new C9S1<Integer>();
	    s.push(1);
	    s.push(2);
	    check(s.max() == 2,
	          "failed max() call with stack created by push 1, push 2");
	    System.out.println(s.max()); // 2
	    System.out.println(s.pop()); // 2
	    check(s.max() == 1,
	          "failed max() call with stack created by push 1, push 2, pop");
	    System.out.println(s.max()); // 1
	    s.push(3);
	    s.push(2);
	    check(
	        s.max() == 3,
	        "failed max() call with stack created by push 1, push 2, pop, push 3, push 2");
	    System.out.println(s.max()); // 3
	    s.pop();
	    check(
	        s.max() == 3,
	        "failed max() call with stack created by push 1, push 2, pop, push 3, push 2, pop");
	    System.out.println(s.max()); // 3
	    s.pop();
	    check(
	        s.max() == 1,
	        "failed max() call with stack created by push 1, push 2, pop, push 3, push 2, pop, pop");
	    System.out.println(s.max()); // 1
	    s.pop();

	    try {
	      s.max();
	      missedMaxException();
	    } catch (RuntimeException e) {
	      System.out.println(
	          "Got expected exception calling max() on an empty stack:"
	          + e.getMessage());
	    }

	    try {
	      s.pop();
	      missedPopException();
	    } catch (RuntimeException e) {
	      System.out.println(
	          "Got expected exception calling pop() on an empty stack:"
	          + e.getMessage());
	    }
	  }

}
