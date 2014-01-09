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
 
/**
 * Bouton est la classe representant un Bouton sur le panel
 * 
 * @version 1.0
 */
public class Bouton {
	private String nom;
	private int x;
	private int y;
	private int hauteur;
	private int largeur;
	
	/**
	 * Constructeur par defaut d'un bouton
	 */
	public Bouton(){
		this.nom = "bouton";
		this.x = -1;
		this.y = -1;
		this.largeur = -1;
		this.hauteur = -1;
	}
	
	/**
	 * constructeur avec parametre d'un bouton
	 * @param nom Un String, qui correspond au nom attribue au bouton
	 * @param x Un int, qui correspond a la position x du bouton
	 * @param y Un int, qui correspond a la position y du bouton
	 * @param largeur Un int, qui correspond a la taille du bouton en largeur
	 * @param hauteur Un int, qui correspond a la taille du bouton en hauteur
	 */
	public Bouton(String nom, int x, int y, int largeur, int hauteur){
		this.nom = nom;
		this.x = x;
		this.y = y;
		this.largeur = largeur;
		this.hauteur = hauteur;
	}
	
	/**
	 * Dessine le bouton defini
	 * @param g Une instance de Graphics
	 * @see Graphics
	 */
	public void paintComponent(Graphics g){
		g.setColor(new Color(200, 200, 200));
		g.fillRect(this.x, this.y, this.largeur, this.hauteur);

		int h = g.getFontMetrics().getHeight();
		int l = g.getFontMetrics().stringWidth(this.nom);

		g.setColor(new Color(45, 45, 45));
	    g.drawString(this.nom, x - l / 2 + this.largeur / 2, y + h / 4 + this.hauteur / 2);
	}

	/**
	 * Recupère le nom du bouton
	 * @return String qui correspond au nom du bouton
	 */
	public String getNom(){
		return this.nom;
	}
	
	/**
	 * Récupere la coordonnee X du bouton
	 * @return int qui correspond a la coordonnee X du bouton
	 */
	public int getX(){
		return this.x;
	}
	
	/**
	 * Récupère la coordonnee Y du bouton
	 * @return int qui correspond a la coordonnee Y du bouton
	 */
	public int getY(){
		return this.y;
	}
	
	/**
	 * Recupère la largeur du bouton
	 * @return int qui correspond a la taille en largeur du bouton
	 */
	public int getLargeur(){
		return this.largeur;
	}
	
	/**
	 * Recupere la hauteur du bouton
	 * @return int qui correspond a la taille en hauteur du bouton
	 */
	public int getHauteur(){
		return this.hauteur;
	}
	
	/**
	 * definit le nom du bouton
	 * @param nom qui correspond au nom du bouton
	 */
	public void setNom(String nom){
		this.nom = nom;
	}
	
	/**
	 * definit la coordonnee X du bouton
	 * @param x int qui correspond a la coordonnee X du bouton
	 */
	public void setX(int x){
		this.x = x;
	}
	
	/**
	 * definit la coordonnee Y du bouton
	 * @param y int qui correspond a la coordonnee Y du bouton
	 */
	public void setY(int y){
		this.y = y;
	}
	
	/**
	 * definit la largeur du bouton
	 * @param largeur int qui correspond a la largeur du bouton
	 */
	public void setLargeur(int largeur){
		this.largeur = largeur;
	}
	
	/**
	 * definit la hauteur du bouton
	 * @param hauteur int qui correspond a la hauteur du bouton
	 */
	public void setHauteur(int hauteur){
		this.hauteur = hauteur;
	}
}