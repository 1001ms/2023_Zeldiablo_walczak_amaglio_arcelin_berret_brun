package gameLaby.laby;

public class Combat {
    public Personnage adversaire;
    public Aventurier pj;

    public boolean toursPj;

    public Combat(Element e, Element j){
        if(e instanceof Aventurier){
            pj = (Aventurier) e;
            adversaire = (Personnage) j;
        }else{
            pj = (Aventurier) j;
            adversaire = (Personnage) e;
        }
        toursPj = true;
    }

    public void attaque(){
        if(toursPj == true){
            adversaire.setHP(adversaire.getHP()-10);
        }
    }

    public boolean derouléeCombat(){
        while ((pj.getHP() > 0)&&(adversaire.getHP() >0 )){
        }
        return true;
    }
}
