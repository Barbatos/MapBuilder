package vues;

import javax.swing.*;

public class Vue {
	private JFrame fenetre;
	private Affichage panel;
	
	public Vue(){
		fenetre = new JFrame();
		panel = new Affichage();
		
		// On d�finit le titre de la fen�tre
		fenetre.setTitle("MapBuilder v0.0.0.0.1");

		// Taille par d�faut
		fenetre.setSize(1200, 675);

		// On centre la fen�tre
		fenetre.setLocationRelativeTo(null);

		// On emp�che le redimensionnement de la fen�tre
		fenetre.setResizable(false);

		// Op�ration par d�faut quand on quitte
		fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// Import de la classe Affichage
		fenetre.setContentPane(panel);
		
		fenetre.setVisible(true);
	}
	
	public JFrame getFenetre(){
		return fenetre;
	}
	
	public Affichage getPanel(){
		return panel;
	}
}
