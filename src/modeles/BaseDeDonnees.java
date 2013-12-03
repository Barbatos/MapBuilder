package modeles;

import java.sql.*;

public class BaseDeDonnees {
	private Connection connexion = null;
	private Statement statement = null;
	private String utilisateur = "";
	private String motDePasse = "";
	private String host = "";
	
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
		
		// Connexion � la base de donn�es
		try {
			this.connexion = DriverManager.getConnection(this.host, this.utilisateur, this.motDePasse);
			this.statement = this.connexion.createStatement();
		} 
		catch (SQLException e){
			System.out.println("Connexion impossible � MySQL !");
			e.printStackTrace();
			System.exit(2);
		}

	}
	
	public ResultSet select(String args) throws SQLException {
		ResultSet resultat = this.statement.executeQuery(args);
		return resultat;
	}
	
	public int query(String args) throws SQLException {
		int resultat = this.statement.executeUpdate(args);
		return resultat;
	}
	
	public void setUtilisateur(String _utilisateur){
		this.utilisateur = _utilisateur;
	}
	
	public void setMotDePasse(String _motDePasse){
		this.motDePasse = _motDePasse;
	}
	
	public void setHost(String _host){
		this.host = _host;
	}
	
	public void fermerConnexion(){
		try {
			connexion.close();
		}
		catch (SQLException e){
			System.out.println("Impossible de fermer la connexion � la base de donn�es !");
			e.printStackTrace();
		}
	}
}
