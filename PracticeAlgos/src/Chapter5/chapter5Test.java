package Chapter5;
import static org.junit.Assert.*;
import org.junit.Test;

public class chapter5Test {
	


	
	public long binaryToint(String a){
	return Integer.parseInt(a, 2);
	}
	
	@Test
	public void parityEven(){
		long a =  binaryToint("10101");
		assertEquals(1, chapter5.parity(a));
	}
	
	@Test
	public void correctNextPermutation(){
		byte a[] = new byte[4];
		a[0] = 0;
		a[1] = 3;
		a[2] = 1;
		a[3] = 2;
		a = chapter5.nextPremutation(a);
		assertEquals(1, a[3]);
		a = chapter5.nextPremutation(a);
		assertEquals(1, a[0]);
		
	}

}
