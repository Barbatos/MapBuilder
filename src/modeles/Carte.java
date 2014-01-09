package modeles;

import java.awt.AlphaComposite;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.RenderingHints;
import java.util.Vector;

/**
 * Une carte est modelisee par cette classe
 * 
 * @version 1.0
 */
public class Carte {
	private Vector<Zone> listeZones = new Vector<Zone>();
	private Vector<Ligne> listeLignes = new Vector<Ligne>();
	private Vector<Ville> listeVilles = new Vector<Ville>();
	private Vector<Station> listeStations = new Vector<Station>();
	
	public static int VILLE = 0;

	public Carte(){
	}
	
	/**
	 * Constructeur parametre
	 * 
	 * @param listeZones, Un vecteur de zone sur la carte
	 * @param listeLignes, Un vecteur de ligne sur la carte
	 * @param listeVilles, Un vecteur de villes sur la carte
	 * @param listeStations, Un vecteur de stations sur la carte
	 * 
	 * @see Zone
	 * @see Ligne
	 * @see Ville
	 * @see Station
	 */
	public Carte(Vector<Zone> listeZones, Vector<Ligne> listeLignes, Vector<Ville> listeVilles, Vector<Station> listeStations) {
		this.listeLignes = listeLignes;
		this.listeStations = listeStations;
		this.listeVilles = listeVilles;
		this.listeZones = listeZones;
	}
	
	/**
	 * Methode qui donne les stations qui n'appartiennent pas a une zone donnee
	 * 
	 * @param zone, La zone dot on veut les stations exterieures
	 * @return Vector<Station>, Un vecteur de stations qui n'appartiennent pas a la zone
	 * @see Station
	 */
	public Vector<Station> stationsExterieures(Zone zone) {
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
	
	/**
	 * Methode qui dessine une zone sur la carte en faisant un algorithme
	 * 
	 * @param g, Une instance de Graphics
	 * @see Graphics
	 * @see Zone
	 * @see Station
	 */
	// inverser les commentaires pour une utilisation sur les 2 premières sations uniquement !
	public void dessinerZone(Graphics g){
		Vector<Station> listeExte = new Vector<Station>();

		int x[] = new int[3];
		int y[] = new int[3];

		for(int f = 0; f < listeZones.size(); f++) {
			listeExte = stationsExterieures(listeZones.elementAt(f));
			g.setColor(listeZones.elementAt(f).getCouleur());

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
						
						//dessinerToutLisse8Points(x, y, g, listeExte);
						//dessinerToutLisse4Points(x, y, g, listeExte);
						dessinerNormal(x, y, g, listeExte);
					}
				}
			}
		}

	}
	
	/**
	 * Methode qui dessine les zones de maniere simple : trace point par point entre les stations
	 * 
	 * @param x, Liste d'entiers qui correspondent a des abcisses
	 * @param y, Liste d'entiers qui correspondent a des ordonnees
	 * @param g, Une instance de Graphics
	 * @param listeExte
	 * @see Graphics
	 * @see Station
	 * @see Zone
	 */
	private void dessinerNormal(int[] x, int[] y, Graphics g, Vector<Station> listeExte) {
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

	/**
	 * Methode qui dessine les zones de maniere complexe : 
	 * Chaque station voit 8 points autour d'elle, lesquels sont relies aux autres des autres stations
	 * 
	 * @param x, Liste d'entiers qui correspondent a des abcisses
	 * @param y, Liste d'entiers qui correspondent a des ordonnees
	 * @param g, Une instance de Graphics
	 * @param listeExte
	 * @see Graphics
	 * @see Station
	 * @see Zone
	 */
	@SuppressWarnings("unused")
	private void dessinerToutLisse8Points(int[] x, int[] y, Graphics g, Vector<Station> listeExte) {
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
	
	/**
	 * Methode qui dessine les zones de maniere ingenieuse : 
	 * On dessine des cercles centres sur chaque stations de diametre egal a la distance avec la station la plus proche
	 * 
	 * @param g, Une instance de Graphics
	 * @param listeExte
	 * @see Graphics
	 * @see Station
	 * @see Zone
	 */
	public void dessinerZoneCercles(Graphics g){		
		int distanceMin;
		int distanceCourante;
		boolean staExterne = false;
		
		for(int i = 0;i < listeStations.size();i++){
			distanceMin = 9999;
			for(int j = 0;j < listeStations.size();j++){
				if(listeStations.elementAt(i) != listeStations.elementAt(j)){
					distanceCourante = (int) Math.sqrt((listeStations.elementAt(i).getX() - listeStations.elementAt(j).getX()) * (listeStations.elementAt(i).getX() - listeStations.elementAt(j).getX()) + ((listeStations.elementAt(i).getY() - listeStations.elementAt(j).getY()) * (listeStations.elementAt(i).getY() - listeStations.elementAt(j).getY())));
					if(distanceMin > distanceCourante){
						distanceMin = distanceCourante;
						if(listeStations.elementAt(i).getZone() != listeStations.elementAt(j).getZone()){
							staExterne = true;
						}
						else{
							staExterne = false;
						}
					}
				}
			}

			Graphics2D g2d = (Graphics2D) g;
	        g2d.setRenderingHint(
	            RenderingHints.KEY_ANTIALIASING,
	            RenderingHints.VALUE_ANTIALIAS_ON);
	        g2d.setComposite(AlphaComposite.getInstance(
	            AlphaComposite.SRC_OVER, 0.6f));
	        
			if(listeStations.elementAt(i).getVille() == VILLE){
				g.setColor(listeStations.elementAt(i).getZone().getCouleur());
				
				if(staExterne){
					g.fillOval(listeStations.elementAt(i).getX() - distanceMin / 2, listeStations.elementAt(i).getY() - distanceMin / 2, distanceMin, distanceMin);
				}
				else{
					g.fillOval(listeStations.elementAt(i).getX() - (distanceMin+10), listeStations.elementAt(i).getY() - (distanceMin+10), (distanceMin+10) * 2, (distanceMin+10) * 2);
				}
			}
		}
	}

	/**
	 * Methode qui dessine la carte
	 * 
	 * @param g, Une instance de Graphics
	 * @see Graphics
	 * @see Ligne
	 */
	public void dessinerCarte(Graphics g){
		Graphics2D g2 = (Graphics2D)g;
		g2.setStroke(new BasicStroke(2));

		// Affichage des zones
//		for(int i = 0;i < listeZones.size();i++){
//			listeZones.elementAt(i).dessinerZone(g);
//		}
		
		dessinerZoneCercles(g);
		
		// Affichage des lignes
		for(int i = 0; i < listeLignes.size(); i++){
			if(listeLignes.elementAt(i).getStation(0).getVille() == VILLE){
				listeLignes.elementAt(i).dessinerLigne(g2);
			}
		}
	}
	
	/**
	 * Setter sur les zones de la carte
	 * 
	 * @param zones, Un vecteur de zones
	 * @see Zone
	 */
	public void setVilleCourante(int ville){
		Carte.VILLE = ville;
	}

	public void setListeZones(Vector<Zone> zones){
		this.listeZones = zones;
	}
	
	/**
	 * Setter sur les lignes de la carte
	 * 
	 * @param lignes, Un vecteur de lignes
	 * @see Ligne
	 */
	public void setListeLignes(Vector<Ligne> lignes){
		this.listeLignes = lignes;
	}
	
	/**
	 * Setter sur les stations de la carte
	 * 
	 * @param stations, Un vecteur de stations 
	 * @see Station
	 */
	public void setListeStations(Vector<Station> stations){
		this.listeStations = stations;
	}
	
	/**
	 * Setter sur les villes de la carte
	 * 
	 * @param villes, Un vecteur de villes
	 * @see Ville
	 */
	public void setListeVilles(Vector<Ville> villes){
		this.listeVilles = villes;
	}

	/**
	 * Getter sur les zones de la carte
	 * 
	 * @return Vector<Zone>, Un vecteur des zones de la carte
	 * @see Zone
	 */
	public Vector<Zone> getListeZones(){
		return this.listeZones;
	}

	/**
	 * Getter sur les lignes de la carte
	 * 
	 * @return Vector<Ligne>, Un vecteur des lignes de la carte
	 * @see Ligne
	 */
	public Vector<Ligne> getListeLignes() {
		return this.listeLignes;
	}
	
	/**
	 * Getter sur les stations de la carte
	 * 
	 * @return Vector<Station>, Un vecteur des stations de la carte
	 * @see Station
	 */
	public Vector<Station> getListeStations(){
		return this.listeStations;
	}
	
	/**
	 * Getter sur les villes de la carte
	 * 
	 * @return Vector<Ville>, Un vecteur des villes de la carte
	 * @see Ville
	 */
	public Vector<Ville> getListeVilles(){
		return this.listeVilles;
	}
}
