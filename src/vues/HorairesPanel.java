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
		
		// On définit le titre de la fenêtre
		fenetre.setTitle("Horaires " + station.getNom());

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
	
	public void drawComponent(Graphics g){
		super.paintComponent(g);
		setBackground(new Color(100, 200, 100));
		
		g.setColor(new Color(100, 100, 200));
		g.fillRect(0, 0, 50, 50);
		
		for(int i = 0;i < this.station.getListeHoraires().size();i++){
			this.station.getListeHoraires().elementAt(i).dessinerHoraire(g, 0, i * 30);
		}
	}
	
}
