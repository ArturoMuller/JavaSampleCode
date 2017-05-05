package Chapter8;

import java.util.Iterator;

public class NodeIterator implements Iterator<node<?>>{
		node<?> position = null;
		
		public NodeIterator(node<?> head){
			this.position = head;
		}

		@Override
		public boolean hasNext() {
			if(position.next != null){
				return true;
			}
			return false;
		}
		@Override
		public node<?> next() {
			if(hasNext()){
				node<?> next = position;
				position = position.next;
				return next;
			}
			return null;
		}
		 
		
}
