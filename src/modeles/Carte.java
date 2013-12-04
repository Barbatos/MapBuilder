package modeles;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.Vector;

public class Carte {
	private Vector<Zone> listeZones = new Vector<Zone>();
	private Vector<Ligne> listeLignes = new Vector<Ligne>();
	
	public Carte(){
		
	}
	
	public void dessinerCarte(Graphics g){
		Graphics2D g2 = (Graphics2D)g;
		g2.setStroke(new BasicStroke(2));
		
		// Affichage des zones
		g.setColor(new Color(200, 100, 100));
		
		for(int i = 0; i < listeZones.size(); i++){
			listeZones.elementAt(i).dessinerZone(g);
		}

		// Affichage des lignes
		for(int i = 0; i < listeLignes.size(); i++){
			listeLignes.elementAt(i).dessinerLigne(g2);
		}
	}
	
	public void setListeZones(Vector<Zone> zones){
		this.listeZones = zones;
	}
	
	public void setListeLignes(Vector<Ligne> lignes){
		this.listeLignes = lignes;
	}
}
