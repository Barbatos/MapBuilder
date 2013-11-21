public class Horaire {
	private int jSemaine;
	private int heure;
	private int minute;
	private int periode;
	
	public Horaire(){
		this.jSemaine = -1;
		this.heure = -1;
		this.minute = -1;
		this.periode = -1;
	}
	
	public Horaire(int jSemaine, int heure, int minute, int periode){
		this.jSemaine = jSemaine;
		this.heure = heure;
		this.minute = minute;
		this.periode = periode;
	}
	
	public int getJSemaine(){
		return this.jSemaine;
	}
	
	public int getHeure(){
		return this.heure;
	}
	
	public int getMinute(){
		return this.minute;
	}
	
	public int getPeriode(){
		return this.periode;
	}
	
	public void setJSemaine(int jSemaine){
		this.jSemaine = jSemaine;
	}
	
	public void setHeure(int heure){
		this.heure = heure;
	}
	
	public void setMinute(int minute){
		this.minute = minute;
	}
	
	public void setPeriode(int periode){
		this.periode = periode;
	}
	
	public String toString(){
		return "Il est " + this.getHeure() + ":" + this.getMinute() + " au " + this.getJSemaine() + "e jour de la semaine dans la periode " + this.getPeriode();
	}
}
