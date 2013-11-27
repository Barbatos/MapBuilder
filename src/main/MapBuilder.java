package main;

import javax.swing.SwingUtilities;

import vues.Vue;
import controleurs.Controleur;

public class MapBuilder {
	public static void main(String args[]){
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run(){
				Vue vue = new Vue();
				Controleur controleur = new Controleur(vue);
				controleur.initialiser();
			}
		});
	}
}
