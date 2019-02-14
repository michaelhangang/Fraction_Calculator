/**
 * Program Name: FractionCalculator.java
 * Purpose: The application simulates a Fraction calculator.
 * Coder: Gang Han,  0811301
 * Date: Apr 14, 2018
 */
import java.awt.Event.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.*;
import javax.swing.*;
import javax.xml.stream.events.EndDocument;

import java.awt.*;
public class FractionCalculator    extends  JFrame
{
   //Declare GUI components
   private JPanel palEnterFra;
   private JPanel palShowFra;
   private JPanel palSelectOpt;
   private JPanel palShowOpt;
   private JLabel labNumerator;
   private JLabel labDenominator;
   private JTextField textFNum;
   private JTextField textFDen;
   private JButton btnBuildFra;
   private JButton btnStart;
   private JTextArea textAFraction;
   private JComboBox<String> cboSelect;
   private JTextArea textOpt;
   private JMenuBar mnuBar;
   private JMenu  munOption;
   private JMenuItem  dec;
   private JMenuItem  rec;
   private JMenuItem  fraAdd;
   private JMenuItem  fraTime;
   private JMenuItem  fraEaqul;
   private JMenuItem  fraGreater;
   private JMenuItem  lowterm;
   private JMenuItem  sortlist;
   private JMenuItem  about;
   //Declare class variables
   private ArrayList<Fraction> arrayListFra;
   int lastIndex ;
   int lastSecondIndex ;
   Fraction lastFra ;
   Fraction lastSecondFra ;	
   
   //No-arg constructor
   public FractionCalculator() {
	  //Call to superclass constructor
	  super("Fraction Calculator");
	  
	  //Set the layout manager
	  setLayout(new GridLayout());
	 
	  //Initialize class variables
	  arrayListFra = new ArrayList<Fraction>();
	  lastIndex =0;
	  lastSecondFra = new Fraction();
	  lastSecondIndex = 0;
	  lastFra = new Fraction();
	  
	  //Build panels
	  buildPalEnterFra();
	  buildPalShowFra();
	  buildPalSelectOpt();
	  buildPalShowOpt();
	  buildmenuBar();
	  
	  //Add panels to frame 
	  add(palEnterFra);
	  add(palShowFra) ;
	  add(palSelectOpt) ;
	  add(palShowOpt) ;
	  
	  //Set up methods for the frame
	  setSize(800, 500);							
      setLocationRelativeTo(null);										
	  setDefaultCloseOperation(EXIT_ON_CLOSE);				
	  setVisible(true);	
   }//End constructor
   
   private void buildPalEnterFra() {
	   //Create panel
	   palEnterFra  = new JPanel();
	   palEnterFra.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLACK, 1), "Enter a fraction:"));
	   palEnterFra.setLayout(new FlowLayout(1,1,14));
	
	   //Create GUI components
	   labNumerator = new JLabel("Numerator:");
	   textFNum = new JTextField(15);
	   labDenominator = new JLabel("Denominator:");
	   textFDen = new JTextField(15);
	   buildBtnBuildFra();
	   buildBtnStart();
	    
	   //Add components to panel
	   palEnterFra.add(labNumerator);
	   palEnterFra.add(textFNum);
	   palEnterFra.add(labDenominator);
	   palEnterFra.add(textFDen);
	   palEnterFra.add(btnBuildFra);
	   palEnterFra.add(btnStart);     
   }//End of buildPalEnterFra
	
   private void buildBtnBuildFra() 
   {	   
	   //Build button
	   btnBuildFra = new JButton("Build Fraction") ;
	   
	   //Add listener
	   btnBuildFra.addActionListener(new ActionListener()  
	{   
		@Override
		public void actionPerformed(ActionEvent e) 
		{
			try {
			//Get the numerator and denominator input from the text fields
			String StringNum = textFNum.getText();  
			String StringDen = textFDen.getText();
			
			//check if input is empty 
			if(StringNum.equals("")&&StringDen.equals(""))
				throw new EmptyOperandException();
			
			if(StringNum.equals(""))
				throw new EmptyOperandException(1);
			
			if(StringDen.equals(""))
				throw new EmptyOperandException(2);
			
			//Data Validation
			if(isNumeric(StringNum)==false)  //Call the method to validate the numerator. if values are not integer, prompt user to input again
			{
				JOptionPane.showMessageDialog(null,"Only integer values are allowed!","Warning",JOptionPane.WARNING_MESSAGE);	
				textFNum.requestFocusInWindow();
			}
				
			if(isNumeric(StringDen)==false)  //Call the method to validate the denominator.if values are not integer, prompt user to input again
			{
				JOptionPane.showMessageDialog(null,"Only integer values are allowed!","Warning",JOptionPane.WARNING_MESSAGE);	
				textFDen.requestFocusInWindow();
			}

			long num =Long.parseUnsignedLong(StringNum);  //Transform the string of numerator to a  long type variable
			long den = Long.parseUnsignedLong(StringDen); //Transform the string of numerator to a  long type variable
			
			//Create a Fraction object
			Fraction fraction = new Fraction(num,den);
			
			//Add the Fraction object to the list
			arrayListFra.add(fraction);
			
			//Display the fraction in the Fraction text area
			textAFraction.append("This fraction is "+fraction.getNum() + "/" + fraction.getDen()+"\n");
			
			//Clear numerator and denominator input
			textFNum.setText("");
			textFDen.setText("");		
			}//End of try		
			
			//Catch  exception
			catch (EmptyOperandException ee) {
				//A JOptionPane dialog warning box  appear and advise the user that either the numerator or denominator box has been left empty. 
				ee.Message(); 
				 if (ee.getValue() == 0) {	 
					 textFNum.requestFocusInWindow();  //Return the focus to the field that has the bad data.
				 }
					 
				 else if(ee.getValue() == 1) {
					 textFNum.requestFocusInWindow();  //Return the focus to the field that has the bad data.
				 }
					
				 else {
					 textFDen.requestFocusInWindow();  //Return the focus to the field that has the bad data.
				 }
			}//End of catch
					
			catch (LongOperandException err) {
				//A JOptionPane warning dialog message is displayed, if the value user enter exceeds the maximum value for the integer data type.
				err.Message();
				 if (err.getValue() ==0) {
					 textFNum.setText("");  //Clear the text input area
					 textFDen.setText("");  //Clear the text input area
					 textFNum.requestFocusInWindow(); //Return the focus to the field that has the bad data.
				 }
					 
				 else if(err.getValue() ==1) {
					 textFNum.setText("");
					 textFNum.requestFocusInWindow(); //Return the focus to the field that has the bad data.
				 }
					
				 else {
					 textFDen.setText("");
					 textFDen.requestFocusInWindow(); //Return the focus to the field that has the bad data.
				 }					 
			}
			
			catch(DenominatorOfZeroException er) {
				 //A JOptionPane warning dialog message is displayed, if the user enters a fraction with a denominator of zero
				 er.Message();
				 textFDen.setText(""); //Clear the text input area
				 textFDen.requestFocusInWindow(); //Return the focus to the field that has the bad data.
			}
			
			catch(NumberFormatException d) {
				 
			}
		   }		
	});  
   }//End of buildBtnBuildFra
	
   private void  buildBtnStart() {
	   //Build button
	   btnStart = new JButton("Start Over!") ;
	   //Add listener
	   btnStart.addActionListener(new ActionListener()
		{		
			@Override
			public void actionPerformed(ActionEvent e)
			{
				//Clear all GUI components
				textFNum.setText("");
				textFDen.setText("");
				textAFraction.setText("");
				textOpt.setText("");
				//Clear all class variables
				arrayListFra.clear();
			}
		});   
   }//End of buildBtnStart
   
   private void  buildPalShowFra() {
	   //Create panel
	   palShowFra = new JPanel();
	   palShowFra.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLACK, 1), "Here is your fraction:"));
	  
	   //Create GUI components 
	   textAFraction = new JTextArea(25,15);  
	   textAFraction.setLineWrap(true);
	   textAFraction.setWrapStyleWord(true);
	   textAFraction.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 1));
		
	   //Add components to panel
	   palShowFra.add(textAFraction); 
   }
   
   private void buildPalSelectOpt() {
	   //Create panel
	   palSelectOpt = new JPanel();
	   palSelectOpt.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLACK, 1), "Select an operation:"));
	  
	   //Create combo box
	   buildCboSelect();
	    
	   //Add combo box to panel
	   palSelectOpt.add(cboSelect); 
   } //End of buildPalSelectOpt
   
   public void buildCboSelect() {
	   //Create combo box
	   String [] list = {"Decimal","Reciprocal","Fraction1 + Fraction2","Fraction1 X Fraction2","Is Fraction1 = Fraction2","Is Fraction1 > Fraction2","Lowest Terms","Sort List"};
	   cboSelect = new JComboBox<String>(list);
	   
	   //Add a listener
	   cboSelect.addActionListener(new ActionListener()
	   {	
		@Override
		public void actionPerformed(ActionEvent e)
		{
			try{ 
			//Check which combo box operation is selected
			String aString =(String)cboSelect.getSelectedItem();		
			
			// If no fraction was built, warn user and prompt user to create a fraction 
			if(arrayListFra.size() == 0) {				
				JOptionPane.showMessageDialog(null,"No Fraction!\n A fraction should be built first!","Warning",JOptionPane.WARNING_MESSAGE);
			}
			
			//If only one fraction was built, methods of Decimal, Lowest Terms and Reciprocal can be used.
			else if(arrayListFra.size() ==1) {
				lastIndex = arrayListFra.size()-1;   //Get the index of the last element in arraylistFra
				lastFra = arrayListFra.get(lastIndex); //Get the last element in arraylistFra
				
				if(aString=="Decimal") {
				    double decimal=	lastFra.convertToDecimal();
					textOpt.append(lastFra.getNum()+"/"+lastFra.getDen()+" is "+decimal+"\n");
				}
				
	            else if(aString=="Lowest Terms") {
	            	Fraction lowestsTerm = lastFra.lowestTerms();
	            	textOpt.append(lastFra.getNum()+"/"+lastFra.getDen()+" in lowest terms is "+lowestsTerm.getNum()+"/"+lowestsTerm.getDen()+"\n");
				}
					
				else if(aString=="Reciprocal") {
					Fraction reciprocal = lastFra.convertToReciprocal();
					textOpt.append(lastFra.getNum()+"/"+lastFra.getDen()+" and "+reciprocal.getNum()+"/"+reciprocal.getDen()+"\n");
				}
				
				//If only one fraction has been added to the list and the user tries to do binary arithmetic or logical operation, warn the user	
				else {				
					JFrame warnbox = new JFrame();
					JOptionPane.showMessageDialog(warnbox,"Only one fraction!\n Another fraction should be input!","Warning",JOptionPane.WARNING_MESSAGE);
				}	
			}//End of single fraction
			
			//If more than one fraction exist, all methods can be used.
			else {
				 lastIndex = arrayListFra.size()-1;          //the index of last fraction 
				 lastSecondIndex = arrayListFra.size()-2;    //the index of last second fraction 
				 lastFra = arrayListFra.get(lastIndex);      //get last fraction 
				 lastSecondFra = arrayListFra.get(lastSecondIndex);  // get last second fraction
				 
				if(aString=="Decimal") {
					 double decimal=	lastFra.convertToDecimal();
					 textOpt.append(lastFra.getNum()+"/"+lastFra.getDen()+" is "+decimal+"\n");	
				}
					
				else if(aString=="Reciprocal") {
					Fraction reciprocal = lastFra.convertToReciprocal();
					textOpt.append(lastFra.getNum()+"/"+lastFra.getDen()+" and "+reciprocal.getNum()+"/"+reciprocal.getDen()+"\n");
				}
					
				else if(aString=="Fraction1 + Fraction2") {
					Fraction total =lastSecondFra.add(lastFra);
					textOpt.append(lastSecondFra.getNum()+"/"+lastSecondFra.getDen()+" + "+lastFra.getNum()+"/"+lastFra.getDen()+" = "+total.getNum()+"/"+total.getDen()+"\n");
				}
				
				else if(aString=="Fraction1 X Fraction2") {
					Fraction result =lastSecondFra.multiply(lastFra);
					textOpt.append(lastSecondFra.getNum()+"/"+lastSecondFra.getDen()+" X "+lastFra.getNum()+"/"+lastFra.getDen()+" = "+result.getNum()+"/"+result.getDen()+"\n");
				}
					
				else if(aString=="Is Fraction1 = Fraction2") {
					boolean isEqual = lastSecondFra.equals(lastFra);
					String equal;
					if(isEqual==true)
						equal = " is equal to ";
					else 
						equal =" is not equal to";
					
					textOpt.append(lastSecondFra.getNum()+"/"+lastSecondFra.getDen()+equal+lastFra.getNum()+"/"+lastFra.getDen()+"\n");
				}
					
				else if(aString=="Is Fraction1 > Fraction2") {
					boolean isGreat = lastSecondFra.greaterThan(lastFra);
					String great;
					if(isGreat==true)
						great = " is greater than ";
					else 
						great =" is not greater than ";
					textOpt.append(lastSecondFra.getNum()+"/"+lastSecondFra.getDen()+great+lastFra.getNum()+"/"+lastFra.getDen()+"\n");
				}
					
				else if(aString=="Lowest Terms") {				
					for(int i =0; i<arrayListFra.size();i++) {
						Fraction original = arrayListFra.get(i);
						Fraction lowestsTerm  = arrayListFra.get(i).lowestTerms();
						arrayListFra.set(i, lowestsTerm);
						textAFraction.append(original.getNum()+"/"+original.getDen()+" in lowest terms is "+lowestsTerm.getNum()+"/"+lowestsTerm.getDen()+"\n");
					}
				}
					
				else if(aString=="Sort List") {
					Collections.sort(arrayListFra);
					for(Fraction a: arrayListFra)
					{
						textOpt.append(a.getNum()+"/"+a.getDen()+",");
						textAFraction.append(a.getNum()+"/"+a.getDen()+",");
				    }
					textOpt.append("\n");
					textAFraction.append("\n");
				}
			}//End of more than one fraction
			
			cboSelect.setSelectedIndex(0);	// restore "Decimal"" operation to display at the top of combo box
		    }//End of try
			
			//Catch  the exceptions
			catch(DenominatorOfZeroException er) {
				 er.Message();
			}
			
			catch(LongOperandException er) {
				 er.Message();
			}	
		}//End of try  
	});	   
   }//End of building body 
   
   private void buildPalShowOpt() {
	   //Create panel
	   palShowOpt = new JPanel();
	   palShowOpt.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLACK, 1), "Here is your operation:"));
	   
	   //Create GUI components 
	   textOpt = new JTextArea(25,15);  
	   textOpt.setLineWrap(true);
	   textOpt.setWrapStyleWord(true);
	   textOpt.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 1));
		
	   //Add components to panel
	   palShowOpt.add(textOpt);
   }//End of buildPalShowOpt
   
   private void buildmenuBar() {
	   //Create the menubar
	   mnuBar = new JMenuBar();
	   
	   //Call methods to build menu
	   buildMenuOption();
	  
	   //Add menu items to menu bar
	   mnuBar.add(munOption);
	   
	   //Set the window's menu bar
	   setJMenuBar(mnuBar);		//JFrame method to ensure menu system is visible
   }//End of buildmenuBar
   
   private void buildMenuOption(){
	   //Create Operation menu
	   munOption = new JMenu("Operation");
	   
	   //Set up Key O as hot key for Operation menu
	   munOption.setMnemonic(79);
	   
	   //Create menu items
	   dec = new JMenuItem("Decimal");
	   rec  = new JMenuItem("Reciprocal");
	   fraAdd = new JMenuItem("Fraction1 + Fraction2");
	   fraTime = new JMenuItem("Fraction1 X Fraction2");
	   fraEaqul = new JMenuItem("Is Fraction1 = Fraction2");
	   fraGreater = new JMenuItem("Is Fraction1 > Fraction2");
	   lowterm = new JMenuItem("Lowest Terms");
	   sortlist = new JMenuItem("Sort List"); 
	   about = new JMenuItem("About");
	   
	   //Create hot keys to each of menu items
	   dec.setAccelerator( KeyStroke.getKeyStroke('D',Toolkit.getDefaultToolkit ().getMenuShortcutKeyMask()));
	   rec.setAccelerator( KeyStroke.getKeyStroke('R',Toolkit.getDefaultToolkit ().getMenuShortcutKeyMask()));
	   fraAdd.setAccelerator( KeyStroke.getKeyStroke('A',Toolkit.getDefaultToolkit ().getMenuShortcutKeyMask()));
	   fraTime.setAccelerator( KeyStroke.getKeyStroke('T',Toolkit.getDefaultToolkit ().getMenuShortcutKeyMask()));
	   fraEaqul.setAccelerator( KeyStroke.getKeyStroke('E',Toolkit.getDefaultToolkit ().getMenuShortcutKeyMask()));
	   fraGreater.setAccelerator( KeyStroke.getKeyStroke('G',Toolkit.getDefaultToolkit ().getMenuShortcutKeyMask()));
	   lowterm.setAccelerator( KeyStroke.getKeyStroke('L',Toolkit.getDefaultToolkit ().getMenuShortcutKeyMask()));
	   sortlist.setAccelerator( KeyStroke.getKeyStroke('S',Toolkit.getDefaultToolkit ().getMenuShortcutKeyMask()));
	   about.setAccelerator( KeyStroke.getKeyStroke('N',Toolkit.getDefaultToolkit ().getMenuShortcutKeyMask()));
	   
	   //Add  action listeners to menu items
	   dec.addActionListener(new ActionListener()
	  {	
		@Override
		public void actionPerformed(ActionEvent e)
		{
			if(arrayListFra.size() == 0) {			
				JOptionPane.showMessageDialog(null,"No Fraction!\n A fraction should be built first!","Warning",JOptionPane.WARNING_MESSAGE);
			}
			
			else  {
			lastIndex = arrayListFra.size()-1; 
	    	lastFra = arrayListFra.get(lastIndex);		
		    double decimal=	lastFra.convertToDecimal();
			textOpt.append(lastFra.getNum()+"/"+lastFra.getDen()+" is "+decimal+"\n");		
			}			
		} 	
	}); //End of  addActionlistener
	   
	   rec.addActionListener(new ActionListener()
	{
		
		@Override
		public void actionPerformed(ActionEvent e)
		{
			if(arrayListFra.size() == 0) {	
				JOptionPane.showMessageDialog(null,"No Fraction!\n A fraction should be built first!","Warning",JOptionPane.WARNING_MESSAGE);
			}
			
			else {
				lastIndex = arrayListFra.size()-1; 
		    	lastFra = arrayListFra.get(lastIndex);		
		    	Fraction reciprocal = lastFra.convertToReciprocal();
				textOpt.append(lastFra.getNum()+"/"+lastFra.getDen()+" and "+reciprocal.getNum()+"/"+reciprocal.getDen()+"\n");			
			}
		}
	});//End of  addActionlistener
	   
	   lowterm.addActionListener(new ActionListener()
	{	
		@Override
		public void actionPerformed(ActionEvent e)
		{
			try {
			if(arrayListFra.size() == 0) {
				
				JOptionPane.showMessageDialog(null,"No Fraction!\n A fraction should be built first!","Warning",JOptionPane.WARNING_MESSAGE);
			}
			
			else {
				for(int i =0; i<arrayListFra.size();i++) {
					Fraction original = arrayListFra.get(i);
					Fraction lowestsTerm  = arrayListFra.get(i).lowestTerms();
					arrayListFra.set(i, lowestsTerm);
					textAFraction.append(original.getNum()+"/"+original.getDen()+" in lowest terms is "+lowestsTerm.getNum()+"/"+lowestsTerm.getDen()+"\n");
				}//End of for loop
			} //End of else		
		}
			
			//Catch  the exceptions
			catch(DenominatorOfZeroException er) {
				 er.Message();
			}
			
			catch(LongOperandException er) {
				 er.Message();
			}	
		}
	}); //End of addActionListener
	   
	   fraAdd.addActionListener(new ActionListener()
	{
		
		@Override
		public void actionPerformed(ActionEvent e)
		{
			try {
			if(arrayListFra.size() <2 ) {			
				JOptionPane.showMessageDialog(null,"This operation needs at least two fractions!","Warning",JOptionPane.WARNING_MESSAGE);
			}
			
			else {
				lastIndex = arrayListFra.size()-1;
				 lastSecondIndex = arrayListFra.size()-2;
				 lastFra = arrayListFra.get(lastIndex);
				 lastSecondFra = arrayListFra.get(lastSecondIndex); 
				Fraction total = lastSecondFra.add(lastFra);
				textOpt.append(lastSecondFra.getNum()+"/"+lastSecondFra.getDen()+" + "+lastFra.getNum()+"/"+lastFra.getDen()+" = "+total.getNum()+"/"+total.getDen()+"\n");
			}		
		}//End of try
		
			//Catch  the exceptions
			catch(DenominatorOfZeroException er) {
				 er.Message();
			}
			
			catch(LongOperandException er) {
				 er.Message();
			}	
		}
	});//End of addAcitionListener
	   
	   fraTime.addActionListener(new ActionListener()
	{
		
		@Override
		public void actionPerformed(ActionEvent e)
		{
			try {
			if(arrayListFra.size() <2 ) {			
				JOptionPane.showMessageDialog(null,"This operation needs at least two fractions!","Warning",JOptionPane.WARNING_MESSAGE);
			}
			
			else {
				 lastIndex = arrayListFra.size()-1;
				 lastSecondIndex = arrayListFra.size()-2;
				 lastFra = arrayListFra.get(lastIndex);
				 lastSecondFra = arrayListFra.get(lastSecondIndex); 
				 Fraction result =lastSecondFra.multiply(lastFra);
				 textOpt.append(lastSecondFra.getNum()+"/"+lastSecondFra.getDen()+" X "+lastFra.getNum()+"/"+lastFra.getDen()+" = "+result.getNum()+"/"+result.getDen()+"\n");	
			}
			}//End of try
			
			//Catch  the exceptions
			catch(DenominatorOfZeroException er) {
				 er.Message();
			}
			
			catch(LongOperandException er) {
				 er.Message();
			}	
		}
	});  //End of addAcitionListener


	   fraEaqul.addActionListener(new ActionListener()
	{	
		@Override
		public void actionPerformed(ActionEvent e)
		{
			if(arrayListFra.size() <2 ) {			
				JOptionPane.showMessageDialog(null,"This operation needs at least two fractions!","Warning",JOptionPane.WARNING_MESSAGE);
			}
			
			else {
				boolean isEqual = lastSecondFra.equals(lastFra);
				String equal;
				if(isEqual==true)
					equal = " is equal to ";
				else 
					equal =" is not equal to";
				
				textOpt.append(lastSecondFra.getNum()+"/"+lastSecondFra.getDen()+equal+lastFra.getNum()+"/"+lastFra.getDen()+"\n");			
			}
		}
	});//End of addAcitionListener
          
	   fraGreater.addActionListener(new ActionListener()
	{	
		@Override
		public void actionPerformed(ActionEvent e)
		{
			if(arrayListFra.size() <2 ) {			
				JOptionPane.showMessageDialog(null,"This operation needs at least two fractions!","Warning",JOptionPane.WARNING_MESSAGE);
			}
			
			else {
				boolean isGreat = lastSecondFra.greaterThan(lastFra);
				String great;
				if(isGreat==true)
					great = " is greater than ";
				else 
					great =" is not greater than ";
				textOpt.append(lastSecondFra.getNum()+"/"+lastSecondFra.getDen()+great+lastFra.getNum()+"/"+lastFra.getDen()+"\n");
			}	
		}
	});//End of addAcitionListener

	   sortlist.addActionListener(new ActionListener()
	{	
		@Override
		public void actionPerformed(ActionEvent e)
		{
			if(arrayListFra.size() <2 ) {			
				JOptionPane.showMessageDialog(null,"This operation needs at least two fractions!","Warning",JOptionPane.WARNING_MESSAGE);
			}
			
            else {
            	Collections.sort(arrayListFra);
				for(Fraction a: arrayListFra)
				{
					textOpt.append(a.getNum()+"/"+a.getDen()+",");
					textAFraction.append(a.getNum()+"/"+a.getDen()+",");
			    }
				textOpt.append("\n");
				textAFraction.append("\n");
			}//End of else		
		}
	}); //End of AddActionListener

	    //Add a About menu item to show the names of coders
	    about.addActionListener(new ActionListener()  
		{
			public void actionPerformed(ActionEvent e)
			{
				JOptionPane.showMessageDialog(null, "Coder: Gang Han ",
							"The list of the coders.", JOptionPane.INFORMATION_MESSAGE);
			}
		});//End of addAcitionListener
	   
	   //Add menu items to menu
	   munOption.add(dec);
	   munOption.add(rec);
	   munOption.add(fraAdd);
	   munOption.add(fraTime);
	   munOption.add(fraEaqul);
	   munOption.add(fraGreater);
	   munOption.add(lowterm);
	   munOption.add(sortlist);
	   munOption.addSeparator();
	   munOption.add(about);
   } //End of buildMenuOption
    
   /**
   * Method Name:   isNumeric
   * Purpose: check if all characters of a string  are numbers.
   * Parameters: String 
   * Returns:  boolean
   */
   private  boolean isNumeric(String str)
   {
	   //Parse all characters of the string by transforming the string to a char array 
       for (char c : str.toCharArray())
       {
           if (!Character.isDigit(c)) return false;  //if one of characters is not number, return false
       }
       return true;   // otherwise return true
   }
	
public static void main(String[] args)
	{
		FractionCalculator  fractionCalculator = new FractionCalculator();
	}// End of main method
}//End of class
