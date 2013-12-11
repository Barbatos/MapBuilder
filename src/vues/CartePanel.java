package vues;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JPanel;

import modeles.Carte;
import modeles.Ligne;
import modeles.Station;
import modeles.Ville;
import modeles.Zone;

public class CartePanel extends JPanel{
	private static final long serialVersionUID = 1L;
	public final static int WIDTH = 1200;
	public final static int HEIGHT = 675;
	
	private JFrame fenetre;
	private Vector<Zone> listeZones = new Vector<Zone>();
	private Vector<Ligne> listeLignes = new Vector<Ligne>();
	private Vector<Ville> listeVilles = new Vector<Ville>();
	private Vector<Station> listeStations = new Vector<Station>();
	private Station stationActuelle = null;
	private Station stationPassageSouris = null;
	
	private Carte carte = new Carte(listeZones, listeLignes, listeVilles, listeStations);
	
	public CartePanel(){
		fenetre = new JFrame();
		
		// On d�finit le titre de la fen�tre
		fenetre.setTitle("MapBuilder v0.0.0.0.1");

		// Taille par d�faut
		fenetre.setSize(WIDTH, HEIGHT);

		// On centre la fen�tre
		fenetre.setLocationRelativeTo(null);

		// On emp�che le redimensionnement de la fen�tre
		fenetre.setResizable(false);

		// Op�ration par d�faut quand on quitte
		fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// Import de la classe Affichage
		fenetre.setContentPane(this);
		
		fenetre.setVisible(true);
	}	
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		setBackground(new Color(100, 100, 100));
		
		Graphics2D g2 = (Graphics2D)g;
		g2.setStroke(new BasicStroke(2));
		
		g.setColor(new Color(65, 65, 65));
		g.fillRect(CartePanel.WIDTH - 300, 0, 300, CartePanel.HEIGHT);
		
		g.setColor(new Color(175, 175, 225));
		g.drawString("INFORMATIONS", CartePanel.WIDTH - 300 / 2 - g.getFontMetrics().stringWidth("INFORMATIONS") / 2, 20);
		
		carte.dessinerCarte(g);
		
//		// Affichage des zones
//		g.setColor(new Color(200, 100, 100));
//		
//		for(int i = 0; i < listeZones.size(); i++){
//			listeZones.elementAt(i).dessinerZone(g);
//		}
//
//		// Affichage des lignes
//		for(int i = 0; i < listeLignes.size(); i++){
//			listeLignes.elementAt(i).dessinerLigne(g2);
//		}
		
		// Affichage informations de la station cliqu�e
		if(stationActuelle != null){
			stationActuelle.dessinerInfo(g);
		}
		
		// Affichage nom de la station sur laquelle on passe
		if(stationPassageSouris != null){
			stationPassageSouris.dessinerNom(g);
		}
	}
	
	public JFrame getFenetre(){
		return fenetre;
	}
	
	public Station getStationActuelle(){
		return stationActuelle;
	}
	
	public Station getStationPassageSouris(){
		return stationPassageSouris;
	}
	
	public void setListeZones(Vector<Zone> zones){
		this.listeZones = zones;
	}
	
	public void setListeLignes(Vector<Ligne> lignes){
		this.listeLignes = lignes;
	}
	
	public void setStationActuelle(Station _station){
		this.stationActuelle = _station;
	}
	
	public void setStationPassageSouris(Station station){
		this.stationPassageSouris = station;
	}
}
