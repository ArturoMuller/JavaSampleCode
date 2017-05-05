package Chapter13;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class c13p1 {
	public static boolean testPalindrome(String input){
		Map<Character,Integer> test = new HashMap<Character,Integer>();
		for(int i = 0; i < input.length(); i++){
			Character a = new Character(input.charAt(i));
			if(test.containsKey(a)){
				Integer current = test.get(a);
				test.put(a, current + 1);
			}else{
				test.put(new Character(input.charAt(i)), 1);
			}
		}
		//even odd part
		//test1 odd num chars if everything is even and there is only one or none odd
		int odd = 0;
		for (Map.Entry<Character, Integer> entry : test.entrySet()) {
			if(entry.getValue()%2 != 0){
				odd++;
		}
		}
		
		return (odd <= 1)? true: false;

	}
	
	public static void main(String[] args){
			if(testPalindrome("rotator") == false){
				System.out.println("ERROR");
			}
			if(testPalindrome("level") == false){
				System.out.println("ERROR");
			}
			if(testPalindrome("edified") == false){
				System.out.println("ERROR");
			}
			if(testPalindrome("rotatossdsvfsr") == true){
				System.out.println("ERROR");
			}
			if(testPalindrome("deified") == true){
				System.out.println("correct");
			}
		
			
			
			
			
	}
}
