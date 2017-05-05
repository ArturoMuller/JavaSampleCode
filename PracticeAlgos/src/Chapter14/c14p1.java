package Chapter14;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class c14p1 {
	public static List<Integer> intersection(int[] a, int[] b){
		List<Integer> intersection = new ArrayList<Integer>();
		if(a.length <= b.length){
			int  iB =0;
			for(int iA = 0; iA < a.length; iA++){
				while(iB < b.length && a[iA] != b[iB] ){
					if(a[iA] > b[iB]){
						iB++;
					}
				}
				if(iB < b.length && a[iA] == b[iB]){
				if(intersection.size() == 0 || a[iA] != intersection.get(intersection.size() - 1)){
				intersection.add(a[iA]);
				}}
		}	
		}
		else{
			return intersection(b,a);
		}
		return intersection;
	}
	
	
private static void check(int[] A,int[] B, List<Integer> expected) {
List<Integer> got = intersection(A, B);
if (!got.equals(expected)) {
System.err.println("Incorrect intersection");
System.err.println("A = " + A);
System.err.println("B = " + B);
System.err.println("Got " + got);
System.err.println("Expected " + expected);

}
}

public static void main(String[] args) {
List<Integer> empty = Collections.emptyList();
int[] empty1 =new int[10];
check(new int[] {1, 2, 3, 4} , new int[] {1, 4, 5} , Arrays.asList(1, 4));
check(empty1, new int[] {1, 4, 5}, empty);
check(empty1, empty1, empty);
check(new int[] {1, 4, 5}, empty1, empty);
check(new int[] {1, 2, 2, 4}, new int[] {1, 2, 5},
Arrays.asList(1, 2));
check(new int[] {1, 2, 3, 4}, new int[] {1, 4, 5},
Arrays.asList(1, 4));
check(new int[] {1, 2, 3, 4}, new int[] {5, 6, 9}, empty);
check(new int[] {1, 1, 1, 1}, new int[] {1, 1, 1}, Arrays.asList(1));
check(new int[] {1, 1, 1, 2, 2}, new int[] {1, 1, 1, 1, 1, 2},
Arrays.asList(1, 2));
check(new int[] {-2, 1, 1, 1, 2, 2},
new int[] {-1, 1, 1, 1, 1, 1, 2, 2}, Arrays.asList(1, 2));

}
}
