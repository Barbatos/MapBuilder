import java.awt.Graphics;
import javax.swing.JPanel;

public class Affichage extends JPanel {
	private static final long serialVersionUID = 1L;

	public void paintComponent(Graphics g){
		//Vous verrez cette phrase chaque fois que la méthode sera invoquée
		System.out.println("Je suis exécutée !");
		
		//Initialisation stations
		Station sta1 = new Station(new Coordonnees(10, 50), 1, "sta1");
		Station sta2 = new Station(new Coordonnees(50, 100), 2, "sta2");
		
		//Initialisation des segments
		Segment seg1 = new Segment(sta1, sta2, 1);
		
		//Affichage Station test1
		sta1.dessinerStation(g);
		
		//Affichage Station test2
		sta2.dessinerStation(g);
		
		//Affichage ligne test1-test2
		seg1.dessinerSegment(g);
	}
}
