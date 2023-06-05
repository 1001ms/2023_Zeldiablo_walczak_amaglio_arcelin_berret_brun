import gameLaby.laby.*;
import org.junit.Test;
import gameLaby.laby.LabyDessin;
import gameLaby.laby.LabyJeu;
import moteurJeu.MoteurJeu;
import org.junit.jupiter.api.Assertions;

import java.io.IOException;
import java.util.ArrayList;

public class TestCombat {

    /**
     * Methode de teste verrifiant qu'un combat et bien lancé quand le joeur veut se déplacer vers un monstre
     * @throws IOException
     */
    @Test
        public void test_miseEnCombat() throws IOException {

            // creation des objets
            Labyrinthe l = new Labyrinthe("Zeldiablo/labyTest/labyTest4.txt", 0, new Echappatoire(), 0);
            l.deplacerPerso("Haut");

            Assertions.assertEquals(true,l.getCombat().etatCombat,"un combat devrai avoir lieux");
        }

    /**
     * Methode de teste verrifiant qu'un combat et bien lancé quand le joeur veut se déplacer vers un monstre
     * @throws IOException
     */
    @Test
    public void test_suppMonstre() throws IOException {

        // creation des objets
        Labyrinthe l = new Labyrinthe("Zeldiablo/labyTest/labyTest4.txt", 0, new Echappatoire(), 0);
        ArrayList<Personnage> m = l.personnages;
        l.deplacerPerso("Haut");
        for(int i =0; i<3; i++){
            l.getCombat().attaque(1);
        }
        Assertions.assertEquals(m.size()-1,l.monstres.size(),"le nombre de personnage devrait etre reduits");
    }
}
