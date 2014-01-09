
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

/**
 * L'ajout des lignes dans la base de données passe par la vue générée par cette classe
 * 
 * @version 1.0
 */
public class AjoutLignePanel extends JPanel{
	private static final long serialVersionUID = 1L;
	public final static int WIDTH = 260;
	public final static int HEIGHT = 260;
	
	private JFrame fenetre;
	private BaseDeDonnees bdd;
	
	private final JTextField nomLigneField = new JTextField();
	private final JTextField couleurR = new JTextField();
	private final JTextField couleurG = new JTextField();
	private final JTextField couleurB = new JTextField();
	
	private final JComboBox<String> listeMT = new JComboBox<String>();
	
	/**
	 * Constructeur de la fenêtre qui contient le formulaire d'ajout de la ligne. 
	 * Elle prend en paramètre la base de données où seront effectives les modifications.
	 * 
	 * @param _bdd
	 * @see BaseDeDonnees
	 */
	public AjoutLignePanel(BaseDeDonnees _bdd){
		ResultSet reponseMT;
		
		this.bdd = _bdd;
		
		fenetre = new JFrame();
		
		// On définit le titre de la fenêtre
		fenetre.setTitle("Ajouter une ligne");

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
		
		JLabel nomLigneLabel  = new JLabel("Nom ligne");
		JLabel couleurRLabel = new JLabel("Couleur R");
		JLabel couleurGLabel = new JLabel("Couleur G");
		JLabel couleurBLabel = new JLabel("Couleur B");
		JLabel MTLabel = new JLabel("Moyen de Transport");
		
		try {
			reponseMT = this.bdd.select("SELECT nom FROM `moyentransport` ORDER BY nom ASC");
			
			while(reponseMT.next()){
				listeMT.addItem(reponseMT.getString("nom"));
			}
		} catch (SQLException e){
			e.printStackTrace();
		}
		
		JButton boutonOk = new JButton("Ajouter");
		boutonOk.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				ajouterLigneBdd();
			}
		});
		
		
		JButton boutonRetour = new JButton("Retour");
		boutonRetour.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				fenetre.dispose();
			}
		});
		
		pane.add(nomLigneLabel);
		pane.add(nomLigneField, "wrap");
		pane.add(couleurRLabel);
		pane.add(couleurR, "wrap");
		pane.add(couleurGLabel);
		pane.add(couleurG, "wrap");
		pane.add(couleurBLabel);
		pane.add(couleurB, "wrap");
		pane.add(MTLabel);
		pane.add(listeMT, "wrap");
		pane.add(boutonRetour);
		pane.add(boutonOk);
		
        fenetre.setContentPane(pane);
	}
	
	/**
	 * Méthode qui ajoute la ligne dans la base de données
	 */
	public void ajouterLigneBdd(){
		try {
			this.bdd.query("INSERT INTO `ligne` (nom, couleurR, couleurG, couleurB) VALUES ('"+nomLigneField.getText()+"', '"+Integer.parseInt(couleurR.getText())+"', '"+Integer.parseInt(couleurG.getText())+"', '"+Integer.parseInt(couleurB.getText())+"')");
			
		} catch (SQLException e2) {
			e2.printStackTrace();
		}
		
		try {
			this.bdd.query("INSERT INTO `ligne-transport` (idLigne, idTransport) VALUES ( (SELECT id FROM ligne WHERE nom = '"+nomLigneField.getText()+"'), (SELECT id FROM moyentransport WHERE nom = '"+listeMT.getSelectedItem().toString()+"'))");
		} catch (SQLException e2) {
			e2.printStackTrace();
		}
		
		fenetre.dispose();
		System.out.println("Ligne ajoutée: "+nomLigneField.getText());
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
