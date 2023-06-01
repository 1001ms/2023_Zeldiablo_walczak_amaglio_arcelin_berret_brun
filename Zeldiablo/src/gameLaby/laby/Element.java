package gameLaby.laby;

public interface Element {
    /**
     * permet de savoir si le personnage est en x,y
     *
     * @param dx position testee
     * @param dy position testee
     * @return true si le personnage est bien en (dx,dy)
     */
    boolean etrePresent(int dx, int dy);

    /**
     * @return position x du personnage
     */
     int getX();

    /**
     * @return position y du personnage
     */
     int getY();
}
