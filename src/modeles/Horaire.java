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
 * Horaire est une classe qui donne les donnees sur les horaires de la ligne a une station
 * Elle indique le jour dans la semaine, l'heure et la minute de passage ainsi que la periode
 * @version 1.0
 */
public class Horaire {
	private int jSemaine;
	private int heure;
	private int minute;
	private int periode;
	
	/**
	 * Constructeur par defaut
	 */
	public Horaire(){
		this.jSemaine = -1;
		this.heure = -1;
		this.minute = -1;
		this.periode = -1;
	}
	
	/**
	 * Constructeur prenant en paramtre des entiers indiquants le jour de la semaine, l'heure, la minute et la periode
	 * @param jSemaine int qui definit le jour de la semaine (compris entre 1 et 7)
	 * @param heure int qui definit l'heure de passage (compris entre 0 et 23)
	 * @param minute int qui definit la minute de passage (compris entre 0 et 59)
	 * @param periode int qui sert d'identifiant de la période
	 */
	public Horaire(int jSemaine, int heure, int minute, int periode){
		this.jSemaine = jSemaine;
		this.heure = heure;
		this.minute = minute;
		this.periode = periode;
	}
	
	/**
	 * Dessine la fenetre avec les horaires
	 * @param g Une instance de Graphics
	 * @param x int qui correspond a l'abscisse d'origine de la fenetre
	 * @param y int qui correspond a l'ordonnee d'origine de la fenetre
	 * @see Graphics 
	 */
	public void dessinerHoraire(Graphics g, int x, int y){
		int hauteur = 30;
		int largeur = 90;
		String str = "";
		
		g.setColor(new Color(10, 10, 10));
		g.drawRect(x, y, largeur, hauteur);
		
		if(this.heure < 10){
			str += "0" + this.heure;
		}
		else{
			str += this.heure;
		}
		
		str += ":";
		
		if(this.minute < 10){
			str += "0" + this.minute;
		}
		else{
			str += this.minute;
		}
		int h = g.getFontMetrics().getHeight();
		int l = g.getFontMetrics().stringWidth(str);
		
		g.drawString(str, x - l / 2 + largeur / 2, y + h / 4 + hauteur / 2);
	}
	
	/**
	 * Recupere le jour de la semaine
	 * @return int qui correspond au jour de la semaine
	 */
	public int getJSemaine(){
		return this.jSemaine;
	}
	
	/**
	 * Recupere l'heure
	 * @return int qui correspond a l'heure
	 */
	public int getHeure(){
		return this.heure;
	}
	
	/**
	 * Recupere la minute
	 * @return int qui correspond a la minute 
	 */
	public int getMinute(){
		return this.minute;
	}
	
	/**
	 * Recupere la periode
	 * @return int qui correspond a la periode
	 */
	public int getPeriode(){
		return this.periode;
	}
	
	/**
	 * Definit le jour de la semaine
	 * @param jSemaine int compris entre 1 et 7
	 */
	public void setJSemaine(int jSemaine){
		if(jSemaine < 8 && jSemaine > 0)
			this.jSemaine = jSemaine;
	}
	
	/**
	 * Definit l'heure
	 * @param heure int compris entre 0 et 23
	 */
	public void setHeure(int heure){
		if(heure >= 24){
			this.setJSemaine(this.getJSemaine() + this.getHeure() / 24);
			this.heure = heure % 24;
		}
		else{
			this.heure = heure;
		}
	}
	
	/**
	 * Definit la minute
	 * @param minute int compris entre 0 et 59
	 */
	public void setMinute(int minute){
		if(minute >= 60){
			this.setHeure(this.getHeure() + this.getMinute() / 60);
			this.minute = minute % 60;
		}
		else{
			this.minute = minute;
		}
	}
	
	/**
	 * Definit la periode
	 * @param periode int
	 */
	public void setPeriode(int periode){
		this.periode = periode;
	}
	
	/**
	 * Affiche l'heure et la minute de l'horaire
	 */
	public String toString(){
		String str = "";
		str += this.getHeure(); 
		str += ":" + this.getMinute();
		
		return str;
	}
}
