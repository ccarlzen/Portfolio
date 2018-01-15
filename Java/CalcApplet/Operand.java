/* 
 * Created on:	1/29/2015
 * Last edit:	2/5/2015
 */

/**
 * This class defines basic methods for operands in integer form. 
 * Instances of this class are used on the stack to facilitate the
 * evaluation algorithm
 * 
 * @author Casey Carlzen
 */
public class Operand {
	// Constructors
	/**
	 * Creates an instance of Operand in response to string input
	 * 
	 * @param tok This constructor expects a String that contains
	 * an integer
	 */
	Operand(String tok)
	{
		val = Integer.parseInt(tok);
	}
	
	/**
	 * Creates an instance of Operand in response to string input
	 * 
	 * @param value This constructor expects a valid integer value
	 */
	Operand(int value)
	{
		val = value;
	}
	
	// Members
	/**
	 *  Stores the value of the Operand
	 */
	private int val;
	
	// Methods
	/**
	 * Provides a means of checking if a String is a valid Operand. Returns
	 * Booklean value
	 * 
	 * @param tok String to be checked
	 * @return Returns true if valid; false otherwise.
	 */
	public static boolean check(String tok)
	{
		try
		{
			int i = Integer.parseInt(tok);
		}
		catch(NumberFormatException nfe)
		{
			return false;
		}
		return true;
	}
	
	/**
	 * Acessor method to allow value of Operand to be reached
	 * 
	 * @return Returns the value of the Operand object
	 */
	public int getValue()
	{
		return val;
	}

}
