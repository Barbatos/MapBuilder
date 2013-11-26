package modeles;
import java.awt.Color;
import java.awt.Graphics;
import java.util.Vector;

/**
 * Station est la classe representant une station, quelque soit le type de celle-ci
 * 
 * @version 1.0
 */
public class Station extends Coordonnees {
	private Coordonnees coordonnees;
	private int id;
	private String nom;
	private Vector<MoyenTransport> listeTransports = new Vector<MoyenTransport>();
	
	/**
	 * Constructeur par defaut d'une Station
	 */
	public Station(){
		this.coordonnees = new Coordonnees();
		this.id = -1;
		this.nom = "Sans nom";
	}
	
	/**
	 * Constructeur avec parametres d'une Station
	 * @param coordonnees Une instance de Coordonnees, qui correspond aux coordonnees x et y auxquelles se situe cette Station 
	 * @param id Un entier, qui correspond a l'id de cette Station
	 * @param nom Une chaine de caractere, qui correspond au nom cette Station
	 * @see Coordonnees
	 */
	public Station(Coordonnees coordonnees, int id, String nom){
		this.coordonnees = coordonnees;
		this.id = id;
		this.nom = nom;
	}
	
	/**
	 * Recupere les coordonnees de cette Station
	 * @return Une instance de Coordonnees, qui correspond aux coordonnees x et y de cette Station
	 * @see Coordonnees
	 */
	public Coordonnees getCoordonnees(){
		return this.coordonnees;
	}
	
	/**
	 * Recupere l'id de cette Station
	 * @return Un entier, qui correspond a l'id de cette Station
	 */
	public int getId(){
		return this.id;
	}
	
	/**
	 * Recupere le nom de cette Station
	 * @return Une chaine de caracteres,qui correspond au nom de cette Station
	 */
	public String getNom(){
		return this.nom;
	}
	
	/**
	 * Definit les coordonnees de cette Station
	 * @param coordonnees Une instance de Coordonnees, qui correspond aux coordonnees x et y de cette Station
	 * @see Coordonnees
	 */
	public void setCoordonnees(Coordonnees coordonnees){
		this.coordonnees = coordonnees;
	}
	
	/**
	 * Definit l'id de cette Station
	 * @param id Un entier, qui correspond a l'id de cette Station
	 */
	public void setId(int id){
		this.id = id;
	}
	
	/**
	 * Definit le nom de cette Station
	 * @param nom Une chaine de caracteres, qui correspond au nom de cette Station
	 */
	public void setNom(String nom){
		this.nom = nom;
	}
	
	/**
	 * Ajoute un moyen de transport a cette Station
	 * @param transport Une instance de MoyenTransport, qui coerrspond au moyen de transport a ajouter a cette Station
	 * @see MoyenTransport
	 */
	public void insertTransport(MoyenTransport transport){
		listeTransports.add(transport);
	}
	
	/**
	 * Recupere un moyen de transport de cette Station
	 * @param numero Un entier, qui correspond a la position du moyen de transport dans la liste des moyens de transport de cette Station
	 * @return Une instance de MoyenTransport, qui correspond au moyen de transport a la position donnee
	 * @see MoyenTransport
	 */
	public MoyenTransport getTransport(int numero) {
		if (numero < listeTransports.size())
			return listeTransports.elementAt(numero);
		else{
			System.out.println("Erreur d'insertion de transport dans la station !");
			return listeTransports.lastElement();
		}
	}
	
	/**
	 * Dessine cette Station a l'affichage
	 * @param g Une instance de Graphics
	 * @see Graphics
	 */
	public void dessinerStation(Graphics g){
		int taille = 10;
		g.fillOval(this.getCoordonnees().getX() - taille/2, this.getCoordonnees().getY() - taille/2, taille, taille);
		g.drawString(this.getNom(), this.getCoordonnees().getX() + 7, this.getCoordonnees().getY() - 3);
	}
	
	/**
	 * Dessine les informations de cette Station a l'affichage
	 * @param g Une instance de Graphics
	 * @see Graphics
	 */
	public void dessinerInfo(Graphics g){
		g.setColor(new Color(65, 65, 65));
		g.fillRect(1200 - 300, 0, 1200, 200);
		g.setColor(new Color(175, 175, 225));
		g.drawString("Station : " + this.getNom(), 1200 - 290, 20);
		g.drawString("Numéro : " + this.getId(), 1200 - 290, 40);
		g.drawString("Coordonnées : X :" + this.getCoordonnees().getX() + " Y : " + this.getCoordonnees().getY(), 1200 - 290, 60);
		g.drawString("Moyen(s) de transport : ", 1200 - 290, 80);
		for(int i = 0;i < listeTransports.size();i++){
			g.drawString("- " + this.getTransport(i).getNom(), 1200 - 160, 80 + i * 20);
		}
	}
	
	public String toString(){
		String str;
		str = "";
		str += "Vous etes a la station " + this.getNom();
		str += " numero " + this.getId();
		str += "dont les coordonnees sont : " + this.getCoordonnees();
		str += ". \n";
		
		return str;
	}
}
