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

import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import modeles.BaseDeDonnees;

import net.miginfocom.swing.MigLayout;

/**
 * L'ajout des villes dans la base de données passe par la vue générée par cette classe
 * 
 * @version 1.0
 */
public class AjoutVillePanel extends JPanel{
	private static final long serialVersionUID = 1L;
	public final static int WIDTH = 200;
	public final static int HEIGHT = 110;
	
	private JFrame fenetre;
	private BaseDeDonnees bdd;
	
	/**
	 * Constructeur de la fenêtre qui contient le formulaire d'ajout de la ville. 
	 * Elle prend en paramètre la base de données où seront effectives les modifications.
	 * 
	 * @param _bdd
	 * @see BaseDeDonnees
	 */
	public AjoutVillePanel(BaseDeDonnees _bdd){
		this.bdd = _bdd;
		
		fenetre = new JFrame();
		
		// On définit le titre de la fenêtre
		fenetre.setTitle("Ajouter une ville");

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
		
		MigLayout layout = new MigLayout("fillx", "[right]rel[grow,fill]", "[]10[]");
		pane.setLayout(layout);
		
		JLabel nomVilleLabel = new JLabel("Nom ville");
		final JTextField nomVilleField = new JTextField();
		
		JButton boutonOk = new JButton("Ajouter");
		boutonOk.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				ajouterVilleBdd(nomVilleField.getText());
			}
		});
		
		
		JButton boutonRetour = new JButton("Retour");
		boutonRetour.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				fenetre.dispose();
			}
		});
		
		pane.add(nomVilleLabel);
		pane.add(nomVilleField, "wrap");
		pane.add(boutonRetour);
		pane.add(boutonOk);
		
        fenetre.setContentPane(pane);
	}
	
	/**
	 * Méthode qui ajoute la ville dans la base de données
	 */
	public void ajouterVilleBdd(String ville){
		try {
			this.bdd.query("INSERT INTO ville (nom) VALUE ('"+ville+"')");
			System.out.println("Ville ajoutée: "+ville);
			fenetre.dispose();
		} catch (SQLException e2) {
			e2.printStackTrace();
		}
	}
	
	/**
	 * Cette méthode dessine à l'écran la fenêtre formulaire.
	 * 
	 * @param g Une instance de Graphics
	 * @see Graphics
	 */
	public void paintComponent(Graphics g){
		super.paintComponent(g);
        setBackground(new Color(175, 175, 175));
	}
}
