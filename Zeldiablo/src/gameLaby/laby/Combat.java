package gameLaby.laby;

public class Combat {
    public Personnage adversaire;
    public Aventurier pj;

    public boolean etatCombat;
    public boolean toursPj;

    public Combat(Element e, Element j){
        etatCombat = true;
        if(e instanceof Aventurier){
            pj = (Aventurier) e;
            adversaire = (Personnage) j;
        }else{
            pj = (Aventurier) j;
            adversaire = (Personnage) e;
        }
        toursPj = true;

    }

    public void attaque(int codeAttaque){
        if((pj.getHP() > 0)&&(adversaire.getHP() > 0 )){

        if (toursPj == true){
            switch (codeAttaque){
                case 1:
                    adversaire.setHP(adversaire.getHP()-10);
                    toursPj = false;
                    break;
                case 2:
                    adversaire.setHP(adversaire.getHP()-50);
                    toursPj = false;
                    break;
                case 3:
                    pj.setHP(pj.getHP()+20);
                    toursPj = false;
                    break;
                default:
                    break;
            }
        }else{
           pj.setHP(pj.getHP()-10);
            toursPj = true;
        }
        }else{
            etatCombat = false;
        }
    }
}
