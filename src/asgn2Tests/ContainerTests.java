package asgn2Tests;

/* Some valid container codes used in the tests below:
 * INKU2633836
 * KOCU8090115
 * MSCU6639871
 * CSQU3054389
 */

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import asgn2Codes.ContainerCode;
import asgn2Exceptions.InvalidCodeException;

/**
 * Test class for the ContainerCode class
 * 
 * @author Brendan Rothwell n8540683
 * @version 1.0
 *
 */
public class ContainerTests {

	// Initializations

	private static String CODE1 = "INKU2633836";
	private static String CODE2 = "KOCU8090115";
	private static ContainerCode cc;

	// Setup

	@Before
	public void setUpBefore() throws Exception {
		cc = new ContainerCode(CODE1);
	}

	// Testing Constructor Exceptions

	/**
	 * Test method for
	 * {@link asgn2Codes.ContainerCode#ContainerCode(java.lang.String)}.
	 */
	@Test(expected = Exception.class)
	public final void emptyContainerCode() throws InvalidCodeException {
		cc = new ContainerCode("");
	}

	/**
	 * Test method for
	 * {@link asgn2Codes.ContainerCode#ContainerCode(java.lang.String)}.
	 */
	@Test(expected = Exception.class)
	public final void shortContainerCode() throws InvalidCodeException {
		cc = new ContainerCode("MSCU63988");
	}

	/**
	 * Test method for
	 * {@link asgn2Codes.ContainerCode#ContainerCode(java.lang.String)}.
	 */
	@Test(expected = Exception.class)
	public final void nullContainerCode() throws InvalidCodeException {
		cc = new ContainerCode(null);
	}

	/**
	 * Test method for
	 * {@link asgn2Codes.ContainerCode#ContainerCode(java.lang.String)}.
	 */
	@Test(expected = Exception.class)
	public final void lowerCaseLetters() throws InvalidCodeException {
		cc = new ContainerCode("mscu6639871");
	}

	/**
	 * Test method for
	 * {@link asgn2Codes.ContainerCode#ContainerCode(java.lang.String)}.
	 */
	@Test(expected = Exception.class)
	public final void someLowerCaseLetters() throws InvalidCodeException {
		cc = new ContainerCode("msCU6639871");
	}

	/**
	 * Test method for
	 * {@link asgn2Codes.ContainerCode#ContainerCode(java.lang.String)}.
	 */
	@Test(expected = Exception.class)
	public final void firstLowerCaseLetter() throws InvalidCodeException {
		cc = new ContainerCode("mSCU6639871");
	}

	/**
	 * Test method for
	 * {@link asgn2Codes.ContainerCode#ContainerCode(java.lang.String)}.
	 */
	@Test(expected = Exception.class)
	public final void lasLowerCaseLetter() throws InvalidCodeException {
		cc = new ContainerCode("MSCu6639871");
	}

	/**
	 * Test method for
	 * {@link asgn2Codes.ContainerCode#ContainerCode(java.lang.String)}.
	 */
	@Test(expected = Exception.class)
	public final void notEnoughLetters() throws InvalidCodeException {
		cc = new ContainerCode("MSU62639871");
	}

	/**
	 * Test method for
	 * {@link asgn2Codes.ContainerCode#ContainerCode(java.lang.String)}.
	 */
	@Test(expected = Exception.class)
	public final void tooManyLetters() throws InvalidCodeException {
		cc = new ContainerCode("MSCUU669870");
	}

	/**
	 * Test method for
	 * {@link asgn2Codes.ContainerCode#ContainerCode(java.lang.String)}.
	 */
	@Test(expected = Exception.class)
	public final void ownerCodeNotLetters() throws InvalidCodeException {
		cc = new ContainerCode("282U6639871");
	}

	/**
	 * Test method for
	 * {@link asgn2Codes.ContainerCode#ContainerCode(java.lang.String)}.
	 */
	@Test(expected = Exception.class)
	public final void incorrectCategoryIdentifier() throws InvalidCodeException {
		cc = new ContainerCode("MSCV6639872");
	}

	/**
	 * Test method for
	 * {@link asgn2Codes.ContainerCode#ContainerCode(java.lang.String)}.
	 */
	@Test(expected = Exception.class)
	public final void incorrectCheckDigit() throws InvalidCodeException {
		cc = new ContainerCode("MSCU6639873");
	}

	// Testing equals(Object) method

	/**
	 * Test method for {@link asgn2Codes.ContainerCode#equals(Object)}.
	 */
	@Test
	public final void trueEqualsMethod() {
		assertTrue(cc.equals(CODE1));
	}

	/**
	 * Test method for {@link asgn2Codes.ContainerCode#equals(Object)}.
	 */
	@Test
	public final void falseEqualsMethod() {
		assertFalse(cc.equals(CODE2));
	}

}
