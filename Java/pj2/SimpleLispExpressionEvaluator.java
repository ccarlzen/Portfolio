/************************************************************************************
 *Requirements:
 *
 * - Design and implement an algorithm that uses Java API stacks to evaluate a 
 *   Valid Lisp expression composed of the four basic operators and integer values. 
 * - Valid tokens in an expression are '(',')','+','-','*','/',and positive integers (>=0)
 * - In case of errors, your program must throw SimpleLispExpressionEvaluatorException.
 * - Display result as floating point number with at 2 decimal places
 * - Negative number is not a valid "input" operand, e.g. (+ -2 3) 
 *   However, you may create a negative number using parentheses, e.g. (+ (-2)3)
 * - There may be any number of blank spaces, >= 0, in between tokens
 *   Thus, the following expressions are valid:
 *   	(+   (-6)3)
 *   	(/(+20 30)) *
 *************************************************************************************/
 
 /* Edited by: Casey Carlzen    */

import java.util.*;

public class SimpleLispExpressionEvaluator
{
    // Current input Lisp expression
    private String inputExpr;

    // Main expression stack & current operation stack, see algorithm in evaluate()
    private Stack<Object> exprStack;
    private Stack<Double> currentOpStack;


    // default constructor
    // set inputExpr to "" 
    // create stack objects
    public SimpleLispExpressionEvaluator()
    {
    	this("");
    }

    // default constructor
    // set inputExpr to inputExpression 
    // create stack objects
    public SimpleLispExpressionEvaluator(String inputExpression) 
    {
    	if (isValidInput(inputExpression))
    		inputExpr = inputExpression;
    	else
    		inputExpr = "";
    	exprStack = new Stack<Object>();
    	currentOpStack= new Stack<Double>();
    }

    // set inputExpr to inputExpression 
    // clear stack objects
    public void reset(String inputExpression) 
    {
    	if (isValidInput(inputExpression))
    		inputExpr = inputExpression;
    	else
    		inputExpr = "";
    	exprStack.clear();
    	currentOpStack.clear();
    }

    // returns true if inputExpression is valid Lisp expression
    // throws SimpleLispExpressionEvaluatorException when input is not valid
    private boolean isValidInput(String inputExpression)
    {
    	Stack<Character> parenBalance = new Stack<Character>();
    	Scanner inputExprScanner = new Scanner(inputExpression);
    	inputExprScanner = inputExprScanner.useDelimiter("\\s*");
    	boolean isValid = true;
    	
    	
    	
    	while (inputExprScanner.hasNext() && isValid)
    	{
    		// cases for scanned operands
    		if (inputExprScanner.hasNextInt())
    		{
    			String dataString = inputExprScanner.findInLine("\\d+");
    			int dataInt = Integer.parseInt(dataString);
    			// operands may not precede initial open parentheses
    			// operands may not follow closing parentheses
    			if (parenBalance.isEmpty())
    			{
    				isValid = false;
    				throw new SimpleLispExpressionEvaluatorException("Input cannot begin with integer");
    			}
    			else
    			{ 
    				// operands must be positive
    				if (dataInt < 0)
    				{
    					isValid = false;
    					throw new SimpleLispExpressionEvaluatorException("Operands must be non-negative");
    				}
    			}
    		}
    		// cases for non-integer characters
    		else
    		{
    			// read and store next character
    			char nextNonIntToken = inputExprScanner.next().charAt(0);
    			
    			switch (nextNonIntToken)
    			{
    			case '(':
    				// expression cannot end with '('
    				if (inputExprScanner.hasNext())
    				{
    					// read and store next character
    					char nextNonIntTokenTwo = inputExprScanner.next().charAt(0);
    					
    					// an operator must follow open parentheses
        				switch (nextNonIntTokenTwo)
        				{
        				// valid cases
        				// store '(' in stack to check parentheses balance
        				case '+':
        				case '-':
        				case '*':
        				case '/':
        					parenBalance.push(nextNonIntToken);
        					break;
        				// any other character is invalid
        				default:
        					isValid = false;
        					throw new SimpleLispExpressionEvaluatorException("Operator must directly follow open parentheses");
        				}    				
    				}
    				// nothing follows '('
    				else 
    				{
    					isValid = false;
    					throw new SimpleLispExpressionEvaluatorException("Unbalanced parentheses");
    				}
    				break;
    			case ')':
    				// more closing parentheses than opening, unbalanced
    				if (parenBalance.isEmpty())
    				{
    					isValid = false;
    					throw new SimpleLispExpressionEvaluatorException("Unbalanced parentheses");
    				}
    				// stack has 1 or more items, pop to match
    				// check more for end of expression
    				else
    				{
    					parenBalance.pop();
    					// invalid tokens after closing parentheses
    					if (parenBalance.isEmpty() && inputExprScanner.hasNext())
    					{
    						isValid = false;
    						throw new SimpleLispExpressionEvaluatorException("Character(s) exsist outside of closing parentheses");
    					}
    				}
    				break;
    			// operators must follow open parentheses, if they appear anywhere else they are invalid
    			case '+':
    			case '-':
    			case '*':
    			case '/':
    				isValid = false;
    				throw new SimpleLispExpressionEvaluatorException("Improper operator");
    			// any other character is invalid
    			default:
    				isValid = false;
    				throw new SimpleLispExpressionEvaluatorException("Improper token");
    			}
    		}
    	}
    	
    	return (isValid);
    }
   
    

    // This function evaluate current operator with its operands
    // See complete algorithm in evaluate()
    //
    // Main Steps:
    // 		Pop operands from exprStack and push them onto 
    // 			currentOpStack until you find an operator
    //  	Apply the operator to the operands on currentOpStack
    //          Push the result into exprStack
    //
    private void evaluateCurrentOperation()
    {
    	int operandCounter = 0;
    	while (exprStack.peek().getClass() == Double.class)
    	{
    		currentOpStack.push(new Double(exprStack.pop().toString()));
    		operandCounter++;
    	}
    	
    	char operator = exprStack.pop().toString().charAt(0);
    	switch (operator)
    	{
    		case '+':
    					exprStack.push(sumCurrentOp());
    					break;
    		case '-':
    					exprStack.push(differenceCurrentOp(operandCounter));
    					break;
    		case '*':
    					exprStack.push(productCurrentOp());
    					break;
    		case '/':
    					exprStack.push(quotientCurrentOp(operandCounter));
    					break;
    		default:
    					throw new SimpleLispExpressionEvaluatorException("Unexpected Error");
    	}
    }
    
    private Double sumCurrentOp()
    {
    	double summation = 0;
    	while (!currentOpStack.empty())
    	{
    		summation += currentOpStack.pop().doubleValue();
    	}
    	Double result = new Double(summation);
    	return result;
    }
    
    private Double differenceCurrentOp(int stackSize)
    {
    	double difference = 0;
    	if (stackSize == 1)
    	{
    		difference -= currentOpStack.pop().doubleValue();
    	}
    	else
    	{
    		difference = currentOpStack.pop().doubleValue();
    		while (!currentOpStack.empty())
    		{
    			difference -= currentOpStack.pop().doubleValue();
    		}
    	}
    	Double result = new Double(difference);
    	return result;
    }
    
    private Double productCurrentOp()
    {
    	double product = 1;
    	while(!currentOpStack.empty())
    	{
    		product *= currentOpStack.pop().doubleValue();
    	}
    	Double result = new Double(product);
    	return result;
    }
    
    private Double quotientCurrentOp(int stackSize)
    {
    	double quotient;
    	if (stackSize == 1)
    	{
    		if (currentOpStack.peek().doubleValue() != 0)
    			quotient = 1.0 / currentOpStack.pop().doubleValue();
    		else
    			throw new SimpleLispExpressionEvaluatorException("Cannot divide by zero");
    	}
    	else
    	{
    		quotient = currentOpStack.pop().doubleValue();
    		while (!currentOpStack.empty())
    		{
    			if (currentOpStack.peek().doubleValue() != 0)
    				quotient /= currentOpStack.pop().doubleValue();
    			else
    				throw new SimpleLispExpressionEvaluatorException("Cannot divide by zero");
    		}
    	}
    	Double result = new Double(quotient);
    	return result;
    }

    /**
     * This function evaluates current Lisp expression in inputExpr
     * It return result of the expression 
     *
     * The algorithm:  
     *
     * Step 1   Scan the tokens in the string.
     * Step 2		If you see an operand, push operand object onto the exprStack
     * Step 3  	    	If you see "(", next token should be an operator
     * Step 4  		If you see an operator, push operator object onto the exprStack
     * Step 5		If you see ")", do steps 6,7,8 in evaluateCurrentOperation() :
     * Step 6			Pop operands and push them onto currentOpStack 
     * 					until you find an operator
     * Step 7			Apply the operator to the operands on currentOpStack
     * Step 8			Push the result into exprStack
     * Step 9    If you run out of tokens, the value on the top of exprStack is
     *           is the result of the expression.
     */
    public double evaluate()
    {
        // use scanner to tokenize inputExpr
        @SuppressWarnings("resource")
		Scanner inputExprScanner = new Scanner(inputExpr);
        
        // Use zero or more white space as delimiter
        inputExprScanner = inputExprScanner.useDelimiter("\\s*");

        // Step 1: Scan the tokens in the string.
        while (inputExprScanner.hasNext())
        {
		
     	    // Step 2: If you see an operand, push operand object onto the exprStack
            if (inputExprScanner.hasNextInt())
            {
                // This force scanner to grab all of the digits
                // Otherwise, it will just get one char
                String dataString = inputExprScanner.findInLine("\\d+");
                exprStack.push(new Double(dataString));

            }
            else
            {
                // Get next token, only one char in string token
                String aToken = inputExprScanner.next();
                char item = aToken.charAt(0);
                
                switch (item)
                {
     		    // Step 3: If you see "(", next token should be an operator
     		    // Step 4: If you see an operator, push operator object onto the exprStack
     		    // Step 5: If you see ")"  do steps 6,7,8 in evaluateCurrentOperation() :
                	case '(':
                				break;
                	case '+':
                	case '-':
                	case '*':
                	case '/':
                				exprStack.push(new Character(item));
                				break;
                	case ')':
                				evaluateCurrentOperation();
                				break;
                    default:  // error
                        throw new SimpleLispExpressionEvaluatorException(item + " is not a legal expression operator");
                } // end switch
            } // end else
        } // end while
        // Step 9: If you run out of tokens, the value on the top of exprStack is
        //         is the result of the expression.
        //
        //         return result

        Double tempResult = new Double(exprStack.pop().toString());
        double result = tempResult.doubleValue();
        return result;
    }
    

    //=====================================================================

    // This static method is used by main() only
    private static void evaluateExprTest(String s, SimpleLispExpressionEvaluator expr)
    {
        Double result;
        System.out.println("Expression " + s);
	expr.reset(s);
        result = expr.evaluate();
        System.out.printf("Result %.2f\n", result);
        System.out.println("-----------------------------");
    }

    // define few test cases, exception may happen 
    public static void main (String args[])
    {
        SimpleLispExpressionEvaluator expr= new SimpleLispExpressionEvaluator();
        String[] test = new String[6];
        test[0] = "(+ (- 6) (* 2 3 4) (/ (+ 3) (* 1) (- 2 3 1)))";
        test[1] = "(+ (- 632) (* 21 3 4) (/ (+ 32) (* 1) (- 21 3 1)))";
        test[2] = "(+ (/ 2) (* 2) (/ (+ 1) (+ 1) (- 2 1 )))";
        test[3] = "(+ (/2))";
        test[4] = "(+ (/2 3 0))";
        test[5] = "(+ (/ 2) (* 2) (/ (+ 1) (+ 3) (- 2 1 ))))";

        for (int i = 0; i < test.length; i++)
        {
        	try
        	{
        		evaluateExprTest(test[i], expr);
        	}
        	catch (Exception e)
        	{
        		System.out.println(e);
        	}
        }
    }
}

