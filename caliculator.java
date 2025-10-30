package caliculatorproject123;

import java.awt.*;
import java.awt.event.*;
import java.util.Arrays;
import javax.swing.*;
import javax.swing.border.LineBorder;

public class caliculator {
	int boardwidth = 360;
	int boardheight = 540;

	Color customlightgray = new Color(212,212,210);
	Color customdarkgray = new Color(80,80,80);
	Color customblack = new Color(28,28,28);
	Color customorange = new Color(255,149,0);
	
	String[] buttonValues = {
	        "AC", "+/-", "%", "÷", 
	        "7", "8", "9", "×", 
	        "4", "5", "6", "-",
	        "1", "2", "3", "+",
	        "0", ".", "√", "="
	    };
	    String[] rightsymbols = {"÷", "×", "-", "+", "="};
	    String[] topsymbols = {"AC", "+/-", "%"};
	
	JFrame frame = new JFrame("Caliculator");
	JLabel displaylabel = new JLabel();
	JPanel displaypanel = new JPanel();
	JPanel buttonpanel = new JPanel();
	
	String A = "0";
	String operator = null;
	String B = null;

	caliculator()
	{
		frame.setVisible(true);
		frame.setSize(boardwidth,boardheight);
		frame.setLocationRelativeTo(null);
		frame.setResizable(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new BorderLayout());
		
		displaylabel.setBackground(customblack);
		displaylabel.setForeground(Color.white);
		displaylabel.setFont(new Font("Arial",Font.PLAIN,80));
		displaylabel.setHorizontalAlignment(JLabel.RIGHT);
		displaylabel.setText("0");
		displaylabel.setOpaque(true);
		
		displaypanel.setLayout(new BorderLayout());
		displaypanel.add(displaylabel);
		frame.add(displaylabel, BorderLayout.NORTH);
		
		
		
		buttonpanel.setOpaque(true);            
		buttonpanel.setFocusable(false); 
		
		buttonpanel.setLayout(new GridLayout(5,4));
		buttonpanel.setBackground(customblack);
		frame.add(buttonpanel, BorderLayout.CENTER);
		
		for(int i = 0; i < buttonValues.length; i++ ) {
			
			String buttonValue = buttonValues[i]; 
			JButton button = new JButton(buttonValue);
			button.setFont(new Font("Arial", Font.PLAIN, 30 ));
			button.setText(buttonValue);
			button.setFocusable(false);
			button.setBorder(new LineBorder(customblack,1));
			if(Arrays.asList(topsymbols).contains(buttonValue))
			{
				button.setOpaque(true);
				button.setBackground(customlightgray);
				button.setForeground(customblack);
				
			}else if(Arrays.asList(rightsymbols).contains(buttonValue)){
				button.setOpaque(true);
				button.setBackground(customorange);
				button.setForeground(Color.white);
				
			}else {
				button.setOpaque(true);
				button.setBackground(customdarkgray);
				button.setForeground(Color.white);
				
			}
			buttonpanel.add(button);
			
			button.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					JButton button = (JButton) e.getSource();
					String buttonValue = button.getText(); 
					if(Arrays.asList(rightsymbols).contains(buttonValue)) {
						if(buttonValue == "=") {
							if(A != null) {
								B = displaylabel.getText();
								double numA = Double.parseDouble(A);
								Double numB = Double.parseDouble(B);
								
								if(operator == "+") {
									displaylabel.setText(removezerodecimal(numA+numB));
								}else if(operator == "-"){
									displaylabel.setText(removezerodecimal(numA-numB));
								}else if(operator == "×") {
									displaylabel.setText(removezerodecimal(numA * numB));
								}else {
									displaylabel.setText(removezerodecimal(numA / numB));
								}
								clearAll();
							}
						}else if("+-×÷".contains(buttonValue)){
							if(operator == null) {
								A = displaylabel.getText();
								displaylabel.setText("0");
								B = "0";
							}
							operator = buttonValue;
						}

						
					}else if(Arrays.asList(topsymbols).contains(buttonValue)) {
						if(buttonValue == "AC") {
							clearAll();
							displaylabel.setText("0");
							
						}else if(buttonValue == "+/-"){
							double numdisplay = Double.parseDouble(displaylabel.getText());
							numdisplay *= -1;
							displaylabel.setText(removezerodecimal(numdisplay));
						}else if(buttonValue == "%"){
							double numdisplay = Double.parseDouble(displaylabel.getText());
							numdisplay /= 100;
							displaylabel.setText(removezerodecimal(numdisplay));
						}
						
					}else {
						if(buttonValue == ".") {
							if(!displaylabel.getText().contains(buttonValue)) {
								displaylabel.setText(displaylabel.getText()+ buttonValue);
							}
							
						}else if("0123456789".contains(buttonValue)){
							if(displaylabel.getText() == "0") {
								displaylabel.setText(buttonValue);
							}else {
								displaylabel.setText(displaylabel.getText()+ buttonValue);
							}
						}
					}
					 
					 
					
				}
			});
					
		
			}
			 
		
		
	} 
	
	//for AC  utton fuction
	void clearAll() {
		 A = "0";
		 operator = null;
		 B = null; 
	 }
	
	//for removoing decimal zero in a whole number
	String removezerodecimal(double numdisplay) {
		if(numdisplay % 1 == 0) {
			return Integer.toString((int) numdisplay);
		}
		return Double.toString(numdisplay);  
		
	}

}
