import gameLaby.laby.Echappatoire;
import gameLaby.laby.Labyrinthe;
import org.junit.Test;
import gameLaby.laby.LabyDessin;
import gameLaby.laby.LabyJeu;
import moteurJeu.MoteurJeu;
import org.junit.jupiter.api.Assertions;

import java.io.IOException;

public class TestCombat {

    /**
     * Methode de teste verrifiant qu'un combat et bien lancé quand le joeur veut se déplacer vers un monstre
     * @throws IOException
     */
    @Test
        public void test_miseEnCombat() throws IOException {

            // creation des objets
            Labyrinthe l = new Labyrinthe("Zeldiablo/labyTest/labyTest4.txt", 0, new Echappatoire(), 0);
            l.deplacerPerso("Gauche");

            Assertions.assertEquals(true,l.getCombat().etatCombat,"un combat devrai avoir lieux");
        }
}
