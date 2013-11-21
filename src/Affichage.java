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
		g.fillOval(test1.getCoordonnees().getX() - 10/2, test1.getCoordonnees().getY() - 10/2, 10, 10);
		g.drawString(test1.getNom(), test1.getCoordonnees().getX() + 7, test1.getCoordonnees().getY() - 3);
		
		//Affichage Station test2
		g.fillOval(test2.getCoordonnees().getX() - 10/2, test2.getCoordonnees().getY() - 10/2, 10, 10);
		g.drawString(test2.getNom(), test2.getCoordonnees().getX() + 7, test2.getCoordonnees().getY() - 3);
		
		//Affichage ligne test1-test2
		g.drawLine(test1.getCoordonnees().getX(), test1.getCoordonnees().getY(), test2.getCoordonnees().getX(), test2.getCoordonnees().getY());
	}
}
