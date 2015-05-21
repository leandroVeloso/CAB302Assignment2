package asgn2Tests;

/* Some valid container codes used in the tests below:
 * INKU2633836
 * KOCU8090115
 * MSCU6639871
 * CSQU3054389
 */

import static org.junit.Assert.assertEquals;
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
 * @version 1.1
 *
 */
public class ContainerTests {

	// Initializations

	private static ContainerCode cc;
	private static String CODE1 = "INKU2633836";
	private static String CODE2 = "KOCU8090115";
	private static String NOTENOUGHNUMBERS = "MSCU63988";
	private static String NOTENOUGHLETTERS = "MSU62639871";
	private static String TOOMANYLETTERS = "MSCUU669870";
	private static String TOOMANYNUMBERS = "MSCU66398712";
	private static String SERIALLETTERS = "MSCUKKKKKK2";
	private static String SERIALSYMBOLS = "MSCU}}}}}}2";
	private static String LOWERCASELETTERS = "mscu6639871";
	private static String SOMELOWERCASE = "msCU6639871";
	private static String FIRSTLOWERCASE = "mSCU6639871";
	private static String LASTLOWERCASE = "MSCu6639871";
	private static String NUMBEROWNERCODE = "282U6639871";
	private static String SYMBOLOWNERCODE = "U6639879";
	private static String WRONGCATIDLETTER = "MSCV6639872";
	private static String SYMBOLCATID = "MSC<6639871";
	private static String WRONGCHECKDIGIT = "MSCU6639873";
	private static String LETTERCHECKDIGIT = "MSCU6639871V";
	private static String SYMBOLCHECKDIGIT = "MSCU663987~";

	// Setup

	@Before
	public void setUpBefore() throws Exception {
		cc = new ContainerCode(CODE1);
	}

	// Testing Exceptions

	/**
	 * Test method for {@link asgn2Codes.ContainerCode#checkCode(String)}.
	 */
	@Test(expected = Exception.class)
	public final void emptyContainerCode() throws InvalidCodeException {
		cc = new ContainerCode("");
	}

	/**
	 * Test method for {@link asgn2Codes.ContainerCode#checkCode(String)}.
	 */
	@Test(expected = Exception.class)
	public final void shortContainerCode() throws InvalidCodeException {
		cc = new ContainerCode(NOTENOUGHNUMBERS);
	}

	/**
	 * Test method for {@link asgn2Codes.ContainerCode#checkCode(String)}.
	 */
	@Test(expected = Exception.class)
	public final void nullContainerCode() throws InvalidCodeException {
		cc = new ContainerCode(null);
	}

	/**
	 * Test method for {@link asgn2Codes.ContainerCode#checkCode(String)}.
	 */
	@Test(expected = Exception.class)
	public final void lowerCaseLetters() throws InvalidCodeException {
		cc = new ContainerCode(LOWERCASELETTERS);
	}

	/**
	 * Test method for {@link asgn2Codes.ContainerCode#checkCode(String)}.
	 */
	@Test(expected = Exception.class)
	public final void someLowerCaseLetters() throws InvalidCodeException {
		cc = new ContainerCode(SOMELOWERCASE);
	}

	/**
	 * Test method for {@link asgn2Codes.ContainerCode#checkCode(String)}.
	 */
	@Test(expected = Exception.class)
	public final void firstLowerCaseLetter() throws InvalidCodeException {
		cc = new ContainerCode(FIRSTLOWERCASE);
	}

	/**
	 * Test method for {@link asgn2Codes.ContainerCode#checkCode(String)}.
	 */
	@Test(expected = Exception.class)
	public final void lastLowerCaseLetter() throws InvalidCodeException {
		cc = new ContainerCode(LASTLOWERCASE);
	}

	/**
	 * Test method for {@link asgn2Codes.ContainerCode#checkCode(String)}.
	 */
	@Test(expected = Exception.class)
	public final void notEnoughLetters() throws InvalidCodeException {
		cc = new ContainerCode(NOTENOUGHLETTERS);
	}

	/**
	 * Test method for {@link asgn2Codes.ContainerCode#checkCode(String)}.
	 */
	@Test(expected = Exception.class)
	public final void tooManyLetters() throws InvalidCodeException {
		cc = new ContainerCode(TOOMANYLETTERS);
	}

	/**
	 * Test method for {@link asgn2Codes.ContainerCode#checkCode(String)}.
	 */
	@Test(expected = Exception.class)
	public final void tooManyNumbers() throws InvalidCodeException {
		cc = new ContainerCode(TOOMANYNUMBERS);
	}

	/**
	 * Test method for {@link asgn2Codes.ContainerCode#checkCode(String)}.
	 */
	@Test(expected = Exception.class)
	public final void serialNumberLetters() throws InvalidCodeException {
		cc = new ContainerCode(SERIALLETTERS);
	}

	/**
	 * Test method for {@link asgn2Codes.ContainerCode#checkCode(String)}.
	 */
	@Test(expected = Exception.class)
	public final void serialNumberSymbols() throws InvalidCodeException {
		cc = new ContainerCode(SERIALSYMBOLS);
	}

	/**
	 * Test method for {@link asgn2Codes.ContainerCode#checkCode(String)}.
	 */
	@Test(expected = Exception.class)
	public final void ownerCodeNumbers() throws InvalidCodeException {
		cc = new ContainerCode(NUMBEROWNERCODE);
	}

	/**
	 * Test method for {@link asgn2Codes.ContainerCode#checkCode(String)}.
	 */
	@Test(expected = Exception.class)
	public final void ownerCodeSymbols() throws InvalidCodeException {
		cc = new ContainerCode(SYMBOLOWNERCODE);
	}

	/**
	 * Test method for {@link asgn2Codes.ContainerCode#checkCode(String)}.
	 */
	@Test(expected = Exception.class)
	public final void incorrectCategoryIdentifier() throws InvalidCodeException {
		cc = new ContainerCode(WRONGCATIDLETTER);
	}

	/**
	 * Test method for {@link asgn2Codes.ContainerCode#checkCode(String)}.
	 */
	@Test(expected = Exception.class)
	public final void symbolCategoryIdentifier() throws InvalidCodeException {
		cc = new ContainerCode(SYMBOLCATID);
	}

	/**
	 * Test method for
	 * {@link asgn2Codes.ContainerCode#calculateCheckDigit(String)}.
	 */
	@Test(expected = Exception.class)
	public final void incorrectCheckDigit() throws InvalidCodeException {
		cc = new ContainerCode(WRONGCHECKDIGIT);
	}

	/**
	 * Test method for
	 * {@link asgn2Codes.ContainerCode#calculateCheckDigit(String)}.
	 */
	@Test(expected = Exception.class)
	public final void letterCheckDigit() throws InvalidCodeException {
		cc = new ContainerCode(LETTERCHECKDIGIT);
	}

	/**
	 * Test method for
	 * {@link asgn2Codes.ContainerCode#calculateCheckDigit(String)}.
	 */
	@Test(expected = Exception.class)
	public final void symbolCheckDigit() throws InvalidCodeException {
		cc = new ContainerCode(SYMBOLCHECKDIGIT);
	}

	/**
	 * Test method for {@link asgn2Codes.ContainerCode#toString()}.
	 */
	@Test
	public final void toStringMethod() {
		assertEquals(cc.toString(), CODE1);
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
