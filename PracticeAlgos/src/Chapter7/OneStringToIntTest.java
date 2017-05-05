package Chapter7;

import static org.junit.Assert.*;

import org.junit.Test;

public class OneStringToIntTest {

	@Test
	public void test() {
		assertEquals(90210, OneStringToInt.stringToInt("90210"));
	}

}
