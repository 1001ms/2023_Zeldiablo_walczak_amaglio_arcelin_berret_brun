import gameLaby.laby.Aventurier;
import gameLaby.laby.Monstre;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class AventurierTest {

    /**
     * méthode de test du constructeur du monstre
     */
    @Test
    public void testAventurierConstructor() {
        int dx = 3;
        int dy = 4;
        int hp = 100;
        Aventurier aventurier = new Aventurier(dx, dy, hp);

        Assertions.assertEquals(dx, aventurier.getX());
        Assertions.assertEquals(dy, aventurier.getY());
        Assertions.assertEquals(hp, aventurier.getHP());
    }

    /**
     * méthode de test du setHp de monstre
     */
    @Test
    public void testAventurierSetHP() {
        int dx = 3;
        int dy = 4;
        int hp = 100;
        Aventurier aventurier = new Aventurier(dx, dy, hp);

        int newHP = 50;
        aventurier.setHP(newHP);

        Assertions.assertEquals(newHP, aventurier.getHP());
    }
}
