package gameLaby.laby;
import moteurJeu.MoteurJeu;

public class MainLaby {

    public static void main(String[] args) {
        int width = 810;
        int height = 718;

        int pFPS = 100;


        // creation des objets
        LabyJeu jeuLaby = new LabyJeu();
        LabyDessin dessinLaby = new LabyDessin();

        // parametrage du moteur de jeu
        MoteurJeu.setTaille(width,height);
        MoteurJeu.setFPS(pFPS);

        // lancement du jeu
        MoteurJeu.launch(jeuLaby, dessinLaby);

    }
}

