/* 
 * Created on:	1/29/2015
 * Last edit:	2/5/2015
 */

import java.util.*;

/**
 * Abstract class that defines the type Operator
 * 
 * @author Casey
 *
 */
public abstract class Operator {
	static HashMap<String, Operator> operators = new HashMap();
	
	public abstract Operand execute(Operand opd1, Operand opd2);
	public abstract int priority();
	
	public static boolean check(String tok)
	{
		if (operators.containsKey(tok))
			return true;
		else
			return false;
	}
	
}
