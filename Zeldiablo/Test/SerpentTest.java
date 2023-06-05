import gameLaby.laby.Coordonnee;
import gameLaby.laby.Serpent;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class SerpentTest {

    /**
     * Teste la création du serpent et l'accès à ses coordonnées
     */
    @Test
    public void testCreationSerpent() {
        Serpent serpent = new Serpent(3, 5, 10);
        ArrayList<Coordonnee> corps = serpent.getCorp();

        Assertions.assertEquals(3, serpent.getX(), "La position x du serpent devrait être 3");
        Assertions.assertEquals(5, serpent.getY(), "La position y du serpent devrait être 5");
        Assertions.assertEquals(3, corps.get(0).getX(), "La position x du premier élément du corps devrait être 3");
        Assertions.assertEquals(5, corps.get(0).getY(), "La position y du premier élément du corps devrait être 5");
    }

    /**
     * Teste la modification des coordonnées du serpent
     */
    @Test
    public void testModifierCoordonneesSerpent() {
        Serpent serpent = new Serpent(3, 5, 10);
        ArrayList<Coordonnee> corps = serpent.getCorp();

        serpent.setX(4);
        serpent.setY(6);

        Assertions.assertEquals(4, serpent.getX(), "La position x du serpent devrait être modifiée à 4");
        Assertions.assertEquals(6, serpent.getY(), "La position y du serpent devrait être modifiée à 6");
        Assertions.assertEquals(4, corps.get(0).getX(), "La position x du premier élément du corps devrait être modifiée à 4");
        Assertions.assertEquals(6, corps.get(0).getY(), "La position y du premier élément du corps devrait être modifiée à 6");
    }
}
