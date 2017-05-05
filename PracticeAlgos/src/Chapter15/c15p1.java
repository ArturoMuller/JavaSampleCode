package Chapter15;

public class c15p1 {
	
	public static boolean isBinaryTreeBST(BinaryTreeNode<Integer> root){
	boolean leftClear = true;
	boolean rightClear = true;
	if(root == null){
		return true;
	}
	if(root.left != null){
		if(root.left.data < root.data){
			leftClear =  isBinaryTreeBST(root.left);
		}
		else{
			return false;
		}
	}
	if(root.right != null){
		if(root.right.data > root.data){
			rightClear =  isBinaryTreeBST(root.right);
		}
		else{
			return false;
		}
	}
	if(leftClear && rightClear){
		return true;
	}
	else{
		return false;
		}
	}
	
	
	
	// @exclude
	static void unitTest(BinaryTreeNode<Integer> root, boolean expectedValue) {
	if (isBinaryTreeBST(root) != expectedValue) {
	System.err.println("Wrong output, got " + (!expectedValue) + ", expected "
	+ expectedValue);
	System.err.println("Tree is " + root);
	System.exit(-1);
	    }
	  }
	public static void main(String[] args) {
	// 3
	// 2 5
	// 1 4 6
	BinaryTreeNode<Integer> tree = new BinaryTreeNode<>(3);
	    tree.left = new BinaryTreeNode<>(2);
	    tree.left.left = new BinaryTreeNode<>(1);
	    tree.right = new BinaryTreeNode<>(5);
	    tree.right.left = new BinaryTreeNode<>(4);
	    tree.right.right = new BinaryTreeNode<>(6);
	    unitTest(tree, true);
	// 10
	// 2 5
	// 1 4 6
	    tree.data = 10;
	// should output false.
	    unitTest(tree, false);
	// should output true.
	    unitTest(null, true);
	  }
	}

