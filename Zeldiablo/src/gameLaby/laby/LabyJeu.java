package gameLaby.laby;
import moteurJeu.Clavier;
import moteurJeu.Jeu;
import java.io.IOException;

import static gameLaby.laby.Labyrinthe.*;
import static gameLaby.laby.Labyrinthe.HAUT;

public class LabyJeu implements Jeu {

    //Labyrinthe et personnage

    private Labyrinthe laby;

    /**
     Constructeur par défaut
     */
    public LabyJeu(){
        try {
            this.laby=new Labyrinthe("Zeldiablo/labySimple/laby1.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void update(double secondes, Clavier clavier) {

        // deplace le perso en fonction des touches
        if (clavier.droite) {
            laby.deplacerPerso(DROITE);
        }
        if (clavier.gauche) {
            laby.deplacerPerso(GAUCHE);
        }
        if (clavier.bas) {
            laby.deplacerPerso(BAS);
        }
        if (clavier.haut) {
            this.laby.deplacerPerso(HAUT);
        }


    }

    @Override
    public void init() {
    }


    @Override
    public boolean etreFini() {
        return laby.etreFini();
    }

    public Labyrinthe getLabyrinthe(){
        return this.laby;
    }



}