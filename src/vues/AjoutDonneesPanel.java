package vues;

import java.awt.Color;
import java.awt.Container;
import java.awt.Graphics;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import net.miginfocom.swing.MigLayout;

public class AjoutDonneesPanel extends JPanel{
	private static final long serialVersionUID = 1L;
	public final static int WIDTH = 200;
	public final static int HEIGHT = 200;
	
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
		
		MigLayout layout = new MigLayout();
		pane.setLayout(layout);
		
		JButton boutonAjoutVille = new JButton("Ajouter une ville");
		JButton boutonAjoutStation = new JButton("Ajouter une station");
		JButton boutonAjoutLigne = new JButton("Ajouter une ligne");
        JButton boutonAjoutZone = new JButton("Ajouter une zone");
        
        pane.add(boutonAjoutVille, "cell 0 1");
        pane.add(boutonAjoutStation, "cell 0 2");
        pane.add(boutonAjoutLigne, "cell 0 3");
        pane.add(boutonAjoutZone, "cell 0 4");
		
        fenetre.setContentPane(pane);
	}
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
        setBackground(new Color(175, 175, 175));
	}
}