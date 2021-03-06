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

package modeles;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Vector;

import vues.CartePanel;

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
	private Vector<Bouton> listeBoutonsHorairesLignes = new Vector<Bouton>();
	private Zone zone;
	private int ville;
	
	/**
	 * Constructeur par defaut d'une Station
	 */
	public Station(){
		this.id = -1;
		this.coordX = -1;
		this.coordY = -1;
		this.nom = "Sans nom";
		this.ville = 0;
	}
	
	/**
	 * Constructeur avec parametres d'une Station
	 * @param coordonnees Une instance de Coordonnees, qui correspond aux coordonnees x et y auxquelles se situe cette Station 
	 * @param id Un entier, qui correspond a l'id de cette Station
	 * @param nom Une chaine de caractere, qui correspond au nom cette Station
	 * @see Coordonnees
	 */
	public Station(int id, int coordX, int coordY, String nom, int ville){
		this.id = id;
		this.coordX = coordX;
		this.coordY = coordY;
		this.nom = nom;
		this.ville = ville;
	}
	
	/**
	 * Dessine cette Station a l'affichage
	 * @param g Une instance de Graphics
	 * @see Graphics
	 */
	public void dessinerStation(Graphics g){
		int taille = 10;
		g.fillOval(this.getX() - taille/2, this.getY() - taille/2, taille, taille);
	}
	
	/**
	 * Dessine les informations de cette Station a l'affichage
	 * @param g Une instance de Graphics
	 * @see Graphics
	 */
	public void dessinerInfo(Graphics g){
		g.setColor(new Color(175, 175, 225));
		g.drawString("Station : " + this.getNom(), CartePanel.WIDTH - 290, 60);
		
		g.drawString("Zone : " + this.getZone().getNom(), CartePanel.WIDTH - 290, 80);
		
		g.drawString("Ligne(s) : ", CartePanel.WIDTH - 290, 100);
		
		for(int i = 0;i < listeLignes.size();i++){
			g.drawString("- ligne " + this.getLigne(i).getNom() + " - " + this.getLigne(i).getTransport().getNom(), CartePanel.WIDTH - 230, 100 + i * 20);
		}
		
		for(int i = 0;i < listeLignes.size();i++){
			listeBoutonsHorairesLignes.add(new Bouton("Horaires de la ligne " + this.getLigne(i).getNom(), CartePanel.WIDTH - 270, 180 + 40 * i, 240, 30));
		}
		
		for(int i = 0;i < listeBoutonsHorairesLignes.size();i++){
			this.getBoutonHoraire(i).paintComponent(g);
		}
	}
	
	public void dessinerNom(Graphics g){
		g.setColor(new Color(0, 0, 0));
		g.drawString(this.getNom(), this.getX() + 7, this.getY() - 3);
	}
	
	/**
	 * Insert un horaire dansla liste des horaires
	 * @param horaire Un horaire, que l'on insert dans la liste
	 */
	public void ajouterHoraire(Horaire horaire){
		listeHoraires.add(horaire);
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
	
	public void ajouterLigne(Ligne ligne){
		listeLignes.add(ligne);
	}
	
	public void insertBoutonHoraire(Bouton bouton){
		listeBoutonsHorairesLignes.add(bouton);
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
	 * Recupere la zone de cette Station
	 * @return Une chaine de caracteres,qui correspond à la zone de cette Station
	 */
	public Zone getZone(){
		return this.zone;
	}
	
	/**
	 * Récupère l'horaire à un indice
	 * @param numero Un int, qui correspond au numéro de l'horaire dans la liste de horaires
	 * @return Horaire, qui correspond à l'horaire dans la liste au numéro indiqué.
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
	 * Recupere la liste des horaires de cette Station
	 * @return Une liste,qui correspond aux horaires de cette Station
	 */
	public Vector<Horaire> getListeHoraires() {
		return listeHoraires;
	}
	
	/**
	 * Recupere le bouton d'affichage des horaires
	 * @param numero Un int qui correspond au numéro du bouton sélectionné
	 * @return Une liste avec les boutons correspondant au bouton sélectionné
	 */
	public Bouton getBoutonHoraire(int numero){
		if(numero < listeBoutonsHorairesLignes.size())
			return listeBoutonsHorairesLignes.elementAt(numero);
		else{
			System.out.println("Erreur d'insertion de Ligne dans la Station !");
			return listeBoutonsHorairesLignes.lastElement();
		}
	}
	
	/**
	 * Recupere la position du bouton d'horaire
	 * @return Une liste avec l'emplacement du bouton sélectionné
	 */
	public Vector<Bouton> getListeBoutonsHoraire(){
		return listeBoutonsHorairesLignes;
	}
	
	public Ligne getLigne(int numero){
		if(numero < listeLignes.size())
			return listeLignes.elementAt(numero);
		else{
			System.out.println("Erreur de recuperation de Ligne dans la Station !");
			return listeLignes.lastElement();
		}
	}
	
	public int getVille(){
		return ville;
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
	 * Definit la zone de cette Station
	 * @param zone Une zone, qui correspond au à la zone de cette Station
	 * @see Zone
	 */
	public void setZone(Zone zone){
		this.zone = zone;
	}
	
	/**
	 * Definit les horaires de cette Station
	 * @param listeHoraires Une liste, qui correspond aux horaires de cette Station
	 * @see Horaire
	 */
	public void setlisteHoraires(Vector<Horaire> listeHoraires) {
		this.listeHoraires = listeHoraires;
	}
	
	public void setVille(int ville){
		this.ville = ville;
	}
	
	/**
	 * Affiche le nom, l'id et la position de la station
	 */
	public String toString(){
		String str;
		str = "Station : ";
		str += " nom - " + this.getNom();
		str += " / num - " + this.getId();
		str += " / X - " + this.getX();
		str += " / Y - " + this.getY();
		
		return str;
	}
}
