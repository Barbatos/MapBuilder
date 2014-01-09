import static org.junit.Assert.assertEquals;

import java.awt.Color;

import modeles.MoyenTransport;
import modeles.Station;
import modeles.Zone;

import org.junit.Test;


public class TestStation {

	@Test //Test sur tous les éléments d'une station (nom, numéro, coordonnees, moyen de transport)
	public void test() {
		final int coordx = 50;
		final int coordy = 400;
		final int id = 2;
		final String nom = "Bastille";
		final int ville = 1;
		final MoyenTransport transport = new MoyenTransport(3, "Train");
		final Color couleur = new Color(200, 200, 200);
		final Zone zone = new Zone(id, nom, couleur, ville);
		
		Station sta1 = new Station(id, coordx, coordy, nom, ville);
		
		
		
		assertEquals(id, sta1.getId());
		assertEquals(nom, sta1.getNom());
	}

}
