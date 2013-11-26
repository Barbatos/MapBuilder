package vues;

import javax.swing.*;

public class Vue {
	private JFrame fenetre;
	private Affichage panel;
	
	public Vue(){
		fenetre = new JFrame();
		panel = new Affichage();
		
		// On définit le titre de la fenêtre
		fenetre.setTitle("MapBuilder v0.0.0.0.1");

		// Taille par défaut
		fenetre.setSize(1200, 675);

		// On centre la fenêtre
		fenetre.setLocationRelativeTo(null);

		// On empêche le redimensionnement de la fenêtre
		fenetre.setResizable(false);

		// Opération par défaut quand on quitte
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
