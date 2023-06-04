import gameLaby.laby.Coordonnee;
import gameLaby.laby.Echappatoire;
import gameLaby.laby.Labyrinthe;
import gameLaby.laby.Serpent;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;

public class LabyrintheTest {
    //!!!!!!!!!!!!!!! possible que les tests ne soit pas forcément juste car le déplacement des monstre est aléatoire
    // donc relancer si ce n'est pas juste !!!!!!!!!!!!!!!!!!

    /**
     * test quand le monste se déplace
     * @throws IOException
     */
    @Test
    public void testDeplacerMonstre() throws IOException {

        Labyrinthe laby = new Labyrinthe("Zeldiablo/labyTest/labyTest1.txt",0,new Echappatoire(),0);
        int yprec=laby.monstres.get(0).getY();
        int xprec =laby.monstres.get(0).getX();
        laby.deplacerMonstre();
        int y=laby.monstres.get(0).getY();
        int x =laby.monstres.get(0).getX();

        Assertions.assertNotEquals(false,yprec==y,"comment");
        Assertions.assertNotEquals(false,xprec==x,"comment");
    }

    /**
     * test quand le perso se déplace
     * @throws IOException
     */
    @Test
    public void testDeplacerPerso() throws IOException {
        Labyrinthe laby = new Labyrinthe("Zeldiablo/labyTest/labyTest1.txt", 0, new Echappatoire(), 0);
        int yPrec = laby.pj.getY();
        int xPrec = laby.pj.getX();

        laby.deplacerPerso("Haut");

        int y = laby.pj.getY();
        int x = laby.pj.getX();

        Assertions.assertEquals(yPrec, y, "Le déplacement vertical du personnage vers le haut s'est produite");
        Assertions.assertNotEquals(xPrec, x, "Le déplacement horizontal du personnage vers le haut ne devrait pas avoir lieu");
    }

    /**
     * test quand le perso va dans un mur
     * @throws IOException
     */
    @Test
    public void testDeplacerPersoVersMur() throws IOException {
        Labyrinthe laby = new Labyrinthe("Zeldiablo/labyTest/labyTest1.txt", 0, new Echappatoire(), 0);
        int yPrec = laby.pj.getY();
        int xPrec = laby.pj.getX();

        laby.deplacerPerso("Gauche");

        int y = laby.pj.getY();
        int x = laby.pj.getX();

        Assertions.assertEquals(yPrec, y, "Le mur bloque le personnage donc le y ne change pas");
        Assertions.assertEquals(xPrec, x, "Le mur bloque le personnage donc le x ne change pas");
    }

    /**
     * test du déplacement du serpent
     */

    /**
     * test du déplacement du serpent
     */
    @Test
    public void testDeplacerSerpent() throws IOException {
        Serpent serpent = new Serpent(3, 5, 10);
        Labyrinthe laby = new Labyrinthe("Zeldiablo/labyTest/labyTest1.txt",0,new Echappatoire(),0);
        int xPrec = serpent.getX();
        int yPrec = serpent.getY();

        laby.deplacerSerpent();

        int x = serpent.getX();
        int y = serpent.getY();

        Assertions.assertEquals(xPrec, x, "Le déplacement vertical du serpent vers le haut ne devrait pas avoir lieu");
        Assertions.assertEquals(yPrec, y, "Le déplacement horizontal du serpent vers le haut s'est produit");
        }
}
