package gameLaby.laby;
import moteurJeu.MoteurJeu;

import java.io.IOException;

public class MainLaby {

    public static void main(String[] args) throws IOException {
        int width = 810;
        int height = 718;

        int pFPS = 20;


        // creation des objets
        LabyJeu jeuLaby = new LabyJeu();
        LabyDessin dessinLaby = new LabyDessin();

        // parametrage du moteur de jeu
        MoteurJeu.setTaille(width,height);
        MoteurJeu.setFPS(pFPS);

        // lancement du jeu
        MoteurJeu.launch(jeuLaby, dessinLaby);

        System.out.println("Labyrinthe 1");

    }
}

