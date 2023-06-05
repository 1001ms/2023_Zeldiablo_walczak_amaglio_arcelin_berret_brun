package gameLaby.laby;


/**
 * gere un personnage situe en x,y
 */
public class Aventurier extends Personnage {
    //déclarations des attributs
    private int nbTresors;
    private double rayonTorche;
    /**
     * constructeur
     *
     * @param dx position selon x
     * @param dy position selon y
     */
    public Aventurier(int dx, int dy, int HP) {
        super(dx,dy,HP);
        this.rayonTorche=Torche.RAYONTORCHE;
    }

    /**
     * getter du rayon de torche
     * @return return le rayon de torche
     */
    public double getRayonTorche() {
        return rayonTorche;
    }

    /**
     * setter du rayon de torche
     * @param dureeTorche maj du rayon de torche
     */
    public void setRayonTorche(double dureeTorche) {
        rayonTorche = dureeTorche;
    }

    /**
     * méthode setNbTresors
     * @param nbTresors set le nombre de trésors
     */
    public void setNbTresors(int nbTresors) {
        this.nbTresors = nbTresors;
    }

    /**
     * méthode getNbTresors
     * @return le nombre de tresors que le personnage possede
     */
    public int getNbTresors() {
        return nbTresors;
    }
}
