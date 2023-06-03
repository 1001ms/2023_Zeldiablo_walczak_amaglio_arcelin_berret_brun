package gameLaby.laby;


import java.util.ArrayList;

/**
 * gere un personnage situe en x,y
 */
public class Serpent implements Element {

    /**
     * position du personnage
     */

    ArrayList<Coordonnee> corp;

    /**
     * constructeur
     *
     * @param dx position selon x
     * @param dy position selon y
     */
    public Serpent(int dx, int dy) {
        this.corp=new ArrayList<Coordonnee>();
        //ajout du corp
        for(int i =0;i<=4;i++){
            Coordonnee iCorp = new Coordonnee(dx,dy);
            corp.add(iCorp);
        }
    }

    public ArrayList<Coordonnee> getCorp(){
        return this.corp;
    }

    /**
     * @return position x du personnage
     */
    public int getX() {
        // getter
        return this.corp.get(0).getX();
    }

    /**
     * @return position y du personnage
     */
    public int getY() {
        //getter
        return this.corp.get(0).getY();
    }

    /**
     * @return position x du personnage
     */
    public void setX(int x) {
        // getter
        this.corp.get(0).setX(x);
    }

    /**
     * @return position y du personnage
     */
    public void setY(int y) {
        //getter
        this.corp.get(0).setY(y);
    }
}
