import static org.junit.Assert.assertEquals;

import java.awt.Color;

import modeles.Ligne;
import modeles.MoyenTransport;

import org.junit.Test;


public class TestLigne {

	@Test
	public void test() {
		final int _id = 12;
		final String _nom = "Bastille";
		final Color _color =new Color(100, 100, 100);
		final MoyenTransport _transport = new MoyenTransport(3, "Train");
		Ligne lgn = new Ligne(_id, _nom, _color, _transport);
		
		assertEquals(_id, lgn.getId());
		assertEquals(_nom, lgn.getNom());
	}

}
