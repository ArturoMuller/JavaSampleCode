package Chapter7;

public class c7s2 {
	public static String baseConvert(String input, int b1, int b2){
		int max = input.length() - 1;
		int num = 0;
		for(int i = 0; i <= max; i++){
			int digit = input.charAt(i) - 48;
			int power = (int) Math.pow(b1, max - i);
			num += digit * power;
		}
		int i = -1;
		do{
			i++;
		}while((num / Math.pow(b2, i)) > 2);
		
		StringBuilder sb = new StringBuilder();
		for(;i >= 0; i--){
			
			int next = (int) (num/Math.pow(b2, i));
			num -= next * Math.pow(b2, i) ;
			sb.append(hex(next));
		}
		
		
		return sb.toString();
		
	}
	
	static String hex(int input){
		switch(input){
			case 10: 
				return "A";
			case 11:
				return "B";
			case 12:
				return "C";
			case 13:
				return "D";
			case 14:
				return "E";			
			case 15:
				return "F";			
		default:
				return Integer.toString(input);
		}
	}
	
	public static void main(String [] args){
		System.out.print(baseConvert("615",7,13));
	}
}
