package modeles;

import java.awt.Color;
import java.awt.Graphics;
 
public class BoutonHoraire{
	private String nom;
	private int x;
	private int y;
	private int hauteur;
	private int largeur;
	
	public BoutonHoraire(String nom, int x, int y, int largeur, int hauteur){
		this.nom = nom;
		this.x = x;
		this.y = y;
		this.largeur = largeur;
		this. hauteur = hauteur;
	}
	
	public void paintComponent(Graphics g){
		g.setColor(new Color(200, 200, 200));
		g.fillRect(this.x, this.y, this.largeur, this.hauteur);

		int h = g.getFontMetrics().getHeight();
		int l = g.getFontMetrics().stringWidth(this.nom);

		g.setColor(new Color(45, 45, 45));
	    g.drawString(this.nom, x - l / 2 + this.largeur / 2, y + h / 4 + this.hauteur / 2);
	}

	public String getNom(){
		return this.nom;
	}
	
	public int getX(){
		return this.x;
	}
	
	public int getY(){
		return this.y;
	}
	
	public int getLargeur(){
		return this.largeur;
	}
	
	public int getHauteur(){
		return this.hauteur;
	}
	
	public void setNom(String nom){
		this.nom = nom;
	}
	
	public void setX(int x){
		this.x = x;
	}
	
	public void setY(int y){
		this.y = y;
	}
	
	public void setLargeur(int largeur){
		this.largeur = largeur;
	}
	
	public void setHauteur(int hauteur){
		this.hauteur = hauteur;
	}
}