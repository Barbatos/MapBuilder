import modeles.Bouton;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class TestBouton {

	@Test
	public void test(){
		
		final String _nom = "Bouton1";
		final int _x = 100;
		final int _y = 200;
		final int _hauteur = 10;
		final int _largeur = 20;
		Bouton btn = new Bouton(_nom, _x, _y, _largeur, _hauteur);
		
		assertEquals(_nom, btn.getNom());
		assertEquals(_x, btn.getX());
		assertEquals(_y, btn.getY());
		assertEquals(_largeur, btn.getLargeur());
		assertEquals(_hauteur, btn.getHauteur());
	}
}
