package Chapter11;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.PriorityQueue;

public class C11p4 implements Comparable<C11p4>{
	private double x, y, z;
	public  C11p4(double x, double y, double z){
		this.x= x; this.y = y; this.z = z;
	}
	
	public double distance(){
		return Math.sqrt(x*x + y*y + z*z);
	}
	
	@Override 
	public int compareTo(C11p4 rhs){
		return Double.compare(this.distance(), rhs.distance());
	}
	
	public List<C11p4> findClosestKStars(int k, Iterator<C11p4> stars){
		//max heap to store the K closest stars seen so far.
		PriorityQueue<C11p4> maxHeap = new PriorityQueue<>(k, Collections.reverseOrder());
		while(stars.hasNext()){
			C11p4 star = stars.next();
			maxHeap.add(star);
			if(maxHeap.size() == k + 1){
				maxHeap.remove();
			}		
		}
	List<C11p4> orderedStars = new ArrayList<>(maxHeap);
	Collections.sort(orderedStars);
	
	return orderedStars;
	}
	
	
}
