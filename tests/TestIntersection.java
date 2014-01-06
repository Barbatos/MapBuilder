import modeles.Intersection;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class TestIntersection {

	@Test
	public void test(){
		
		final int _id = 2;
		final String _type = "carrefour";
		Intersection inter = new Intersection(_id, _type);
		
		assertEquals(_id, inter.getId());
		assertEquals(_type, inter.getType());
	}
}
