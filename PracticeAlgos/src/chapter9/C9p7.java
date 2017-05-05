package chapter9;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class C9p7 {
	
	 static List<List<Integer>> depthList;
	
	
	public static void createList(BinaryTreeNode<Integer> bnode, int depth){
		if(bnode != null){
		try{
			depthList.get(depth);
		}
		catch(IndexOutOfBoundsException e){
			depthList.add(new ArrayList<Integer>());
		}
		depthList.get(depth).add(bnode.data);
		
		createList(bnode.left, depth +1);
		createList(bnode.right, depth + 1);
		}
	}
	
	public  static List<List<Integer>> driver(BinaryTreeNode<Integer> bnode){
	
	   depthList = new ArrayList<List<Integer>>();
	   createList(bnode, 0);
	   return depthList;
	}
	
	public static void main(String[] args) {
	    //      3
	    //    2   5
	    //  1    4 6
	    // 10
	    // 13
	    BinaryTreeNode<Integer> tree = new BinaryTreeNode<>(3);
	    tree.left = new BinaryTreeNode<>(2);
	    tree.left.left = new BinaryTreeNode<>(1);
	    tree.left.left.left = new BinaryTreeNode<>(10);
	    tree.left.left.left.right = new BinaryTreeNode<>(13);
	    tree.right = new BinaryTreeNode<>(5);
	    tree.right.left = new BinaryTreeNode<>(4);
	    tree.right.right = new BinaryTreeNode<>(6);
	    List<List<Integer>> result = driver(tree);
	    List<List<Integer>> goldenRes = Arrays.asList(
	        Arrays.asList(3), Arrays.asList(2, 5), Arrays.asList(1, 4, 6),
	        Arrays.asList(10), Arrays.asList(13));
	    if (!goldenRes.equals(result)) {
	      System.err.println("Failed on input " + tree);
	      System.err.println("Expected " + goldenRes);
	      System.err.println("Your code produced " + result);
	      System.exit(-1);
	    } else {
	      System.out.println("You passed all tests.");
	    }
	  }
}
