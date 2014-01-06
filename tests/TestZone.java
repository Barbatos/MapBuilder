import static org.junit.Assert.assertEquals;

import java.awt.Color;

import modeles.Zone;

import org.junit.Test;


public class TestZone {

	@Test
	public void test() {
		final int _id = 1;
		final String _nom = "Test";
		final Color _couleur = new Color(100, 100, 100);
		Zone zone = new Zone(_id, _nom, _couleur);
		
		assertEquals(_id, zone.getId());
		assertEquals(_nom, zone.getNom());
		assertEquals(_couleur, zone.getCouleur());
	}

}
