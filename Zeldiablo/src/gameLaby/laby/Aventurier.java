package gameLaby.laby;


/**
 * gere un personnage situe en x,y
 */
public class Aventurier extends Personnage {

    /**
     * constructeur
     *
     * @param dx position selon x
     * @param dy position selon y
     */
    private int nbTresors;
    private double rayonTorche;
    public Aventurier(int dx, int dy, int HP) {
        super(dx,dy,HP);
        this.rayonTorche=Torche.RAYONTORCHE;
    }

    public double getRayonTorche() {
        return rayonTorche;
    }

    public void setRayonTorche(double dureeTorche) {
        rayonTorche = dureeTorche;
    }

    public void setNbTresors(int nbTresors) {
        this.nbTresors = nbTresors;
    }

    public int getNbTresors() {
        return nbTresors;
    }
}
