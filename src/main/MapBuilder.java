package main;

import javax.swing.SwingUtilities;
import controleurs.Controleur;

public class MapBuilder {
	public static void main(String args[]){
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run(){
				Controleur controleur = new Controleur();
				controleur.initialiser();
			}
		});
	}
}
