package chapter9;

class BinaryTreeNode<T> {
	BinaryTreeNode(T input){
		this.data = input;
	}
	public T data;
	public BinaryTreeNode<T> left, right;
	
}
