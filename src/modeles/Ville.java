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
	 * @return String qui correspond au nom de cette ville
	 */
	public String getNom(){
		return this.nom;
	}
	
	/**
	 * Definit le nom de cette ville
	 * @param _nom String qui correspond au nom de la ville
	 */
	public void setNom(String _nom){
		this.nom = _nom;
	}
	
}
