package main;

import javax.swing.SwingUtilities;

import vues.Carte;
import controleurs.Controleur;

public class MapBuilder {
	public static void main(String args[]){
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run(){
				Carte carte = new Carte();
				Controleur controleur = new Controleur(carte);
				controleur.initialiser();
			}
		});
	}
}
