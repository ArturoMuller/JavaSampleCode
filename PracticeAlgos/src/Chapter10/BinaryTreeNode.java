package Chapter10;

class BinaryTreeNode<T> {
	public T data;
	public BinaryTreeNode<T> left, right;
	public BinaryTreeNode<T> ancestor;
	BinaryTreeNode(){
		
	}
	
	BinaryTreeNode(T value, BinaryTreeNode<T> left, BinaryTreeNode<T> right,BinaryTreeNode<T> parent){
		this.data = value;
		this.left = left;
		this.right = right;
		this.ancestor = parent;
	}
}
