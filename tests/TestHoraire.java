import modeles.Horaire;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class TestHoraire {

	@Test
	public void test(){
		
		final int _JSemaine = 1;
		final int _heure = 5;
		final int _minute = 45;
		final int _periode = 1;
		Horaire hour = new Horaire(_JSemaine, _heure, _minute, _periode);
		
		assertEquals(_JSemaine, hour.getJSemaine());
		assertEquals(_heure, hour.getHeure());
		assertEquals(_minute, hour.getMinute());
		assertEquals(_periode, hour.getPeriode());
		
		
	}
}
