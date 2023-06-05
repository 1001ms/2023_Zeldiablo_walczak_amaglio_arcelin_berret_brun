package gameLaby.laby;

public abstract class  Element {

private Coordonnee coordonnee;

/**
 * constructeur d'entite
 * @param x coordonnée x
 * @param y coordonnée y
 */
public Element(int x, int y){
        this.coordonnee=new Coordonnee(x,y);
        }
/**
 * getter de X
 * @return le coordonnée X de la torche
 */
public int getX() {
        return coordonnee.getX();
        }

/**
 * getter de Y
 * @return le coordonnée Y de la torche
 */
public int getY() {
        return coordonnee.getY();
        }

/**
 * setter du X de la torche
 * @param x met à jour le x
 */
public void setX(int x) {
        coordonnee.setX(x);
        }
/**
 * setter du Y de la torche
 * @param y met à jour le y
 */
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
