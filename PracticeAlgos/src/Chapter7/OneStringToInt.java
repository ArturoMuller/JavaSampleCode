package Chapter7;

public class OneStringToInt {
	public static int stringToInt(String s){
		boolean neg = s.charAt(0) == '-';
		int result = 0;
		for(int i = s.charAt(0) == '-' ? 1 : 0; i < s.length(); ++i){
			int digit = s.charAt(i) - '0';
			result = result * 10 + digit;
		}
		return neg ? -result : result;
	}


	
}
