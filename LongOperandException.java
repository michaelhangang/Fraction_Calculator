import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 * Program Name: LongOperandException.java
 * Purpose:The exception class will be used to make a object if the value user enter exceeds the maximum value for the integer data type.
 * Coder: Gang Han,  0811301
 * Date: Apr 15, 2018
 */

public class LongOperandException extends Exception
{
	int value;  //The value is used to mark up the numerator if it is assigned to 1;
	
	public LongOperandException() {
		//Calls superclass constructor
		super("Warning: numerator and denominator entered exceeds int capacity");
		value =0;
	}
	
	public LongOperandException(int v) {
		//Calls superclass constructor
		super("Warning: " + (v==1?"numerator":"denominator") + " entered exceeds int capacity");
		value =v;
	}
	
	/**
	 * Gets the value of the object
	 * @return the value
	 */
	public int getValue()
	{
		return value;
	}

	public void Message () {
		//A JOptionPane warning dialog message is displayed, if the value user enter exceeds the maximum value for the integer data type.
		JOptionPane.showMessageDialog(null,getMessage(),"Warning",JOptionPane.WARNING_MESSAGE);		
	}	
}
// End of class