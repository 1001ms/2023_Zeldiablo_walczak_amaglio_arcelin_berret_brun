package gameLaby.laby;


/**
 * gere un personnage situe en x,y
 */
public abstract class Personnage extends Element {

    /**
     * Nombre de point de vie actuelle du personnage
     */
    private double HP;

    /**
     * Nombre de point de vie maximal du personnage
     */
    final double HPMax;

    /**
     * constructeur
     *
     * @param x position selon x
     * @param y position selon y
     */
    public Personnage(int x, int y, int HP) {
        super(x,y);
        this.HP=HP;
        this.HPMax=HP;
    }

    /**
     * set HP du personnage
     */
    public void setHP(double HP) {
        this.HP = HP;
    }

    /**
     *
     * @return le nombre de point de vie maximal
     */
    public double getHPMax(){
        return HPMax;
    }

    public double getHP() {
        return HP;
    }
}

