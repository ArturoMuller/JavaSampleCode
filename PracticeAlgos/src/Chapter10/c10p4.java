package Chapter10;

import java.util.HashSet;
import java.util.Set;

public class c10p4 {
		public static BinaryTreeNode<Integer> lca(BinaryTreeNode<Integer> a, BinaryTreeNode<Integer> b){
			Set<BinaryTreeNode<Integer>> mySet= new HashSet<BinaryTreeNode<Integer>>();
			
			if(a != null){
				mySet.add(a);
			
			do{
				a = a.ancestor;
				mySet.add(a);
			}while((a.ancestor != null));
			}
			
			if(b != null){	
				if(mySet.contains(b)){
					return (BinaryTreeNode<Integer>) b;
				}
			do{
				b = b.ancestor;
				if(mySet.contains(b)){
					return (BinaryTreeNode<Integer>) b;
				}
			}while(b.ancestor != null);
			}
			return null;
		}
		  public static void main(String[] args) {
			    // 3
			    // 2 5
			    // 1 4 6
			    BinaryTreeNode<Integer> root = new BinaryTreeNode<>(3, null, null, null);
			    root.left = new BinaryTreeNode<>(2, null, null, root);
			    root.left.left = new BinaryTreeNode<>(1, null, null, root.left);
			    root.right = new BinaryTreeNode<>(5, null, null, root);
			    root.right.left = new BinaryTreeNode<>(4, null, null, root.right);
			    root.right.right = new BinaryTreeNode<>(6, null, null, root.right);

			    // should output 3
			    assert(lca(root.left, root.right).data.equals(3));
			    System.out.println(lca(root.left, root.right).data);
			    // should output 5
			    assert(lca(root.right.left, root.right.right).data.equals(5));
			    System.out.println(lca(root.right.left, root.right.right).data);
			    // should output 3
			    assert(lca(root.left, root.right.left).data.equals(3));
			    System.out.println(lca(root.left, root.right.left).data);
			    // should output 2
			    assert(lca(root.left, root.left.left).data.equals(2));
			    System.out.println(lca(root.left, root.left.left).data);
			  }
}
