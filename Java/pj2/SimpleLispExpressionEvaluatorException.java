/************************************************************************************
 * 
 * Do not modify this file.
 *
 * SimpleLispExpressionEvaluatorException class
 *
 * It is used by SimpleLispExpressionEvaluator 
 *
 *************************************************************************************/


@SuppressWarnings("serial")
public class SimpleLispExpressionEvaluatorException
		extends RuntimeException

{
    public SimpleLispExpressionEvaluatorException()
    {
	this("");
    }

    public SimpleLispExpressionEvaluatorException(String errorMsg) 
    {
	super(errorMsg);
    }

}
