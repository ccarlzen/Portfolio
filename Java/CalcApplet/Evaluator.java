/* 
 * Created on:	1/29/2015
 * Last edit:	2/5/2015
 */

import java.util.*;

/**
 * The main purpose of this class is to evaluate a simple expression through
 * its main function eval(). This class expects input to be a string
 * containing the expression without errors.
 * 
 *  <p>The form accepted by eval() is as follows: 
 *  1+2*4/5-2
 */
public class Evaluator {
	
	private Stack<Operand> opdStack;
	private Stack<Operator> oprStack;
	
	/**
	 * Default constructor: Creates an object on which to perform
	 * evaluations
	 */
	public Evaluator()
	{
		// Initialize stacks to hold tokens and facilitate evaluation operation
		opdStack = new Stack<Operand>();
		oprStack = new Stack<Operator>();
		// Add a singleton for each operator into HashMap in Operator class
		Operator.operators.put("+", new AdditionOperator());
		Operator.operators.put("-", new SubtractionOperator());
		Operator.operators.put("*", new MultiplicationOperator());
		Operator.operators.put("/", new DivisionOperator());
		Operator.operators.put("#", new SharpOperator());
		Operator.operators.put("!", new ExclamationOperator());
	}
	
	/**
	 * Primary method used to evaluate expressions passed as String to the
	 * method. 
	 * 
	 * @param expr Must take the form mentioned in class description because
	 * this method does not check for invalid input
	 */
	public int eval(String expr)
	{
		String tok;	
		// Delimiters used in separating expression
		String delimiters = "+-*/#! ";	
		// Append '!' to string to aid evaluation loop
		expr = expr + "!";			
		// Place a SharpOperator on the bottom of oprStack for more efficient
		// loop algorithm
		oprStack.push(new SharpOperator());
			
		StringTokenizer st = new StringTokenizer(expr,delimiters,true);
		
		// Tokenizes the expression string
		while(st.hasMoreTokens())
		{
			if(!(tok = st.nextToken()).equals(" "))
			{
				if(Operand.check(tok))
				{
					opdStack.push(new Operand(tok));
				}
				else
				{
					if(!Operator.check(tok))
					{
						System.out.println("*****invalid token*****");
						System.exit(1);
					}
					
					Operator newOpr = (Operator)Operator.operators.get(tok);
					
					while( ((Operator)oprStack.peek()).priority() >= newOpr.priority())
					{
						Operator oldOpr = ((Operator)oprStack.pop());
						Operand op2 = (Operand)opdStack.pop();
						Operand op1 = (Operand)opdStack.pop();
						opdStack.push(oldOpr.execute(op1,op2));
					}
					oprStack.push(newOpr);
				}
			}
		}
		int result = ((Operand)opdStack.pop()).getValue();
		// Loop is complete clear stacks for next call
		opdStack.clear();
		oprStack.clear();			
		return result;		
	}
}

