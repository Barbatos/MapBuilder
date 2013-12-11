package vues;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;

import modeles.Station;

public class HorairesPanel extends JPanel{
	private static final long serialVersionUID = 1L;
	public final static int WIDTH = 1000;
	public final static int HEIGHT = 500;
	
	private JFrame fenetre;
	private Station station;
	
	public HorairesPanel(Station station){
		this.station = station;
		
		fenetre = new JFrame();
		
		// On d�finit le titre de la fen�tre
		fenetre.setTitle("Horaires " + station.getNom());

		// Taille par d�faut
		fenetre.setSize(WIDTH, HEIGHT);

		// On centre la fen�tre
		fenetre.setLocationRelativeTo(null);

		// On emp�che le redimensionnement de la fen�tre
		fenetre.setResizable(false);
		
		// Import de la classe Affichage
		fenetre.setContentPane(this);
		
		fenetre.setVisible(true);
	}
	
	public void drawComponent(Graphics g){
		super.paintComponent(g);
		setBackground(new Color(100, 200, 100));
	}
}
