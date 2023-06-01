package gameLaby.laby;


/**
 * gere un personnage situe en x,y
 */
public abstract class Personnage implements Element {

    /**
     * position du personnage
     */
    private Coordonnee coordonnee;

    private int HP;

    /**
     * constructeur
     *
     * @param dx position selon x
     * @param dy position selon y
     */
    public Personnage(int dx, int dy) {
        this.coordonnee=new Coordonnee(dx,dy);
    }

    /**
     * permet de savoir si le personnage est en x,y
     *
     * @param dx position testee
     * @param dy position testee
     * @return true si le personnage est bien en (dx,dy)
     */
    public boolean etrePresent(int dx, int dy) {

        return (this.coordonnee.getX() == dx && this.coordonnee.getY() == dy);
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
}

