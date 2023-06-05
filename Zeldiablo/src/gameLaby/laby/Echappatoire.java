package gameLaby.laby;

import java.util.ArrayList;
import java.util.EnumMap;

public class Echappatoire {

    /**
     * attribut escpNv1 de la classe Echappatoire
     * represente la liste des echappatoires du premier etage
     */
    private ArrayList<int[]> escpNv1;

    /**
     * attribut escpNv2 de la classe Echappatoire
     * represente la liste des echappatoires du premier etage
     */
    private ArrayList<int[]> escpNv2;

    /**
     * attribut escpNv3 de la classe Echappatoire
     * represente la liste des echappatoires du premier etage
     */
    private ArrayList<int[]> escpNv3;


    /**
     * constructeur vide qui cree un objet
     * de type Echappatoire
     */
    public Echappatoire() {
        this.escpNv1 = new ArrayList<>(9);
        this.escpNv2 = new ArrayList<>(9);
        this.escpNv3 = new ArrayList<>(9);
        for (int i = 0; i < 9; i++) this.escpNv1.add(new int[]{-1, -1, -1});
        for (int i = 0; i < 9; i++) this.escpNv2.add(new int[]{-1, -1, -1});
        for (int i = 0; i < 9; i++) this.escpNv3.add(new int[]{-1, -1, -1});
    }


    /**
     * methode afficher de la classe Echappatoire
     * permet d afficher dans la console les coordonnees des
     * echappatoires a chacun des niveaux du labyrinthe
     */
    public void afficher() {
        System.out.println("Niveau 0 :");
        for (int i = 0; i < escpNv1.size(); i++) {
            System.out.print("Échappatoire " + (char)('A' + i) + ": ");
            int[] coord = escpNv1.get(i);
            System.out.println("x = " + coord[0] + ", y = " + coord[1] + ", numéro map = " + coord[2]);
        }
        System.out.println("Niveau 1 :");
        for (int i = 0; i < escpNv2.size(); i++) {
            System.out.print("Échappatoire " + (char)('A' + i) + ": ");
            int[] coord = escpNv2.get(i);
            System.out.println("x = " + coord[0] + ", y = " + coord[1] + ", numéro map = " + coord[2]);
        }
        System.out.println("Niveau 2 :");
        for (int i = 0; i < escpNv3.size(); i++) {
            System.out.print("Échappatoire " + (char)('A' + i) + ": ");
            int[] coord = escpNv3.get(i);
            System.out.println("x = " + coord[0] + ", y = " + coord[1] + ", numéro map = " + coord[2]);
        }
    }

    /**
     * methode typeToIndex de la classe Echappatoire
     * qui permet de connaitre l index d un code d escalier
     * parmi les attributs
     * @param type represente le code de l escalier
     * @return l index du code de l escalier
     */
    public int typeToIndex(char type) {
        int res = -1;
        switch (type) {
            case 'A': res = 0; break;
            case 'B': res = 1; break;
            case 'C': res = 2; break;
            case 'D': res = 3; break;
            case 'E': res = 4; break;
            case 'F': res = 5; break;
            case 'G': res = 6; break;
            case 'H': res = 7; break;
            case 'I': res = 8; break;
        }
        return res;
    }

    /**
     * methode indexToType de la classe Echappatoire
     * @param index represente l index du code de l escalier
     * @return le code de l escalier
     */
    public char indexToType(int index) {
        char res = ' ';
        switch (index) {
            case 0: res = 'A'; break;
            case 1: res = 'B'; break;
            case 2: res = 'C'; break;
            case 3: res = 'D'; break;
            case 4: res = 'E'; break;
            case 5: res = 'F'; break;
            case 6: res = 'G'; break;
            case 7: res = 'H'; break;
            case 8: res = 'I'; break;
        }
        return res;
    }

    /**
     * methode determinerNiveau de la classe Echappatoire
     * qui permet de determiner l etage (niveau) du labyrinthe
     * dans lequel l escalier se trouve
     * @param x coordonnees en x
     * @param y coordonnees en y
     * @param niveau niveau du labyrinthe (etage)
     */
    public void determinerNiv(int x, int y, int nMap, int niveau, char type) {
        switch (niveau) {
            case 0:
                this.escpNv1.set(typeToIndex(type), new int[]{x,y,nMap});
                break;
            case 1:
                this.escpNv2.set(typeToIndex(type), new int[]{x,y,nMap});
                break;
            case 2:
                this.escpNv3.set(typeToIndex(type), new int[]{x,y,nMap});
                break;
        }
    }

    /**
     * methode identifierEchap de la classe Echappatoire
     * qui permet d identifier l escalier couple a celui dont
     * les informations transmises en parametres (niveau et type)
     * @param niveau represente le niveau du labyrinthe
     * @param type represente le code de l escalier
     * @return l escalier faisant la transition entre les niveaux
     */
    public int[] identifierEchap(int niveau, char type, int nMap) {
        System.out.println("niveau = " + niveau + ", type = " + type + ", nMap = " + nMap);
        int[] res = new int[]{-1, -1, -1};
        switch (niveau) {
            case 0:
                if ((this.escpNv2.get(this.typeToIndex(type))[0]!=-1)&&(this.escpNv2.get(this.typeToIndex(type))[1] != -1)) {
                    res[0] = 1; res[1] = this.typeToIndex(type); res[2] = 0;
                }
                else if ((this.escpNv3.get(this.typeToIndex(type))[0]!=-1)&&(this.escpNv3.get(this.typeToIndex(type))[1] != -1)){
                    res[0] = 2; res[1] = this.typeToIndex(type); res[2] = 0;
                } else {
                    res[0] = 0; res[1] = this.typeToIndex(type);
                    if (nMap == 0) res[2] = 1;
                    else res[2] = 0;
                }
                break;
            case 1:
                if ((this.escpNv1.get(this.typeToIndex(type))[0]!=-1)&&(this.escpNv1.get(this.typeToIndex(type))[1]!=-1)) {
                    res[0] = 0; res[1] = this.typeToIndex(type); res[2] = 0;
                }
                else if ((this.escpNv3.get(this.typeToIndex(type))[0]!=-1)&&(this.escpNv3.get(this.typeToIndex(type))[1]!=-1)){
                    res[0] = 2; res[1] = this.typeToIndex(type); res[2] = 0;
                } else {
                    res[0] = 1; res[1] = this.typeToIndex(type);
                    if (nMap == 0) res[2] = 1;
                    else res[2] = 0;
                }
                break;
            case 2:
                if((this.escpNv1.get(this.typeToIndex(type))[0]!=-1)&&(this.escpNv1.get(this.typeToIndex(type))[1]!=-1)) {
                    res[0] = 0; res[1] = this.typeToIndex(type); res[2] = 0;
                }
                else if ((this.escpNv2.get(this.typeToIndex(type))[0]!=-1)&&(this.escpNv2.get(this.typeToIndex(type))[1]!=-1)){
                    res[0] = 1; res[1] = this.typeToIndex(type); res[2] = 0;
                }
                else {
                    res[0] = 2; res[1] = this.typeToIndex(type);
                    if (nMap == 0) res[2] = 1;
                    else res[2] = 0;
                }
                break;
        }
        System.out.println("niveau = " + res[0] + ", type = " + this.indexToType(res[1]) + ", nMap = " + res[2]);
        return res;
    }

    /**
     * methode add de la classe Echappatoire
     * permet d ajouter les coordonnees aux listes en attributs
     * en leur attribuant un identifiant
     * @param x coordonnees en x
     * @param y coordonnees en y
     * @param nMap represente le numero d une piece au sein d un etage
     * @param niveau niveau du labyrinthe (etage)
     * @param type identifiant d une paire d escaliers
     */
    public void add(int x, int y, int nMap, int niveau, char type){
        switch (type) {
            case 'A': this.determinerNiv(x, y, nMap, niveau,type); break;
            case 'B': this.determinerNiv(x, y, nMap, niveau,type); break;
            case 'C': this.determinerNiv(x, y, nMap, niveau,type); break;
            case 'D': this.determinerNiv(x, y, nMap, niveau,type); break;
            case 'E': this.determinerNiv(x, y, nMap, niveau,type); break;
            case 'F': this.determinerNiv(x, y, nMap, niveau,type); break;
            case 'G': this.determinerNiv(x, y, nMap, niveau,type); break;
            case 'H': this.determinerNiv(x, y, nMap, niveau,type); break;
            case 'I': this.determinerNiv(x, y, nMap, niveau,type); break;
        }
    }

    /**
     * methode getEscpNv2 de la classe Echappatoire
     * @return l attribut escpNv2 de la classe Echappatoire
     */
    public ArrayList<int[]> getEscpNv(int nv) {
        ArrayList<int[]>[] tab = new ArrayList[] {this.escpNv1, this.escpNv2, this.escpNv3};
        return tab[nv];
    }
}