package gameLaby.laby;


import java.util.ArrayList;

/**
 * gere un personnage situe en x,y
 */
public class Serpent extends Personnage {
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
    public Serpent(int dx, int dy, int HP) {
        super(dx,dy,HP);
        this.corp=new ArrayList<Coordonnee>();
        //ajout du corp
        for(int i =0;i<=4;i++){
            Coordonnee iCorp = new Coordonnee(dx,dy);
            corp.add(iCorp);
        }
    }

    /**
     * méthode getCorp
     * @return l'ArrayList<Coordonnee> qui reprsénte le corp du serpent
     */
    public ArrayList<Coordonnee> getCorp(){
        return this.corp;
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
