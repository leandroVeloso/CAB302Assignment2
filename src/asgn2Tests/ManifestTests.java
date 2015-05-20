package asgn2Tests;

/* Some valid container codes used in the tests below:
 * INKU2633836
 * KOCU8090115
 * MSCU6639871
 * CSQU3054389
 * QUTU7200318
 * IBMU4882351
 */

/**
 * Test class for the CargoManifest class
 * 
 * @author Leandro Rodrigues n9382909
 * @version 1.0
 */

import org.junit.Before;
import org.junit.Test;

import asgn1Collection.ListingException;
import asgn2Codes.ContainerCode;
import asgn2Containers.DangerousGoodsContainer;
import asgn2Containers.FreightContainer;
import asgn2Containers.GeneralGoodsContainer;
import asgn2Containers.RefrigeratedContainer;
import asgn2Exceptions.InvalidCodeException;
import asgn2Exceptions.InvalidContainerException;
import asgn2Exceptions.ManifestException;
import asgn2Manifests.CargoManifest;
import static org.junit.Assert.*;

public class ManifestTests {
	private String CODE1 = "INKU2633836";
	private String CODE2 = "KOCU8090115";
	private ContainerCode containerCode1, containerCode2;
	private GeneralGoodsContainer generalContainer1, generalContainer2;
	private DangerousGoodsContainer dangerousContainer1, dangerousContainer2;
	private RefrigeratedContainer refrigeratedContainer1, refrigeratedContainer2;
	private CargoManifest cargoManifest1;
	
	@Before
	public void setUpBefore() throws Exception {
		containerCode1 = new ContainerCode(CODE1);
		containerCode2 = new ContainerCode(CODE2);
	}
	
	/*
	 *  Tests if the constructor throws an exception when all three parameters are negative
	 *  See constructor: {@link asgn2Manifests.CargoManifest#CargoManifest(Integer, Integer, Integer)}
	 */
	@Test(expected=ManifestException.class)
	public void constructorAllParametersNegative() throws ManifestException {
		cargoManifest1 = new CargoManifest(-20, -1, Integer.MIN_VALUE);
	}
	
	/*
	 *  Tests if the constructor throws an exception when both numStacks and maxHeight are negative
	 */
	@Test(expected=ManifestException.class)
	public void constructorSomeNegativeParameterCase1() throws ManifestException {
		cargoManifest1 = new CargoManifest(-9999, -1, 50);
	}
	
	/*
	 *  Tests if the constructor throws an exception when both numStacks and maxWeight are negative
	 */
	@Test(expected=ManifestException.class)
	public void constructorSomeNegativeParameterCase2() throws ManifestException {
		cargoManifest1 = new CargoManifest(-1, 1, Integer.MIN_VALUE);
	}
	
	/*
	 *  Tests if the constructor throws an exception when both maxHeight and maxWeight are negative
	 */
	@Test(expected=ManifestException.class)
	public void constructorSomeNegativeParameterCase3() throws ManifestException {
		cargoManifest1 = new CargoManifest(50, Integer.MIN_VALUE, -1);
	}
	
	/*
	 *  Tests if the constructor throws an exception when numStacks is negative
	 */
	@Test(expected=ManifestException.class)
	public void constructorNegativeNumStacks() throws ManifestException {
		cargoManifest1 = new CargoManifest(Integer.MIN_VALUE, 1, 2);
	}
	
	/*
	 *  Tests if the constructor throws an exception when maxHeight is negative
	 */
	@Test(expected=ManifestException.class)
	public void constructorNegativeMaxHeight() throws ManifestException {
		cargoManifest1 = new CargoManifest(0, Integer.MIN_VALUE, 0);
	}
	
	/*
	 *  Tests if the constructor throws an exception when maxWeight is negative
	 */
	@Test(expected=ManifestException.class)
	public void constructorNegativeMaxWeight() throws ManifestException {
		cargoManifest1 = new CargoManifest(0, Integer.MIN_VALUE, 0);
	}
	
	/*
	 *  Tests if a CargoManifest is instantiated properly when all parameters are positive
	 */
	@Test
	public void constructorAllPositiveParameters() throws ManifestException {
		cargoManifest1 = new CargoManifest(1, Integer.MAX_VALUE, 0);
	}
	
	/*
	 *  Tests if the constructor throws an exception when all parameters are  negative min integer value
	 */
	@Test(expected=ManifestException.class)
	public void constructorMinValue() throws ManifestException {
		cargoManifest1 = new CargoManifest(Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE);
	}
	
	/*
	 *  Tests if a CargoManifest is instantiated properly when all parameters are positive max integer value
	 */
	@Test
	public void constructorMaxValue() throws ManifestException {
		cargoManifest1 = new CargoManifest(Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE);
	}
	
	/*
	 *  Tests if the method throws an exception when you try to load a new general container with a repeated code
	 */
	@Test(expected=ManifestException.class)
	public void loadTwoGeneralContainersWithSameCode() throws ManifestException {
		// Create a cargomanifest
		cargoManifest1 = new CargoManifest(Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE);

		// Create General Good Containers
		generalContainer1 = new GeneralGoodsContainer(containerCode1, 5);
		generalContainer2 = new GeneralGoodsContainer(containerCode1, 10);
		
		// Load the containers
		cargoManifest1.loadContainer(generalContainer1);
		cargoManifest1.loadContainer(generalContainer2);
	}
	
	/*
	 *  Tests if the method throws an exception when you try to load a new dangerous container with a repeated code
	 */
	@Test(expected=ManifestException.class)
	public void loadTwoDangerousContainersWithSameCode() throws ManifestException {
		// Create a cargomanifest
		cargoManifest1 = new CargoManifest(Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE);

		// Create General Good Containers
		dangerousContainer1 = new DangerousGoodsContainer(containerCode1, 5, 1);
		dangerousContainer2 = new DangerousGoodsContainer(containerCode1, 10, 1);
		
		// Load the containers
		cargoManifest1.loadContainer(dangerousContainer1);
		cargoManifest1.loadContainer(dangerousContainer2);
	}
	
	/*
	 *  Tests if the method throws an exception when you try to load a new refrigerated container with a repeated code
	 */
	@Test(expected=ManifestException.class)
	public void loadTwoRefrigeratedContainersWithSameCode() throws ManifestException {
		// Create a cargomanifest
		cargoManifest1 = new CargoManifest(5, 5, 50);

		// Create General Good Containers
		refrigeratedContainer1 = new RefrigeratedContainer(containerCode1, 5, 1);
		refrigeratedContainer2 = new RefrigeratedContainer(containerCode1, 10, 1);
		
		// Load the containers
		cargoManifest1.loadContainer(refrigeratedContainer1);
		cargoManifest1.loadContainer(refrigeratedContainer2);
	}
	
	/*
	 *  Tests if the method throws an exception when you try to load a new refrigerated container with the same code of a previous loaded general container
	 */
	@Test(expected=ManifestException.class)
	public void loadRefrigeratedAndGeneralContainersWithSameCode() throws ManifestException {
		// Create a cargomanifest
		cargoManifest1 = new CargoManifest(5, 5, 50);

		// Create General Good Containers
		generalContainer1 = new GeneralGoodsContainer(containerCode1, 5);
		refrigeratedContainer1 = new RefrigeratedContainer(containerCode1, 10, 1);
		
		// Load the containers
		cargoManifest1.loadContainer(refrigeratedContainer1);
		cargoManifest1.loadContainer(generalContainer1);
	}
	
	
	
}
