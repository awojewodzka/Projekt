package Project;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JPanel;
//Damian

public class ControlPanel extends JPanel {
	JButton buttonStart;
	JButton buttonStop;
	JCheckBox hideOrbit;
	JPanel panelForButtonStart;
	JPanel panelForButtonStop;
	SimulationPanel sim;
	public ControlPanel(SimulationPanel sim) {
		this.sim=sim;
		setLayout(new GridLayout(3,1));
		
		setBackground(Frame.colorOfRightPanel);
		hideOrbit= new JCheckBox("ukryj orbitę");
		hideOrbit.setBackground(Frame.colorOfRightPanel);
		add(hideOrbit);
		//wciśnięcie powoduje że znika linia orbity
		buttonStart=new JButton("START");
	
		panelForButtonStart=new JPanel();// nie chciałem żeby przyciski się dotykały 
		panelForButtonStart.setBackground(Frame.colorOfRightPanel);//wi�c ka�dy jest w osobnym panelu
		panelForButtonStop=new JPanel();
		panelForButtonStop.setBackground(Frame.colorOfRightPanel);
		panelForButtonStart.add(buttonStart);
		ActionListener start= new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				sim.b=0;
				
			}
		};
		buttonStart.addActionListener(start);
		ActionListener stop= new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				sim.b=1;
				
			}
		};
		buttonStop=new JButton(" STOP ");
		buttonStop.addActionListener(stop);
		panelForButtonStop.add(buttonStop);
		ActionListener orbit= new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(sim.dd==0)
					sim.dd=1;
				else
					sim.dd=0;
				
			}
		};
		hideOrbit.addActionListener(orbit);

		add(panelForButtonStart);
		add(panelForButtonStop);
		
		
		
	}

	public ControlPanel(LayoutManager layout) {
		super(layout);
		// TODO Auto-generated constructor stub
	}

	public ControlPanel(boolean isDoubleBuffered) {
		super(isDoubleBuffered);
		// TODO Auto-generated constructor stub
	}

	public ControlPanel(LayoutManager layout, boolean isDoubleBuffered) {
		super(layout, isDoubleBuffered);
		// TODO Auto-generated constructor stub
	}
	
}
