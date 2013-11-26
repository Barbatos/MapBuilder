import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

public class Affichage extends JPanel {
	private static final long serialVersionUID = 1L;

	public void paintComponent(Graphics g){
		super.paintComponent(g);
		setBackground(new Color(100, 100, 100));
		
		Graphics2D g2 = (Graphics2D)g;
		g2.setStroke(new BasicStroke(2));
		
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
		
		//Initialisation des lignes
		Ligne li1 = new Ligne(1, "1");
		li1.setStationDepart(sta1);
		li1.setStationArrivee(sta5);
		li1.insertStation(sta2, 1);
		li1.insertStation(sta3, 2);
		li1.insertStation(sta4, 3);
		li1.insertStation(sta5, 4);
		
		Ligne li2 = new Ligne(2, "2");
		li2.setStationDepart(sta6);
		li2.setStationArrivee(sta8);
		li2.insertStation(sta3, 1);
		li2.insertStation(sta7, 2);
		
		Ligne li3 = new Ligne(3, "3");
		li3.setStationDepart(sta1);
		li3.setStationArrivee(sta8);
		li3.insertStation(sta2, 1);
		li3.insertStation(sta3, 2);
		li3.insertStation(sta4, 3);
		li3.insertStation(sta5, 4);
		li3.insertStation(sta6, 5);
		li3.insertStation(sta7, 6);
		
		//Affichage zone zon1
		g.setColor(new Color(200, 100, 100));
		zon1.dessinerZone(g);

		//Affichage ligne li1
		li1.dessinerLigne(g, new Color(200, 255, 200));
		
		//Affichager ligne li2
		li2.dessinerLigne(g, new Color(200, 200, 255));
		
		//Affichage rectangle d'information
		g.setColor(new Color(65, 65, 65));
		g.fillRect(1200 - 300, 0, 1200, 200);
		
		//Affichage informations station sta1
		sta1.dessinerInfo(g);
	}
}
