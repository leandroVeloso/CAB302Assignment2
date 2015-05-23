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
import asgn2Containers.DangerousGoodsContainer;
import asgn2Containers.FreightContainer;
import asgn2Containers.GeneralGoodsContainer;
import asgn2Containers.RefrigeratedContainer;
import asgn2Exceptions.InvalidCodeException;
import asgn2Exceptions.InvalidContainerException;

/**
 * Test class for the ContainerCode class
 * 
 * @author Brendan Rothwell n8540683
 * @version 1.2
 *
 */
public class ContainerTests {

	// ContainerCode Testing Initializations

	private static ContainerCode cc;
	private static ContainerCode cc2;
	private static final String CODE1 = "INKU2633836";
	private static final String CODE2 = "KOCU8090115";
	private static final String NOTENOUGHNUMBERS = "MSCU63988";
	private static final String NOTENOUGHLETTERS = "MSU62639871";
	private static final String TOOMANYLETTERS = "MSCUU669870";
	private static final String TOOMANYNUMBERS = "MSCU66398712";
	private static final String SERIALLETTERS = "MSCUKKKKKK2";
	private static final String SERIALSYMBOLS = "MSCU}}}}}}2";
	private static final String LOWERCASELETTERS = "mscu6639871";
	private static final String SOMELOWERCASE = "msCU6639871";
	private static final String FIRSTLOWERCASE = "mSCU6639871";
	private static final String LASTLOWERCASE = "MSCu6639871";
	private static final String NUMBEROWNERCODE = "282U6639871";
	private static final String SYMBOLOWNERCODE = "U6639879";
	private static final String WRONGCATIDLETTER = "MSCV6639872";
	private static final String SYMBOLCATID = "MSC<6639871";
	private static final String WRONGCHECKDIGIT = "MSCU6639873";
	private static final String LETTERCHECKDIGIT = "MSCU6639871V";
	private static final String SYMBOLCHECKDIGIT = "MSCU663987~";

	// FreightContainer Testing Initializations

	private FreightContainer fc;
	private static final Integer GROSSWT = 10;
	private static final Integer GROSSWT2 = 20;
	private static final Integer LOWGROSSWT = 3;
	private static final Integer HIGROSSWT = 31;
	private static final Integer NEGATIVEGROSSWT = -10;

	// RefrigeratedContainer Testing Initializations

	private RefrigeratedContainer rc;
	private static final Integer TEMP1 = 3;
	private static final Integer TEMP2 = 10;

	// DangerousGoodsContainer Testing Initializations

	private DangerousGoodsContainer dgc;
	private static final Integer CAT1 = 3;
	private static final Integer CAT2 = 9;
	private static final Integer HICAT = 10;
	private static final Integer NEGCAT = -4;

	// SETUP

	@Before
	public void setUpBefore() throws Exception {
		cc = new ContainerCode(CODE1);
		cc2 = new ContainerCode(CODE2);
		fc = new FreightContainer(cc, GROSSWT);
		rc = new RefrigeratedContainer(cc, GROSSWT, TEMP1);
		dgc = new DangerousGoodsContainer(cc, GROSSWT, CAT1);
	}

	/* ContainerCode Tests */

	// Testing Exceptions

	/**
	 * Test method for {@link asgn2Codes.ContainerCode#checkCode(String)}.
	 */
	@Test(expected = Exception.class)
	public final void emptyContainerCodeCC() throws InvalidCodeException {
		new ContainerCode("");
	}

	/**
	 * Test method for {@link asgn2Codes.ContainerCode#checkCode(String)}.
	 */
	@Test(expected = Exception.class)
	public final void shortContainerCodeCC() throws InvalidCodeException {
		new ContainerCode(NOTENOUGHNUMBERS);
	}

	/**
	 * Test method for {@link asgn2Codes.ContainerCode#checkCode(String)}.
	 */
	@Test(expected = Exception.class)
	public final void nullContainerCodeCC() throws InvalidCodeException {
		new ContainerCode(null);
	}

	/**
	 * Test method for {@link asgn2Codes.ContainerCode#checkCode(String)}.
	 */
	@Test(expected = Exception.class)
	public final void lowerCaseLettersCC() throws InvalidCodeException {
		new ContainerCode(LOWERCASELETTERS);
	}

	/**
	 * Test method for {@link asgn2Codes.ContainerCode#checkCode(String)}.
	 */
	@Test(expected = Exception.class)
	public final void someLowerCaseLettersCC() throws InvalidCodeException {
		new ContainerCode(SOMELOWERCASE);
	}

	/**
	 * Test method for {@link asgn2Codes.ContainerCode#checkCode(String)}.
	 */
	@Test(expected = Exception.class)
	public final void firstLowerCaseLetterCC() throws InvalidCodeException {
		new ContainerCode(FIRSTLOWERCASE);
	}

	/**
	 * Test method for {@link asgn2Codes.ContainerCode#checkCode(String)}.
	 */
	@Test(expected = Exception.class)
	public final void lastLowerCaseLetterCC() throws InvalidCodeException {
		new ContainerCode(LASTLOWERCASE);
	}

	/**
	 * Test method for {@link asgn2Codes.ContainerCode#checkCode(String)}.
	 */
	@Test(expected = Exception.class)
	public final void notEnoughLettersCC() throws InvalidCodeException {
		new ContainerCode(NOTENOUGHLETTERS);
	}

	/**
	 * Test method for {@link asgn2Codes.ContainerCode#checkCode(String)}.
	 */
	@Test(expected = Exception.class)
	public final void tooManyLettersCC() throws InvalidCodeException {
		new ContainerCode(TOOMANYLETTERS);
	}

	/**
	 * Test method for {@link asgn2Codes.ContainerCode#checkCode(String)}.
	 */
	@Test(expected = Exception.class)
	public final void tooManyNumbersCC() throws InvalidCodeException {
		new ContainerCode(TOOMANYNUMBERS);
	}

	/**
	 * Test method for {@link asgn2Codes.ContainerCode#checkCode(String)}.
	 */
	@Test(expected = Exception.class)
	public final void serialNumberLettersCC() throws InvalidCodeException {
		new ContainerCode(SERIALLETTERS);
	}

	/**
	 * Test method for {@link asgn2Codes.ContainerCode#checkCode(String)}.
	 */
	@Test(expected = Exception.class)
	public final void serialNumberSymbolsCC() throws InvalidCodeException {
		new ContainerCode(SERIALSYMBOLS);
	}

	/**
	 * Test method for {@link asgn2Codes.ContainerCode#checkCode(String)}.
	 */
	@Test(expected = Exception.class)
	public final void ownerCodeNumbersCC() throws InvalidCodeException {
		new ContainerCode(NUMBEROWNERCODE);
	}

	/**
	 * Test method for {@link asgn2Codes.ContainerCode#checkCode(String)}.
	 */
	@Test(expected = Exception.class)
	public final void ownerCodeSymbolsCC() throws InvalidCodeException {
		new ContainerCode(SYMBOLOWNERCODE);
	}

	/**
	 * Test method for {@link asgn2Codes.ContainerCode#checkCode(String)}.
	 */
	@Test(expected = Exception.class)
	public final void incorrectCategoryIdentifierCC()
			throws InvalidCodeException {
		new ContainerCode(WRONGCATIDLETTER);
	}

	/**
	 * Test method for {@link asgn2Codes.ContainerCode#checkCode(String)}.
	 */
	@Test(expected = Exception.class)
	public final void symbolCategoryIdentifierCC() throws InvalidCodeException {
		new ContainerCode(SYMBOLCATID);
	}

	/**
	 * Test method for
	 * {@link asgn2Codes.ContainerCode#calculateCheckDigit(String)}.
	 */
	@Test(expected = Exception.class)
	public final void incorrectCheckDigitCC() throws InvalidCodeException {
		new ContainerCode(WRONGCHECKDIGIT);
	}

	/**
	 * Test method for
	 * {@link asgn2Codes.ContainerCode#calculateCheckDigit(String)}.
	 */
	@Test(expected = Exception.class)
	public final void letterCheckDigitCC() throws InvalidCodeException {
		new ContainerCode(LETTERCHECKDIGIT);
	}

	/**
	 * Test method for
	 * {@link asgn2Codes.ContainerCode#calculateCheckDigit(String)}.
	 */
	@Test(expected = Exception.class)
	public final void symbolCheckDigitCC() throws InvalidCodeException {
		new ContainerCode(SYMBOLCHECKDIGIT);
	}

	// Testing toString() method

	/**
	 * Test method for {@link asgn2Codes.ContainerCode#toString()}.
	 */
	@Test
	public final void toStringMethodCC() {
		assertEquals(cc.toString(), CODE1);
	}

	// Testing equals(Object) method

	/**
	 * Test method for {@link asgn2Codes.ContainerCode#equals(Object)}.
	 */
	@Test
	public final void trueEqualsMethodCC() {
		assertTrue(cc.equals(cc));
	}

	/**
	 * Test method for {@link asgn2Codes.ContainerCode#equals(Object)}.
	 */
	@Test
	public final void falseEqualsMethodCC() {
		assertFalse(cc.equals(cc2));
	}

	/* FreightContainer Tests */

	// Testing Exceptions

	/**
	 * Test method for
	 * {@link asgn2Containers.FreightContainer#FreightContainer(ContainerCode, Integer)}
	 * .
	 */
	@Test(expected = Exception.class)
	public final void highGrossWeightFC() throws InvalidContainerException {
		new FreightContainer(cc, HIGROSSWT);
	}

	/**
	 * Test method for
	 * {@link asgn2Containers.FreightContainer#FreightContainer(ContainerCode, Integer)}
	 * .
	 */
	@Test(expected = Exception.class)
	public final void lowGrossWeightFC() throws InvalidContainerException {
		new FreightContainer(cc, LOWGROSSWT);
	}

	/**
	 * Test method for
	 * {@link asgn2Containers.FreightContainer#FreightContainer(ContainerCode, Integer)}
	 * .
	 */
	@Test(expected = Exception.class)
	public final void zeroGrossWeightFC() throws InvalidContainerException {
		new FreightContainer(cc, 0);
	}

	/**
	 * Test method for
	 * {@link asgn2Containers.FreightContainer#FreightContainer(ContainerCode, Integer)}
	 * .
	 */
	@Test(expected = Exception.class)
	public final void negativeGrossWeightFC() throws InvalidContainerException {
		new FreightContainer(cc, NEGATIVEGROSSWT);
	}

	/**
	 * Test method for
	 * {@link asgn2Containers.FreightContainer#FreightContainer(ContainerCode, Integer)}
	 * .
	 */
	@Test(expected = Exception.class)
	public final void nullGrossWeightFC() throws InvalidContainerException {
		new FreightContainer(cc, null);
	}

	// Testing getCode() method

	/**
	 * Test method for {@link asgn2Containers.FreightContainer#getCode()} .
	 */
	@Test
	public final void testGetCodeFC() {
		assertTrue(fc.getCode() == cc);
	}

	/**
	 * Test method for {@link asgn2Containers.FreightContainer#getCode()} .
	 */
	@Test
	public final void getCodeWrongCodeFC() {
		assertFalse(fc.getCode() == cc2);
	}

	/**
	 * Test method for {@link asgn2Containers.FreightContainer#getCode()} .
	 */
	@Test
	public final void getCodeNotNullCodeFC() {
		assertFalse(fc.getCode() == null);
	}

	/**
	 * Test method for {@link asgn2Containers.FreightContainer#getGrossWeight()}
	 */
	@Test
	public final void testGetGrossWtFC() {
		assertTrue(fc.getGrossWeight() == GROSSWT);
	}

	/**
	 * Test method for {@link asgn2Containers.FreightContainer#getGrossWeight()}
	 */
	@Test
	public final void wrongGrossWtFC() {
		assertFalse(fc.getGrossWeight() == GROSSWT2);
	}

	/**
	 * Test method for {@link asgn2Containers.FreightContainer#getGrossWeight()}
	 */
	@Test
	public final void notNullGrossWtFC() {
		assertFalse(fc.getGrossWeight() == null);
	}

	/* GeneralGoodsContainer Testing */

	// Testing for exceptions

	/**
	 * Test method for
	 * {@link asgn2Containers.GeneralGoodsContainer#GeneralGoodsContainer(ContainerCode, Integer)}
	 */
	@Test(expected = Exception.class)
	public final void highGrossWeightGGC() throws InvalidContainerException {
		new GeneralGoodsContainer(cc, HIGROSSWT);
	}

	/**
	 * Test method for
	 * {@link asgn2Containers.GeneralGoodsContainer#GeneralGoodsContainer(ContainerCode, Integer)}
	 */
	@Test(expected = Exception.class)
	public final void lowGrossWeightGGC() throws InvalidContainerException {
		new GeneralGoodsContainer(cc, LOWGROSSWT);
	}

	/**
	 * Test method for
	 * {@link asgn2Containers.GeneralGoodsContainer#GeneralGoodsContainer(ContainerCode, Integer)}
	 */
	@Test(expected = Exception.class)
	public final void zeroGrossWeightGGC() throws InvalidContainerException {
		new GeneralGoodsContainer(cc, 0);
	}

	/**
	 * Test method for
	 * {@link asgn2Containers.GeneralGoodsContainer#GeneralGoodsContainer(ContainerCode, Integer)}
	 */
	@Test(expected = Exception.class)
	public final void negativeGrossWeightGGC() throws InvalidContainerException {
		new GeneralGoodsContainer(cc, NEGATIVEGROSSWT);
	}

	/**
	 * Test method for
	 * {@link asgn2Containers.GeneralGoodsContainer#GeneralGoodsContainer(ContainerCode, Integer)}
	 */
	@Test(expected = Exception.class)
	public final void nullGrossWeightGGC() throws InvalidContainerException {
		new GeneralGoodsContainer(cc, null);
	}

	/* RefrigeratedContainer Testing */

	// Testing for Exceptions

	/**
	 * Test method for
	 * {@link asgn2Containers.RefrigeratedContainer#RefrigeratedContainer(ContainerCode, Integer, Integer)}
	 */
	@Test(expected = Exception.class)
	public final void highGrossWeightRC() throws InvalidContainerException {
		new RefrigeratedContainer(cc, HIGROSSWT, TEMP1);
	}

	/**
	 * Test method for
	 * {@link asgn2Containers.RefrigeratedContainer#RefrigeratedContainer(ContainerCode, Integer, Integer)}
	 */
	@Test(expected = Exception.class)
	public final void lowGrossWeightRC() throws InvalidContainerException {
		new RefrigeratedContainer(cc, LOWGROSSWT, TEMP1);
	}

	/**
	 * Test method for
	 * {@link asgn2Containers.RefrigeratedContainer#RefrigeratedContainer(ContainerCode, Integer, Integer)}
	 */
	@Test(expected = Exception.class)
	public final void zeroGrossWeightRC() throws InvalidContainerException {
		new RefrigeratedContainer(cc, 0, TEMP1);
	}

	/**
	 * Test method for
	 * {@link asgn2Containers.RefrigeratedContainer#RefrigeratedContainer(ContainerCode, Integer, Integer)}
	 */
	@Test(expected = Exception.class)
	public final void negativeGrossWeightRC() throws InvalidContainerException {
		new RefrigeratedContainer(cc, NEGATIVEGROSSWT, TEMP1);
	}

	/**
	 * Test method for
	 * {@link asgn2Containers.RefrigeratedContainer#RefrigeratedContainer(ContainerCode, Integer, Integer)}
	 */
	@Test(expected = Exception.class)
	public final void nullGrossWeightRC() throws InvalidContainerException {
		new RefrigeratedContainer(cc, null, TEMP1);
	}

	// Testing getTemperature method

	/**
	 * Test method for
	 * {@link asgn2Containers.RefrigeratedContainer#getTemperature()}.
	 */
	@Test
	public final void testGetTemperatureRC() {
		assertEquals(rc.getTemperature(), TEMP1);
	}

	/**
	 * Test method for
	 * {@link asgn2Containers.RefrigeratedContainer#getTemperature()}.
	 */
	@Test
	public final void wrongGetTemperatureRC() {
		assertFalse(rc.getTemperature() == TEMP2);
	}

	// Testing setTemperature method

	/**
	 * Test method for
	 * {@link asgn2Containers.RefrigeratedContainer#setTemperature(Integer)}.
	 */
	@Test
	public final void testSetTemperatureRC() {
		rc.setTemperature(TEMP2);
		assertTrue(rc.getTemperature() == TEMP2);
	}

	/**
	 * Test method for
	 * {@link asgn2Containers.RefrigeratedContainer#setTemperature(Integer)}.
	 */
	@Test
	public final void wrongSetTemperatureRC() {
		rc.setTemperature(TEMP2);
		assertFalse(rc.getTemperature() == TEMP1);
	}

	/* Testing DangerousGoodsContainer */

	// Testing for Exceptions

	/**
	 * Test method for
	 * {@link asgn2Containers.DangerousGoodsContainer#DangerousGoodsContainer(ContainerCode, Integer, Integer)}
	 */
	@Test(expected = Exception.class)
	public final void highGrossWeightDGC() throws InvalidContainerException {
		new DangerousGoodsContainer(cc, HIGROSSWT, CAT1);
	}

	/**
	 * Test method for
	 * {@link asgn2Containers.DangerousGoodsContainer#DangerousGoodsContainer(ContainerCode, Integer, Integer)}
	 */
	@Test(expected = Exception.class)
	public final void lowGrossWeightDGC() throws InvalidContainerException {
		new DangerousGoodsContainer(cc, LOWGROSSWT, CAT1);
	}

	/**
	 * Test method for
	 * {@link asgn2Containers.DangerousGoodsContainer#DangerousGoodsContainer(ContainerCode, Integer, Integer)}
	 */
	@Test(expected = Exception.class)
	public final void zeroGrossWeightDGC() throws InvalidContainerException {
		new DangerousGoodsContainer(cc, 0, CAT1);
	}

	/**
	 * Test method for
	 * {@link asgn2Containers.DangerousGoodsContainer#DangerousGoodsContainer(ContainerCode, Integer, Integer)}
	 */
	@Test(expected = Exception.class)
	public final void negativeGrossWeightDGC() throws InvalidContainerException {
		new DangerousGoodsContainer(cc, NEGATIVEGROSSWT, CAT1);
	}

	/**
	 * Test method for
	 * {@link asgn2Containers.DangerousGoodsContainer#DangerousGoodsContainer(ContainerCode, Integer, Integer)}
	 */
	@Test(expected = Exception.class)
	public final void nullGrossWeightDGC() throws InvalidContainerException {
		new DangerousGoodsContainer(cc, null, CAT1);
	}

	/**
	 * Test method for
	 * {@link asgn2Containers.DangerousGoodsContainer#DangerousGoodsContainer(ContainerCode, Integer, Integer)}
	 */
	@Test(expected = Exception.class)
	public final void highCategoryDGC() throws InvalidContainerException {
		new DangerousGoodsContainer(cc, GROSSWT, HICAT);
	}

	/**
	 * Test method for
	 * {@link asgn2Containers.DangerousGoodsContainer#DangerousGoodsContainer(ContainerCode, Integer, Integer)}
	 */
	@Test(expected = Exception.class)
	public final void negativeCategoryDGC() throws InvalidContainerException {
		new DangerousGoodsContainer(cc, GROSSWT, NEGCAT);
	}

	/**
	 * Test method for
	 * {@link asgn2Containers.DangerousGoodsContainer#DangerousGoodsContainer(ContainerCode, Integer, Integer)}
	 */
	@Test(expected = Exception.class)
	public final void zeroCategoryDGC() throws InvalidContainerException {
		new DangerousGoodsContainer(cc, GROSSWT, 0);
	}

	// Testing getCategory() method

	/**
	 * Test method for
	 * {@link asgn2Containers.DangerousGoodsContainer#getCategory()}.
	 */
	@Test
	public final void testGetCategoryDGC() {
		assertEquals(dgc.getCategory(), CAT1);
	}

	/**
	 * Test method for
	 * {@link asgn2Containers.DangerousGoodsContainer#getCategory()}.
	 */
	@Test
	public final void wrongGetCategoryDGC() {
		assertFalse(dgc.getCategory() == CAT2);
	}

}
