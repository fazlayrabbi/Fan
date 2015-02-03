/*******************************************************************************
Title: Fan.java
Author: Fazlay Rabbi 
Created on: 01/19/2015 
Description: Purpose of this program is to demonstrate how fan works
Modifications: Comments were added on 01/19/2015
*******************************************************************************/
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.Graphics;
public class Fan extends JFrame {
	private Timer timer;// delays the rotation of fan
	private int radius1= 0;//radius of wing 1
	private int radius2= 90;//radius of wing 2
	private int radius3= 180;//radius of wing 3
	private int radius4= 270;//radius of wing 4
	private boolean state= false;// indicates if the fan is on or off. 
	private JButton onOff = new JButton("On / Off");
	private JButton slow = new JButton("Slow");
	private JButton medium = new JButton("Medium");
	private JButton fast = new JButton("Fast");
	
	public static void main(String[] args) {
		JFrame frame = new Fan();
		frame.setTitle("Fan");
		frame.setLocationRelativeTo(null); // Center the frame
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(400, 300);
		frame.setVisible(true);
	}

	public Fan(){
		setLayout(new BorderLayout());
		JPanel p1 = new JPanel(new GridLayout(1, 4));
		add(new ArcsPanel(), BorderLayout.CENTER);
		p1.add(onOff);
		p1.add(slow);
		p1.add(medium);
		p1.add(fast);
		add(p1, BorderLayout.SOUTH);
		onOff.addActionListener(new StateListener());
		slow.addActionListener(new SlowListener());
		medium.addActionListener(new MediumListener());
		fast.addActionListener(new FastListener());
	}
	/**Turns the fan on or off*/
	private class StateListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (state==false)
				state= true;
			else
				state= false;
		}
	}
	/**Rotates the fan in slow speed*/
	private class SlowListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			timer.setDelay(50);
		}
	}
	/**Rotates the fan in medium speed*/
	private class MediumListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			timer.setDelay(10);
		}
	}
	/**Rotates the fan in fast speed*/
	private class FastListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			timer.setDelay(0);
		}
	}
	
	class ArcsPanel extends JPanel {
		ArcsPanel(){
			timer= new Timer(10, new TimerListener());
			timer.start();
		}
		/**Draws the fan using four arcs and increases their current radius*/
		protected void paintComponent(Graphics g){
			super.paintComponent(g);
			int xCenter = getWidth() / 2;// center of x coordinate
			int yCenter = getHeight() / 2;// center of y coordinate
			int radius = (int)(Math.min(getWidth(), getHeight()) * 0.4);// radius of the window
			int x = xCenter - radius;// x coordinate
			int y = yCenter - radius;// y coordinate
			
			if (radius1==0)
				radius1=359;
			else
				radius1--;
			if (radius2==0)
				radius2=359;
			else
				radius2--;
			if (radius3==0)
				radius3=359;
			else
				radius3--;
			if (radius4==0)
				radius4=359;
			else
				radius4--;
				
			g.fillArc(x, y, 2 * radius, 2 * radius, radius1, 30);
			g.fillArc(x, y, 2 * radius, 2 * radius, radius2, 30);
			g.fillArc(x, y, 2 * radius, 2 * radius, radius3, 30);
			g.fillArc(x, y, 2 * radius, 2 * radius, radius4, 30);
		}
		/**Rotates the fan if state is true (fan is on)*/
		class TimerListener implements ActionListener {
			public void actionPerformed(ActionEvent e) {
				if (state)
					repaint();
			}
		}
	}
}