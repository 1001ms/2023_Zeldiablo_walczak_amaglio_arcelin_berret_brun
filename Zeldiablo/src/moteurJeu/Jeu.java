package moteurJeu;


import gameLaby.laby.Echappatoire;
import gameLaby.laby.LabyJeu;
import gameLaby.laby.Labyrinthe;

import java.io.IOException;

/**
 * modele de jeu
 */
public interface Jeu {

    /**
     * methode mise a jour du jeu
     * @param secondes temps ecoule depuis la derniere mise a jour
     * @param clavier objet contenant l'état du clavier'
     */
    void update(double secondes, Clavier clavier);

    /**
     * initialisation du jeu
     */
    void init();

    /**
     * verifie si le jeu est fini
     * @return booleen true si le jeu est fini
     */
    boolean etreFini();

    Labyrinthe getLaby() throws IOException;

    LabyJeu newLaby(String l, int lvl, Echappatoire es, int nM) throws IOException;
    Labyrinthe getLabyrinthe() throws IOException;
}
