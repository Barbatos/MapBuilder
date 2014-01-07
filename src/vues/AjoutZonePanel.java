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
	
	public AjoutZonePanel(BaseDeDonnees _bdd){
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
		pane.add(boutonRetour);
		pane.add(boutonOk);
		
        fenetre.setContentPane(pane);
	}
	
	public void ajouterZoneBdd(){
		try {
			this.bdd.query("INSERT INTO zone (nom, couleurR, couleurG, couleurB) VALUES ('"+nomZoneField.getText()+"', '"+Integer.parseInt(couleurR.getText())+"', '"+Integer.parseInt(couleurG.getText())+"', '"+Integer.parseInt(couleurB.getText())+"')");
			System.out.println("Zone ajoutée: "+nomZoneField.getText());
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
