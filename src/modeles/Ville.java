package modeles;

/**
 * Ville est la classe representant une ville
 * 
 * @version 1.0
 */
public class Ville{
	private String nom;
	
	/**
	 * Constructeur par défaut d'une ville
	 */
	public Ville(){
		this.nom = "Pas de nom";
	}
	
	/**
	 * Constructeur avec parametres d'une Ville
	 * @param _nom un String qui correspond au nom de la ville
	 */
	public Ville(String _nom){
		this.nom = _nom;
	}
	
	/**
	 * Recupere le nom de cette ville
	 * @return Un String, qui correspond au nom de cette ville
	 */
	public String getNom(){
		return this.nom;
	}
	
	/**
	 * Definit le nom de cette ville
	 * @param _nom Un String, qui correspond au nom de la ville
	 */
	public void setNom(String _nom){
		this.nom = _nom;
	}
	
}
