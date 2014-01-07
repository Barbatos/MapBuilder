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
	 * @param jSemaine, Un entier qui definit le jour de la semaine (compris entre 1 et 7)
	 * @param heure, Un entier qui definit l'heure de passage (compris entre 0 et 23)
	 * @param minute, Un entier qui definit la minute de passage (compris entre 0 et 59)
	 * @param periode, Un entier qui sert d'identifiant de la période
	 */
	public Horaire(int jSemaine, int heure, int minute, int periode){
		this.jSemaine = jSemaine;
		this.heure = heure;
		this.minute = minute;
		this.periode = periode;
	}
	
	/**
	 * Dessine la fenetre avec les horaires
	 * @param g, Une instance de Graphics
	 * @param x, Un entier qui correspond a l'abscisse d'origine de la fenetre
	 * @param y, Un entier qui correspond a l'ordonnee d'origine de la fenetre
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
	 * @return Un entier qui correspond au jour de la semaine
	 */
	public int getJSemaine(){
		return this.jSemaine;
	}
	
	/**
	 * Recupere l'heure
	 * @return Un entier qui correspond a l'heure
	 */
	public int getHeure(){
		return this.heure;
	}
	
	/**
	 * Recupere la minute
	 * @return Un entier qui correspond a la minute 
	 */
	public int getMinute(){
		return this.minute;
	}
	
	/**
	 * Recupere la periode
	 * @return Un entier qui correspond a la periode
	 */
	public int getPeriode(){
		return this.periode;
	}
	
	/**
	 * Definit le jour de la semaine
	 * @param jSemaine, Un entier compris entre 1 et 7
	 */
	public void setJSemaine(int jSemaine){
		if(jSemaine < 8 && jSemaine > 0)
			this.jSemaine = jSemaine;
	}
	
	/**
	 * Definit l'heure
	 * @param heure, Un entier compris entre 0 et 23
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
	 * @param minute, Un entier compris entre 0 et 59
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
	 * @param periode, Un entier
	 */
	public void setPeriode(int periode){
		this.periode = periode;
	}
	
	/**
	 * Affiche l'heure, et la minute de l'horaire
	 */
	public String toString(){
		String str = "";
		str += this.getHeure(); 
		str += ":" + this.getMinute();
		
		return str;
	}
}
