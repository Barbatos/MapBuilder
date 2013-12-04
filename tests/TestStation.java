import static org.junit.Assert.*;

import java.awt.Color;

import modeles.Ligne;
import modeles.MoyenTransport;
import modeles.Station;

import org.junit.Test;


public class TestStation {

	@Test //Test sur tous les éléments d'une station (nom, numéro, coordonnees, moyen de transport)
	public void test() {
		final int coordx = 50;
		final int coordy = 400;
		final int id = 2;
		final String nom = "Bastille";
		final MoyenTransport transport = new MoyenTransport(3, "Train");
		final Color couleur = new Color(200, 200, 200);
		//final int numero = 0;
		//final String transport = "Bus";
		
		Station sta1 = new Station(id, coordx, coordy, nom);
		
		/*sta1.insertTransport(new MoyenTransport(1, "Bus"));
		sta1.insertTransport(new MoyenTransport(2, "Tram"));	*/
		sta1.insertLigne(new Ligne(id, nom, couleur, transport));
		
		
		assertEquals(id, sta1.getId());
		assertEquals(nom, sta1.getNom());
		
	}

}
