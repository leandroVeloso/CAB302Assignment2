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

import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;

import asgn2Codes.ContainerCode;
import asgn2Containers.DangerousGoodsContainer;
import asgn2Containers.FreightContainer;
import asgn2Containers.GeneralGoodsContainer;
import asgn2Containers.RefrigeratedContainer;
import asgn2Exceptions.InvalidContainerException;
import asgn2Exceptions.ManifestException;
import asgn2Manifests.CargoManifest;
import static org.junit.Assert.*;

public class ManifestTests {
	private String CODE1 = "INKU2633836";
	private String CODE2 = "KOCU8090115";
	private String CODE3 = "JXLU0654334";
	private String CODE4 = "ESQU4642767";
	private String CODE5 = "ASPU9139465";
	private FreightContainer[] containersArray, containersArray2;
	private ContainerCode containerCode1, containerCode2, containerCode3, containerCode4, containerCode5;
	private GeneralGoodsContainer generalContainer1, generalContainer2;
	private DangerousGoodsContainer dangerousContainer1, dangerousContainer2, dangerousContainer3;
	private RefrigeratedContainer refrigeratedContainer1, refrigeratedContainer2;
	private CargoManifest cargoManifest1;
	
	@Before
	public void setUpBefore() throws Exception {
		containerCode1 = new ContainerCode(CODE1);
		containerCode2 = new ContainerCode(CODE2);
		containerCode3 = new ContainerCode(CODE3);
		containerCode4 = new ContainerCode(CODE4);
		containerCode5 = new ContainerCode(CODE5);
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
	public void loadTwoGeneralContainersWithSameCode() throws ManifestException, InvalidContainerException {
		// Create a cargomanifest
		cargoManifest1 = new CargoManifest(Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE);

		// Create General Good Containers
		generalContainer1 = new GeneralGoodsContainer(containerCode1, 5);
		generalContainer2 = new GeneralGoodsContainer(containerCode1, 10);
		
		// Load containers
		cargoManifest1.loadContainer(generalContainer1);
		cargoManifest1.loadContainer(generalContainer2);
	}
	
	/*
	 *  Tests if the method throws an exception when you try to load a new dangerous container with a repeated code
	 */
	@Test(expected=ManifestException.class)
	public void loadTwoDangerousContainersWithSameCode() throws ManifestException, InvalidContainerException {
		// Create a cargomanifest
		cargoManifest1 = new CargoManifest(Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE);

		// Create Dangerous Good Containers
		dangerousContainer1 = new DangerousGoodsContainer(containerCode1, 5, 1);
		dangerousContainer2 = new DangerousGoodsContainer(containerCode1, 10, 1);
		
		// Load containers
		cargoManifest1.loadContainer(dangerousContainer1);
		cargoManifest1.loadContainer(dangerousContainer2);
	}
	
	/*
	 *  Tests if the method throws an exception when you try to load a new refrigerated container with a repeated code
	 */
	@Test(expected=ManifestException.class)
	public void loadTwoRefrigeratedContainersWithSameCode() throws ManifestException, InvalidContainerException {
		// Create a cargomanifest
		cargoManifest1 = new CargoManifest(5, 5, 50);

		// Create Refrigerated Good Containers
		refrigeratedContainer1 = new RefrigeratedContainer(containerCode1, 5, 1);
		refrigeratedContainer2 = new RefrigeratedContainer(containerCode1, 10, 1);
		
		// Load containers
		cargoManifest1.loadContainer(refrigeratedContainer1);
		cargoManifest1.loadContainer(refrigeratedContainer2);
	}
	
	/*
	 *  Tests if the method throws an exception when you try to load a new refrigerated container with the same code of a previous loaded general container
	 */
	@Test(expected=ManifestException.class)
	public void loadRefrigeratedAndGeneralContainersWithSameCode() throws ManifestException, InvalidContainerException {
		// Create a cargomanifest
		cargoManifest1 = new CargoManifest(5, 5, 50);

		// Create General Good Container and Refrigerated Container
		generalContainer1 = new GeneralGoodsContainer(containerCode1, 5);
		refrigeratedContainer1 = new RefrigeratedContainer(containerCode1, 10, 1);
		
		// Load containers
		cargoManifest1.loadContainer(refrigeratedContainer1);
		cargoManifest1.loadContainer(generalContainer1);
	}
	
	/*
	 *  Tests if the method throws an exception when you try to load a new refrigerated container with the same code of a previous loaded dangerous container
	 */
	@Test(expected=ManifestException.class)
	public void loadRefrigeratedAndDangerousContainersWithSameCode() throws ManifestException, InvalidContainerException {
		// Create a cargomanifest
		cargoManifest1 = new CargoManifest(5, 5, 50);

		// Create Refrigerated Container and Refrigerated Container
		refrigeratedContainer1 = new RefrigeratedContainer(containerCode1, 10, 1);
		dangerousContainer1 = new DangerousGoodsContainer(containerCode1, 10, 2);
		
		// Load containers
		cargoManifest1.loadContainer(refrigeratedContainer1);
		cargoManifest1.loadContainer(dangerousContainer1);
	}
	
	/*
	 *  Tests if the method throws an exception when you try to load a new general container with the same code of a previous loaded dangerous container
	 */
	@Test(expected=ManifestException.class)
	public void loadGeneralAndDangerousContainersWithSameCode() throws ManifestException, InvalidContainerException {
		// Create a cargomanifest
		cargoManifest1 = new CargoManifest(5, 5, 50);

		// Create General Good Container and Dangerous Container
		generalContainer1 = new GeneralGoodsContainer(containerCode1, 5);
		dangerousContainer1 = new DangerousGoodsContainer(containerCode1, 10, 2);
		
		// Load containers
		cargoManifest1.loadContainer(generalContainer1);
		cargoManifest1.loadContainer(dangerousContainer1);
	}
	
	/*
	 *  Tests if the method throws an exception when you try to load a new container with a repeated code after a couple of containers loaded
	 */
	@Test(expected=ManifestException.class)
	public void loadContainerRepeatedCode() throws ManifestException, InvalidContainerException {
		// Create a cargomanifest
		cargoManifest1 = new CargoManifest(5, 5, 50);

		// Create a couple of Containers
		generalContainer1 = new GeneralGoodsContainer(containerCode1, 5);
		dangerousContainer1 = new DangerousGoodsContainer(containerCode2, 10, 2);
		refrigeratedContainer1 = new RefrigeratedContainer(containerCode3, 10, 1);
		dangerousContainer2 = new DangerousGoodsContainer(containerCode4, 10, 2);
		generalContainer2 = new GeneralGoodsContainer(containerCode1, 5);
		
		// Load containers
		cargoManifest1.loadContainer(generalContainer1);
		cargoManifest1.loadContainer(dangerousContainer1);
		cargoManifest1.loadContainer(refrigeratedContainer1);
		cargoManifest1.loadContainer(dangerousContainer2);
		cargoManifest1.loadContainer(generalContainer2);
	}
	
	/*
	 *  Tests if the method throws an exception when you try to load a new container and there's no more weight left in the ship.
	 *  Case 1: same type of container (General Goods)
	 */
	@Test(expected=ManifestException.class)
	public void loadContainerInsufficientWeightCase1() throws ManifestException, InvalidContainerException {
		// Create a cargomanifest
		cargoManifest1 = new CargoManifest(5, 5, 50);

		// Create Containers
		generalContainer1 = new GeneralGoodsContainer(containerCode1, 30);
		generalContainer2 = new GeneralGoodsContainer(containerCode1, 21);
		
		// Load containers
		cargoManifest1.loadContainer(generalContainer1);
		cargoManifest1.loadContainer(generalContainer2);
	}
	
	/*
	 *  Tests if the method throws an exception when you try to load a new container and there's no more weight left in the ship.
	 *  Case 2: same type of container (Refrigerated Goods)
	 */
	@Test(expected=ManifestException.class)
	public void loadContainerInsufficientWeightCase2() throws ManifestException, InvalidContainerException {
		// Create a cargomanifest
		cargoManifest1 = new CargoManifest(5, 5, 9);

		// Create Containers
		refrigeratedContainer1 = new RefrigeratedContainer(containerCode3, 4, 3);
		refrigeratedContainer2 = new RefrigeratedContainer(containerCode4, 6, 1);
		
		// Load containers
		cargoManifest1.loadContainer(refrigeratedContainer1);
		cargoManifest1.loadContainer(refrigeratedContainer1);
	}
	
	/*
	 *  Tests if the method throws an exception when you try to load a new container and there's no more weight left in the ship.
	 *  Case 3: same type of container (Dangerous Goods)
	 */
	@Test(expected=ManifestException.class)
	public void loadContainerInsufficientWeightCase3() throws ManifestException, InvalidContainerException {
		// Create a cargomanifest
		cargoManifest1 = new CargoManifest(5, 5, 20);

		// Create Containers
		dangerousContainer1 = new DangerousGoodsContainer(containerCode4, 19, 5);
		dangerousContainer2 = new DangerousGoodsContainer(containerCode1, 2, 2);
		
		// Load containers
		cargoManifest1.loadContainer(dangerousContainer1);
		cargoManifest1.loadContainer(dangerousContainer2);
	}
	
	/*
	 *  Tests if the method throws an exception when you try to load a new container and there's no more weight left in the ship.
	 *  Case 4: different type of containers
	 */
	@Test(expected=ManifestException.class)
	public void loadContainerInsufficientWeightCase4() throws ManifestException, InvalidContainerException {
		// Create a cargomanifest
		cargoManifest1 = new CargoManifest(5, 5, 20);

		// Create Containers
		dangerousContainer1 = new DangerousGoodsContainer(containerCode4, 5, 5);
		refrigeratedContainer1 = new RefrigeratedContainer(containerCode3, 5, 1);
		generalContainer1 = new GeneralGoodsContainer(containerCode1, 10);
		refrigeratedContainer2 = new RefrigeratedContainer(containerCode5, 1, 1);
		
		// Load containers
		cargoManifest1.loadContainer(dangerousContainer1);
		cargoManifest1.loadContainer(refrigeratedContainer1);
		cargoManifest1.loadContainer(generalContainer1);
		cargoManifest1.loadContainer(refrigeratedContainer2);
	}
	
	/*
	 *  Tests if the method throws an exception when you try to load a new container and there's no more space left in the ship.
	 *  Case 1: There's no more space for a new stack of different container type but there's space in a different type stack
	 */
	@Test(expected=ManifestException.class)
	public void loadContainerInsufficientSpaceCase1() throws ManifestException, InvalidContainerException {
		// Create a cargomanifest
		cargoManifest1 = new CargoManifest(2, 5, 25);

		// Create Containers
		dangerousContainer1 = new DangerousGoodsContainer(containerCode4, 5, 5);
		refrigeratedContainer1 = new RefrigeratedContainer(containerCode3, 5, 1);
		generalContainer1 = new GeneralGoodsContainer(containerCode1, 10);
		
		// Load containers
		cargoManifest1.loadContainer(dangerousContainer1);
		cargoManifest1.loadContainer(refrigeratedContainer1);
		cargoManifest1.loadContainer(generalContainer1);
	}
	
	/*
	 *  Tests if the method throws an exception when you try to load a new container and there's no more space left in the ship.
	 *  Case 1: There's no more space for a new stack and there's no more space in the same type stack
	 */
	@Test(expected=ManifestException.class)
	public void loadContainerInsufficientSpaceCase2() throws ManifestException, InvalidContainerException {
		// Create a cargomanifest
		cargoManifest1 = new CargoManifest(2, 2, 50);

		// Create Containers
		dangerousContainer1 = new DangerousGoodsContainer(containerCode4, 5, 5);
		dangerousContainer2 = new DangerousGoodsContainer(containerCode5, 5, 1);
		dangerousContainer3 = new DangerousGoodsContainer(containerCode2, 5, 1);
		refrigeratedContainer1 = new RefrigeratedContainer(containerCode3, 5, 1);
		refrigeratedContainer2 = new RefrigeratedContainer(containerCode1, 10, 3);
		
		// Load containers
		cargoManifest1.loadContainer(dangerousContainer1);
		cargoManifest1.loadContainer(refrigeratedContainer1);
		cargoManifest1.loadContainer(refrigeratedContainer2);
		cargoManifest1.loadContainer(dangerousContainer2);
		cargoManifest1.loadContainer(dangerousContainer3);
	}
	
	
	/*
	 *  Tests if the method loads containers of the same type properly when there's no constrains about the weight, height / space
	 */
	@Test
	public void loadContainerSameType() throws ManifestException, InvalidContainerException {
		// Create a cargomanifest
		cargoManifest1 = new CargoManifest(2, 1, 50);

		// Create Containers
		dangerousContainer1 = new DangerousGoodsContainer(containerCode4, 5, 5);
		dangerousContainer2 = new DangerousGoodsContainer(containerCode5, 5, 1);
		
		// Load containers
		cargoManifest1.loadContainer(dangerousContainer1);
		cargoManifest1.loadContainer(dangerousContainer2);
	}
	
	/*
	 *  Tests if the method loads containers of different types properly when there's no constrains about the weight, height / space
	 */
	@Test
	public void loadContainerDifferentType() throws ManifestException, InvalidContainerException {
		// Create a cargomanifest
		cargoManifest1 = new CargoManifest(3, 2, 50);

		// Create Containers
		dangerousContainer1 = new DangerousGoodsContainer(containerCode4, 5, 5);
		dangerousContainer2 = new DangerousGoodsContainer(containerCode5, 5, 1);
		refrigeratedContainer1 = new RefrigeratedContainer(containerCode3, 5, 1);
		refrigeratedContainer2 = new RefrigeratedContainer(containerCode1, 10, 3);
		generalContainer2 = new GeneralGoodsContainer(containerCode2, 10);
		
		// Load containers
		cargoManifest1.loadContainer(dangerousContainer1);
		cargoManifest1.loadContainer(refrigeratedContainer1);
		cargoManifest1.loadContainer(refrigeratedContainer2);
		cargoManifest1.loadContainer(dangerousContainer2);
		cargoManifest1.loadContainer(generalContainer2);
	}
	
	/*
	 *  Tests if the method throws an exception when you try to unload a container when it's not on stack's top.
	 *  Case 1: All stacks have different type
	 */
	@Test(expected=ManifestException.class)
	public void unloadContainerNotOnTopCase1() throws ManifestException, InvalidContainerException {
		// Create a cargomanifest
		cargoManifest1 = new CargoManifest(3, 2, 50);

		// Create and Load Container
		dangerousContainer1 = new DangerousGoodsContainer(containerCode1, 5, 5);
		cargoManifest1.loadContainer(dangerousContainer1);
		// Create and Load Container
		refrigeratedContainer1 = new RefrigeratedContainer(containerCode2, 5, 1);
		cargoManifest1.loadContainer(refrigeratedContainer1);
		// Create and Load Container
		refrigeratedContainer1 = new RefrigeratedContainer(containerCode3, 5, 1);
		cargoManifest1.loadContainer(refrigeratedContainer1);
		// Create and Load Container
		refrigeratedContainer1 = new RefrigeratedContainer(containerCode4, 5, 1);
		cargoManifest1.loadContainer(refrigeratedContainer1);
		// Create and Load Container
		dangerousContainer1 = new DangerousGoodsContainer(containerCode5, 5, 5);
		cargoManifest1.loadContainer(dangerousContainer1);
		
		cargoManifest1.unloadContainer(containerCode2);
	}
	
	/*
	 *  Tests if the method throws an exception when you try to unload a container when it's not on stack's top.
	 *  Case 2: All stacks have the same type 
	 */
	@Test(expected=ManifestException.class)
	public void unloadContainerNotOnTopCase2() throws ManifestException, InvalidContainerException {
		// Create a cargomanifest
		cargoManifest1 = new CargoManifest(3, 2, 50);

		// Create and Load Container
		dangerousContainer1 = new DangerousGoodsContainer(containerCode1, 5, 5);
		cargoManifest1.loadContainer(dangerousContainer1);
		// Create and Load Container
		dangerousContainer1 = new DangerousGoodsContainer(containerCode2, 5, 5);
		cargoManifest1.loadContainer(dangerousContainer1);
		// Create and Load Container
		dangerousContainer1 = new DangerousGoodsContainer(containerCode3, 5, 5);
		cargoManifest1.loadContainer(dangerousContainer1);
		// Create and Load Container
		dangerousContainer1 = new DangerousGoodsContainer(containerCode4, 5, 5);
		cargoManifest1.loadContainer(dangerousContainer1);
		// Create and Load Container
		dangerousContainer1 = new DangerousGoodsContainer(containerCode5, 5, 5);
		cargoManifest1.loadContainer(dangerousContainer1);
		
		cargoManifest1.unloadContainer(containerCode3);
	}
	
	/*
	 *  Tests if the method throws an exception when you try to unload a container when it's no on stack's top. 
	 */
	@Test(expected=ManifestException.class)
	public void unloadContainerNotOnBoard() throws ManifestException, InvalidContainerException {
		// Create a cargomanifest
		cargoManifest1 = new CargoManifest(3, 2, 50);

		// Create and Load Container
		dangerousContainer1 = new DangerousGoodsContainer(containerCode1, 5, 5);
		cargoManifest1.loadContainer(dangerousContainer1);
		// Create and Load Container
		dangerousContainer1 = new DangerousGoodsContainer(containerCode2, 5, 5);
		cargoManifest1.loadContainer(dangerousContainer1);
		// Create and Load Container
		dangerousContainer1 = new DangerousGoodsContainer(containerCode3, 5, 5);
		cargoManifest1.loadContainer(dangerousContainer1);
		// Create and Load Container
		dangerousContainer1 = new DangerousGoodsContainer(containerCode4, 5, 5);
		cargoManifest1.loadContainer(dangerousContainer1);

		cargoManifest1.unloadContainer(containerCode2);
	}
	
	/*
	 *  Tests if the method unloads a container when it's on stack's top.
	 *  Case 1: All stacks have the same type 
	 */
	@Test
	public void unloadContainerOnTopCase1() throws ManifestException, InvalidContainerException {
		// Create a cargomanifest
		cargoManifest1 = new CargoManifest(3, 2, 50);

		// Create and Load Container
		dangerousContainer1 = new DangerousGoodsContainer(containerCode1, 5, 5);
		cargoManifest1.loadContainer(dangerousContainer1);
		// Create and Load Container
		dangerousContainer1 = new DangerousGoodsContainer(containerCode2, 5, 5);
		cargoManifest1.loadContainer(dangerousContainer1);
		// Create and Load Container
		dangerousContainer1 = new DangerousGoodsContainer(containerCode3, 5, 5);
		cargoManifest1.loadContainer(dangerousContainer1);
		// Create and Load Container
		dangerousContainer1 = new DangerousGoodsContainer(containerCode4, 5, 5);
		cargoManifest1.loadContainer(dangerousContainer1);
		// Create and Load Container
		dangerousContainer1 = new DangerousGoodsContainer(containerCode5, 5, 5);
		cargoManifest1.loadContainer(dangerousContainer1);
		
		cargoManifest1.unloadContainer(containerCode3);
	}
	
	/*
	 *  Tests if the method unloads a container when it's on stack's top.
	 *  Case 2: Stacks have the different type 
	 */
	@Test
	public void unloadContainerOnTopCase2() throws ManifestException, InvalidContainerException {
		// Create a cargomanifest
		cargoManifest1 = new CargoManifest(3, 2, 50);

		// Create and Load Container
		dangerousContainer1 = new DangerousGoodsContainer(containerCode1, 5, 5);
		cargoManifest1.loadContainer(dangerousContainer1);
		// Create and Load Container
		refrigeratedContainer1 = new RefrigeratedContainer(containerCode2, 5, 1);
		cargoManifest1.loadContainer(refrigeratedContainer1);
		// Create and Load Container
		refrigeratedContainer1 = new RefrigeratedContainer(containerCode5, 5, 1);
		cargoManifest1.loadContainer(refrigeratedContainer1);
		// Create and Load Container
		dangerousContainer1 = new DangerousGoodsContainer(containerCode4, 5, 5);
		cargoManifest1.loadContainer(dangerousContainer1);
		// Create and Load Container
		dangerousContainer1 = new DangerousGoodsContainer(containerCode3, 5, 5);
		cargoManifest1.loadContainer(dangerousContainer1);
		
		cargoManifest1.unloadContainer(containerCode4);
	}
	
	/*
	 *  Tests if the method unloads a container when it's on stack's top.
	 *  Case 2: Stacks have the different type and container is the only one in the stack 
	 */
	@Test
	public void unloadContainerOnTopCase3() throws ManifestException, InvalidContainerException {
		// Create a cargomanifest
		cargoManifest1 = new CargoManifest(3, 2, 50);

		// Create and Load Container
		dangerousContainer1 = new DangerousGoodsContainer(containerCode1, 5, 5);
		cargoManifest1.loadContainer(dangerousContainer1);
		// Create and Load Container
		refrigeratedContainer1 = new RefrigeratedContainer(containerCode2, 5, 1);
		cargoManifest1.loadContainer(refrigeratedContainer1);
		// Create and Load Container
		refrigeratedContainer1 = new RefrigeratedContainer(containerCode5, 5, 1);
		cargoManifest1.loadContainer(refrigeratedContainer1);
		// Create and Load Container
		dangerousContainer1 = new DangerousGoodsContainer(containerCode4, 5, 5);
		cargoManifest1.loadContainer(dangerousContainer1);
		// Create and Load Container
		dangerousContainer1 = new DangerousGoodsContainer(containerCode3, 5, 5);
		cargoManifest1.loadContainer(dangerousContainer1);
		
		cargoManifest1.unloadContainer(containerCode3);
	}
	
	/*
	 *  Tests if the method whichStack return null when the container it's not on board
	 */
	@Test
	public void wichStackNotOnBoard() throws ManifestException, InvalidContainerException {
		//Case 1: no container on board
		// Create a cargomanifest
		cargoManifest1 = new CargoManifest(3, 2, 50);
		assertNull(cargoManifest1.whichStack(containerCode3));
		
		//Case 2: no container on board and ship is set with 0 stack size and height
		// Create a cargomanifest
		cargoManifest1 = new CargoManifest(0, 0, 0);
		assertNull(cargoManifest1.whichStack(containerCode5));
		
		//Case 3: no container with the given code
		// Create a cargomanifest
		cargoManifest1 = new CargoManifest(3, 2, 50);
		// Create and Load Container
		dangerousContainer1 = new DangerousGoodsContainer(containerCode1, 5, 5);
		cargoManifest1.loadContainer(dangerousContainer1);
		// Create and Load Container
		refrigeratedContainer1 = new RefrigeratedContainer(containerCode2, 5, 1);
		cargoManifest1.loadContainer(refrigeratedContainer1);
		// Create and Load Container
		refrigeratedContainer1 = new RefrigeratedContainer(containerCode5, 5, 1);
		cargoManifest1.loadContainer(refrigeratedContainer1);
		assertNull(cargoManifest1.whichStack(containerCode3));
	}
	
	/*
	 *  Tests if the method whichStack return the right stack position of a container
	 */
	@Test
	public void wichStackOnBoard() throws ManifestException, InvalidContainerException {
		//Case 1: Only one container
		// Create a cargomanifest
		cargoManifest1 = new CargoManifest(3, 2, 50);
		// Create and Load Container
		dangerousContainer1 = new DangerousGoodsContainer(containerCode1, 5, 5);
		cargoManifest1.loadContainer(dangerousContainer1);
		assertEquals(Integer.valueOf(0), cargoManifest1.whichStack(containerCode1));
		
		//Case 2: Some containers loaded, wanted container is the last one loaded
		// Create a cargomanifest
		cargoManifest1 = new CargoManifest(3, 2, 50);
		// Create and Load Container
		dangerousContainer1 = new DangerousGoodsContainer(containerCode5, 5, 5);
		cargoManifest1.loadContainer(dangerousContainer1);
		// Create and Load Container
		refrigeratedContainer1 = new RefrigeratedContainer(containerCode2, 5, 1);
		cargoManifest1.loadContainer(refrigeratedContainer1);
		// Create and Load Container
		dangerousContainer1 = new DangerousGoodsContainer(containerCode1, 5, 5);
		cargoManifest1.loadContainer(dangerousContainer1);
		// Create and Load Container
		refrigeratedContainer1 = new RefrigeratedContainer(containerCode4, 5, 1);
		cargoManifest1.loadContainer(refrigeratedContainer1);
		// Create and Load Container		
		generalContainer1 = new GeneralGoodsContainer(containerCode3, 10);
		cargoManifest1.loadContainer(generalContainer1);
		
		assertEquals(Integer.valueOf(2), cargoManifest1.whichStack(containerCode3));
		
		//Case 3: Some containers loaded, expect right index
		assertEquals(Integer.valueOf(0), cargoManifest1.whichStack(containerCode5));
		assertEquals(Integer.valueOf(1), cargoManifest1.whichStack(containerCode4));
		assertEquals(Integer.valueOf(1), cargoManifest1.whichStack(containerCode2));
		assertEquals(Integer.valueOf(0), cargoManifest1.whichStack(containerCode1));
				
		//Case 5: Some containers loaded, expect a wrong index
		assertNotEquals(Integer.valueOf(0), cargoManifest1.whichStack(containerCode4));
		assertNotEquals(Integer.valueOf(2), cargoManifest1.whichStack(containerCode1));
		assertNotEquals(Integer.valueOf(1), cargoManifest1.whichStack(containerCode3));
		assertNotEquals(Integer.valueOf(0), cargoManifest1.whichStack(containerCode2));
		assertNotEquals(Integer.valueOf(2), cargoManifest1.whichStack(containerCode5));
		
	}
	
	/*
	 *  Tests if the method howHigh return null when the container it's not on board
	 */
	@Test
	public void howHighNotOnBoard() throws ManifestException, InvalidContainerException {
		//Case 1: no container on board
		// Create a cargomanifest
		cargoManifest1 = new CargoManifest(3, 2, 50);
		assertNull(cargoManifest1.howHigh(containerCode3));
		
		//Case 2: no container on board and ship is set with 0 stack size and height
		// Create a cargomanifest
		cargoManifest1 = new CargoManifest(0, 0, 0);
		assertNull(cargoManifest1.howHigh(containerCode5));
		
		//Case 3: no container with the given code
		// Create a cargomanifest
		cargoManifest1 = new CargoManifest(3, 2, 50);
		// Create and Load Container
		dangerousContainer1 = new DangerousGoodsContainer(containerCode1, 5, 5);
		cargoManifest1.loadContainer(dangerousContainer1);
		// Create and Load Container
		refrigeratedContainer1 = new RefrigeratedContainer(containerCode2, 5, 1);
		cargoManifest1.loadContainer(refrigeratedContainer1);
		// Create and Load Container
		refrigeratedContainer1 = new RefrigeratedContainer(containerCode5, 5, 1);
		cargoManifest1.loadContainer(refrigeratedContainer1);
		assertNull(cargoManifest1.howHigh(containerCode3));
	}
	
	/*
	 *  Tests if the method whichStack performs the expected action
	 */
	@Test
	public void howHighOnBoard() throws ManifestException, InvalidContainerException {
		//Case 1: Only one container
		// Create a cargomanifest
		cargoManifest1 = new CargoManifest(3, 2, 50);
		// Create and Load Container
		dangerousContainer1 = new DangerousGoodsContainer(containerCode3, 5, 5);
		cargoManifest1.loadContainer(dangerousContainer1);
		assertEquals(Integer.valueOf(0), cargoManifest1.howHigh(containerCode3));
		
		//Case 2: Some containers loaded, wanted container is the last one loaded
		// Create a cargomanifest
		cargoManifest1 = new CargoManifest(3, 2, 50);
		// Create and Load Container
		dangerousContainer1 = new DangerousGoodsContainer(containerCode5, 5, 5);
		cargoManifest1.loadContainer(dangerousContainer1);
		// Create and Load Container
		refrigeratedContainer1 = new RefrigeratedContainer(containerCode2, 5, 1);
		cargoManifest1.loadContainer(refrigeratedContainer1);
		// Create and Load Container
		dangerousContainer1 = new DangerousGoodsContainer(containerCode1, 5, 5);
		cargoManifest1.loadContainer(dangerousContainer1);
		// Create and Load Container
		refrigeratedContainer1 = new RefrigeratedContainer(containerCode4, 5, 1);
		cargoManifest1.loadContainer(refrigeratedContainer1);
		// Create and Load Container		
		generalContainer1 = new GeneralGoodsContainer(containerCode3, 10);
		cargoManifest1.loadContainer(generalContainer1);
		
		assertEquals(Integer.valueOf(0), cargoManifest1.howHigh(containerCode3));
		
		//Case 3: Some containers loaded, expect right height
		assertEquals(Integer.valueOf(0), cargoManifest1.howHigh(containerCode5));
		assertEquals(Integer.valueOf(1), cargoManifest1.howHigh(containerCode4));
		assertEquals(Integer.valueOf(0), cargoManifest1.howHigh(containerCode2));
		assertEquals(Integer.valueOf(1), cargoManifest1.howHigh(containerCode1));
				
		//Case 5: Some containers loaded, expect a wrong height
		assertNotEquals(Integer.valueOf(3), cargoManifest1.howHigh(containerCode4));
		assertNotEquals(Integer.valueOf(0), cargoManifest1.howHigh(containerCode1));
		assertNotEquals(Integer.valueOf(1), cargoManifest1.howHigh(containerCode3));
		assertNotEquals(Integer.valueOf(1), cargoManifest1.howHigh(containerCode2));
		assertNotEquals(Integer.valueOf(3), cargoManifest1.howHigh(containerCode5));
		
	}
	
	/*
	 *  Tests if the method toArray throws an exception when the stack doesn't exist
	 *	Case 1: no container on board
	 */
	@Test(expected=ManifestException.class)
	public void toArrayNotOnBoardCase1() throws ManifestException, InvalidContainerException {
		// Create a cargomanifest
		cargoManifest1 = new CargoManifest(3, 2, 50);
		cargoManifest1.toArray(1);
	}
	
	/*
	 *  Tests if the method toArray throws an exception when the stack doesn't exist
	 *	Case 2: some stacks but such stacks doesn't exist
	 */
	@Test(expected=ManifestException.class)
	public void toArrayNotOnBoardCase2() throws ManifestException, InvalidContainerException {
		// Create a cargomanifest
		cargoManifest1 = new CargoManifest(3, 2, 50);
		// Create and Load Container
		dangerousContainer1 = new DangerousGoodsContainer(containerCode5, 5, 5);
		cargoManifest1.loadContainer(dangerousContainer1);
		// Create and Load Container
		refrigeratedContainer1 = new RefrigeratedContainer(containerCode2, 5, 1);
		cargoManifest1.loadContainer(refrigeratedContainer1);
		// Create and Load Container
		dangerousContainer1 = new DangerousGoodsContainer(containerCode1, 5, 5);
		cargoManifest1.loadContainer(dangerousContainer1);
		// Create and Load Container
		refrigeratedContainer1 = new RefrigeratedContainer(containerCode4, 5, 1);
		cargoManifest1.loadContainer(refrigeratedContainer1);
		// Create and Load Container		
		generalContainer1 = new GeneralGoodsContainer(containerCode3, 10);
		cargoManifest1.loadContainer(generalContainer1);
		
		cargoManifest1.toArray(5);
	}
	
	/*
	 *  Tests if the method toArray performs the expected action
	 */
	@Test
	public void toArrayOnBoard() throws ManifestException, InvalidContainerException {
		containersArray = new FreightContainer[0];
		
		//Case 1: Empty stack
		// Create a cargomanifest
		cargoManifest1 = new CargoManifest(2, 2, 50);
		// Create and Load Container
		dangerousContainer1 = new DangerousGoodsContainer(containerCode3, 5, 5);
		cargoManifest1.loadContainer(dangerousContainer1);
		assertArrayEquals(containersArray, cargoManifest1.toArray(1));
		
		
		// Create a cargomanifest
		cargoManifest1 = new CargoManifest(3, 3, 50);
		// Create and Load Container
		dangerousContainer1 = new DangerousGoodsContainer(containerCode5, 5, 5);
		cargoManifest1.loadContainer(dangerousContainer1);
		// Create and Load Container
		refrigeratedContainer1 = new RefrigeratedContainer(containerCode2, 5, 1);
		cargoManifest1.loadContainer(refrigeratedContainer1);
		// Create and Load Container
		dangerousContainer2 = new DangerousGoodsContainer(containerCode1, 5, 5);
		cargoManifest1.loadContainer(dangerousContainer2);
		// Create and Load Container
		refrigeratedContainer2 = new RefrigeratedContainer(containerCode4, 5, 1);
		cargoManifest1.loadContainer(refrigeratedContainer2);
		// Create and Load Container		
		generalContainer1 = new GeneralGoodsContainer(containerCode3, 10);
		cargoManifest1.loadContainer(generalContainer1);
		
		//Case 2: Some containers loaded, expect right array
		containersArray2 = new FreightContainer[2];
		containersArray2[0] = dangerousContainer1;
		containersArray2[1] = dangerousContainer2;
		assertArrayEquals(containersArray2, cargoManifest1.toArray(0));
		
		containersArray2 = new FreightContainer[2];
		containersArray2[0] = refrigeratedContainer1;
		containersArray2[1] = refrigeratedContainer2;
		assertArrayEquals(containersArray2, cargoManifest1.toArray(1));
		
		containersArray2 = new FreightContainer[1];
		containersArray2[0] = generalContainer1;
		assertArrayEquals(containersArray2, cargoManifest1.toArray(2));
		
		//Case 3: Some containers loaded, expect wrong array
		containersArray2 = new FreightContainer[2];
		containersArray2[0] = refrigeratedContainer2;
		containersArray2[1] = dangerousContainer2;
		assertFalse(cargoManifest1.toArray(1).equals(containersArray2));
		
		containersArray2 = new FreightContainer[3];
		containersArray2[0] = dangerousContainer2;
		assertFalse(cargoManifest1.toArray(0).equals(containersArray2));
		
		containersArray2 = new FreightContainer[1];
		containersArray2[0] = generalContainer1;
		assertFalse(cargoManifest1.toArray(2).equals(containersArray2));
	}
}
