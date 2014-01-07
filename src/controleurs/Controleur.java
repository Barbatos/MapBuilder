package controleurs;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import modeles.BaseDeDonnees;
import modeles.Horaire;
import modeles.Ligne;
import modeles.MoyenTransport;
import modeles.Station;
import modeles.Ville;
import modeles.Zone;
import vues.AjoutDonneesPanel;
import vues.CartePanel;
import vues.HorairesPanel;

public class Controleur {
	private CartePanel cartePanel;
	private Vector<Zone> listeZones = new Vector<Zone>();
	private Vector<Station> listeStations = new Vector<Station>();
	private Vector<Ligne> listeLignes = new Vector<Ligne>();
	private Vector<Ville> listeVilles = new Vector<Ville>();
	private Vector<MoyenTransport> listeMoyensTransport = new Vector<MoyenTransport>();
	private MouseListener mouseListener;
	private MouseMotionListener mouseMotionListener;
	private BaseDeDonnees bdd;
	private boolean clique = false;
	private boolean appui = false;
	
	public Controleur(CartePanel _carte){
		this.cartePanel = _carte;
		this.bdd = new BaseDeDonnees("jdbc:mysql://better.call.barbatos.fr:3306/mapbuilder", "mapbuilder", "");
	}
	
	public void initialiser() {
		ResultSet reponseMoyenTransport;
		ResultSet reponseStation;
		ResultSet reponseHoraire;
		ResultSet reponseLigne;
		ResultSet reponseZone;
		ResultSet reponseLigneStation;
		ResultSet reponseZoneStation;
		ResultSet reponseVille;
		
		/**
		 * Initialisation des moyens de transport
		 */
		try {
			reponseMoyenTransport = this.bdd.select("SELECT * FROM moyentransport");
			
			while(reponseMoyenTransport.next()){
				listeMoyensTransport.add(new MoyenTransport(reponseMoyenTransport.getInt("id"), reponseMoyenTransport.getString("nom")));
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		
		/**
		 * Initialisation des stations
		 */
		try {
			reponseStation = this.bdd.select("SELECT * FROM station");
			
			while(reponseStation.next()){
				listeStations.add(new Station(reponseStation.getInt("id"), reponseStation.getInt("coordX"), reponseStation.getInt("coordY"), reponseStation.getString("nom")));
			}
		} catch (SQLException e2) {
			e2.printStackTrace();
		}
		
		/**
		 * Initialisation des lignes
		 */
		try {
			reponseLigne = this.bdd.select("SELECT l.*, lt.idTransport FROM `ligne` l INNER JOIN `ligne-transport` lt ON lt.idLigne = l.id");
			
			while(reponseLigne.next()){
				listeLignes.add(new Ligne(reponseLigne.getInt("id"), reponseLigne.getString("nom"), new Color(reponseLigne.getInt("couleurR"), reponseLigne.getInt("couleurG"), reponseLigne.getInt("couleurB")), getMoyenTransportId(reponseLigne.getInt("idTransport"))));		
			}
		} catch (SQLException e3){
			e3.printStackTrace();
		}
		
		/**
		 * Initialisation des horaires
		 */
		try {
			reponseHoraire = this.bdd.select("SELECT * FROM horaire order by heure, minute");
			
			while(reponseHoraire.next()){
				getStationId(reponseHoraire.getInt("idStation")).ajouterHoraire(new Horaire(reponseHoraire.getInt("jSemaine"), reponseHoraire.getInt("heure"), reponseHoraire.getInt("minute"), reponseHoraire.getInt("periode")));
			}
		} catch (SQLException e4) {
			e4.printStackTrace();
		}
		
		/**
		 * Initialisation des relations stations/lignes
		 */
		try {
			reponseLigneStation = this.bdd.select("SELECT * FROM `station-ligne` ORDER BY idLigne, ordre");
			
			while(reponseLigneStation.next()){
				getLigneId(reponseLigneStation.getInt("idLigne")).ajouterStation(getStationId(reponseLigneStation.getInt("idStation")));
				getStationId(reponseLigneStation.getInt("idStation")).ajouterLigne(getLigneId(reponseLigneStation.getInt("idLigne")));;
			}
		} catch (SQLException e5){
			e5.printStackTrace();
		}
		
		/**
		 * Initialisation des zones
		 */
		try {
			reponseZone = this.bdd.select("SELECT * FROM zone");
			
			while(reponseZone.next()){
				listeZones.add(new Zone(reponseZone.getInt("id"), reponseZone.getString("nom"), new Color(reponseZone.getInt("couleurR"), reponseZone.getInt("couleurG"), reponseZone.getInt("couleurB"))));
			}
		} catch (SQLException e6) {
			e6.printStackTrace();
		}
		
		/**
		 * Initialisation des points de chaque zone
		 */
		try {
			reponseZone = this.bdd.select("SELECT * FROM `point-zone` order by idZone, ordre");
			
			while(reponseZone.next()){
				getZoneId(reponseZone.getInt("idZone")).ajouterPointX(reponseZone.getInt("coordX"));
				getZoneId(reponseZone.getInt("idZone")).ajouterPointY(reponseZone.getInt("coordY"));
			}
			
		} catch (SQLException e7) {
			e7.printStackTrace();
		}
		
		/**
		 * Initialisation des relations stations/zones
		 */
		try {
			reponseZoneStation = this.bdd.select("SELECT * FROM `station-zone`");
			
			while(reponseZoneStation.next()){
				getZoneId(reponseZoneStation.getInt("idZone")).ajouterStation(getStationId(reponseZoneStation.getInt("idStation")));
				getStationId(reponseZoneStation.getInt("idStation")).setZone(getZoneId(reponseZoneStation.getInt("idZone")));
			}
		} catch (SQLException e8){
			e8.printStackTrace();
		}
		
		/**
		 * Initialisation des villes
		 */
		try {
			reponseVille = this.bdd.select("SELECT * FROM ville");
			
			while(reponseVille.next()){
				listeVilles.add(new Ville(reponseVille.getString("nom")));
			}
		} catch (SQLException e9){
			e9.printStackTrace();
		}

		this.cartePanel.setListeStations(listeStations);
		this.cartePanel.setListeLignes(listeLignes);
		this.cartePanel.setListeZones(listeZones);
		
		mouseListener = new MouseListener(){
			public void mouseClicked(MouseEvent event){
				verifierClicStation(event.getX(), event.getY());
				verifierClicBouton(event.getX(), event.getY());
				cartePanel.repaint();
			}
			
			public void mouseEntered(MouseEvent event){}			
			public void mouseExited(MouseEvent event){}
			public void mouseReleased(MouseEvent event){
				if(appui == true && !verifierPassageStation(event.getX(), event.getY())){
					deplacerStation(event.getX(), event.getY());
				}
				appui = false;
			}
			
			public void mousePressed(MouseEvent event){
				if(verifierClicStation(event.getX(), event.getY())){
					appui = true;
				}
				else {
					appui = false;
				}
			}
		};
		
		mouseMotionListener = new MouseMotionListener(){
			public void mouseDragged(MouseEvent event){
				if(appui){
					System.out.println("Position souris: x:" +event.getX()+", y:"+event.getY());
				}
			}
			public void mouseMoved(MouseEvent event){
				verifierPassageStation(event.getX(), event.getY());
				cartePanel.repaint();
			}
		};
		
		this.cartePanel.addMouseMotionListener(mouseMotionListener);
		this.cartePanel.addMouseListener(mouseListener);
	}
	
	public void deplacerStation(int x, int y){
		if(!appui){
			return;
		}
		
		try {
			this.bdd.query("UPDATE station SET coordX = "+x+", coordY = "+y+" WHERE id = "+this.cartePanel.getStationActuelle().getId());
			this.cartePanel.getStationActuelle().setX(x);
			this.cartePanel.getStationActuelle().setY(y);
		} catch (SQLException e2) {
			e2.printStackTrace();
		}
		
		cartePanel.repaint();
	}
	
	public boolean verifierClicStation(int x, int y){
		for(int i = 0; i < listeStations.size(); i++){
			if( 
				(x <= (listeStations.elementAt(i).getX() + 7)) &&
				(x >= (listeStations.elementAt(i).getX() - 7)) && 
				(y <= (listeStations.elementAt(i).getY() + 7)) && 
				(y >= (listeStations.elementAt(i).getY() - 7))
			  ){
				this.cartePanel.setStationActuelle(listeStations.elementAt(i));
				clique = true;
				return true;
			}
		}
		
		return false;
	}
	
	public boolean verifierPassageStation(int x, int y){
		for(int i = 0; i < listeStations.size(); i++){
			if( 
				(x <= (listeStations.elementAt(i).getX() + 7)) &&
				(x >= (listeStations.elementAt(i).getX() - 7)) && 
				(y <= (listeStations.elementAt(i).getY() + 7)) && 
				(y >= (listeStations.elementAt(i).getY() - 7))
			  ){
				this.cartePanel.setStationPassageSouris(listeStations.elementAt(i));
				return true;
			}
		}
		
		return false;
	}
	
	public void verifierClicBouton(int x, int y){
		// Vérification du clic sur les boutons
		for(int i = 0;i < this.cartePanel.getListeBoutons().size();i++){
			if( 
				(x <= this.cartePanel.getBouton(i).getX() + this.cartePanel.getBouton(i).getLargeur()) &&
				(x >= this.cartePanel.getBouton(i).getX()) && 
				(y <= this.cartePanel.getBouton(i).getY() + this.cartePanel.getBouton(i).getHauteur()) && 
				(y >= this.cartePanel.getBouton(i).getY())
			){
				if(this.cartePanel.getBouton(i).getNom() == "Ajout de données"){
					new AjoutDonneesPanel(bdd);
					return;
				}
			}
		}
		
		// Vérification du clic sur les boutons d'horaires, seulement si on
		// a sélectionné la station qui nous intéresse.
		if(clique){
			for(int i = 0;i < this.cartePanel.getStationActuelle().getListeBoutonsHoraire().size();i++){
				if( 
					(x <= this.cartePanel.getStationActuelle().getBoutonHoraire(i).getX() + this.cartePanel.getStationActuelle().getBoutonHoraire(i).getLargeur()) &&
					(x >= this.cartePanel.getStationActuelle().getBoutonHoraire(i).getX()) && 
					(y <= this.cartePanel.getStationActuelle().getBoutonHoraire(i).getY() + this.cartePanel.getStationActuelle().getBoutonHoraire(i).getHauteur()) && 
					(y >= this.cartePanel.getStationActuelle().getBoutonHoraire(i).getY())
				){
					new HorairesPanel(this.cartePanel.getStationActuelle().getLigne(i));
					return;
				}
			}
		}
	}
	
	public Station getStationId(int id){
		for(int i = 0;i < listeStations.size();i++){
			if(listeStations.elementAt(i).getId() == id){
				return listeStations.elementAt(i);
			}
		}
		return null;
	}
	
	public Ligne getLigneId(int id){
		for(int i = 0;i < listeLignes.size();i++){
			if(listeLignes.elementAt(i).getId() == id){
				return listeLignes.elementAt(i);
			}
		}
		return null;
	}
	
	public MoyenTransport getMoyenTransportId(int id){
		for(int i = 0;i < listeMoyensTransport.size();i++){
			if(listeMoyensTransport.elementAt(i).getId() == id){
				return listeMoyensTransport.elementAt(i);
			}
		}
		return null;
	}
	
	public Zone getZoneId(int id){
		for(int i = 0;i < listeZones.size();i++){
			if(listeZones.elementAt(i).getId() == id){
				return listeZones.elementAt(i);
			}
		}
		return null;
	}
}
