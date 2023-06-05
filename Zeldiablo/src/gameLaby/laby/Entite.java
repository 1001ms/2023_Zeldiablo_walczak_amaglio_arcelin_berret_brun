package gameLaby.laby;

public abstract class Entite implements Element{

    private Coordonnee coordonnee;

    public Entite(int x, int y){
        this.coordonnee=new Coordonnee(x,y);
    }
    /**
     * getter de X
     * @return le coordonnée X de la torche
     */
    @Override
    public int getX() {
        return coordonnee.getX();
    }

    /**
     * getter de Y
     * @return le coordonnée Y de la torche
     */
    @Override
    public int getY() {
        return coordonnee.getY();
    }

    /**
     * setter du X de la torche
     * @param x met à jour le x
     */
    @Override
    public void setX(int x) {
        coordonnee.setX(x);
    }
    /**
     * setter du Y de la torche
     * @param y met à jour le y
     */
    @Override
    public void setY(int y) {
        coordonnee.setY(y);
    }

    /**
     * méthode estPresent
     * @param x coordonnée x
     * @param y coordonnée y
     * @return true si x==x et y==y, false sinon
     */
    public boolean estPresent(int x, int y){
        return coordonnee.estPresent(x,y);
    }
}
