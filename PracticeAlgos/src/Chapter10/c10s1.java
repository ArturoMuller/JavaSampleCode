package Chapter10;

public class c10s1 {
  public static boolean isBalanced(BinaryTreeNode<?> root){
	  if(checkNodes(root) == -1){
		  return false;
	  }
	  else{
		  return true;
	  }
  }
  
  
  public static int checkNodes(BinaryTreeNode<?> root){
	  int leftSum = 0;
	  int rightSum = 0;
	  if(root.left != null){
	  leftSum = checkNodes(root.left);
	  }
	  if(root.right != null){
	  rightSum = checkNodes(root.right);
	  }
	  if(leftSum == -1 || rightSum == -1){
		  return -1;
	  }
	  if( Math.abs(leftSum - rightSum) > 1){
		  return -1;
	  }
	  else{
		  return (leftSum > rightSum)? leftSum + 1: rightSum + 1;
	  }
  }
  
  public static void main(String[] args) {
    // balanced binary tree test
    // 3
    // 2 5
    // 1 4 6
    BinaryTreeNode<Integer> tree = new BinaryTreeNode<Integer>();
    tree.left = new BinaryTreeNode<Integer>();
    tree.left.left = new BinaryTreeNode<Integer>();
    tree.right = new BinaryTreeNode<Integer>();
    tree.right.left = new BinaryTreeNode<Integer>();
    tree.right.right = new BinaryTreeNode<Integer>();
    if (!isBalanced(tree)) {
      System.err.println("Incorrect result on balanced tree " + tree);
      System.exit(-1);
    }
    tree = new BinaryTreeNode<>();
    tree.left = new BinaryTreeNode<Integer>();
    tree.left.left = new BinaryTreeNode<Integer>();
    if (isBalanced(tree)) {
      System.err.println("Incorrect result on unbalanced tree: " + tree);
      System.exit(-1);
    }
  }
}