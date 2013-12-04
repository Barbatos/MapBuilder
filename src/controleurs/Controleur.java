package controleurs;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Vector;

import modeles.Horaire;
import modeles.Ligne;
import modeles.MoyenTransport;
import modeles.Station;
import modeles.Zone;
import modeles.BaseDeDonnees;
import vues.Vue;

public class Controleur {
	private Vue vue;
	private Vector<Zone> listeZones = new Vector<Zone>();
	private Vector<Ligne> listeLignes = new Vector<Ligne>();
	private MouseListener mouseListener;
	private BaseDeDonnees bdd;
	private boolean clique = false;
	
	public Controleur(Vue _vue){
		this.vue = _vue;
		this.bdd = new BaseDeDonnees("jdbc:mysql://localhost:3306/mapbuilder", "root", "");
	}
	
	public void initialiser(){
		//Initialisation moyens de transport
		MoyenTransport bus = new MoyenTransport(1, "Bus");
		MoyenTransport tram = new MoyenTransport(2, "Tram");
		
		//Initialisation stations
		Station sta1 = new Station(1, 10, 50, "sta1");
		Station sta2 = new Station(2, 50, 100, "sta2");
		Station sta3 = new Station(3, 100, 150, "sta3");
		Station sta4 = new Station(4, 200, 300, "sta4");
		Station sta5 = new Station(5, 500, 310, "sta5");
		Station sta6 = new Station(6, 50, 170, "sta6");
		Station sta7 = new Station(7, 170, 70, "sta7");
		Station sta8 = new Station(8, 500, 500, "sta8");

		//Initialisation zone
		Zone zon1 = new Zone(1, "1");
		zon1.insertStation(sta1);
		zon1.insertStation(sta2);
		zon1.insertStation(sta3);
		zon1.insertStation(sta4);
		zon1.insertStation(sta5);
		zon1.insertStation(sta6);
		zon1.insertStation(sta7);
		zon1.insertStation(sta8);
		listeZones.add(zon1);
		
		this.vue.setListeZones(listeZones);
		
		//Initialisation des lignes
		Ligne li1 = new Ligne(1, "1", new Color(200, 255, 200), tram);
		li1.setStationDepart(sta1);
		li1.setStationArrivee(sta5);
		li1.insertStation(sta2, 1);
		li1.insertStation(sta3, 2);
		li1.insertStation(sta4, 3);
		
		for(int i = 0; i < 10; i++) {
			for(int j = 0; j < li1.getListeStations().size(); j++) {
				Horaire hor = new Horaire(i, j, i+j, i*j);
				li1.getStation(j).insertHoraire(hor);
			}
		}
		
		Ligne li2 = new Ligne(2, "2", new Color(110, 200, 250), bus);
		li2.setStationDepart(sta6);
		li2.setStationArrivee(sta8);
		li2.insertStation(sta3, 1);
		li2.insertStation(sta7, 2);
		
		System.out.println(li1);

		for(int i = 0;i < li1.getListeStations().size();i++){
			li1.getStation(i).insertLigne(li1);
		}
		
		for(int i = 0;i < li2.getListeStations().size();i++){
			li2.getStation(i).insertLigne(li2);
		}
		
		listeLignes.add(li1);
		listeLignes.add(li2);
		this.vue.setListeLignes(listeLignes);
		
		mouseListener = new MouseListener(){
			public void mouseClicked(MouseEvent event){
				verifierClicStation(event.getX(), event.getY());
				if(clique){
					verifierClicBoutonHoraire(event.getX(), event.getY());
				}
				vue.repaint();
			}
			public void mouseEntered(MouseEvent event){}
			public void mouseExited(MouseEvent event){}
			public void mouseReleased(MouseEvent event){}
			public void mousePressed(MouseEvent event){}
		};
		
		this.vue.addMouseListener(mouseListener);
	}
	
	public void verifierClicStation(int x, int y){
		for(int j = 0; j < listeZones.size(); j++){
			Vector<Station> listeStations = listeZones.elementAt(j).getListeStations();
			for(int i = 0; i < listeStations.size(); i++){
				if( 
					(x <= (listeStations.elementAt(i).getX() + 7)) &&
					(x >= (listeStations.elementAt(i).getX() - 7)) && 
					(y <= (listeStations.elementAt(i).getY() + 7)) && 
					(y >= (listeStations.elementAt(i).getY() - 7))
				  ){
					this.vue.setStationActuelle(listeStations.elementAt(i));
					clique = true;
				}
			}
		}
	}
	
	public void verifierClicBoutonHoraire(int x, int y){
		System.out.println("youpi");
		for(int i = 0;i < this.vue.getStationActuelle().getListeBoutonsHoraire().size();i++){
			if( 
				(x <= this.vue.getStationActuelle().getBoutonHoraire(i).getX() + this.vue.getStationActuelle().getBoutonHoraire(i).getLargeur()) &&
				(x >= this.vue.getStationActuelle().getBoutonHoraire(i).getX()) && 
				(y <= this.vue.getStationActuelle().getBoutonHoraire(i).getY() + this.vue.getStationActuelle().getBoutonHoraire(i).getHauteur()) && 
				(y >= this.vue.getStationActuelle().getBoutonHoraire(i).getY())
			){
				System.out.println("YEAH !" + this.vue.getStationActuelle().getBoutonHoraire(i).getNom());
				return;
			}
		}
	}
}
