package asgn2Codes;

import asgn2Exceptions.InvalidCodeException;

/* Note to self:
 * Use option "-noqualifier java.lang" when generating Javadoc from this
 * file so that, for instance, we get "String" instead of
 * "java.lang.String".
 */

/**
 * This class provides cargo container codes in a format similar to that
 * prescribed by international standard ISO 6346.  (The only difference
 * is that we use a simpler algorithm for calculating the check digit.)
 * <p>
 * A container code is an 11-character string consisting of the following
 * fields:
 * <ul>
 * <li>
 * An <em>Owner Code</em> which uniquely identifies the company that owns
 * the container.  The owner code consists of three upper-case letters.
 * (To ensure uniqueness in practice, owner codes must be registered at
 * the <em>Bureau International des Containers</em> in Paris.)
 * </li>
 * <li>
 * A <em>Category Identifier</em> which identifies the type of container.
 * The identifier is one of three letters, 'U', 'J' or 'Z'.  For our
 * purposes the category identifier is <em>always</em> 'U', which denotes a
 * freight container.
 * </li>
 * <li>
 * A <em>Serial Number</em> used by the owner to uniquely identify the
 * container.  The number consists of six digits.
 * </li>
 * <li>
 * A <em>Check Digit</em> used to ensure that the number has not been
 * transcribed incorrectly.  It consists of a single digit derived from the
 * other 10 characters according to a specific algorithm, described below.
 * </li>
 * </ul>
 * <p>
 * <strong>Calculating the check digit:</strong> ISO 6346 specifies a
 * particular algorithm for deriving the check digit from the
 * other 10 characters in the code.  However, because this algorithm is
 * rather complicated, we use a simpler version for this assignment.
 * Our algorithm works as follows:
 * <ol>
 * <li>
 * Each of the 10 characters in the container code (excluding the check
 * digit) is treated as a number.  The digits '0' to '9' each have
 * their numerical value.  The upper case alphabetic letters 'A' to
 * 'Z' are treated as the numbers '0' to '25', respectively.
 * </li>
 * <li>
 * These 10 numbers are added up.
 * </li>
 * <li>
 * The check digit is the least-significant digit in the sum produced
 * by the previous step.
 * </li>
 * </ol>
 * For example, consider container code <code>MSCU6639871</code> which
 * has owner code <code>MSC</code>, category identifier <code>U</code>,
 * serial number <code>663987</code> and check digit <code>1</code>.  We can 
 * confirm that this code is valid by treating the letters as numbers (e.g.,
 * '<code>M</code>' is 12, '<code>S</code>' is 18, etc) and calculating the
 * following sum.
 * <p>
 * <center>
 * 12 + 18 + 2 + 20 + 6 + 6 + 3 + 9 + 8 + 7 = 91
 * </center>
 * <p>
 * The check digit is then the least-sigificant digit in the number 91,
 * i.e., '<code>1</code>', thus confirming that container code 
 * <code>MSCU6639871</code> is valid.
 * 
 * @author Leandro Rodrigues n9382909 
 * @version 1.0
 */ 
public class ContainerCode {

	private String code;
	
	/**
	 * Constructs a new container code.
	 * 
	 * @param code the container code as a string
	 * @throws InvalidCodeException if the container code is not eleven characters long; if the
	 * Owner Code does not consist of three upper-case letters; if the
	 * Category Identifier is not 'U'; if the Serial Number does not consist
	 * of six digits; or if the Check Digit is incorrect.
	 */
	public ContainerCode(String code) throws InvalidCodeException {
		checkCode(code);
		//If no exception is thrown and the program is still running, store the code into the object
		this.code = code;
	}
	
	/**
	 * Check if a given container code is valid
	 * 
	 * @param code the container code as a string to be checked
	 * @return true if is a valid container code
	 * @throws InvalidCodeException when is a invalid container code
	 */
	private void checkCode(String code) throws InvalidCodeException{
		//Check if code length is different than 11
		if(code.length() != 11)
			throw new InvalidCodeException("Code is not eleven characters long");
		//Check if owner code digits (First three digits) are upper-case letters
		if(!(Character.isUpperCase(code.charAt(0)) && Character.isUpperCase(code.charAt(1)) && Character.isUpperCase(code.charAt(2))))
			throw new InvalidCodeException("Owner Code does not consist of three upper-case letters");
		//Check if category identifier is equal to U
		if(code.charAt(3) != 'U')
			throw new InvalidCodeException("Category Identifier is not 'U");
		//Check if serial number only contains numeric digits
		if(!code.substring(4, 10).matches("[0-9]+"))
			throw new InvalidCodeException("Serial Number does not consist of six digits");
		//Check if check digit is valid
		if(calculateCheckDigit(code) != Character.getNumericValue(code.charAt(10)))
			throw new InvalidCodeException("Check Digit is incorrect");
	}
	
	/**
	 * Calculate check digit for a given container code
	 * 
	 * @param code the container code as a string to calculate the check digit
	 * @return int calculated check digit
	 */
	private int calculateCheckDigit(String code){
		int sum = 0;
		//Iterates through the code, excluding the last digit
		for (int i = 0; i < code.length()-1; i++) {
			//Get char at actual position and convert to a string
			String actualCharacter = code.charAt(i)+"";
			//Check if string  is a number
	        if (actualCharacter.matches("[0-9]"))
	        	//If is a number convert to a integer and add it to sum variable
	        	sum = sum + Integer.parseInt(actualCharacter);
	        else{
	        	//If is not a number, convert to lower case and get the letter numeric position within the alphabet
	        	int charNumericValue = actualCharacter.toLowerCase().charAt(0) - 97;
	        	//Add its numeric position to sum variable
	        	sum = sum + charNumericValue;
	        }
	    }
		//Return  least-significant digit in the number
		return (sum % 10);
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return this.code;
	}

	
	/**
	 * Returns true iff the given object is a container code and has an
	 * identical value to this code.
	 * 
	 * @param obj an object
	 * @return true if the given object is a container code with the
	 * same string value as this code, false otherwise
	 */
	@Override
	public boolean equals(Object obj) {
		//Verify if object is an instance of Container code and if it has the same code
		if (obj instanceof ContainerCode && this.code.equals(obj.toString()))
			return true;
		else
			return false;
	}
}

