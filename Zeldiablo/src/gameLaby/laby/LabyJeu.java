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

    /**
     * méthode init
     */
    @Override
    public void init() {
    }

    /**
     * méthode etreFini
     * @return true si le jeu est fini (une condition de victoire ou de défaite est présente)
     */
    @Override
    public boolean etreFini() {
        return laby.etreFini();
    }

    /**
     * méthode getLaby
     * @return le labyrinthe
     * @throws IOException
     */
    @Override
    public Labyrinthe getLabyrinthe() throws IOException {
        return this.laby;
    }

    /**
     * méthode setLabyrinthe
     * @param l le labyrinthe
     */
    public void setLabyrinthe(Labyrinthe l){
        this.laby=l;
    }

    /**
     * méthode new Laby
     * @param name nom du fichier du labyrinthe
     * @param nv niveau dans lequel le laby se situe
     * @param es code de l'échapatoire
     * @param nM numéro de la Map dans la dit niveau
     * @return LabyJeu
     * @throws IOException
     */
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