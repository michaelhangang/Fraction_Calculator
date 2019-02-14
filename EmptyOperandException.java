import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 * Program Name: EmptyOperandException.java
 * Purpose:The exception class will be used to make a object if  the numerator or denominator box has been left empty.
 * Coder: Gang Han,  0811301
 * Date: Apr 15, 2018
 */

public class EmptyOperandException  extends Exception
{
	int value; //The value is used to mark up the numerator if it is assigned to 1;
	
	/**
	 * 0-arg constructor (replaces the default)
	 */
	public EmptyOperandException() {
		//Call to superclass constructor
		super("The numerator and denominator\r\n" + 
				"box has been left empty.");
		value =0;
	}
	
   public EmptyOperandException(int v) {
	 //Call to superclass constructor
		super((v==1?"Numerator":"Denominator") + 
				" box has been left empty.");
		value = v;
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
		//A JOptionPane dialog warning box  appear and advise the user that either the numerator or denominator box has been left empty. 
		JOptionPane.showMessageDialog(null,getMessage(),"Warning:",JOptionPane.WARNING_MESSAGE);		
	}
}
// End of class