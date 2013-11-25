import java.awt.Color;

import javax.swing.JFrame;

public class Fenetre extends JFrame {
	private static final long serialVersionUID = 1L;
	private Affichage affichage = new Affichage();

	public Fenetre(){                
		super();
		CreerFenetre();
	}     

	private void CreerFenetre(){		
		// On définit le titre de la fenêtre
		setTitle("MapBuilder v0.0.0.0.1");

		// Taille par défaut: 800x600
		setSize(1200, 675);

		// On centre la fenêtre
		setLocationRelativeTo(null);

		// On empêche le redimensionnement de la fenêtre
		setResizable(false);

		// Opération par défaut quand on quitte
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// Import de la classe Affichage
		this.setContentPane(affichage);
	}

	public static void main(String args[]){
		Fenetre fenetre = new Fenetre();
		fenetre.setVisible(true);
	}
}