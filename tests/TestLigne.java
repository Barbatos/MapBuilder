import static org.junit.Assert.assertEquals;

import java.awt.Color;

import modeles.Ligne;
import modeles.MoyenTransport;
import modeles.Station;
import modeles.Ville;

import org.junit.Test;


public class TestLigne {

	@Test
	public void test() {
		final int _id = 12;
		final String _nom = "Bastille";
		final Color _color =new Color(100, 100, 100);
		final MoyenTransport _transport = new MoyenTransport(3, "Train");
		
		Ligne lgn = new Ligne(_id, _nom, _color, _transport);
		
		Station st1 = new Station(1, 10, 10, "ST1", 0);
		Station st2 = new Station(2, 20, 20, "ST2", 0);
		Station st3 = new Station(3, 30, 30, "ST3", 0);
		
		lgn.ajouterStation(st1);
		lgn.ajouterStation(st3);
		lgn.insertStation(st2, 1);
		
		assertEquals(st1, lgn.getStation(0));
		assertEquals(st2, lgn.getStation(1));
		assertEquals(st3, lgn.getStation(2));
		
		assertEquals(_id, lgn.getId());
		assertEquals(_nom, lgn.getNom());
	}

}
