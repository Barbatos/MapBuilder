package modeles;
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
		if(heure >= 24){
			this.setJSemaine(this.getJSemaine() + this.getHeure() / 24);
			this.heure = heure % 24;
		}
		else{
			this.heure = heure;
		}
	}
	
	public void setMinute(int minute){
		if(minute >= 60){
			this.setHeure(this.getHeure() + this.getMinute() / 60);
			this.minute = minute % 60;
		}
		else{
			this.minute = minute;
		}
	}
	
	public void setPeriode(int periode){
		this.periode = periode;
	}
	
	public String toString(){
		String str = "";
		str += this.getHeure(); 
		str += ":" + this.getMinute();
		
		return str;
	}
}
