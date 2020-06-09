package Project;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.LayoutManager;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
//Damian
public class OutputParametersPanel extends JPanel {
	JLabel valueVelocity;
	JLabel velocityLabel;
	JLabel valueTime;
	JLabel timeLabel;
	JLabel valueDistance;
	JLabel distanceLabel;
	SimulationPanel p;

	JPanel panelForLabels;
	JPanel panelForValues;
	
	public OutputParametersPanel() {
		 
		setBackground(Frame.colorOfRightPanel);
		setLayout(new BorderLayout());
		panelForLabels= new JPanel(new GridLayout(3,1));
		panelForValues= new JPanel(new GridLayout(3,1));
		panelForLabels.setBackground(Frame.colorOfRightPanel);
		panelForValues.setBackground(Frame.colorOfRightPanel);
		add(panelForLabels, BorderLayout.WEST);
		add(panelForValues,BorderLayout.EAST);
		
		valueVelocity= new JLabel("10 km/s ");
		velocityLabel=new JLabel("aktulana prędkość: ");
		panelForLabels.add(velocityLabel);
		panelForValues.add(valueVelocity);
		
		valueTime= new JLabel("100dni ");
		timeLabel=new JLabel("rzeczwisty czas ruchu: ");
		panelForLabels.add(timeLabel);
		panelForValues.add(valueTime);
		
		valueDistance= new JLabel("1 j.a ");
		distanceLabel=new JLabel("odległość od słońca: ");
		panelForLabels.add(distanceLabel);
		panelForValues.add(valueDistance);
		p=new SimulationPanel(this);
		//wszystkie wartości zmieniaj� sie w czasie rzeczywistym 
	}

	public OutputParametersPanel(LayoutManager layout) {
		super(layout);
		// TODO Auto-generated constructor stub
	}

	public OutputParametersPanel(boolean isDoubleBuffered) {
		super(isDoubleBuffered);
		// TODO Auto-generated constructor stub
	}

	public OutputParametersPanel(LayoutManager layout, boolean isDoubleBuffered) {
		super(layout, isDoubleBuffered);
		// TODO Auto-generated constructor stub
	}
}
