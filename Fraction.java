/**
 *  Program Name:		Fraction.java
 *  Purpose:			A class to instantiate Fraction objects
 *  Coder:				Gang Han, Student Number
 *  Date:				April 14, 2018
 */

public class Fraction implements Comparable<Fraction>
{
	private int num;
	private int den;
	
	/**
	 * 0-arg constructor (replaces the default)
	 */
	public Fraction()
	{
		num = 1;
		den = 1;
	}	
	
	
	/**
	 * 2-arg constructor
	 * @param num - int
	 * @param den - int
	 */
	public Fraction(long num, long den)  throws DenominatorOfZeroException,LongOperandException
	{
		if(den == 0)
			throw new DenominatorOfZeroException();
		if(num >Integer.MAX_VALUE && den>Integer.MAX_VALUE )
			throw new LongOperandException();
		
		else if(num >Integer.MAX_VALUE) 
			throw new LongOperandException(1);
		
		else if(den>Integer.MAX_VALUE)
			throw new LongOperandException(2);
		
		else 
		{
		this.num = (int)num;
		this.den = (int)den;
		}
	}
		
	/**
	 * Returns the numerator of this fraction
	 * @return int
	 */
	public int getNum()
	{
		return num;
	}

	/**
	 *  Sets the numerator of this fraction
	 *  @param num the num to set
	 */
	public void setNum(int num)
	{
		this.num = num;
	}

	/**
	 * Returns the denominator of this Fraction
	 * @return int
	 */
	public int getDen()
	{
		return den;
	}

	/**
	 * Sets the denominator of this fraction
	 * @param den the den to set
	 */
	public void setDen(int den)
	{
		this.den = den;
	}

	/**
	 * Converts this fraction to a decimal
	 * @return double
	 */
	public double convertToDecimal()
	{
		double decimal = 0.0;
		decimal = (double)this.num/this.den;
		decimal = (int)((decimal + 0.005) * 100) / 100.0;
		return decimal;
	}
	
	/**
	 * Converts this fraction to its reciprocal
	 * @return Fraction
	 */
	public Fraction convertToReciprocal()
	{
		Fraction f = new Fraction();
		f.setNum(this.den);
		f.setDen(this.num);
		return f;		
	}
	
	/**
	 * Multiplies this fraction by another fraction
	 * @param f Fraction
	 * @return Fraction
	 */
	public Fraction multiply(Fraction f) throws DenominatorOfZeroException,LongOperandException
	{
		Fraction result = new Fraction();
		result.num = this.num * f.num;
		result.den = this.den * f.den;	
		result = result.lowestTerms();
		return result;
	}
	
	/**
	 * Adds this fraction to another fraction
	 * @param f Fraction
	 * @return Fraction
	 */
	public Fraction add(Fraction f) throws DenominatorOfZeroException,LongOperandException
	{
		Fraction result = new Fraction();
		result.num = this.num * f.den + this.den * f.num;
		result.den = this.den * f.den;
		result = result.lowestTerms();
		return result;		
	}
	
	/**
	 * Converts this fraction to lowest terms
	 * @return Fraction
	 */
	public Fraction lowestTerms() throws DenominatorOfZeroException,LongOperandException,LongOperandException
	{
		Fraction result = new Fraction(this.num, this.den);
		for(int i = result.den; i >= 2; i--)
		{
			//if num AND den take i as a factor, then "mutate"
			if((result.num % i == 0) && (result.den % i == 0))
			{
				result.num = result.num / i;
				result.den = result.den /i;
			}
		}
		return result;
	}
	
	/**
	 * Returns true if this fraction is equal to another (overloaded method)
	 * @param f Fraction
	 * @return
	 */
	public boolean equals(Fraction f)
	{
		//make temp int variables 
		int tempNum1, tempNum2;
		
		//make  common terms
		tempNum1 = f.num * this.den;
		tempNum2= f.den * this.num;
		
		//compare common terms
		return tempNum1 == tempNum2;		
	}
		
	/**
	 * Returns true if this fraction is greater than another fraction
	 * @param f Fraction
	 * @return boolean
	 */
	public boolean greaterThan(Fraction f)
	{
		int result =this.compareTo(f);
		boolean flag =false;
		if ( result == 1)
			flag = true;
			
		return flag;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	public int compareTo(Fraction f)
	{
		//make temp int variables 
		int tempNum1, tempNum2;
						
		//make  common terms
		tempNum1 = f.num * this.den;
		tempNum2 = f.den * this.num;
	
		if(tempNum2 >tempNum1)
			return 1;
		else if (tempNum2 < tempNum1)
			return -1;
		else
			return 0;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString()
	{
		return "This fraction is "+num + "/" + den;
	}	
}//End of class