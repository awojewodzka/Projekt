package Project;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

//Ola
public class SimulationPanel extends JPanel implements Runnable {
	OutputParametersPanel out;
	ControlPanel control;
	InputParametersPanel inp;
	 
	BufferedImage[] images;
	BufferedImage planetImage;
	
	//wartości dla Słońca
	final double G = 6.674*Math.pow(10, -11);
	final double M = 1.989*Math.pow(10, 30);
	
	//domyślne wartości dla Ziemi
	double semiMajorAxis = 1.496*Math.pow(10, 11);
	double eccentricity = 0.0168;
	double totalAngularMomentum = 2.67*Math.pow(10, 40);
	double mass = 5.97*Math.pow(10,24);
	int dt = 86400;
	int dtPrim = 20;
	
	//skala dla każdej planety
	final int semiMajorAxisPrim = 250;
	
	double r, xPosition, yPosition, speed;
	int xPositionPrim, yPositionPrim;
	double fi = 0;
	int p = 0;
	int z = 0;
	int b=1;
	int c=0;
	int n=0;
	int dd=0;
	//rysowanie orbit
	List<Integer> xOrbit = new ArrayList<Integer>();
	List<Integer> yOrbit = new ArrayList<Integer>();
	 
	
	public SimulationPanel(OutputParametersPanel out) {
		this.out=out;
		
		
		images = new BufferedImage [11];
		String[] fileNames = {"resources/space.png", "resources/sun1.png", "resources/earth.png", "resources/mercury.png", "resources/venus.png","resources/mars.png", "resources/jupiter.png","resources/saturn.png","resources/uranus.png","resources/neptun.png", "resources/asteroid.png"};
		for(int i = 0; i<11; i++){
			File inputFile = new File(fileNames[i]);
			try {
				images[i] = ImageIO.read(inputFile);
				Dimension dim = new Dimension(images[0].getWidth(),images[0].getHeight());
				this.setPreferredSize(dim);
			}
			catch (IOException ex) {
				this.setBackground(Color.white);
			}
		}
		
		planetImage = images[2];	
		control=new ControlPanel(this);
		inp= new InputParametersPanel(this);
	}
	//run() to efekt wspólnej pracy
	public void run() {
		double x;
		double y;
		
		do {
			if(c==0) {
				r = (semiMajorAxis*(1-Math.pow(eccentricity, 2))/(1+eccentricity*Math.cos(fi)));
				fi = fi+(totalAngularMomentum*dt)/(mass*Math.pow(r, 2));
				xPosition = r*Math.cos(fi);
				yPosition = r*Math.sin(fi);
				x = (xPosition*semiMajorAxisPrim)/semiMajorAxis;
				y = (yPosition*semiMajorAxisPrim)/semiMajorAxis;
				xPositionPrim = (int) x;
				yPositionPrim = (int) y;
				c=1;
			}
			
			
			if(b==0) {
				
				r = (semiMajorAxis*(1-Math.pow(eccentricity, 2))/(1+eccentricity*Math.cos(fi)));
				fi = fi+(totalAngularMomentum*dt)/(mass*Math.pow(r, 2));
				xPosition = r*Math.cos(fi);
				yPosition = r*Math.sin(fi);
				x = (xPosition*semiMajorAxisPrim)/semiMajorAxis;
				y = (yPosition*semiMajorAxisPrim)/semiMajorAxis;
				xPositionPrim = (int) x;
				yPositionPrim = (int) y;
				n++;
			}
			
			speed = Math.sqrt((2*G*M)/r - (G*M)/semiMajorAxis);
			out.valueTime.setText(String.valueOf(n)+"dni");
			
			out.valueVelocity.setText(String.format("%.3f", speed/1000)+"km/s");
			out.valueDistance.setText(String.format("%.3f", r/149600000/1000)+"j.a");
			xOrbit.add(xPositionPrim);
			yOrbit.add(yPositionPrim);
			
			try {
				Thread.sleep(dtPrim);
				} 
			catch (InterruptedException e) {
					e.printStackTrace();
				}
			
			repaint();
			}
			
		
		while(z == 0);
		}

	//Ola
	protected void paintComponent(Graphics gy) {
		super.paintComponent(gy);
		Graphics2D g = (Graphics2D)gy;
		g.drawImage(images[0], 0, 0, this);
		
		//początek układu wspórzędnych w środku panelu
		int centerX = this.getWidth()/2;
		int centerY = this.getHeight()/2;
		
		int sunPosition = (int) eccentricity*semiMajorAxisPrim;
		
		g.setColor(Color.yellow);
		if(dd==0) {
			for(int i = 0; i<xOrbit.size(); i++) {
				if( i == 0) {
					g.drawLine(centerX+xOrbit.get(i), centerY+yOrbit.get(i), centerX+xOrbit.get(i), centerY+yOrbit.get(i));
				}
				else {
					g.drawLine(centerX+xOrbit.get(i-1), centerY+yOrbit.get(i-1), centerX+xOrbit.get(i), centerY+yOrbit.get(i));	
				}
			}
		}
		g.drawImage(images[1],  centerX-sunPosition-30, centerY-30, 60, 60, this);
		g.drawImage(planetImage, centerX+xPositionPrim-15,centerY+yPositionPrim-15,30,30,this);
	}
	

}
