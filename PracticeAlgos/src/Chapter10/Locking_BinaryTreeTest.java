package Chapter10;

import static org.junit.Assert.*;

import org.junit.Test;

public class Locking_BinaryTreeTest {

	@Test
	public void addNodes() {
		Locking_BinaryTree<Integer> root = new Locking_BinaryTree<Integer>(5);
		Locking_BinaryTree<Integer> left = root.insert(4);
		Locking_BinaryTree<Integer> right =  root.insert(9);
		assertEquals(new Integer(9), root.getRight().getValue());
	}

}
