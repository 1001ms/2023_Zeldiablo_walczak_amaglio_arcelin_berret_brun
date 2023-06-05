package gameLaby.laby;

public class Caisse implements Element {

    private int x,y;

    /**
     * Constructeur de Depot
     * @param x
     * @param y
     */
    public Caisse(int x, int y) {
        this.x=x;
        this.y=y;
    }
    /**
     * permet de savoir si le personnage est en x,y
     *
     * @param dx position testee
     * @param dy position testee
     * @return true si le personnage est bien en (dx,dy)
     */
    public boolean etrePresent(int dx, int dy) {

        return (x == dx && y== dy);
    }

    /**
     * @return position x du personnage
     */
    public int getX() {
        // getter
        return this.x;
    }

    /**
     * @return position y du personnage
     */
    public int getY() {
        //getter
        return this.y;
    }

    /**
     * @return position x du personnage
     */
    public void setX(int x) {
        // getter
        this.x=x;
    }

    /**
     * @return position y du personnage
     */
    public void setY(int y) {
        //getter
        this.y=y;
    }
}
