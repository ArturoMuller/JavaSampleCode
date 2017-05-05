package Chapter11;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class C10p1 {
	
	public static void mergeSortedArrays(List<Integer> a, List<Integer> b){
		
	}
	
	 private static void simpleTest() {
		    List<List<Integer>> S
		        = Arrays.asList(Arrays.asList(1, 5, 10), Arrays.asList(2, 3, 100),
		                        Arrays.asList(2, 12, Integer.MAX_VALUE));
		    List<Integer> ans = mergeSortedArrays(S);
		    List<Integer> golden
		        = Arrays.asList(1, 2, 2, 3, 5, 10, 12, 100, Integer.MAX_VALUE);
		    check(S, ans, golden);

		    S = Arrays.asList(Arrays.asList(1));
		    ans = mergeSortedArrays(S);
		    golden = Arrays.asList(1);
		    check(S, ans, golden);

		    S = Arrays.asList(new ArrayList<Integer>(), Arrays.asList(1),
		                      Arrays.asList(2));
		    ans = mergeSortedArrays(S);
		    golden = Arrays.asList(1, 2);
		    check(S, ans, golden);
		  }
	 
	private static void check(List<List<Integer>> s, List<Integer> ans, List<Integer> golden) {
		// TODO Auto-generated method stub
		
	}

	private static List<Integer> mergeSortedArrays(List<List<Integer>> s) {
		// TODO Auto-generated method stub
		return null;
	}

	public static void main(String[] args) {
	    simpleTest();
	    Random rnd = new Random();
	    for (int times = 0; times < 100; ++times) {
	      int n;
	      if (args.length == 2) {
	        n = Integer.parseInt(args[0]);
	      } else {
	        n = 1 + rnd.nextInt(100);
	      }

	      List<List<Integer>> S = new ArrayList<>();
	      for (int i = 0; i < n; ++i) {
	        int m = rnd.nextInt(500);
	        List<Integer> last = new ArrayList<>(m);
	        S.add(last);
	        for (int j = 0; j < m; ++j) {
	          last.add(rnd.nextInt(500));
	        }
	        Collections.sort(last);
	      }

	      List<Integer> ans = mergeSortedArrays(S);
	      for (int i = 1; i < ans.size(); ++i) {
	        assert(ans.get(i - 1) <= ans.get(i));
	      }
	    }
	  }

}
