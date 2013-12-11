package main;

import javax.swing.SwingUtilities;

import vues.CartePanel;
import controleurs.Controleur;

public class MapBuilder {
	public static void main(String args[]){
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run(){
				CartePanel carte = new CartePanel();
				Controleur controleur = new Controleur(carte);
				controleur.initialiser();
			}
		});
	}
}
