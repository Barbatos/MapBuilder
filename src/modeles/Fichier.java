package modeles;
import java.awt.List;
import java.io.*;

public class Fichier {
	private String nom;
	private List liste;
	
	public final static String EXTENSION_FICHIER = ".save";
	public final static String REPERTOIRE_FICHIERS = "fichiers/";
	
	public Fichier(String _nom, List _liste){
		this.nom = _nom;
		this.liste = _liste;
	}
	
	public String getNom(){
		return this.nom;
	}
	
	public List getListe(){
		return this.liste;
	}
	
	public void setNom(String _nom){
		this.nom = _nom;
	}
	
	public void setListe(List _liste){
		this.liste = _liste;
	}
	
	public boolean enregistrerFichier(){
		String cheminFichier;
		
		cheminFichier = Fichier.REPERTOIRE_FICHIERS + this.nom + Fichier.EXTENSION_FICHIER;
		
		try {
			
			// Création du fichier
			FileOutputStream fichier = new FileOutputStream(cheminFichier);
			ObjectOutputStream infos = new ObjectOutputStream(fichier);
			
			// On y écrit notre objet
			infos.writeObject(this.liste);
			infos.flush();
			infos.close();
			fichier.close();
			
			System.out.println("Enregistrement du fichier " + cheminFichier + " réussi.");
			
			return true;
		}
		
		catch(IOException e){
			System.out.println("Impossible d'enregistrer le fichier " + cheminFichier + " !");
			e.printStackTrace();
			return false;
		}
	}
	
	public List chargerFichier(){
		String cheminFichier;
		
		cheminFichier = Fichier.REPERTOIRE_FICHIERS + this.nom + Fichier.EXTENSION_FICHIER;
		
		this.liste = new List();
		
		try {
			
			// On ouvre le fichier
			File f = new File(cheminFichier);
			
			if(!f.exists()){
				System.out.println("Le fichier " + cheminFichier + " n'existe pas !");
				return null;
			}
			
			FileInputStream fichier = new FileInputStream(f);
			ObjectInputStream infos = new ObjectInputStream(fichier);
			
			// On récupère son contenu et on le place dans une liste
			this.liste = (List) infos.readObject();
			
			// On ferme le fichier
			infos.close();
			fichier.close();
			
			return this.liste;
		}
		
		
		catch(IOException e){
			System.out.println("Impossible de charger le fichier " + cheminFichier);
			e.printStackTrace();
			return null;
		}
		
		catch(ClassNotFoundException e){
			System.out.println("Impossible de charger le fichier " + cheminFichier + "... classe introuvable !");
			e.printStackTrace();
			return null;
		}
	}
}
