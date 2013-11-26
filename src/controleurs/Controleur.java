package controleurs;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Vector;

import modeles.*;
import vues.*;

public class Controleur {
	private Vue vue;
	private Vector<Zone> listeZones = new Vector<Zone>();
	private Vector<Ligne> listeLignes = new Vector<Ligne>();
	
	public Controleur(Vue _vue){
		this.vue = _vue;
	}
	
	public void initialiser(){
		
		//Initialisation moyens de transport
		MoyenTransport bus = new MoyenTransport(1, "Bus");
		MoyenTransport tram = new MoyenTransport(2, "Tram");
		
		//Initialisation stations
		Station sta1 = new Station(new Coordonnees(10, 50), 1, "sta1");
		Station sta2 = new Station(new Coordonnees(50, 100), 2, "sta2");
		Station sta3 = new Station(new Coordonnees(100, 150), 3, "sta3");
		Station sta4 = new Station(new Coordonnees(200, 300), 4, "sta4");
		Station sta5 = new Station(new Coordonnees(500, 310), 5, "sta5");
		Station sta6 = new Station(new Coordonnees(50, 170), 6, "sta6");
		Station sta7 = new Station(new Coordonnees(170, 70), 7, "sta7");
		Station sta8 = new Station(new Coordonnees(500, 500), 8, "sta8");
		
		//Ajout des moyens de transport
		sta1.insertTransport(bus);
		sta1.insertTransport(tram);

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
		Ligne li1 = new Ligne(1, "1", new Color(200, 255, 200));
		li1.setStationDepart(sta1);
		li1.setStationArrivee(sta5);
		li1.insertStation(sta2, 1);
		li1.insertStation(sta3, 2);
		li1.insertStation(sta4, 3);
		li1.insertStation(sta5, 4);
		
		Ligne li2 = new Ligne(2, "2", new Color(110, 200, 250));
		li2.setStationDepart(sta6);
		li2.setStationArrivee(sta8);
		li2.insertStation(sta3, 1);
		li2.insertStation(sta7, 2);
		
		Ligne li3 = new Ligne(3, "3", new Color(40, 70, 20));
		li3.setStationDepart(sta1);
		li3.setStationArrivee(sta8);
		li3.insertStation(sta2, 1);
		li3.insertStation(sta3, 2);
		li3.insertStation(sta4, 3);
		li3.insertStation(sta5, 4);
		li3.insertStation(sta6, 5);
		li3.insertStation(sta7, 6);
		
		listeLignes.add(li1);
		listeLignes.add(li2);
		listeLignes.add(li3);
		this.vue.setListeLignes(listeLignes);
	}
}
