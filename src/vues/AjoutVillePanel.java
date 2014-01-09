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

public class AjoutVillePanel extends JPanel{
	private static final long serialVersionUID = 1L;
	public final static int WIDTH = 200;
	public final static int HEIGHT = 110;
	
	private JFrame fenetre;
	private BaseDeDonnees bdd;
	
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
	
	public void ajouterVilleBdd(String ville){
		try {
			this.bdd.query("INSERT INTO ville (nom) VALUE ('"+ville+"')");
			System.out.println("Ville ajoutée: "+ville);
			fenetre.dispose();
		} catch (SQLException e2) {
			e2.printStackTrace();
		}
	}
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
        setBackground(new Color(175, 175, 175));
	}
}
