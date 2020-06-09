package Project;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;


//Ola
public class InputParametersPanel extends JPanel {
	JLabel choosePlanet, enterParameters, empty, eccentricityLabel, massLabel;
	JComboBox<String> planet;
	JButton parametersOptionButton;
	JTextField semiMajorAxisTf, eccentricityTf, totalAngularMomentumTf, massTf;
	JPanel panelForPlanets, panelForParameters, buttonPanel;
	SimulationPanel sim;
	String[] planets = {"Merkury", "Wenus", "Ziemia", "Mars", "Jowisz", "Saturn", "Uran", "Neptun"};
	
	public InputParametersPanel(SimulationPanel sim) {
		this.sim=sim;
		
		setBackground(Frame.colorOfRightPanel);	
		this.setLayout(new GridLayout(1,1));
		
		panelForPlanets = new JPanel();
		panelForPlanets.setLayout(new GridLayout(7,1));
		panelForPlanets.setBackground(Frame.colorOfRightPanel);
		
		choosePlanet = new JLabel("Wybierz obiekt:");
		panelForPlanets.add(choosePlanet);
		
		
		planet = new JComboBox<String>(planets);
		
		planet.setSelectedItem(planets[2]);
		planet.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				sim.dt = 86400;
				sim.fi = 0;
				sim.n=0;
				sim.xOrbit.clear();
				sim.yOrbit.clear();
				String s = (String) planet.getSelectedItem();
				switch(s) {
				case "Merkury":
					sim.semiMajorAxis =  0.579*Math.pow(10, 11);
					sim.eccentricity = 0.2056;
					sim.totalAngularMomentum = 0.09*Math.pow(10, 40);
					sim.mass = 0.33*Math.pow(10,24);
					sim.planetImage = sim.images[3];
					sim.dtPrim=20;
					sim.c=0;
					sim.p=0;
					break;
				case "Wenus":
					sim.semiMajorAxis =  1.08*Math.pow(10, 11);
					sim.eccentricity = 0.0068;
					sim.totalAngularMomentum = 1.85*Math.pow(10, 40);
					sim.mass = 4.87*Math.pow(10,24);
					sim.planetImage = sim.images[4];
					sim.dtPrim=20;
					sim.c=0;
					sim.p=0;
					break;
				case "Ziemia":
					sim.semiMajorAxis =  1.496*Math.pow(10, 11);
					sim.eccentricity = 0.0168;
					sim.totalAngularMomentum = 2.67*Math.pow(10, 40);
					sim.mass = 5.97*Math.pow(10,24);
					sim.planetImage = sim.images[2];
					sim.dtPrim=20;
					sim.c=0;
					sim.p=0;
					break;
				case "Mars":
					sim.semiMajorAxis =  2.28*Math.pow(10, 11);
					sim.eccentricity = 0.0933;
					sim.totalAngularMomentum = 0.352*Math.pow(10, 40);
					sim.mass = 0.642*Math.pow(10,24);
					sim.planetImage = sim.images[5];
					sim.dtPrim=20;
					sim.c=0;
					sim.p=0;
					break;
				case "Jowisz":
					sim.semiMajorAxis =  7.78*Math.pow(10, 11);
					sim.eccentricity = 0.0483;
					sim.totalAngularMomentum = 1940*Math.pow(10, 40);
					sim.mass = 1899*Math.pow(10,24);
					sim.planetImage = sim.images[6];
					sim.dtPrim=8;
					sim.c=0;
					sim.p=0;
					break;
				case "Saturn":
					sim.semiMajorAxis =  14.3*Math.pow(10, 11);
					sim.eccentricity = 0.0559;
					sim.totalAngularMomentum = 784*Math.pow(10, 40);
					sim.mass = 568*Math.pow(10,24);
					sim.planetImage = sim.images[7];
					sim.dtPrim=7;
					sim.c=0;
					sim.p=0;
					break;
				case "Uran":
					sim.semiMajorAxis =  28.7*Math.pow(10, 11);
					sim.eccentricity = 0.0471;
					sim.totalAngularMomentum = 170*Math.pow(10, 40);
					sim.mass = 87.2*Math.pow(10,24);
					sim.planetImage = sim.images[8];
					sim.dtPrim=2;
					sim.c=0;
					sim.p=0;
					break;
				case "Neptun":
					sim.semiMajorAxis =  45*Math.pow(10, 11);
					sim.eccentricity = 0.0085;
					sim.totalAngularMomentum = 250*Math.pow(10, 40);
					sim.mass = 102*Math.pow(10,24);
					sim.planetImage = sim.images[9];
					sim.dtPrim=2;
					sim.c=0;
					sim.p=0;
					break;
				}
			}
		});
		panelForPlanets.add(planet);
		
		buttonPanel = new JPanel();
		buttonPanel.setBackground(Frame.colorOfRightPanel);
		parametersOptionButton = new JButton("Wprowadz parametry:");
		parametersOptionButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				sim.fi = 0;
				sim.n=0;
				sim.c = 0;
				sim.p=1;
				sim.xOrbit.clear();
				sim.yOrbit.clear();
			
				sim.planetImage = sim.images[10];
				try {
					sim.totalAngularMomentum = 2.67*Math.pow(10, 40);
					sim.semiMajorAxis = 1.496*Math.pow(10, 11);
					sim.eccentricity = Double.parseDouble(eccentricityTf.getText());
					sim.mass = Double.parseDouble(massTf.getText()) *Math.pow(10, 25);
					sim.dtPrim = 7;
					
					if(Double.parseDouble(massTf.getText()) < 1)
						sim.mass = 1 *Math.pow(10, 24);
					if (sim.eccentricity > 0.9) {
						JOptionPane.showMessageDialog(null, "Mimośród bliski 1 powoduje kształt orbity zbliżony do paraboli, natomiast >1 - hiperboli", "Wartość", JOptionPane.INFORMATION_MESSAGE);
						sim.eccentricity =0.9;
					}
						
				}
				catch(NumberFormatException ex) {
					JOptionPane.showMessageDialog(null, "Wprowadzone parametry sa nieprawidłowe", "Błąd", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		buttonPanel.add(parametersOptionButton);
		panelForPlanets.add(buttonPanel);
		this.add(panelForPlanets);
		
		panelForParameters = new JPanel();
		panelForParameters.setLayout(new GridLayout(2,2));
		panelForParameters.setBackground(Frame.colorOfRightPanel);
		eccentricityLabel = new JLabel("Mimosród:");
		massLabel = new JLabel("Masa obiektu:  [*10^(24)] ");
		
		
		semiMajorAxisTf = new JTextField();
		semiMajorAxisTf.setColumns(10);
		eccentricityTf = new JTextField();
		eccentricityTf.setColumns(10);
		totalAngularMomentumTf = new JTextField();
		totalAngularMomentumTf.setColumns(10);
		massTf = new JTextField();
		massTf.setColumns(10);
		totalAngularMomentumTf.setSize(new Dimension(200,10));
		
		
		panelForPlanets.add(eccentricityLabel);
		panelForPlanets.add(eccentricityTf);
		panelForPlanets.add(massLabel);
		panelForPlanets.add(massTf);
		
	}

	
	
	

}
