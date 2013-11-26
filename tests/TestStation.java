import static org.junit.Assert.*;
import modeles.Coordonnees;
import modeles.MoyenTransport;
import modeles.Station;

import org.junit.Test;


public class TestStation {

	@Test //Test sur tous les éléments d'une station (nom, numéro, coordonnees, moyen de transport)
	public void test() {
		final Coordonnees coord = new Coordonnees(80, 160);
		final int id = 2;
		final String nom = "Bastille";
		final int numero = 0;
		final String transport = "Bus";
		
		Station sta1 = new Station(coord, id, nom);
		
		sta1.insertTransport(new MoyenTransport(1, "Bus"));
		sta1.insertTransport(new MoyenTransport(2, "Tram"));	
		
		assertEquals(coord, sta1.getCoordonnees());
		assertEquals(id, sta1.getId());
		assertEquals(nom, sta1.getNom());
		assertEquals(transport, sta1.getTransport(numero).getNom());
		
	}

}
