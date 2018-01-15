/*************************************************************************************
 *
 * This class represents a fraction whose numerator and denominator are integers.
 *
 *
 ************************************************************************************/

// Edited by: Casey Carlzen

package PJ1;

public class Fraction implements FractionInterface, Comparable<Fraction>
{
	private	int num;	// Numerator	
	private	int den;	// Denominator	

	public Fraction()
	{
		// set fraction to default = 0/1
		setFraction(0, 1);
	}	// end default constructor

	public Fraction(int numerator, int denominator)
	{
		setFraction(numerator, denominator);
	}	// end constructor

	public void setFraction(int numerator, int denominator)
	{
		if (denominator == 0) 
		{
			throw new ArithmeticException("Cannot divide by zero.");
		}
		else if (denominator < 0) 
		{
			num = -numerator;
			den = -denominator;
		}
		else {
			num = numerator;
			den = denominator;
		}
		reduceToLowestTerms();			
		// return ArithmeticException if initialDenominator is 0
	}	// end setFraction

	public int getNumerator()
	{
		return num;
	}	// end getNumerator

	public int getDenominator()
	{
		return den;
	}	// end getDenominator

	public char getSign()
	{
		if (num < 0) 
			return '-';
		else 
			return '+';
	}	// end getSign

	public void switchSign()
	{
		num = -num;
	}	// change setSign

	public FractionInterface add(FractionInterface secondFraction)
	{
		Fraction returnFraction = new Fraction();
		returnFraction.setFraction(this.num*secondFraction.getDenominator() +
			secondFraction.getNumerator()*this.den, this.den*secondFraction.getDenominator());
		return returnFraction;
		// a/b + c/d is (ad + cb)/(bd)
	}	// end add

	public FractionInterface subtract(FractionInterface secondFraction)
	{
		Fraction returnFraction = new Fraction();
		returnFraction.setFraction(this.num*secondFraction.getDenominator() -
			secondFraction.getNumerator()*this.den, this.den*secondFraction.getDenominator());
		return returnFraction;
		// a/b - c/d is (ad - cb)/(bd)		
	}	// end subtract

	public FractionInterface multiply(FractionInterface secondFraction)
	{
		Fraction returnFraction = new Fraction();
		returnFraction.setFraction(this.num*secondFraction.getNumerator(), 
			this.den*secondFraction.getDenominator());
		return returnFraction;
		// a/b * c/d is (ac)/(bd)
	}	// end multiply

	public FractionInterface divide(FractionInterface secondFraction)
	{
		if (secondFraction.getNumerator() == 0) 
			throw new ArithmeticException("Cannot divide by zero.");
		else 
		{
			Fraction returnFraction = new Fraction();
			returnFraction.setFraction(this.num*secondFraction.getDenominator(), 
				this.den*secondFraction.getNumerator());
			return returnFraction;
		}
		// return ArithmeticException if secondFraction is 0
		// a/b / c/d is (ad)/(bc)
	}	// end divide

	public FractionInterface getReciprocal() 
	{
		if (num == 0)
			throw new ArithmeticException("Cannot divide by zero.");
		else 
		{
			Fraction returnFraction = new Fraction(den, num);
			return returnFraction;
		}
		// return ArithmeticException if secondFraction is 0
	} 	// end getReciprocal


	public boolean equals(Object other)
	{
		if (other.getClass() == this.getClass()) 
		{
			Fraction otherFraction = (Fraction)other;
			otherFraction.reduceToLowestTerms();
			this.reduceToLowestTerms();
			return (otherFraction.getDenominator() == this.getDenominator() &&
				otherFraction.getNumerator() == this.getNumerator());
		}
		else
			return false;
	} 	// end equals


	public int compareTo(Fraction other)
	{
		if (this.equals(other))
			return 0;
		else if (num*1.0/den < other.getNumerator()*1.0/other.getDenominator())
			return -1;
		else
			return 1;
	} // end compareTo

	
	public String toString()
	{
		return num + "/" + den;
	} // end toString


	/** Task: Reduces a fraction to lowest terms. */

        //-----------------------------------------------------------------
        //  private methods start here
        //-----------------------------------------------------------------

	private void reduceToLowestTerms()
	{
		if (num < 0)
		{
			num = -num;
			int gcd = greatestCommonDivisor(num, den);
			num = num/gcd;
			num = -num;
			den = den/gcd;
		}
		else
		{
			int gcd = greatestCommonDivisor(num, den);
			num = num/gcd;
			den = den/gcd;
		}
                // Outline:
                // compute GCD of num & den
                // greatestCommonDivisor works for + numbers.
                // So, you should eliminate - sign
                // then reduce numbers : num/GCD and den/GCD
	}	// end reduceToLowestTerms

  	/** Task: Computes the greatest common secondFraction of two integers.
	 *  @param integerOne	 an integer
	 *  @param integerTwo	 another integer
	 *  @return the greatest common divisor of the two integers */
	private int greatestCommonDivisor(int integerOne, int integerTwo)
	{
		 int result;

		 if (integerOne % integerTwo == 0)
			result = integerTwo;
		 else
			result = greatestCommonDivisor(integerTwo, integerOne % integerTwo);

		 return result;
	}	// end greatestCommonDivisor


	//-----------------------------------------------------------------
	//  Simple test is provided here 

	public static void main(String[] args)
	{
		FractionInterface firstOperand = null;
		FractionInterface secondOperand = null;
		FractionInterface result = null;

		Fraction nineSixteenths = new Fraction(9, 16);	// 9/16
		Fraction oneFourth = new Fraction(1, 4);        // 1/4

		// 7/8 + 9/16
		firstOperand = new Fraction(7, 8);
		result = firstOperand.add(nineSixteenths);
		System.out.println("The sum of " + firstOperand + " and " +
				nineSixteenths + " is \t\t" + result);

		// 9/16 - 7/8
		firstOperand = nineSixteenths;
		secondOperand = new Fraction(7, 8);
		result = firstOperand.subtract(secondOperand);
		System.out.println("The difference of " + firstOperand	+
				" and " +	secondOperand + " is \t" + result);

		// 15/-2 * 1/4
		firstOperand.setFraction(15, -2);
		result = firstOperand.multiply(oneFourth);
		System.out.println("The product of " + firstOperand	+
				" and " +	oneFourth + " is \t" + result);

		// (-21/2) / (3/7)
		firstOperand.setFraction(-21, 2);
		secondOperand.setFraction(3, 7);
		result = firstOperand.divide(secondOperand);
		System.out.println("The quotient of " + firstOperand	+
				" and " +	secondOperand + " is \t" + result);

		// -21/2 + 7/8
		firstOperand.setFraction(-21, 2);
		secondOperand.setFraction(7, 8);
		result = firstOperand.add(secondOperand);
		System.out.println("The sum of " + firstOperand	+
				" and " +	secondOperand + " is \t\t" + result);

		System.out.println();

		// equality check
		if (firstOperand.equals(firstOperand))
			System.out.println("Identity of fractions OK");
		else
			System.out.println("ERROR in identity of fractions");

		secondOperand.setFraction(-42, 4);
		if (firstOperand.equals(secondOperand))
			System.out.println("Equality of fractions OK");
		else
			System.out.println("ERROR in equality of fractions");

		// comparison check
		Fraction first  = (Fraction)firstOperand;
		Fraction second = (Fraction)secondOperand;
		
		if (first.compareTo(second) == 0)
			System.out.println("Fractions == operator OK");
		else
			System.out.println("ERROR in fractions == operator");

		second.setFraction(7, 8);
		if (first.compareTo(second) < 0)
			System.out.println("Fractions < operator OK");
		else
			System.out.println("ERROR in fractions < operator");

		if (second.compareTo(first) > 0)
			System.out.println("Fractions > operator OK");
		else
			System.out.println("ERROR in fractions > operator");

		System.out.println();

		try {
			Fraction a1 = new Fraction(1, 0);		    
		}
		catch ( ArithmeticException arithmeticException )
           	{
              		System.err.printf( "\nException: %s\n", arithmeticException );
           	} // end catch

		try {
			Fraction a2 = new Fraction();		    
			Fraction a3 = new Fraction(1, 2);		    
			a3.divide(a2);
		}
		catch ( ArithmeticException arithmeticException )
           	{
              		System.err.printf( "\nException: %s\n", arithmeticException );
           	} // end catch



	}	// end main
} // end Fraction

