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
            Echappatoire escp =new Echappatoire();
            this.laby=new Labyrinthe("Zeldiablo/labySimple/laby0A0.txt",0,escp,0);
            laby.escapes.afficher();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     Constructeur avec une String en parametre
     */
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

    @Override
    public Labyrinthe getLaby() throws IOException {
        return this.laby;
    }

    public Labyrinthe getLabyrinthe(){
        return this.laby;
    }

    public void setLabyrinthe(Labyrinthe l){
        this.laby=l;
    }


    public LabyJeu newLaby(String name,int nv,Echappatoire es, int nM) throws IOException {
        try {
            this.laby=new Labyrinthe(name,nv,es, nM);
            laby.escapes.afficher();
            return this;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}