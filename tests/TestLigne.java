import static org.junit.Assert.*;

import org.junit.Test;


public class TestLigne {

	@Test
	public void test() {
		final int _id = 12;
		final String _nom = "Bastille";
		Ligne lgn = new Ligne(_id, _nom);
		
		assertEquals(_id, lgn.getId());
		assertEquals(_nom, lgn.getNom());
	}

}
