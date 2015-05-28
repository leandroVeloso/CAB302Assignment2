package asgn2Manifests;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Stack;

import asgn2Codes.ContainerCode;
import asgn2Containers.FreightContainer;
import asgn2Exceptions.ManifestException;

/**
 * A class for managing a container ship's cargo manifest.  It
 * allows freight containers of various types to be loaded and
 * unloaded, within various constraints.
 * <p>
 * In particular, the ship's captain has set the following rules
 * for loading of new containers:
 * <ol>
 * <li>
 * New containers may be loaded only if doing so does not exceed
 * the ship's weight limit.
 * </li>
 * <li>
 * New containers are to be loaded as close to the bridge as possible.
 * (Stack number zero is nearest the bridge.)
 * </li>
 * <li>
 * A new container may be added to a stack only if doing so will
 * not exceed the maximum allowed stack height.
 * <li>
 * A new container may be loaded only if a container with the same
 * code is not already on board.
 * </li>
 * <li>
 * Stacks of containers must be homogeneous, i.e., each stack must
 * consist of containers of one type (general,
 * refrigerated or dangerous goods) only.
 * </li>
 * </ol>
 * <p>
 * Furthermore, since the containers are moved by an overhead
 * crane, a container can be unloaded only if it is on top of
 * a stack.
 *  
 * @author Leandro Rodrigues & Brendan Rothwell n8540683
 * @version 1.0
 */
public class CargoManifest {
	private Integer numStacks;
	private Integer maxHeight;
	private Integer maxWeight;
	private ArrayList<Stack<FreightContainer>> manifest;
	Stack<FreightContainer> stack;

	/**
	 * Constructs a new cargo manifest in preparation for a voyage.
	 * When a cargo manifest is constructed the specific cargo
	 * parameters for the voyage are set, including the number of
	 * stack spaces available on the deck (which depends on the deck configuration
	 * for the voyage), the maximum allowed height of any stack (which depends on
	 * the weather conditions expected for the
	 * voyage), and the total weight of containers allowed onboard (which depends
	 * on the amount of ballast and fuel being carried).
	 * 
	 * @param numStacks the number of stacks that can be accommodated on deck
	 * @param maxHeight the maximum allowable height of any stack
	 * @param maxWeight the maximum weight of containers allowed on board 
	 * (in tonnes)
	 * @throws ManifestException if negative numbers are given for any of the
	 * parameters
	 */
	public CargoManifest(Integer numStacks, Integer maxHeight, Integer maxWeight)
	throws ManifestException {
		// Verifies negative inputs
		if(numStacks < 0 || maxHeight < 0 || maxWeight < 0)
			throw new ManifestException("Inputs can't be negative");
		// Set parameters
		this.numStacks = numStacks;
		this.maxHeight = maxHeight;
		this.maxWeight = maxWeight;
		// Creates a arrayList of stacks
		manifest = new ArrayList<Stack<FreightContainer>>();
		// Create empty stacks
		for (int i = 0; i < this.numStacks; i++){
			stack = new Stack <FreightContainer>();
			manifest.add(stack);
		}
	}

	/**
	 * Loads a freight container onto the ship, provided that it can be
	 * accommodated within the five rules set by the captain.
	 * 
	 * @param newContainer the new freight container to be loaded
	 * @throws ManifestException if adding this container would exceed
	 * the ship's weight limit; if a container with the same code is
	 * already on board; or if no suitable space can be found for this
	 * container
	 */
	public void loadContainer(FreightContainer newContainer) throws ManifestException {
		// First checks for a repeated code
		if(whichStack(newContainer.getCode()) != null)
			throw new ManifestException("Container with same code is aready on board");
		// After checks for the ship weight limit
		if(getActualWeight() + newContainer.getGrossWeight() > this.maxWeight)
			throw new ManifestException("Adding this container would exceed the ship's weight limit");
		
		// Create a hashmap with a summary of every stack.  Key = stack index. Value = Its Height / Its Class type
		HashMap<Integer, String> stackSpaceAndType = checkStacks();
		
		// Controller to assert if was possible to load the container
		int controller = 0 ;
		// Iterates through all stacks
		for(int i = 0; i < stackSpaceAndType.size(); i++){
			// Gets stack's HashMap value
			String classAndHeightString = stackSpaceAndType.get(i);
			// Split stacks's HashMap value into an array. Index 0 is its height, index 1 is its class
			String[] stackStringHeight = classAndHeightString.split("/");
			// Check if newContainer is the same type of the actual stack and also if the there's an available space in the stack
			if(stackStringHeight[1].equals(newContainer.getClass().toString()) && Integer.parseInt(stackStringHeight[0]) < this.maxHeight){
				// If so controller gets 1, means it was possible to find a place for the container
				controller = 1;
				// Adds the container into the stack and breaks the loop
				manifest.get(i).push(newContainer);
				break;
			}
			// Else checks if stack is empty, if so controller gets 1 (form the same reason that before) and adds container and breaks the loop
			else if(stackStringHeight[1].equals("Empty")){
				controller = 1;
				manifest.get(i).push(newContainer);
				break;
			}
		}
		
		// If it was not possible to find a place to this container (controller == 0) then throws an exception
		if(controller == 0)
			throw new ManifestException("No suitable space can be found for this container");
		
		
	}
	
	/*
	 * Helper Function to get actual of the ship
	 */
	private Integer getActualWeight(){
		Integer actualWeight = 0;
		// Iterates through all the stacks adding all containers weight
		for (int i = 0; i < manifest.size(); i++) {
			for (int j = 0; j <  manifest.get(i).size(); j++)
				actualWeight = actualWeight + manifest.get(i).elementAt(j).getGrossWeight();
		}
		
		return actualWeight;
	}
	
	/*
	 * Helper Function to get information about height and class of each stack
	 */
	private HashMap<Integer, String> checkStacks(){
		//Create a hashmap with a summary of every stack.  Key = stack index. Value = Its Height / Its Class type
		HashMap<Integer, String> stackSpaceAndType = new HashMap<Integer, String>();
		
		// Iterates through all the stacks checking if its empty or what is its height/class type
		for (int i = 0; i < manifest.size(); i++) 
			if(manifest.get(i).size() > 0)
				stackSpaceAndType.put(i, manifest.get(i).size()+"/"+manifest.get(i).peek().getClass().toString());
			else
				stackSpaceAndType.put(i, "Empty/Empty");
		
		return stackSpaceAndType;
	}
	

	/**
	 * Unloads a particular container from the ship, provided that
	 * it is accessible (i.e., on top of a stack).
	 * 
	 * @param containerId the code of the container to be unloaded
	 * @throws ManifestException if the container is not accessible because
	 * it's not on the top of a stack (including the case where it's not on board
	 * the ship at all)
	 */
	public void unloadContainer(ContainerCode containerId) throws ManifestException {
		int controller = 0;
		// Iterates through all stacks checking if the container at the top (peek) has the same code of the one the user is trying to unload. 
		for (int i = 0; i < manifest.size(); i++) {
			if(!manifest.get(i).isEmpty()){
				if(manifest.get(i).peek().getCode().equals(containerId)){
					// If exist a container on the top of the stack with the code, then remove it and assign 1 to the controller
					manifest.get(i).pop();
					controller = 1;
				}
			}
		}
		
		// If it was not possible to find a container at the top of any stack (controller == 0) then throws an exception
		if(controller == 0)
			throw new ManifestException("Container Code not on the top of any container stack or is not on board");
			
	}

	
	/**
	 * Returns which stack holds a particular container, if any.  The
	 * container of interest is identified by its unique
	 * code.  Constant <code>null</code> is returned if the container is
	 * not on board.
	 * 
	 * @param queryContainer the container code for the container of interest
	 * @return the number of the stack with the container in it, or <code>null</code>
	 * if the container is not on board
	 */
	public Integer whichStack(ContainerCode queryContainer) {
		// Iterates through all stacks checking if exists a container in the stack with the same code, if so return its stack index
		for (int i = 0; i < manifest.size(); i++) {
			for (int j = 0; j <  manifest.get(i).size(); j++)
				if(manifest.get(i).elementAt(j).getCode().equals(queryContainer))
					return i;
		}
		
		// If it was not possible to find the container then returns null
		return null;
	}

	
	/**
	 * Returns how high in its stack a particular container is.  The container of
	 * interest is identified by its unique code.  Height is measured in the
	 * number of containers, counting from zero.  Thus the container at the bottom
	 * of a stack is at "height" 0, the container above it is at height 1, and so on.
	 * Constant <code>null</code> is returned if the container is
	 * not on board.
	 * 
	 * @param queryContainer the container code for the container of interest
	 * @return the container's height in the stack, or <code>null</code>
	 * if the container is not on board
	 */
	public Integer howHigh(ContainerCode queryContainer) {
		// Iterates through all stacks checking if exists a container in the stack with the same code, if so returns its height
		for (int i = 0; i < manifest.size(); i++) {
			for (int j = 0; j <  manifest.get(i).size(); j++)
				if(manifest.get(i).elementAt(j).getCode().equals(queryContainer))
					return j;
		}
		
		// If it was not possible to find the container then returns null
		return null;
	}


	/**
	 * Returns the contents of a particular stack as an array,
	 * starting with the bottommost container at position zero in the array.
	 * 
	 * @param stackNo the number of the stack of interest
	 * @return the stack's freight containers as an array
	 * @throws ManifestException if there is no such stack on the ship
	 */
	public FreightContainer[] toArray(Integer stackNo) throws ManifestException {
		// If exists a stack with the given index then convert it to an array and returns it
		if(manifest.size() >= stackNo){
			int size = manifest.get(stackNo).size();
			FreightContainer[]convertedStack = new FreightContainer[size];
			convertedStack = manifest.get(stackNo).toArray(convertedStack);

			return convertedStack;
		}
		// If there's no such stack then throws an exception
		else
			throw new ManifestException("There is no such stack on this ship");
	}

	
	/* ***** toString methods added to support the GUI ***** */
	
	public String toString(ContainerCode toFind) {
		//Some variables here are used and not declared. You can work it out 
		String toReturn = "";
		for (int i = 0; i < manifest.size(); ++i) {
			ArrayList<FreightContainer> currentStack = null;
			// Tries to convert an Array as an arraylist
			try {
				currentStack = new ArrayList<FreightContainer>(Arrays.asList(this.toArray(i)));
			} catch (ManifestException e) {
				e.printStackTrace();
			}
			toReturn += "|";
			for (int j = 0; j < currentStack.size(); ++j) {
				if (toFind != null && currentStack.get(j).getCode().equals(toFind))
					toReturn += "|*" + currentStack.get(j).getCode().toString() + "*|";
				else
					toReturn += "| " + currentStack.get(j).getCode().toString() + " |";
			}
			if (currentStack.size() == 0)
				toReturn +=  "|  ||\n";
			else
				toReturn += "|\n";
		}
		return toReturn;
	}

	@Override
	public String toString() {
		return toString(null);
	}
}
