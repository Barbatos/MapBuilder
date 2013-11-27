package modeles;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Vector;
import vues.Vue;

/**
 * Station est la classe representant une station, quelque soit le type de celle-ci
 * 
 * @version 1.0
 */
public class Station extends Coordonnees {
	private Coordonnees coordonnees;
	private int id;
	private String nom;
	private Vector<Horaire> listeHoraires = new Vector<Horaire>();
	private Vector<Ligne> listeLignes = new Vector<Ligne>();
	private Vector<Bouton> listeBoutons = new Vector<Bouton>();
	
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
	
	public void insertHoraire(Horaire horaire){
		listeHoraires.add(horaire);
	}
	
	public Horaire getHoraire(int numero) {
		if (numero < listeHoraires.size())
			return listeHoraires.elementAt(numero);
		else{
			System.out.println("Erreur d'insertion d'Horaire dans la Station !");
			return listeHoraires.lastElement();
		}
	}
	
	public void insertLigne(Ligne ligne){
		listeLignes.add(ligne);
	}
	
	public Ligne getLigne(int numero){
		if(numero < listeLignes.size())
			return listeLignes.elementAt(numero);
		else{
			System.out.println("Erreur d'insertion de Ligne dans la Station !");
			return listeLignes.lastElement();
		}
	}
	
	public void insertBouton(Bouton bouton){
		listeBoutons.add(bouton);
	}
	
	public Bouton getBouton(int numero){
		if(numero < listeBoutons.size())
			return listeBoutons.elementAt(numero);
		else{
			System.out.println("Erreur d'insertion de Ligne dans la Station !");
			return listeBoutons.lastElement();
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
		g.setColor(new Color(175, 175, 225));
		g.drawString("Station : " + this.getNom(), Vue.WIDTH - 290, 60);
		g.drawString("Numéro : " + this.getId(), Vue.WIDTH - 290, 80);
		g.drawString("Ligne(s) : ", Vue.WIDTH - 290, 100);
		
		for(int i = 0;i < listeLignes.size();i++){
			g.drawString("- ligne " + this.getLigne(i).getNom() + " - " + this.getLigne(i).getTransport().getNom(), Vue.WIDTH - 230, 100 + i * 20);
		}
		
		listeBoutons.add(new Bouton("Horaires de la station " + this.getNom(), Vue.WIDTH - 270, 180, 240, 30));
		
		for(int i = 0;i < listeLignes.size();i++){
			listeBoutons.add(new Bouton("Horaires de la ligne " + this.getLigne(i).getNom(), Vue.WIDTH - 270, 220 + 40 * i, 240, 30));
		}
		
		for(int i = 0;i < listeBoutons.size();i++){
			this.getBouton(i).paintComponent(g);
		}
	}
	
	public Vector<Horaire> getlisteHoraires() {
		return listeHoraires;
	}

	public void setlisteHoraires(Vector<Horaire> listeHoraires) {
		this.listeHoraires = listeHoraires;
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
