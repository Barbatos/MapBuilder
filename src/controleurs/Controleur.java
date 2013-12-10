package controleurs;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import modeles.BaseDeDonnees;
import modeles.Ligne;
import modeles.MoyenTransport;
import modeles.Station;
import modeles.Zone;
import vues.Carte;

public class Controleur {
	private Carte carte;
	private Vector<Zone> listeZones = new Vector<Zone>();
	private Vector<Station> listeStations = new Vector<Station>();
	private Vector<Ligne> listeLignes = new Vector<Ligne>();
	private Vector<MoyenTransport> listeMoyensTransport = new Vector<MoyenTransport>();
	private MouseListener mouseListener;
	private MouseMotionListener mouseMotionListener;
	private BaseDeDonnees bdd;
	private boolean clique = false;
	
	public Controleur(Carte _carte){
		this.carte = _carte;
		this.bdd = new BaseDeDonnees("jdbc:mysql://better.call.barbatos.fr:3306/mapbuilder", "mapbuilder", "bite");
	}
	
	public void initialiser() {
		ResultSet reponseMoyenTransport;
		ResultSet reponseStation;
		ResultSet reponseLigne;
		ResultSet reponseZone;
		ResultSet reponseLigneStation;
		
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
				MoyenTransport tmp = getMoyenTransportId(reponseLigne.getInt("idTransport"));
				
				listeLignes.add(new Ligne(reponseLigne.getInt("id"), reponseLigne.getString("nom"), new Color(reponseLigne.getInt("couleurR"), reponseLigne.getInt("couleurG"), reponseLigne.getInt("couleurB")), tmp));		
			}
		} catch (SQLException e3){
			e3.printStackTrace();
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
		} catch (SQLException e4){
			e4.printStackTrace();
		}
		
		//Initialisation des zones
		try {
			reponseZone = this.bdd.select("SELECT * FROM zone");
			
			while(reponseZone.next()){
				listeZones.add(new Zone(reponseZone.getInt("id"), reponseZone.getString("nom")));
			}
		} catch (SQLException e5) {
			e5.printStackTrace();
		}

		this.carte.setListeLignes(listeLignes);
		
		mouseListener = new MouseListener(){
			public void mouseClicked(MouseEvent event){
				verifierClicStation(event.getX(), event.getY());
				if(clique){
					verifierClicBoutonHoraire(event.getX(), event.getY());
				}
				carte.repaint();
			}
			
			public void mouseEntered(MouseEvent event){}			
			public void mouseExited(MouseEvent event){}
			public void mouseReleased(MouseEvent event){}
			public void mousePressed(MouseEvent event){}
		};
		
		mouseMotionListener = new MouseMotionListener(){
			public void mouseDragged(MouseEvent event){}
			public void mouseMoved(MouseEvent event){
				verifierPassageStation(event.getX(), event.getY());
				carte.repaint();
			}
		};
		
		this.carte.addMouseMotionListener(mouseMotionListener);
		this.carte.addMouseListener(mouseListener);
	}
	
	public void verifierClicStation(int x, int y){
		for(int i = 0; i < listeStations.size(); i++){
			if( 
				(x <= (listeStations.elementAt(i).getX() + 7)) &&
				(x >= (listeStations.elementAt(i).getX() - 7)) && 
				(y <= (listeStations.elementAt(i).getY() + 7)) && 
				(y >= (listeStations.elementAt(i).getY() - 7))
			  ){
				this.carte.setStationActuelle(listeStations.elementAt(i));
				clique = true;
			}
		}
	}
	
	public void verifierPassageStation(int x, int y){
		for(int i = 0; i < listeStations.size(); i++){
			if( 
				(x <= (listeStations.elementAt(i).getX() + 7)) &&
				(x >= (listeStations.elementAt(i).getX() - 7)) && 
				(y <= (listeStations.elementAt(i).getY() + 7)) && 
				(y >= (listeStations.elementAt(i).getY() - 7))
			  ){
				this.carte.setStationPassageSouris(listeStations.elementAt(i));
			}
		}
	}
	
	public void verifierClicBoutonHoraire(int x, int y){
		for(int i = 0;i < this.carte.getStationActuelle().getListeBoutonsHoraire().size();i++){
			if( 
				(x <= this.carte.getStationActuelle().getBoutonHoraire(i).getX() + this.carte.getStationActuelle().getBoutonHoraire(i).getLargeur()) &&
				(x >= this.carte.getStationActuelle().getBoutonHoraire(i).getX()) && 
				(y <= this.carte.getStationActuelle().getBoutonHoraire(i).getY() + this.carte.getStationActuelle().getBoutonHoraire(i).getHauteur()) && 
				(y >= this.carte.getStationActuelle().getBoutonHoraire(i).getY())
			){
				//TODO
				return;
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
}
