package Chapter5;

public class chapter5 {

	public static void main(String args[]){
		
	}

	/*5.1*/
	public static short parity(long x){
		short result  = 0;
		while(x != 0){
			result ^= 1;
			x &= (x - 1);
			String y = Integer.toBinaryString((int)x);
			System.out.println(y);
		}
		return result;
	}
	
	
	/*6.1*/
	public static byte[] nextPremutation(byte[] a){
		int k = -1;
		int l = -1;
		for(int i = a.length - 1; i > 0; i--){
			if(a[i] < a[i-1]){
				continue;
			}
			else{
				k = i -1;
				break;
			}
		}
		int find = a[k] + 1;
		for(int i = 0; i < a.length; i++){
			if(a[i] == find){
				l = i;
			}
		}
		byte kay = a[k];
		byte el = a[l];
		a[k] = el;
		a[l] = kay;
		for(int i = 1; i < Math.abs(k - a.length)/2  ; i++){
			byte left = a[i + k];
			byte right = a[a.length - i];
			a[i + k] = right;
			a[a.length - i] = left;
		}
		return a;
	}
	
	

}
