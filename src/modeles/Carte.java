package modeles;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.util.Vector;

public class Carte {
	private Vector<Zone> listeZones = new Vector<Zone>();
	private Vector<Ligne> listeLignes = new Vector<Ligne>();
	private Vector<Ville> listeVilles = new Vector<Ville>();
	private Vector<Station> listeStations = new Vector<Station>();

	public Carte(){
	}
	
	public Carte(Vector<Zone> listeZones, Vector<Ligne> listeLignes, Vector<Ville> listeVilles, Vector<Station> listeStations) {
		this.listeLignes = listeLignes;
		this.listeStations = listeStations;
		this.listeVilles = listeVilles;
		this.listeZones = listeZones;
	}
	
	Vector<Station> stationsExterieures(Zone zone) {
		Vector<Station> listeExte = new Vector<Station>();
		boolean dehors;
		
		for(int i = 0; i < listeStations.size(); i++) {
			dehors = true;
			
			for(int j = 0; j < zone.getListeStations().size(); j++) {
				if(listeStations.elementAt(i) == zone.getStation(j)) {
					dehors = false;
				}
			}
			
			if(dehors) {
				listeExte.add(listeStations.elementAt(i));
			}
		}	
		
		return listeExte;
	}
	
	public void dessinerZoneBis(Graphics g){
		int x[] = new int[3];
		int y[] = new int[3];
		Vector<Station> listeExte = new Vector<Station>();
		boolean dessinable;
		
		for(int f = 0; f < listeZones.size(); f++) {
			listeExte = stationsExterieures(listeZones.elementAt(f));

			for(int i = 0;i < listeZones.elementAt(f).getListeStations().size();i++){
				x[0] = listeZones.elementAt(f).getListeStations().elementAt(i).getX();
				y[0] = listeZones.elementAt(f).getListeStations().elementAt(i).getY();
				
				for(int j = i;j < listeZones.elementAt(f).getListeStations().size();j++){
					x[1] = listeZones.elementAt(f).getListeStations().elementAt(j).getX();
					y[1] = listeZones.elementAt(f).getListeStations().elementAt(j).getY();
					
					for(int k = j;k < listeZones.elementAt(f).getListeStations().size();k++){
						x[2] = listeZones.elementAt(f).getListeStations().elementAt(k).getX();
						y[2] = listeZones.elementAt(f).getListeStations().elementAt(k).getY();
						
						Polygon polygonXY = new Polygon(x, y, 3);
						dessinable = true;
						
						for(int m = 0; m < listeExte.size(); m ++) {
							// si le polygone formé par x et y ne contient aucune station de listeExte, alors on le dessine.
							if(polygonXY.contains(listeExte.elementAt(m).getX(), listeExte.elementAt(m).getY())) {
								dessinable = false;
							}
						}
						
						if(dessinable) {
							g.fillPolygon(x, y, 3);
						}
					}
				}
			}
		}

	}
	
	public void dessinerCarte(Graphics g){
		Graphics2D g2 = (Graphics2D)g;
		g2.setStroke(new BasicStroke(2));
		
		// Affichage des zones
		g.setColor(new Color(200, 100, 100));
		
		this.dessinerZoneBis(g);
		
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
