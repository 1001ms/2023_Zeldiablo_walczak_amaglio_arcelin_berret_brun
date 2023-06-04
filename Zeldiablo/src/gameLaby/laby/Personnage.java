package gameLaby.laby;


/**
 * gere un personnage situe en x,y
 */
public abstract class Personnage implements Element {

    /**
     * position du personnage
     */
    private Coordonnee coordonnee;

    /**
     * Nombre de point de vie actuelle du personnage
     */
    private int HP;
    /**
     * Nombre de point de vie maximal du personnage
     */
    final int HPMax;

    /**
     * constructeur
     *
     * @param dx position selon x
     * @param dy position selon y
     */
    public Personnage(int dx, int dy, int HP) {
        this.coordonnee=new Coordonnee(dx,dy);
        this.HP=HP;
        this.HPMax=HP;
    }

    /**
     * @return position x du personnage
     */
    public int getX() {
        // getter
        return this.coordonnee.getX();
    }

    /**
     * @return position y du personnage
     */
    public int getY() {
        //getter
        return this.coordonnee.getY();
    }

    /**
     * @return position y du personnage
     */
    public int getHP() {
        //getter
        return this.HP;
    }

    /**
     * set position x du personnage
     */
    public void setX(int x) {
        // getter
        this.coordonnee.setX(x);
    }

    /**
     * set position y du personnage
     */
    public void setY(int y) {
        // getter
        this.coordonnee.setY(y);
    }

    /**
     * set HP du personnage
     */
    public void setHP(int HP) {
        this.HP = HP;
    }

    /**
     *
     * @return le nombre de point de vie maximal
     */
    public int getHPMax(){
        return HPMax;
    }
}

