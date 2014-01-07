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
import modeles.Ligne;
import net.miginfocom.swing.MigLayout;

public class AjoutStationPanel extends JPanel{
	private static final long serialVersionUID = 1L;
	public final static int WIDTH = 380;
	public final static int HEIGHT = 260;
	
	private JFrame fenetre;
	private BaseDeDonnees bdd;
	
	private final JTextField nomStationField = new JTextField();
	private final JTextField coordXField = new JTextField();
	private final JTextField coordYField = new JTextField();
	private final JComboBox<String> listeLignes = new JComboBox<String>();
	private final JComboBox<String> listeZones = new JComboBox<String>();
	
	public AjoutStationPanel(BaseDeDonnees _bdd){
		ResultSet reponseLigne;
		ResultSet reponseZone;
		
		this.bdd = _bdd;
		
		fenetre = new JFrame();
		
		// On définit le titre de la fenêtre
		fenetre.setTitle("Ajouter une station");

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
		
		JLabel nomStationLabel = new JLabel("Nom station");
		JLabel coordXLabel = new JLabel("Coordonnée X");
		JLabel coordYLabel = new JLabel("Coordonnée Y");
		JLabel ligneLabel = new JLabel("Ligne");
		JLabel zoneLabel = new JLabel("Zone");
		
		try {
			reponseLigne = this.bdd.select("SELECT nom FROM `ligne` ORDER BY nom ASC");
			
			while(reponseLigne.next()){
				listeLignes.addItem(reponseLigne.getString("nom"));
			}
		} catch (SQLException e){
			e.printStackTrace();
		}
		
		try {
			reponseZone = this.bdd.select("SELECT nom FROM `zone` ORDER BY nom ASC");
			
			while(reponseZone.next()){
				listeZones.addItem(reponseZone.getString("nom"));
			}
		} catch (SQLException e){
			e.printStackTrace();
		}
		
		JButton boutonOk = new JButton("Ajouter");
		boutonOk.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				ajouterStationBdd();
			}
		});
		
		
		JButton boutonRetour = new JButton("Retour");
		boutonRetour.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				fenetre.dispose();
			}
		});
		
		pane.add(nomStationLabel);
		pane.add(nomStationField, "wrap");
		pane.add(coordXLabel);
		pane.add(coordXField, "wrap");
		pane.add(coordYLabel);
		pane.add(coordYField, "wrap");
		pane.add(ligneLabel);
		pane.add(listeLignes, "wrap");
		pane.add(zoneLabel);
		pane.add(listeZones, "wrap");
		pane.add(boutonRetour);
		pane.add(boutonOk);
		
        fenetre.setContentPane(pane);
	}
	
	public void ajouterStationBdd(){
		try {
			this.bdd.query("INSERT INTO station (nom, coordX, coordY) VALUES ('"+nomStationField.getText()+"', '"+Integer.parseInt(coordXField.getText())+"', '"+Integer.parseInt(coordYField.getText())+"')");
			//this.bdd.query("INSERT INTO station-zone ")
			System.out.println("Station ajoutée: "+nomStationField.getText());
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