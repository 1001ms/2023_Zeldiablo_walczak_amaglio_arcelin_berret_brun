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

    private double rayonTorche;
    public Aventurier(int dx, int dy) {
        super(dx,dy);
        this.rayonTorche=Torche.RAYONTORCHE;
    }

    public double getRayonTorche() {
        return rayonTorche;
    }

    public void setRayonTorche(double dureeTorche) {
        rayonTorche = dureeTorche;
    }
}
