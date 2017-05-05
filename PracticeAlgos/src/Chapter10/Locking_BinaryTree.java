package Chapter10;


/*problem 10.17*/
public class Locking_BinaryTree<T extends Comparable<T>>{
	private T value;
	public Locking_BinaryTree<T> getLeft() {
		return left;
	}

	public void setLeft(Locking_BinaryTree<T> left) {
		this.left = left;
	}

	public Locking_BinaryTree<T> getRight() {
		return right;
	}

	public void setRight(Locking_BinaryTree<T> right) {
		this.right = right;
	}

	public Locking_BinaryTree<T> getParent() {
		return parent;
	}

	public void setParent(Locking_BinaryTree<T> parent) {
		this.parent = parent;
	}

	private Locking_BinaryTree<T> left, right, parent;
	private boolean locked = false;
	private int numLockedDescendants = 0;
	 
	public Locking_BinaryTree(T value){
		this.value = value;
	}
	
	public T getValue(){
		return value;
	}
	public void setValue(T value){
		this.value = value;
	}
	public boolean isLocked(){
		return locked;
	}
	
	public Locking_BinaryTree<T> insert(T value){
		int order =  value.compareTo(this.value);
		if(locked == false){
			if(order <= 0){
				if(left == null){
					this.left = new Locking_BinaryTree<T>(value);
					return left;
				}
				return left.insert(value);
			}
			if(right == null){
				this.right = new Locking_BinaryTree<T>(value);
				return right;
			}
			return right.insert(value);
			}
	return null;	
	}

		

		
	
	public boolean lock(){
		if(numLockedDescendants  > 0 || locked){
			return false;
		}
		
		for (Locking_BinaryTree<?> iter = parent; iter != null; iter = iter.parent){
			if(iter.locked){
				return false;
			}
		}
		
		locked  = true;
		
		for(Locking_BinaryTree<?> iter = parent; iter != null; iter = iter.parent){
			iter.numLockedDescendants++;
		}
		return true;
	}
	
	public void unlock(){
		if(locked){
			locked = false;

			for(Locking_BinaryTree<?> iter = parent; iter != null; iter = iter.parent){
				iter.numLockedDescendants--;
			}
		}
	}
}