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

import java.sql.*;

/**
 * L'acces et l'utilisation de la base de donnees sont modélise par cette classe
 * 
 * @version 1.0
 */
public class BaseDeDonnees {
	private Connection connexion = null;
	private Statement statement = null;
	private String utilisateur = "";
	private String motDePasse = "";
	private String host = "";
	
	/**
	 * Constructeur parametre
	 * 
	 * @param _host, Une chaine de caractere qui correspond a l'hote
	 * @param _utilisateur, Une chaine de caractere qui correspond a l'utilisateur
	 * @param _motDePasse, Une chaine de caractere qui correspond au mot de passe
	 * @throws SQLException
	 */
	public BaseDeDonnees(String _host, String _utilisateur, String _motDePasse){
		this.utilisateur = _utilisateur;
		this.motDePasse = _motDePasse;
		this.host = _host;
		
		// Chargement du driver
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
		} 
		catch (Exception e) {
			e.printStackTrace();
			System.exit(2);
		}
		
		// Connexion à la base de données
		try {
			this.connexion = DriverManager.getConnection(this.host, this.utilisateur, this.motDePasse);
			this.statement = this.connexion.createStatement();
		} 
		catch (SQLException e){
			System.out.println("Connexion impossible à MySQL !");
			e.printStackTrace();
			System.exit(2);
		}

	}
	
	/**
	 * Methode qui fait un select sur la base
	 * 
	 * @param args, Une chaine de caractere qui correspond a la requete
	 * @return ResultSet, le resultat de la requete
	 * @throws SQLException, Une exception mysql
	 * @see ResultSet
	 */
	public ResultSet select(String args) throws SQLException {
		ResultSet resultat = this.statement.executeQuery(args);
		return resultat;
	}
	
	/**
	 * Methode qui fait un update sur la base
	 * 
	 * @param args, Une chaine de caractere qui correspond a la requete
	 * @return Un entier, le code de retour de la requete
	 * @throws SQLExceptio, Une exception mysql
	 */
	public int query(String args) throws SQLException {
		int resultat = this.statement.executeUpdate(args);
		return resultat;
	}
	
	/**
	 * Setter sur l'utilisateur
	 * 
	 * @param _utilisateur, Une chaine de caractere qui correspond a l'utilisateur
	 */
	public void setUtilisateur(String _utilisateur){
		this.utilisateur = _utilisateur;
	}
	
	/**
	 * Setter sur le mot de passe
	 * 
	 * @param _motDePasse, Une chaine de caractere qui correspond au mot de passe
	 */
	public void setMotDePasse(String _motDePasse){
		this.motDePasse = _motDePasse;
	}
	
	/**
	 * Setter sur l'hote
	 * 
	 * @param _host, Une chaine de caractere qui correspond a l'hote
	 */
	public void setHost(String _host){
		this.host = _host;
	}
	
	/**
	 * Ferme la connexion a la base
	 * 
	 * @throws SQLException, Une exception mysql
	 */
	public void fermerConnexion(){
		try {
			connexion.close();
		}
		catch (SQLException e){
			System.out.println("Impossible de fermer la connexion à la base de données !");
			e.printStackTrace();
		}
	}
}
