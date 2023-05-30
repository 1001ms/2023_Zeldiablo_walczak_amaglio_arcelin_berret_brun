package gameLaby.laby;
import moteurJeu.Clavier;
import moteurJeu.Jeu;
import java.io.IOException;
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