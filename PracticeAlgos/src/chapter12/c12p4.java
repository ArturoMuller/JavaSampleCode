package chapter12;

public class c12p4 {
	public static int squareRoot(int k){
		int left = 0;
		int right =k;
		while(left <= right){
			int mid = (left + (right - left)) /2;
			int midSquared = mid * mid;
			if(midSquared > k){
				right = mid - 1;
			}
			else if(midSquared <= k){
				left = mid + 1;
			}
		}
		return (int) left - 1;
		
	}
}
