package vues;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Graphics;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

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
		
		Container pane = fenetre.getContentPane();
		
		pane.setLayout(new BoxLayout(pane, BoxLayout.Y_AXIS));
		
		JButton boutonAjoutVille = new JButton("Ajouter une ville");
		boutonAjoutVille.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		JButton boutonAjoutStation = new JButton("Ajouter une station");
		boutonAjoutStation.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		JButton boutonAjoutLigne = new JButton("Ajouter une ligne");
		boutonAjoutLigne.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        JButton boutonAjoutZone = new JButton("Ajouter une zone");
        boutonAjoutZone.setAlignmentX(Component.CENTER_ALIGNMENT);
       
        pane.add(boutonAjoutVille);
        pane.add(boutonAjoutStation);
        pane.add(boutonAjoutLigne);
        pane.add(boutonAjoutZone);
        
        fenetre.setContentPane(pane);
	}
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
        setBackground(new Color(175, 175, 175));
	}
}
