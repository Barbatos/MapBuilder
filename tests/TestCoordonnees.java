import static org.junit.Assert.*;
import modeles.Coordonnees;

import org.junit.Test;


public class TestCoordonnees {

	@Test
	public void test() {
		final int x = 100;
		final int y = 100;
		Coordonnees coor = new Coordonnees(x, y);
		
		assertEquals(x, coor.getX());
		assertEquals(y, coor.getY());
	}
}
