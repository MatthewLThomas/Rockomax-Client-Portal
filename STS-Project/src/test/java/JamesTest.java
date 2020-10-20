import static org.junit.Assert.*;

import org.junit.Test;



import test.JavaTest1;

public class JamesTest {

	JavaTest1 t = new JavaTest1();
	@Test
	public void test() {
		assertTrue(t.test(false));
	}
	@Test
	public void test2() {
		assertFalse(t.test(true));
	}
}
