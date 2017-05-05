package chapter12;

public class c12p1 {
	
	public static int firstKey(int[] a, int key){
		int front = 0;
		int back = a.length - 1;
		
		while(a[(front + back) / 2] != key && front < back ){
			if(a[(front + back) / 2] > key ){
				back = ((front + back) / 2) ;
			}
			else{
				front = (front + back )/ 2 + 1;
			}
		}
		
		while(a[(front + back -1) / 2] == key){
			back--;
		}
		return (front + back) / 2;
	}
	
	
	
	public static void main(String[] args){
		int a[] = {1,2,2, 2 , 3,4,5,6,7, 7, 7};
		System.out.println(firstKey(a, 2));
	}
}
