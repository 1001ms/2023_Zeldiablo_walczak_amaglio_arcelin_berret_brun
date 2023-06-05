import gameLaby.laby.Tresor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TresorTest {

    @Test
    public void testGetX() {
        Tresor tresor = new Tresor(10, 20);
        Assertions.assertEquals(10, tresor.getX(), "La valeur retournée par getX() ne correspond pas à la valeur attendue");
    }

    @Test
    public void testGetY() {
        Tresor tresor = new Tresor(10, 20);
        Assertions.assertEquals(20, tresor.getY(), "La valeur retournée par getY() ne correspond pas à la valeur attendue");
    }

    @Test
    public void testSetX() {
        Tresor tresor = new Tresor(10, 20);
        tresor.setX(30);
        Assertions.assertEquals(30, tresor.getX(), "La valeur de x après avoir appelé setX() ne correspond pas à la valeur attendue");
    }

    @Test
    public void testSetY() {
        Tresor tresor = new Tresor(10, 20);
        tresor.setY(40);
        Assertions.assertEquals(40, tresor.getY(), "La valeur de y après avoir appelé setY() ne correspond pas à la valeur attendue");
    }

    @Test
    public void testEstPresent() {
        Tresor tresor = new Tresor(10, 20);
        Assertions.assertTrue(tresor.estPresent(10, 20), "La méthode estPresent() ne retourne pas true pour des coordonnées correspondantes");
        Assertions.assertFalse(tresor.estPresent(30, 40), "La méthode estPresent() ne retourne pas false pour des coordonnées non correspondantes");
    }
}
