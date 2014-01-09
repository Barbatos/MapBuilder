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
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import modeles.BaseDeDonnees;
import net.miginfocom.swing.MigLayout;

public class AjoutZonePanel extends JPanel{
	private static final long serialVersionUID = 1L;
	public final static int WIDTH = 260;
	public final static int HEIGHT = 260;
	
	private JFrame fenetre;
	private BaseDeDonnees bdd;
	
	private final JTextField nomZoneField = new JTextField();
	private final JTextField couleurR = new JTextField();
	private final JTextField couleurG = new JTextField();
	private final JTextField couleurB = new JTextField();
	private final JComboBox<String> listeVilles = new JComboBox<String>();
	
	/**
	 * Constructeur de la fenêtre qui contient le formulaire d'ajout de la zone. 
	 * Elle prend en paramètre la base de données où seront effectives les modifications.
	 * 
	 * @param _bdd
	 * @see BaseDeDonnees
	 */
	public AjoutZonePanel(BaseDeDonnees _bdd){
		ResultSet reponseVille;
		
		this.bdd = _bdd;
		
		fenetre = new JFrame();
		
		// On définit le titre de la fenêtre
		fenetre.setTitle("Ajouter une zone");

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
		
		JLabel nomZoneLabel  = new JLabel("Nom zone");
		JLabel couleurRLabel = new JLabel("Couleur R");
		JLabel couleurGLabel = new JLabel("Couleur G");
		JLabel couleurBLabel = new JLabel("Couleur B");
		JLabel villeLabel 	 = new JLabel("Ville");
			
		try {
			reponseVille = this.bdd.select("SELECT nom FROM `ville` ORDER BY nom ASC");
			
			while(reponseVille.next()){
				listeVilles.addItem(reponseVille.getString("nom"));
			}
		} catch (SQLException e){
			e.printStackTrace();
		}
		
		JButton boutonOk = new JButton("Ajouter");
		boutonOk.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				ajouterZoneBdd();
			}
		});
		
		
		JButton boutonRetour = new JButton("Retour");
		boutonRetour.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				fenetre.dispose();
			}
		});
		
		pane.add(nomZoneLabel);
		pane.add(nomZoneField, "wrap");
		pane.add(couleurRLabel);
		pane.add(couleurR, "wrap");
		pane.add(couleurGLabel);
		pane.add(couleurG, "wrap");
		pane.add(couleurBLabel);
		pane.add(couleurB, "wrap");
		pane.add(villeLabel);
		pane.add(listeVilles, "wrap");
		pane.add(boutonRetour);
		pane.add(boutonOk);
		
        fenetre.setContentPane(pane);
	}
	
	/**
	 * Méthode qui ajoute la zone dans la base de données
	 */
	public void ajouterZoneBdd(){
		try {
			this.bdd.query("INSERT INTO zone (nom, couleurR, couleurG, couleurB, ville) "
					+ "VALUES ('"+nomZoneField.getText()+"', "
					+ "'"+Integer.parseInt(couleurR.getText())+"', "
					+ "'"+Integer.parseInt(couleurG.getText())+"', "
					+ "'"+Integer.parseInt(couleurB.getText())+"', "
					+ "(SELECT id FROM ville WHERE nom = '"+listeVilles.getSelectedItem().toString()+"'))");
			System.out.println("Zone ajoutée: "+nomZoneField.getText());
			fenetre.dispose();
		} catch (SQLException e2) {
			e2.printStackTrace();
		}
	}
	
	/**
	 * Cette méthode dessine à l'écran la fenêtre formulaire.
	 * 
	 * @param g, Une instance de Graphics
	 * @see Graphics
	 */
	public void paintComponent(Graphics g){
		super.paintComponent(g);
        setBackground(new Color(175, 175, 175));
	}
}
