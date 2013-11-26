package vues;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Vector;

import javax.swing.*;

import modeles.Station;
import modeles.Zone;

public class Vue extends JPanel {
	private JFrame fenetre;
	private Zone zone;
	private MouseListener mouseListener;
	private Station stationActuelle = null;
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		setBackground(new Color(100, 100, 100));
		
		Graphics2D g2 = (Graphics2D)g;
		g2.setStroke(new BasicStroke(2));
		
		//Affichage zone zon1
		g.setColor(new Color(200, 100, 100));
		zone.dessinerZone(g);

		//Affichage ligne li1
		/*li1.dessinerLigne(g, new Color(200, 255, 200));
		
		//Affichager ligne li2
		li2.dessinerLigne(g, new Color(200, 200, 255));
		
		//Affichage rectangle d'information
		g.setColor(new Color(65, 65, 65));
		g.fillRect(1200 - 300, 0, 1200, 200);
		
		//Affichage informations station sta1
		*/
		if(stationActuelle != null){
			stationActuelle.dessinerInfo(g);
		}
		
		mouseListener = new MouseListener(){
			public void mouseClicked(MouseEvent event){
				verifierClicStation(event.getX(), event.getY());
				repaint();
			}
			
			public void mouseEntered(MouseEvent event){}
			public void mouseExited(MouseEvent event){}
			public void mouseReleased(MouseEvent event){}
			public void mousePressed(MouseEvent event){}
			
		};
		
		this.addMouseListener(mouseListener);
	}
	
	public void verifierClicStation(int x, int y){
		Vector<Station> listeStations = zone.getListeStations();
		for(int i = 0; i < listeStations.size(); i++){
			if( 
				(x <= (listeStations.elementAt(i).getCoordonnees().getX() + 7)) &&
				(x >= (listeStations.elementAt(i).getCoordonnees().getX() - 7)) && 
				(y <= (listeStations.elementAt(i).getCoordonnees().getY() + 7)) && 
				(y >= (listeStations.elementAt(i).getCoordonnees().getY() - 7))
			  ){
				setStationActuelle(listeStations.elementAt(i));
			}
		}
	}
	
	public Vue(){
		fenetre = new JFrame();
		
		// On définit le titre de la fenêtre
		fenetre.setTitle("MapBuilder v0.0.0.0.1");

		// Taille par défaut
		fenetre.setSize(1200, 675);

		// On centre la fenêtre
		fenetre.setLocationRelativeTo(null);

		// On empêche le redimensionnement de la fenêtre
		//fenetre.setResizable(false);

		// Opération par défaut quand on quitte
		fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// Import de la classe Affichage
		fenetre.setContentPane(this);
		
		fenetre.setVisible(true);
	}
	
	public JFrame getFenetre(){
		return fenetre;
	}
	
	public void setZone(Zone zone){
		this.zone = zone;
	}
	
	public void setStationActuelle(Station _station){
		this.stationActuelle = _station;
	}
	
}
