package gameLaby.laby;
//import javazoom.jl.player.Player;

import moteurJeu.MoteurJeu;

import java.io.IOException;

public class MainLaby {


    public static void main(String[] args) throws IOException {
        int width = 900;
        int height = 800;

        int pFPS = 8;


        // creation des objets
        LabyJeu jeuLaby = new LabyJeu();
        LabyDessin dessinLaby = new LabyDessin();

        // parametrage du moteur de jeu
        MoteurJeu.setTaille(width,height);
        MoteurJeu.setFPS(pFPS);

        //lancement du jeu
        MoteurJeu.launch(jeuLaby, dessinLaby);
        System.out.println("Labyrinthe 1");
    }
}

