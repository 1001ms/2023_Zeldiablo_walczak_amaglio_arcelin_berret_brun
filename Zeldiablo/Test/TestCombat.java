import org.junit.jupiter.api.Test;
import gameLaby.laby.LabyDessin;
import gameLaby.laby.LabyJeu;
import moteurJeu.MoteurJeu;

import java.io.IOException;

public class TestCombat {

        @Test
        public void test_miseEnPause() throws IOException {
            int width = 900;
            int height = 800;
            int pFPS = 8;

            // creation des objets
            LabyJeu jeuLaby = new LabyJeu();
            LabyDessin dessinLaby = new LabyDessin();
            // parametrage du moteur de jeu
            MoteurJeu.setTaille(width, height);
            MoteurJeu.setFPS(pFPS);
            // lancement du jeu
            MoteurJeu.launch(jeuLaby, dessinLaby);

            jeuLaby.getLabyrinthe().deplacerPerso("Gauche");

            /////////////////////int nbFrame = MoteurJeu.getFrameStats().getFrameCount();

            //le but est de compter le nombre de frame "apres l'arret supposé, faire une pause d'une seconde et recomparer le nb de frame
            // si le nombre de frame est identique alors les boucles se sont belle et bien arreter .
            // on peut aussi regarder si il n'y a pas une fonction qui permet de voirs si une timeLine et a l'arrret (Timer aussi)
        }
}
