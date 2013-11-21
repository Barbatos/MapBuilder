import javax.swing.JFrame;

public class Fenetre extends JFrame {
	private static final long serialVersionUID = 1L;

	public Fenetre(){                
		super();
		CreerFenetre();
	}     

	private void CreerFenetre(){

		// On d�finit le titre de la fen�tre
		setTitle("MapBuilder v0.0.0.0.1");

		// Taille par d�faut: 800x600
		setSize(800, 600);

		// On centre la fen�tre
		setLocationRelativeTo(null);

		// On emp�che le redimensionnement de la fen�tre
		setResizable(false);

		// Op�ration par d�faut quand on quitte
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// Import de la classe Affichage
		this.setContentPane(new Affichage());
	}

	public static void main(String args[]){
		Fenetre fenetre = new Fenetre();
		fenetre.setVisible(true);
	}
}