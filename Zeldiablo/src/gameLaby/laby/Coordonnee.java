package gameLaby.laby;

public class Coordonnee {
    private int x,y;

    /**
     * Contructeur de Coordonnée
     * @param x coordonnée x
     * @param y coordonnée y
     */
    Coordonnee(int x,int y){
        this.x=x;
        this.y=y;
    }

    /**
     * getter du coordonnée x
     * @return le coordonnée x
     */
    public int getX(){
        return this.x;
    }

    /**
     * getter du coordonnée y
     * @return le coordonnée y
     */
    public int getY(){
        return this.y;
    }

    /**
     * setter de x
     * @param x nouvel coordonnée a set
     */
    public void setX(int x){
        this.x=x;
    }

    /**
     * setter de y
     * @param y nouvel coordonnée y
     */
    public void setY(int y){
        this.y=y;
    }

    public boolean estPresent(int x, int y){
        return this.x==x&&this.y==y;
    }

}
