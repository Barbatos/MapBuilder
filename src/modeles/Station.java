package modeles;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Vector;

import vues.Carte;

/**
 * Station est la classe representant une station, quelque soit le type de celle-ci
 * 
 * @version 1.0
 */
public class Station{
	private int id;
	private int coordX;
	private int coordY;
	private String nom;
	private Vector<Horaire> listeHoraires = new Vector<Horaire>();
	private Vector<Ligne> listeLignes = new Vector<Ligne>();
	private Vector<BoutonHoraire> listeBoutonsHoraire = new Vector<BoutonHoraire>();
	
	/**
	 * Constructeur par defaut d'une Station
	 */
	public Station(){
		this.id = -1;
		this.coordX = -1;
		this.coordY = -1;
		this.nom = "Sans nom";
	}
	
	/**
	 * Constructeur avec parametres d'une Station
	 * @param coordonnees Une instance de Coordonnees, qui correspond aux coordonnees x et y auxquelles se situe cette Station 
	 * @param id Un entier, qui correspond a l'id de cette Station
	 * @param nom Une chaine de caractere, qui correspond au nom cette Station
	 * @see Coordonnees
	 */
	public Station(int id, int coordX, int coordY, String nom){
		this.id = id;
		this.coordX = coordX;
		this.coordY = coordY;
		this.nom = nom;
	}
	
	/**
	 * Recupere les coordonnees de cette Station
	 * @return Un entier, qui correspond a la coordonnee x de cette Station
	 */
	public int getX(){
		return this.coordX;
	}
	
	/**
	 * Recupere les coordonnees de cette Station
	 * @return Un entier, qui correspond a la coordonnee y de cette Station
	 */
	public int getY(){
		return this.coordY;
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
	 * @param coordX Un entier, qui correspond a la coordonnee x de cette Station
	 */
	public void setX(int coordX){
		this.coordX = coordX;
	}
	
	/**
	 * Definit les coordonnees de cette Station
	 * @param coordY Un entier, qui correspond a la coordonnee y de cette Station
	 */
	public void setY(int coordY){
		this.coordY = coordY;
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
	 * Insert un horaire dansla liste des horaires
	 * @param horaire Un horaire, que l'on insert dans la liste
	 */
	public void insertHoraire(Horaire horaire){
		listeHoraires.add(horaire);
	}
	
	/**
	 * R�cup�re l'horaire � un indice
	 * @param numero Un int, qui correspond au num�ro de l'horaire dans la liste de horaires
	 * @return Horaire, qui correspond � l'horaire dans la liste au num�ro indiqu�.
	 */
	public Horaire getHoraire(int numero) {
		if (numero < listeHoraires.size())
			return listeHoraires.elementAt(numero);
		else{
			System.out.println("Erreur d'insertion d'Horaire dans la Station !");
			return listeHoraires.lastElement();
		}
	}

	/**
	 * Remet les horaires par ordre chronologique dans la liste
	 */
	public void trierHoraireChronologique() {
		Horaire horaireTemp = new Horaire();

		for (int i = 0; i < listeHoraires.size() - 1; i++) {
			for (int j = i + 1; j < listeHoraires.size(); j++) {
				if(
					( getHoraire(i).getHeure() > getHoraire(j).getHeure() ) ||
					( getHoraire(i).getHeure() == getHoraire(j).getHeure() &&
						getHoraire(i).getMinute() > getHoraire(j).getMinute() )
				) {
						horaireTemp = getHoraire(i);
						listeHoraires.set(i, getHoraire(j));
						listeHoraires.set(j, horaireTemp);
				}
			}
		}
	}
	
	public void insertLigne(Ligne ligne){
		listeLignes.add(ligne);
	}
	
	public Ligne getLigne(int numero){
		if(numero < listeLignes.size())
			return listeLignes.elementAt(numero);
		else{
			System.out.println("Erreur de recuperation de Ligne dans la Station !");
			return listeLignes.lastElement();
		}
	}
	
	public void insertBoutonHoraire(BoutonHoraire bouton){
		listeBoutonsHoraire.add(bouton);
	}
	
	public BoutonHoraire getBoutonHoraire(int numero){
		if(numero < listeBoutonsHoraire.size())
			return listeBoutonsHoraire.elementAt(numero);
		else{
			System.out.println("Erreur d'insertion de Ligne dans la Station !");
			return listeBoutonsHoraire.lastElement();
		}
	}
	
	public Vector<BoutonHoraire> getListeBoutonsHoraire(){
		return listeBoutonsHoraire;
	}
	
	/**
	 * Dessine cette Station a l'affichage
	 * @param g Une instance de Graphics
	 * @see Graphics
	 */
	public void dessinerStation(Graphics g){
		int taille = 10;
		g.fillOval(this.getX() - taille/2, this.getY() - taille/2, taille, taille);
		g.drawString(this.getNom(), this.getX() + 7, this.getY() - 3);
	}
	
	/**
	 * Dessine les informations de cette Station a l'affichage
	 * @param g Une instance de Graphics
	 * @see Graphics
	 */
	public void dessinerInfo(Graphics g){
		g.setColor(new Color(175, 175, 225));
		g.drawString("Station : " + this.getNom(), Carte.WIDTH - 290, 60);
		g.drawString("Numero : " + this.getId(), Carte.WIDTH - 290, 80);
		g.drawString("Ligne(s) : ", Carte.WIDTH - 290, 100);
		
		for(int i = 0;i < listeLignes.size();i++){
			g.drawString("- ligne " + this.getLigne(i).getNom() + " - " + this.getLigne(i).getTransport().getNom(), Carte.WIDTH - 230, 100 + i * 20);
		}
		
		listeBoutonsHoraire.add(new BoutonHoraire("Horaires de la station " + this.getNom(), Carte.WIDTH - 270, 180, 240, 30));
		//listeBoutonsHoraire.elementAt(0).initialiser();
		
		for(int i = 0;i < listeLignes.size();i++){
			listeBoutonsHoraire.add(new BoutonHoraire("Horaires de la ligne " + this.getLigne(i).getNom(), Carte.WIDTH - 270, 220 + 40 * i, 240, 30));
		}
		
		for(int i = 0;i < listeBoutonsHoraire.size();i++){
			this.getBoutonHoraire(i).paintComponent(g);
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
		str += " num " + this.getId();
		str += " : X :" + this.getX();
		str += " Y : " + this.getY();
		
		return str;
	}
}
