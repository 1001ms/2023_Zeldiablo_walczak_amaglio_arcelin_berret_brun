package gameLaby.laby;

public interface Element {
    /**
     * @return position x du personnage
     */
     int getX();

    /**
     * @return position y du personnage
     */
     int getY();

    /**
     * setter de X
     * @param x met à jour le x
     */
    void setX(int x);

    /**
     * setter de Y
     * @param y met à jour y
     */
    void setY(int y);

}
