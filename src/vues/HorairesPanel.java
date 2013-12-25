package vues;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;

import modeles.Ligne;

public class HorairesPanel extends JPanel{
	private static final long serialVersionUID = 1L;
	public final static int WIDTH = 1000;
	public final static int HEIGHT = 500;
	
	private JFrame fenetre;
	private Ligne ligne;
	
	public HorairesPanel(Ligne ligne){
		this.ligne = ligne;
		
		fenetre = new JFrame();
		
		// On définit le titre de la fenêtre
		fenetre.setTitle("Horaires " + ligne.getNom());

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
		super.paintComponent(g);
		setBackground(new Color(175, 175, 175));
		
		for(int i = 0;i < this.ligne.getListeStations().size();i++){
			g.setColor(new Color(10, 10, 10));
			g.drawRect(0, i * 30, 180, 30);
			
			int h = g.getFontMetrics().getHeight();
			int l = g.getFontMetrics().stringWidth(this.ligne.getStation(i).getNom());
			
			g.drawString(this.ligne.getStation(i).getNom(), 0 - l / 2 + 180 / 2, (i * 30) + h / 4 + 30 / 2);
			
			for(int j = 0;j < this.ligne.getStation(i).getListeHoraires().size();j++){
				this.ligne.getStation(i).getListeHoraires().elementAt(j).dessinerHoraire(g, 180 + j * 90, i * 30);
			}
		}
	}
	
}
