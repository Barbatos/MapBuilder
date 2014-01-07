package vues;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Container;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;

import modeles.BaseDeDonnees;
import modeles.Bouton;
import modeles.Carte;
import modeles.Ligne;
import modeles.Station;
import modeles.Ville;
import modeles.Zone;
import net.miginfocom.swing.MigLayout;

public class CartePanel extends JPanel{
	private static final long serialVersionUID = 1L;
	public final static int WIDTH = 1200;
	public final static int HEIGHT = 675;
	
	public static int VILLE = 0;
	
	private JFrame fenetre;
	private Vector<Zone> listeZones = new Vector<Zone>();
	private Vector<Ligne> listeLignes = new Vector<Ligne>();
	private Vector<Ville> listeVilles = new Vector<Ville>();
	private Vector<Station> listeStations = new Vector<Station>();
	private Station stationActuelle = null;
	private Station stationPassageSouris = null;
	private Vector<Bouton> listeBoutons = new Vector<Bouton>();
	
	private ImageIcon backgroundImage = null;
	private BaseDeDonnees bdd;
	
	private final JComboBox<String> comboVilles = new JComboBox<String>();
	
	private Carte carte = new Carte(listeZones, listeLignes, listeVilles, listeStations);
	
	public CartePanel(BaseDeDonnees _bdd){
		ResultSet reponseVille;
		
		this.bdd = _bdd;
		
		fenetre = new JFrame();
		
		// On définit le titre de la fenêtre
		fenetre.setTitle("MapBuilder v0.0.0.0.1");

		// Taille par défaut
		fenetre.setSize(WIDTH, HEIGHT);

		// On centre la fenêtre
		fenetre.setLocationRelativeTo(null);

		// On empêche le redimensionnement de la fenêtre
		fenetre.setResizable(false);

		// Opération par défaut quand on quitte
		fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// Import de la classe Affichage
		fenetre.setContentPane(this);
		
		fenetre.setVisible(true);
		
		Container pane = fenetre.getContentPane();
		
		MigLayout layout = new MigLayout("fillx", "[right]rel[grow,fill]", "[]10[]");
		pane.setLayout(layout);
		
		try {
			reponseVille = this.bdd.select("SELECT nom FROM `ville` ORDER BY nom ASC");
			
			while(reponseVille.next()){
				comboVilles.addItem(reponseVille.getString("nom"));
			}
		} catch (SQLException e){
			e.printStackTrace();
		}
		
		pane.add(comboVilles, "wrap");
		
		comboVilles.addItemListener(new ItemListener(){
			public void itemStateChanged(ItemEvent e){
				VILLE = comboVilles.getSelectedIndex();
				System.out.println(VILLE);
				carte.setVilleCourante(VILLE);
			}
		});
		
		fenetre.setContentPane(pane);
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
		
		backgroundImage = new ImageIcon("caen.png");
		g.drawImage(backgroundImage.getImage(), 0, 0, 894, 660, this);
		
		carte.dessinerCarte(g);
		
		// Affichage informations de la station cliquée
		if(stationActuelle != null){
			stationActuelle.dessinerInfo(g);
		}
		
		// Affichage nom de la station sur laquelle on passe
		if(stationPassageSouris != null){
			stationPassageSouris.dessinerNom(g);
		}
		
		// Affichage du bouton d'ajout de données
		listeBoutons.add(new Bouton("Ajout de données", CartePanel.WIDTH - 270, 600, 240, 30));
		
		// Affichage des boutons
		for(int i = 0;i < listeBoutons.size();i++){
			this.listeBoutons.elementAt(i).paintComponent(g);
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
	
	public void setListeVilles(Vector<Ville> villes){
		this.carte.setListeVilles(villes);
	}
	
	public void setListeStations(Vector<Station> stations){
		this.carte.setListeStations(stations);
	}
	
	public void setListeZones(Vector<Zone> zones){
		this.carte.setListeZones(zones);
	}
	
	public void setListeLignes(Vector<Ligne> lignes){
		this.carte.setListeLignes(lignes);
	}
	
	public void setStationActuelle(Station _station){
		this.stationActuelle = _station;
	}
	
	public void setStationPassageSouris(Station station){
		this.stationPassageSouris = station;
	}
	
	public Vector<Bouton> getListeBoutons(){
		return this.listeBoutons;
	}
	
	public Bouton getBouton(int i){
		return this.listeBoutons.elementAt(i);
	}
}
