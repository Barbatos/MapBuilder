import java.awt.Graphics;

class Segment extends Coordonnees {

	private Coordonnees coordonneesDepart;
	private Coordonnees coordonneesArrivee;
	private int numero;

 	public Segment() {
		coordonneesDepart = new Coordonnees();
		coordonneesArrivee = new Coordonnees();
		numero = -1;
	}

	public Segment(Coordonnees _coordonneesDepart, Coordonnees _coordonneesArrivee, int _numero) {
		coordonneesDepart = _coordonneesDepart;
		coordonneesArrivee = _coordonneesArrivee;
		numero = _numero;
	}
	
	public Segment(Station sta1, Station sta2, int _numero) {
		coordonneesDepart = sta1.getCoordonnees();
		coordonneesArrivee = sta2.getCoordonnees();
		numero = _numero;
	}

	public  Coordonnees getDepart() {
		return this.coordonneesDepart;
	}

	public Coordonnees getArrivee() {
		return this.coordonneesArrivee;
	}

	public int getNumero() {
		return this.numero;
	}

	public void setDepart(Coordonnees _coordonneesDepart) {
		coordonneesDepart = _coordonneesDepart;
	}

	public void setArrivee(Coordonnees _coordonneesArrivee) {
		coordonneesArrivee = _coordonneesArrivee;
	}

	public void setNumero(int _numero) {
		numero = _numero;
	}
	
	public void dessinerSegment(Graphics g){
		g.drawLine(this.getDepart().getX(), this.getDepart().getY(), this.getArrivee().getX(), this.getArrivee().getY());
	}
	
	public String toString() {
		String str;
		str = "";
		str += "Le Segment numero" + this.getNumero();
		str += " demarre en " + this.getDepart();
		str += " et finit en " + this.getArrivee();
		str += " . \n";

		return str;
	}
}
