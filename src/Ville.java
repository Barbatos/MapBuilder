import java.util.Vector;

public class Ville extends Segment{
	private String nom;
	private Vector<Segment> listeSegments = new Vector<Segment>();
	
	public Ville(){
		this.nom = "Pas de nom";
	}
	
	public Ville(String _nom){
		this.nom = _nom;
	}
	
	public String getNom(){
		return this.nom;
	}
	
	public void setNom(String _nom){
		this.nom = _nom;
	}
	
	public void insertSegment(Segment segment, int numero) {
		if (numero < listeSegments.size())
			listeSegments.insertElementAt(segment, numero);
		else
			System.out.println("Erreur d'insertion de station dans la Ligne !");
	}

	public Segment getSegment(int numero) {
		if (numero < listeSegments.size())
			return listeSegments.elementAt(numero);
		else 
			System.out.println("Erreur d'insertion de station dans la Ligne !");
			return listeSegments.lastElement();
	}

	public int getNumeroSegment(Segment segment) {
		return listeSegments.indexOf(segment);
	}
}
