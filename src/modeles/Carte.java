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
	

	// inverser les commentaires pour une utilisation sur les 2 premières sations uniquement !
	public void dessinerZone(Graphics g){
		Vector<Station> listeExte = new Vector<Station>();

		int x[] = new int[3];
		int y[] = new int[3];

		for(int f = 0; f < listeZones.size(); f++) {
			listeExte = stationsExterieures(listeZones.elementAt(f));

			for(int i = 0;i < listeZones.elementAt(f).getListeStations().size();i++){
			//for(int i = 0;i < 8;i++){
				x[0] = listeZones.elementAt(f).getListeStations().elementAt(i).getX();
				y[0] = listeZones.elementAt(f).getListeStations().elementAt(i).getY();
				
				for(int j = i;j < listeZones.elementAt(f).getListeStations().size();j++){
				//for(int j = i;j < 8;j++){
					x[1] = listeZones.elementAt(f).getListeStations().elementAt(j).getX();
					y[1] = listeZones.elementAt(f).getListeStations().elementAt(j).getY();
					
					for(int k = j;k < listeZones.elementAt(f).getListeStations().size();k++){
					//for(int k = j;k < 8;k++){
						x[2] = listeZones.elementAt(f).getListeStations().elementAt(k).getX();
						y[2] = listeZones.elementAt(f).getListeStations().elementAt(k).getY();
						
						//dessinerToutLisse(x, y, g, listeExte);
						dessinerToutLisse2(x, y, g, listeExte);
						//dessinerNormal(x, y, g, listeExte);
					}
				}
			}
		}

	}
	
	private void dessinerNormal(int[] x, int[] y, Graphics g, Vector<Station> listeExte) {
		// TODO Auto-generated method stub
		boolean dessinable;
		
		Polygon polygonXY = new Polygon(x, y, 3);
		dessinable = true;
		
		for(int m = 0; m < listeExte.size(); m ++) {
			// si le polygone formé par x et y ne contient aucune station de listeExte, alors on le dessine.
			if(polygonXY.contains(listeExte.elementAt(m).getX(), listeExte.elementAt(m).getY())) {
				dessinable = false;
			}
		}
		if(dessinable) {			
			g.setColor(new Color(10, 10, 200)); 
			g.fillPolygon(x, y, 3);
		}
	}

	private void dessinerToutLisse(int[] x, int[] y, Graphics g, Vector<Station> listeExte) {
		// TODO Auto-generated method stub
		boolean dessinable;
		int xTour[] = new int[24];
		int yTour[] = new int[24];
		
		Polygon polygonXY = new Polygon(x, y, 3);
		dessinable = true;
		
		for(int m = 0; m < listeExte.size(); m ++) {
			// si le polygone formé par x et y ne contient aucune station de listeExte, alors on le dessine.
			if(polygonXY.contains(listeExte.elementAt(m).getX(), listeExte.elementAt(m).getY())) {
				dessinable = false;
			}
		}

		// permet de dessiner tout autour de chaque station, et lisse ainsi le rendu
		for(int d = 0; d < 3; d++) {
			xTour[0+d*8] = x[d];
			xTour[1+d*8] = x[d] + 7; 
			xTour[2+d*8] = x[d] + 10;
			xTour[3+d*8] = x[d] + 7;
			xTour[4+d*8] = x[d];
			xTour[5+d*8] = x[d] - 7; 
			xTour[6+d*8] = x[d] - 10;
			xTour[7+d*8] = x[d] - 7;
			
			yTour[0+d*8] = y[d] + 10; 
			yTour[1+d*8] = y[d] + 7;
			yTour[2+d*8] = y[d];
			yTour[3+d*8] = y[d] - 7;
			yTour[4+d*8] = y[d] - 10;
			yTour[5+d*8] = y[d] - 7;
			yTour[6+d*8] = y[d];
			yTour[7+d*8] = y[d] + 7;
		}
		
		if(dessinable) {			
			for(int i = 0; i < 24; i++) {
				for(int j = i + 1; j < 24; j++) {
					for(int k = j + 1; k < 24; k++) {
						int xDraw[] = new int[3];
						int yDraw[] = new int[3];
						
						xDraw[0] = xTour[i];
						yDraw[0] = yTour[i]; 
						xDraw[1] = xTour[j]; 
						yDraw[1] = yTour[j]; 
						xDraw[2] = xTour[k]; 
						yDraw[2] = yTour[k];
						
						g.setColor(new Color(10, 200, 10)); 
						g.fillPolygon(xDraw, yDraw, 3); 
						
					}
				}
			}
		}
	}

	
	private void dessinerToutLisse2(int[] x, int[] y, Graphics g, Vector<Station> listeExte) {
		// TODO Auto-generated method stub
		boolean dessinable;
		int xTour[] = new int[12];
		int yTour[] = new int[12];
		
		Polygon polygonXY = new Polygon(x, y, 3);
		dessinable = true;
		
		for(int m = 0; m < listeExte.size(); m ++) {
			// si le polygone formé par x et y ne contient aucune station de listeExte, alors on le dessine.
			if(polygonXY.contains(listeExte.elementAt(m).getX(), listeExte.elementAt(m).getY())) {
				dessinable = false;
			}
		}

		// permet de dessiner tout autour de chaque station, et lisse ainsi le rendu
		for(int d = 0; d < 3; d++) {
			xTour[0+d*4] = x[d];
			xTour[1+d*4] = x[d] + 10;
			xTour[2+d*4] = x[d];
			xTour[3+d*4] = x[d] - 10;
			
			yTour[0+d*4] = y[d] + 10; 
			yTour[1+d*4] = y[d];
			yTour[2+d*4] = y[d] - 10;
			yTour[3+d*4] = y[d];
		}
		
		if(dessinable) {			
			for(int i = 0; i < 12; i++) {
				for(int j = i + 1; j < 12; j++) {
					for(int k = j + 1; k < 12; k++) {
						int xDraw[] = new int[3];
						int yDraw[] = new int[3];
						
						xDraw[0] = xTour[i];
						yDraw[0] = yTour[i]; 
						xDraw[1] = xTour[j]; 
						yDraw[1] = yTour[j]; 
						xDraw[2] = xTour[k]; 
						yDraw[2] = yTour[k];
						
						g.setColor(new Color(200, 10, 10)); 
						g.fillPolygon(xDraw, yDraw, 3); 
						
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
		
		this.dessinerZone(g);
		
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
	
	public void setListeStations(Vector<Station> stations){
		this.listeStations = stations;
	}
	
	public void setListeVilles(Vector<Ville> villes){
		this.listeVilles = villes;
	}

	public Vector<Zone> getListeZones(){
		return this.listeZones;
	}

	public Vector<Ligne> getListeLignes() {
		return this.listeLignes;
	}
	
	public Vector<Station> getListeStations(){
		return this.listeStations;
	}
	
	public Vector<Ville> getListeVilles(){
		return this.listeVilles;
	}
}
