import gameLaby.laby.Torche;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TorcheTest {

    @Test
    public void testGetX() {
        Torche torche = new Torche(10,20);
        Assertions.assertEquals(10, torche.getX(), "La valeur retournée par getX() ne correspond pas à la valeur attendue");
    }

    @Test
    public void testGetY() {
        Torche torche = new Torche(10, 20);
        Assertions.assertEquals(20, torche.getY(), "La valeur retournée par getY() ne correspond pas à la valeur attendue");
    }

    @Test
    public void testSetX() {
        Torche torche = new Torche(10, 20);
        torche.setX(30);
        Assertions.assertEquals(30, torche.getX(), "La valeur de x après avoir appelé setX() ne correspond pas à la valeur attendue");
    }

    @Test
    public void testSetY() {
        Torche torche = new Torche(10, 20);
        torche.setY(40);
        Assertions.assertEquals(40, torche.getY(), "La valeur de y après avoir appelé setY() ne correspond pas à la valeur attendue");
    }

    @Test
    public void testEstPresent() {
        Torche torche = new Torche(10, 20);
        Assertions.assertTrue(torche.estPresent(10, 20), "La méthode estPresent() ne retourne pas true pour des coordonnées correspondantes");
        Assertions.assertFalse(torche.estPresent(30, 40), "La méthode estPresent() ne retourne pas false pour des coordonnées non correspondantes");
    }
}
