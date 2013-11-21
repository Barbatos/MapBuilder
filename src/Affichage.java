import java.awt.Graphics;
import javax.swing.JPanel;

public class Affichage extends JPanel {
	private static final long serialVersionUID = 1L;

	public void paintComponent(Graphics g){
		//Vous verrez cette phrase chaque fois que la méthode sera invoquée
		System.out.println("yop");
		
		//Initialisation stations
		Station sta1 = new Station(new Coordonnees(10, 50), 1, "sta1");
		Station sta2 = new Station(new Coordonnees(50, 100), 2, "sta2");
		Station sta3 = new Station(new Coordonnees(100, 150), 3, "sta3");
		Station sta4 = new Station(new Coordonnees(200, 300), 4, "sta4");
		Station sta5 = new Station(new Coordonnees(500, 310), 5, "sta5");
		Station sta6 = new Station(new Coordonnees(50, 170), 6, "sta6");
		Station sta7 = new Station(new Coordonnees(170, 70), 7, "sta7");

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
		li2.setStationArrivee(sta7);
		li2.insertStation(sta3, 1);
		
		//Affichage ligne li1
		li1.dessinerLigne(g);
		
		//Affichager ligne li2
		li2.dessinerLigne(g);
	}
}
