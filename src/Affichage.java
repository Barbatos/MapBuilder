import javax.swing.JFrame;

public class Affichage extends JFrame {
	private static final long serialVersionUID = 1L;

	public Affichage(){
		super();
		
		CreerFenetre();
	}
	
	private void CreerFenetre(){
		
		// On définit le titre de la fenêtre
		setTitle("MapBuilder v0.0.0.0.1");
		
		// Taille par défaut: 800x600
		setSize(800, 600);
		
		// On centre la fenêtre
		setLocationRelativeTo(null);
		
		// On empêche le redimensionnement de la fenêtre
		setResizable(false);
		
		// Opération par défaut quand on quitte
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public static void main(String args[]){
		Affichage a = new Affichage();
		a.setVisible(true);
	}
}
