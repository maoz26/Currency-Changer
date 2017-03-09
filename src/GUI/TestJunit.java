package GUI;
import javax.xml.bind.JAXBException;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TestJunit {
	@Test 
	public void testAdding()
	{
		String str = "mark";
		assertEquals("mark", str);
	}
	
	@Test 			/* testing the UnmushelingCheck function */
	public void UnmushelingCheck() throws JAXBException
	{
		Musheling m = new Musheling("currency.xml"); // other name would not be valid..
		m.unMarshaling();
		assertEquals(true, Musheling.mushTest);
	}
}