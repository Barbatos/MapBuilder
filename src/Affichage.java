import java.awt.Graphics;
import javax.swing.JPanel;

public class Affichage extends JPanel {
	private static final long serialVersionUID = 1L;

	public void paintComponent(Graphics g){
		//Vous verrez cette phrase chaque fois que la méthode sera invoquée
		System.out.println("Je suis exécutée !");
		
		//Initialisation stations
		Station test1 = new Station(new Coordonnees(10, 50), 1, "test1");
		Station test2 = new Station(new Coordonnees(50, 100), 1, "test2");
		
		//Affichage Station test1
		test1.dessinerStation(g);
		
		//Affichage Station test2
		test2.dessinerStation(g);
		
		//Affichage ligne test1-test2
		g.drawLine(test1.getCoordonnees().getX(), test1.getCoordonnees().getY(), test2.getCoordonnees().getX(), test2.getCoordonnees().getY());
	}
}
