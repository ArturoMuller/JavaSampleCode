package Chapter5;

public class chapter5p7 {

	public static double power(double x, int y){
		double result = 1.0;
		int power = y;
		if(y < 0){
			power = -power;
			x = 1.0/x;
			
		}
		System.out.println("before: " + Integer.toBinaryString(new Integer((int) power)));
		while(power != 0){
			System.out.println("after: " + Integer.toBinaryString(new Integer((int) power)));
			System.out.println("inside: " + Long.toBinaryString(new Integer(power & 1)));
			if((power & 1) != 0){
				
				result *= x;
			}
		 x *= x;
		 power >>>= 1;
		}
		return result;
	}
	
	public static void main(String[] args){
		System.out.println(power(3.0,10));
	}
}
