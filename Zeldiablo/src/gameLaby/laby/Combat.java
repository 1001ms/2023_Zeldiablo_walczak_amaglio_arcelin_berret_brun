package gameLaby.laby;

public class Combat {
    /**
     * attribut de type personnage qui représente l'adeversaire du pj
     */
    public Personnage adversaire;

    /**
     * attribut de type Aventurier qui représente le pj
     */
    public Aventurier pj;

    /**
     * attribut de type boolean est a true lorsque le combats et en cours et false a la fin du combats
     */
    public boolean etatCombat;

    /**
     * attribut de type boolean est a true si c'est aux tours du personnage de jouer
     */
    public boolean toursPj;

    /**
     * Constructeur qui attribut le personnage a pj et met le tours du pj a true
     * @param e Un element personnage qui peut représenter le pj ou l'adversaire
     * @param j Un element personnage qui peut représenter le pj ou l'adversaire
     */
    public Combat(Personnage e, Personnage j){
        etatCombat = true;
        if(e instanceof Aventurier){
            pj = (Aventurier) e;
            adversaire =  j;
        }else{
            pj = (Aventurier) j;
            adversaire =  e;
        }
        toursPj = true;

    }

    /**
     * méthode gérant l'attaque du personnage et de l'adversaire à partir d'un codeAttaque
     * @param codeAttaque code (compris entre 1 et 4)  représentant quelle attaque a choisit l'utilisateur et où 4 represente l'attaque de l'adversaire
     */
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

    /**
     * getteur retournant l'aventurier qui a combatue
     * @return pj
     */
    public Aventurier getPj() {
        return pj;
    }
}
