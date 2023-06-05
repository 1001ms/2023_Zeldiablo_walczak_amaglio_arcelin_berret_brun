import gameLaby.laby.Coordonnee;
import gameLaby.laby.Echappatoire;
import gameLaby.laby.Labyrinthe;
import gameLaby.laby.Serpent;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

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

        assertEquals(yPrec, y, "Le déplacement vertical du personnage vers le haut s'est produite");
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

        assertEquals(yPrec, y, "Le mur bloque le personnage donc le y ne change pas");
        assertEquals(xPrec, x, "Le mur bloque le personnage donc le x ne change pas");
    }

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

        assertEquals(xPrec, x, "Le déplacement vertical du serpent vers le haut ne devrait pas avoir lieu");
        assertEquals(yPrec, y, "Le déplacement horizontal du serpent vers le haut s'est produit");
        }

    /**
     * test deplacerPerso lorsque que le perso va dans la direction d'une caisse adjacente à une autre
     * le perso ne devrait pas pouvoir déplacer deux caisses
     *
     * @throws Exception
     */
    @Test
    public void test_deplacerPerso_2_CAISSES() throws Exception {
        Labyrinthe laby = new Labyrinthe("Zeldiablo/labyTest/labyTest2.txt",0,new Echappatoire(),0);


        laby.deplacerPerso(Labyrinthe.GAUCHE);

        assertEquals(4, laby.pj.getX(), "Les coordonnées de personnage ne change pas");
        assertEquals(2, laby.caisses.getElementByIndice(0).getX(), "Les coordonnées de la première caisse de change pas");
        assertEquals(3, laby.caisses.getElementByIndice(1).getX(), "Les coordonnées de la seconde caisse ne change pas");
    }

    /**
     * test deplacerPerso lorsqu'il y a une seul caisse, cette fois-ci le perso doit pouvoir
     * déplacer la caisse
     *
     * @throws Exception
     */
    @Test
    public void test_deplacerPerso_1_CAISSE() throws Exception {
        Labyrinthe laby = new Labyrinthe("Zeldiablo/labyTest/labyTest3.txt",0,new Echappatoire(),0);

        laby.deplacerPerso(Labyrinthe.HAUT);

        assertEquals(3, laby.pj.getX(), "La getX doit renvoyer x-1");
        assertEquals(2, laby.caisses.getElementByIndice(0).getX(), "La getX doit renvoyer x-1");
    }
}
