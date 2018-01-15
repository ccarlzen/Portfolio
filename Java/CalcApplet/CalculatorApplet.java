/* 
 * Created on:	1/29/2015
 * Last edit:	2/5/2015
 */

import java.applet.Applet;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.*;
import java.util.*;

/**
 * Applet creates a basic calculator applet that accepts input from button
 * clicks through the applet but not through keyboard. Input must be in
 * integer form and the parentheses operator is not functional
 * 
 * @author Casey
 *
 */
public class CalculatorApplet extends Applet implements ActionListener {

	Panel p = new Panel();
	Button buttons[] = new Button[20];
	// String to aid in naming buttons
	String bNames = "( ) C CE 7 8 9 + 4 5 6 - 1 2 3 * 0 . = /";
	
	TextField tf = new TextField();

	// Basic initiation factors for applet
	public void init(){
		setLayout(new BorderLayout());
		add(tf, BorderLayout.NORTH);
		add(p, BorderLayout.CENTER);
		p.setLayout(new GridLayout(5,4));
		StringTokenizer st = new StringTokenizer(bNames);
		tf.setEditable(false);
		
		int btCounter = 0;
		while (st.hasMoreTokens())
		{
			buttons[btCounter] = new Button(st.nextToken());
			p.add(buttons[btCounter]);
			buttons[btCounter].addActionListener(this);
			btCounter++;
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		/**
		 * Handles the actions for when buttons are clicked by client
		 */
		if (arg0.getSource()==buttons[4])
			tf.setText(tf.getText()+"7");
		else if (arg0.getSource()==buttons[5])
			tf.setText(tf.getText()+"8");
		else if (arg0.getSource()==buttons[6])
			tf.setText(tf.getText()+"9");
		else if (arg0.getSource()==buttons[8])
			tf.setText(tf.getText()+"4");
		else if (arg0.getSource()==buttons[9])
			tf.setText(tf.getText()+"5");
		else if (arg0.getSource()==buttons[10])
			tf.setText(tf.getText()+"6");
		else if (arg0.getSource()==buttons[12])
			tf.setText(tf.getText()+"1");
		else if (arg0.getSource()==buttons[13])
			tf.setText(tf.getText()+"2");
		else if (arg0.getSource()==buttons[14])
			tf.setText(tf.getText()+"3");
		else if (arg0.getSource()==buttons[16])
			tf.setText(tf.getText()+"0");
		else if (arg0.getSource()==buttons[7])
			tf.setText(tf.getText()+"+");
		else if (arg0.getSource()==buttons[11])
			tf.setText(tf.getText()+"-");
		else if (arg0.getSource()==buttons[15])
			tf.setText(tf.getText()+"*");
		else if (arg0.getSource()==buttons[19])
			tf.setText(tf.getText()+"/");
		else if (arg0.getSource()==buttons[2])
			tf.setText(tf.getText().substring(0,  tf.getText().length()-1));
		else if (arg0.getSource()==buttons[3])
			tf.setText("");
		else if (arg0.getSource()==buttons[18])
		{
			Evaluator anEvaluator = new Evaluator();
			tf.setText(Integer.toString(anEvaluator.eval(tf.getText())));
		}
	}

}