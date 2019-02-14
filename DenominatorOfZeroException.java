import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 * Program Name: DenominatorOfZeroException.java
 * Purpose: the exception class will be used to make a object if the user enter a fraction with a denominator of zero
 * Coder: Gang Han,  0811301
 * Date: Apr 15, 2018
 */

public class DenominatorOfZeroException  extends Exception
{
public DenominatorOfZeroException() {
	    //Call to superclass constructor
		super("Denominator can't be Zero!");
	}
	
	public void Message () {
		//A JOptionPane warning dialog message is displayed, if the user enters a fraction with a denominator of zero
		JOptionPane.showMessageDialog(null,getMessage(),"Warning",JOptionPane.WARNING_MESSAGE);		
	}	
}
// End of class