package modeles;

import java.awt.Graphics;
import java.util.Vector;

public class Ville{
	private String nom;
	private int x[];
	private int y[];
	
	public Ville(){
		this.nom = "Pas de nom";
	}
	
	public Ville(String _nom, int x[], int y[]){
		this.nom = _nom;
		this.x = x;
		this.y = y;
	}
	
	public String getNom(){
		return this.nom;
	}
	
	public void setNom(String _nom){
		this.nom = _nom;
	}
	
	public void dessinerVille(Graphics g) {
		//il faut faire un polygone à partir des coordonnées de la ville.
		//la série de points est à encadrer selon l'algorithme de graham ou de jarvis
	}
}
