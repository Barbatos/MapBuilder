/*
 * This software is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.

 * This software is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.

 * You should have received a copy of the GNU Lesser General Public
 * License along with this software; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301  USA
 *
 * @author Bardoll
 * @author Charles "Barbatos" Duprey <barbatos@f1m.fr>
 * @author Soullessoni
 * @author Thornydre
 * 
 * @copyright (C) 2013 - 2014 Dat Java DreamTeam
 * 
 */

package modeles;
import java.awt.Color;
import java.awt.Graphics;
import java.util.Vector;

/**
 * Zone est la classe representant une zone géographique
 * 
 * @version 1.0
 */
public class Zone extends Station {
	private int id;
	private String nom;
	private Color couleur;
	private int[] pointsX;
	private int[] pointsY;
	private Vector<Station> listeStations = new Vector<Station>();
	private int ville;
	
	public Zone(){
		this.id = -1;
		this.nom = "Pas de nom";
		this.couleur = new Color(100, 100, 100);
		this.pointsX = new int[0];
		this.pointsY = new int[0];
		this.ville = 0;
	}
	
	public Zone(int _id, String _nom, Color couleur, int ville){
		this.id = _id;
		this.nom = _nom;
		this.couleur = couleur;
		this.pointsX = new int[0];
		this.pointsY = new int[0];
		this.ville = ville;
	}
	
	public void dessinerZone(Graphics g){
		g.setColor(this.couleur);
		g.fillPolygon(this.pointsX, this.pointsY, this.pointsX.length);
	}
	
	public void ajouterStation(Station station) {
		listeStations.add(station);
	}
	
	public void ajouterPointX(int coordX){
		int[] tmp = new int[this.pointsX.length + 1];
		
		for(int i = 0;i < this.pointsX.length;i++){
			tmp[i] = this.pointsX[i];
		}
		
		tmp[this.pointsX.length] = coordX;
		
		pointsX = tmp;
	}
	
	public void ajouterPointY(int coordY){
		int tmp[] = new int[this.pointsY.length + 1];		
		
		for(int i = 0;i < this.pointsY.length;i++){
			tmp[i] = this.pointsY[i];
		}
		
		tmp[this.pointsY.length] = coordY;
		
		pointsY = tmp;
	}
	
	public int getId(){
		return this.id;
	}
	
	public String getNom(){
		return this.nom;
	}
	
	public Color getCouleur(){
		return this.couleur;
	}
	
	public int[] getPointsX(){
		return pointsX;
	}
	
	public int[] getPointsY(){
		return pointsY;
	}
	
	public int getVille(){
		return ville;
	}
	
	public Station getStation(int numero) {
		if (numero < listeStations.size())
			return listeStations.elementAt(numero);
		else 
			System.out.println("Erreur d'insertion de station dans la ligne !");
			return listeStations.lastElement();
	}

	public int getNumeroStation(Station station) {
		return listeStations.indexOf(station);
	}
	
	public Vector<Station> getListeStations(){
		return listeStations;
	}
	
	public void setId(int _id){
		this.id = _id;
	}
	
	public void setNom(String _nom){
		this.nom = _nom;
	}
	
	public void setCouleur(Color couleur){
		this.couleur = couleur;
	}
	
	public void setPointsZone(int[] coordX, int[] coordY){
		this.pointsX = coordX;
		this.pointsY = coordY;
	}
	
	public void setVille(int ville){
		this.ville = ville;
	}
}
