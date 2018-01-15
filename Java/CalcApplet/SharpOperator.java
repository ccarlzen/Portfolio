/* 
 * Created on:	1/29/2015
 * Last edit:	2/5/2015
 */

/**
 * Dummy operator to aid in evaluation algorithm
 * 
 * @author Casey
 *
 */
public class SharpOperator extends Operator {

	public Operand execute(Operand opd1, Operand opd2)
	{
		return null;
	}
	
	public int priority()
	{
		return 0;
	}
	
}
