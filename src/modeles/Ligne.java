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
 * Ligne est la classe representant une ligne, quelque soit le type de celle-ci
 * Attention, une ligne réelle est constituée de deux lignes conceptuelles.
 * 
 * @version 1.0
 */
public class Ligne extends Station{
	private int id;
	private String nom;
	private Color couleur;
	private MoyenTransport transport;
	private Vector<Station> listeStations = new Vector<Station>();
	
	/**
	 * Constructeur par defaut d'une ligne
	 */
	public Ligne(){
		this.id = -1;
		this.nom = "Pas de nom";
		this.couleur = new Color(100, 100, 100);
	}
	
	/**
	 * Constructeur d'une ligne avec parametres
	 * @param _id 		un entier un identifiant unique pour la ligne 
	 * @param _nom 		Une chaine de caracteres, le nom de la ligne
	 * @param _couleur 	Ue couleur (3 entiers), définit la couleur sur la carte de la ligne, 
	 * attention, la ligne retour doit possèder la même couleur
	 * @param transport	Un MoyenTransport, le moyen de transport utilisé sur la ligne (bus, tram etc)
	 * @see Color
	 * @see MoyenTransport
	 */
	public Ligne(int _id, String _nom, Color _couleur, MoyenTransport transport){
		this.id = _id;
		this.nom = _nom;
		this.couleur = _couleur;
		this.transport = transport;
	}
	
	/**
	 * Dessine la ligne a l'affichage
	 * @param g Une instance de Graphics
	 * @see Graphics
	 */
	public void dessinerLigne(Graphics g){
		for(int i = 0;i < listeStations.size();i++){
			if(i < listeStations.size() - 1){
				g.setColor(this.couleur);
				g.drawLine(listeStations.elementAt(i).getX(), listeStations.elementAt(i).getY(), listeStations.elementAt(i + 1).getX(), listeStations.elementAt(i + 1).getY());
			}
			
			g.setColor(new Color(0, 0, 0));
			listeStations.elementAt(i).dessinerStation(g);
		}
	}

	/**
	 * Insert une station dans la ligne
	 * @param station	Une Station que l'on inclut dans la ligne
	 * @param numero	Un entier qui donne l'ordre de la station pour la ligne
	 */
	public void insertStation(Station station, int numero) {
		if (numero < listeStations.size())
			listeStations.insertElementAt(station, numero);
		else
			System.out.println("Erreur d'insertion de station dans la Ligne !");
	}
	
	/**
	 * Insert une station a la fin de la ligne
	 * @param station	Une Station qui devient le nouveau terminus
	 */
	public void ajouterStation(Station station){
		listeStations.add(station);
	}
	
	/**
	 * Donne l'identifiant de la station
	 * @return int l'identifiant de la station
	 */
	public int getId(){
		return this.id;
	}
	
	/**
	 * Donne le nom de la station
	 * @return String le nom de la ligne
	 */
	public String getNom(){
		return this.nom;
	}

	/**
	 * Donne la couleur de la station
	 * @return Color la couleur de la ligne
	 * @see Color
	 */
	public Color getCouleur(){
		return this.couleur;
	}
	
	/**
	 * Donne le moyen de transport de la ligne
	 * @return Un MoyenTransport, le moyen de transport employe par la ligne
	 * @see Moyentransport
	 */
	public MoyenTransport getTransport(){
		return this.transport;
	}
	
	/**
	 * Retourne la station au numero indique
	 * @param numero un entier auquel correspond une station 
	 * @return Station celle au numero envoye en parametre
	 * @see Station 
	 */
	public Station getStation(int numero) {
		if (numero < listeStations.size())
			return listeStations.elementAt(numero);
		else{
			System.out.println("Erreur d'insertion de station dans la Ligne !");
			return listeStations.lastElement();
		}
	}
	
	/**
	 * Retourne l'ordre de la station indiquee dans la ligne
	 * @param station une station dont on cherche le numero
	 * @return int l'ordre de la station envoyee en parametre dans la ligne
	 * @see Station
	 */
	public int getNumeroStation(Station station) {
		return listeStations.indexOf(station);
	}
	
	/**
	 * Retourne la liste des stations dans l'ordre de la ligne
	 * @return Vector<Stations> la liste des stations de la ligne
	 * @see Station
	 */
	public Vector<Station> getListeStations() {
		return listeStations;
	}
	
	/**
	 * Permet de donner un id a la ligne
	 * @param _id
	 */
	public void setId(int _id){
		this.id = _id;
	}
	
	/**
	 * Permet de donner un nom a la ligne
	  * @param _nom
	 */
	public void setNom(String _nom){
		this.nom = _nom;
	}

	/**
	 * Permet de donner une couleur a la ligne
	 * @param _couleur
	 */
	public void setCouleur(Color _couleur){
		this.couleur = _couleur;
	}
	
	/**
	 * Permet de donner un moyen de transport a la ligne
	 * @param transport
	 */
	public void setTransport(MoyenTransport transport){
		this.transport = transport;
	}
	
	/**
	 * Definit la station de depart
	 * @param station
	 */
	public void setStationDepart(Station station) {
		listeStations.add(0, station);
	}

	/**
	 * Definit le terminus
	 * @param station
	 */
	public void setStationArrivee(Station station) {
		listeStations.add(listeStations.size() , station);
	}
	
	/**
	 * Permet de donner une liste de station a la ligne
	 * @param listeStations
	 */
	public void setListeStations(Vector<Station> listeStations) {
		this.listeStations = listeStations;
	}
	
	/**
	 * Affiche l'ID, le nom et les stations de la ligne avec les horaires.
	 */
	public String toString() {
		String str;
		str = "";
		str += "Ligne " + this.getNom();
		str += " ID : " + this.getId();
		str += "\n";

		for (int i = 0; i < listeStations.size(); i++) {
			str += this.getStation(i).toString();
			str += "\t";
		}
		
		str += "\n";

		for (int i = 0; i < this.getStation(0).getListeHoraires().size(); i++) {
			for (int j = 0; i < listeStations.size(); i++) {
				str += this.getStation(j).getHoraire(i) + " \t\t\t ";
			}
			str += "\n";
		}
		str += "\n\n";

		return str;
	}
}
