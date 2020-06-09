package Project;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.swing.JColorChooser;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.filechooser.FileNameExtensionFilter;

public class Frame extends JFrame {

	SimulationPanel simPanel;
	InputParametersPanel inPanel;
	OutputParametersPanel outPanel;
	ControlPanel contPanel;
	JPanel gridPanel;
	File file;
	public Frame() throws HeadlessException {
		//Damian start
		//wygląd panelu 
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setSize(1200,800);
		this.setMinimumSize(new Dimension(1000,600));
		colorOfRightPanel= new Color(255,252,175);
		
	
		gridPanel=new JPanel(new GridLayout(3,1));
		add(gridPanel,BorderLayout.EAST);
		outPanel=new OutputParametersPanel();
		simPanel=new SimulationPanel(outPanel);
		inPanel=new InputParametersPanel(simPanel);
		gridPanel.add(inPanel);
	
		gridPanel.add(outPanel);
		
		contPanel=new ControlPanel(simPanel);
		gridPanel.add(contPanel);
		add(simPanel,BorderLayout.CENTER);
		//Damian Stop
		
		
		//Ola start
		this.setTitle("Symulacja Układu Słonecznego");
		menuBar = new JMenuBar();
		this.setJMenuBar(menuBar);
		menu = new JMenu("Menu");
		save = new JMenuItem("Zapisz");
		menu.add(save);
		reset = new JMenuItem("Reset");
		menu.add(reset);
		
		colors = new JMenuItem("Zmiana koloru");
		colors.addActionListener( new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				colorOfRightPanel = JColorChooser.showDialog(null, "Wybierz kolor", colorOfRightPanel);
				inPanel.setBackground(colorOfRightPanel);
				inPanel.panelForPlanets.setBackground(colorOfRightPanel);
				inPanel.panelForParameters.setBackground(colorOfRightPanel);
				inPanel.buttonPanel.setBackground(colorOfRightPanel);
				outPanel.setBackground(colorOfRightPanel);
				outPanel.panelForLabels.setBackground(colorOfRightPanel);
				outPanel.panelForValues.setBackground(colorOfRightPanel);
				contPanel.setBackground(colorOfRightPanel);
				contPanel.panelForButtonStart.setBackground(colorOfRightPanel);
				contPanel.panelForButtonStop.setBackground(colorOfRightPanel);
				contPanel.hideOrbit.setBackground(colorOfRightPanel);
			}
		});
		menu.add(colors);
		menuBar.add(menu);
		//Ola stop
		//Damian start
		ActionListener r = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				simPanel.b=1;
				inPanel.planet.setSelectedItem(inPanel.planets[2]);
				inPanel.eccentricityTf.setText("");
				inPanel.massTf.setText("");
				
			}
		};
		reset.addActionListener(r);
		JFileChooser fC = new JFileChooser();
		fC.setDialogTitle("Wybierz plik");
		FileNameExtensionFilter filter = new FileNameExtensionFilter("*.txt", "txt");
		fC.setFileFilter(filter);
		
		ActionListener s= new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				double odleglosc=simPanel.r/149600000/1000;
					int returnVal = fC.showOpenDialog(simPanel);
					
					if (returnVal == JFileChooser.APPROVE_OPTION) {
						 file = fC.getSelectedFile();
					
						try {
							BufferedWriter bw= new BufferedWriter( new OutputStreamWriter(
							        new FileOutputStream(file+".txt"),
							        Charset.forName("UTF-8").newEncoder() 
							    ));
							if(simPanel.p==0)
								bw.write("Wybrana planeta: "+inPanel.planet.getSelectedItem());
							if(simPanel.p==1)
								bw.write("Wybrana planeta: "+"własny obiekt");
							bw.newLine();
							bw.write("aktulana prędkość: "+String.format("%.2f", simPanel.speed/1000)+"km/s");
							bw.newLine();
							bw.write("aktualna odległość od słońca: "+String.format("%.3f", odleglosc)+"j.a");
							bw.close();
						} catch (FileNotFoundException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					
				}
			}
			
		};
		save.addActionListener(s);
			
			
		
	}


	static Color colorOfRightPanel;
	JMenuBar menuBar;
	JMenu menu;
	JMenuItem save, reset, colors;
	
	
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				Frame frame = new Frame();
				
				frame.setVisible(true);
				
				
				ExecutorService exec = Executors.newFixedThreadPool(1);
				exec.execute(frame.simPanel);
				exec.shutdown();
			}
		});
		

	}
		
}
