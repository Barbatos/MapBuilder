import modeles.MoyenTransport;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class TestMoyenTransport {

	@Test
	public void test(){
		
		final int _id = 3;
		final String _nom = "Bus";
		MoyenTransport MT = new MoyenTransport(_id, _nom);
		
		assertEquals(_id, MT.getId());
		assertEquals(_nom, MT.getNom());
	}
}
