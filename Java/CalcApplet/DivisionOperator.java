/* 
 * Created on:	1/29/2015
 * Last edit:	2/5/2015
 */

/**
 * Provides division functionality
 * 
 * @author Casey
 *
 */
public class DivisionOperator extends Operator {
	
	public Operand execute(Operand opd1, Operand opd2)
	{
		Operand op = new Operand(opd1.getValue()/opd2.getValue());
		return op;
	}
	
	public int priority()
	{
		return 3;
	}

}
