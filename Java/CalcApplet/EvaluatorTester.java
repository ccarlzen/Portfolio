/* 
 * Created on:	1/29/2015
 * Last edit:	2/5/2015
 */

/**
 * The purpose of this class is to test the functionality of the eval() 
 * method of the Evaluator class. Command line arguments are used to 
 * facilitate user input.
 * 
 * @author Casey Carlzen
 */
public class EvaluatorTester {
	/**
	 * 
	 * @param 	args User supplied expressions must be in normal form,
	 * 			eg. 1+6*7/2-3 and separated with spaces.
	 */
	public static void main(String[] args)
	{
		
		Evaluator anEvaluator = new Evaluator();
		for(String arg:args)
		{
			System.out.println(arg + " = " + anEvaluator.eval(arg));
		}	
						
	}
}
