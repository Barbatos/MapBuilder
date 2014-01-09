/*
 * This software is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.

 * This software is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.

 * You should have received a copy of the GNU Lesser General Public
 * License along with this software; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301  USA
 *
 * @author Bardoll
 * @author Charles "Barbatos" Duprey <barbatos@f1m.fr>
 * @author Soullessoni
 * @author Thornydre
 * 
 * @copyright (C) 2013 - 2014 Dat Java DreamTeam
 * 
 */

package vues;

import java.awt.Color;
import java.awt.Container;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import modeles.BaseDeDonnees;

import net.miginfocom.swing.MigLayout;

/**
 * Les vues permettant les ajouts de données sont modélisées par cette classe.
 * 
 * @version 1.0
 */
public class AjoutDonneesPanel extends JPanel{
	private static final long serialVersionUID = 1L;
	public final static int WIDTH = 200;
	public final static int HEIGHT = 200;
	
	private JFrame fenetre;
	private BaseDeDonnees bdd;
	
	/**
	 * Constructeur de la vue. Elle prend en paramètre une base de données sur laquelle seront faits les ajouts.
	 * 
	 * @param _bdd Une base de données, sur laquelle seront faits les ajouts.
	 * @see BaseDeDonnees
	 */
	public AjoutDonneesPanel(BaseDeDonnees _bdd){
		this.bdd = _bdd;
		
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
		boutonAjoutVille.addActionListener(new ActionListener() {  
            public void actionPerformed(ActionEvent e) {  
                ajouterVille();
            }  
        });  
		
		JButton boutonAjoutStation = new JButton("Ajouter une station");
		boutonAjoutStation.addActionListener(new ActionListener() {  
            public void actionPerformed(ActionEvent e) {  
                ajouterStation();
            }  
        });
		
		JButton boutonAjoutLigne = new JButton("Ajouter une ligne");
		boutonAjoutLigne.addActionListener(new ActionListener() {  
            public void actionPerformed(ActionEvent e) {  
                ajouterLigne();
            }  
        });
		
        JButton boutonAjoutZone = new JButton("Ajouter une zone");
        boutonAjoutZone.addActionListener(new ActionListener() {  
            public void actionPerformed(ActionEvent e) {  
                ajouterZone();
            }  
        });
        
        pane.add(boutonAjoutVille, "cell 0 1");
        pane.add(boutonAjoutStation, "cell 0 2");
        pane.add(boutonAjoutLigne, "cell 0 3");
        pane.add(boutonAjoutZone, "cell 0 4");
		
        fenetre.setContentPane(pane);
	}
	
	/**
	 * Méthode d'ajout d'une Ville à la base de données.
	 * @see bdd
	 */
	public void ajouterVille(){
		new AjoutVillePanel(bdd);
	}
	
	/**
	 * Méthode d'ajout d'une Station à la base de données.
	 * @see bdd
	 */	
	public void ajouterStation(){
		new AjoutStationPanel(bdd);
	}
	
	/**
	 * Méthode d'ajout d'une Zone à la base de données.
	 * @see bdd
	 */
	public void ajouterZone(){
		new AjoutZonePanel(bdd);
	}
	
	/**
	 * Méthode d'ajout d'une Ligne à la base de données.
	 * @see bdd
	 */
	public void ajouterLigne(){
		new AjoutLignePanel(bdd);
	}
	
	/**
	 * Méthode qui permet de dessiner la vue.
	 * 
	 * @param g Une instance de Graphics
	 * @see graphics
	 */
	public void paintComponent(Graphics g){
		super.paintComponent(g);
        setBackground(new Color(175, 175, 175));
	}
}
