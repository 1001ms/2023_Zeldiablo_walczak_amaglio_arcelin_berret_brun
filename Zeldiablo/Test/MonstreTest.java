import gameLaby.laby.Monstre;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class MonstreTest {

    @Test
    public void testMonstreConstructor() {
        int dx = 3;
        int dy = 4;
        int hp = 100;
        Monstre monstre = new Monstre(dx, dy, hp);

        Assertions.assertEquals(dx, monstre.getX());
        Assertions.assertEquals(dy, monstre.getY());
        Assertions.assertEquals(hp, monstre.getHP());
    }

    @Test
    public void testMonstreSetHP() {
        int dx = 3;
        int dy = 4;
        int hp = 100;
        Monstre monstre = new Monstre(dx, dy, hp);

        int newHP = 50;
        monstre.setHP(newHP);

        Assertions.assertEquals(newHP, monstre.getHP());
    }
}
