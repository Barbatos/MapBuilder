package vues;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class AjoutDonneesPanel extends JPanel{
	private static final long serialVersionUID = 1L;
	public final static int WIDTH = 1000;
	public final static int HEIGHT = 500;
	
	private JFrame fenetre;
	
	public AjoutDonneesPanel(){
		
		fenetre = new JFrame();
		
		// On définit le titre de la fenêtre
		fenetre.setTitle("Ajout de données");

		// Taille par défaut
		fenetre.setSize(WIDTH, HEIGHT);

		// On centre la fenêtre
		fenetre.setLocationRelativeTo(null);

		// On empêche le redimensionnement de la fenêtre
		fenetre.setResizable(false);
		
		// Import de la classe Affichage
		fenetre.setContentPane(this);
		
		fenetre.setVisible(true);
	}
	
	public void paintComponent(Graphics g){
		JTextField nomStation = new JTextField();
		
		super.paintComponent(g);
		setBackground(new Color(175, 175, 175));
		
	}
}
