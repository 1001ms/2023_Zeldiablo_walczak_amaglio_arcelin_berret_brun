package gameLaby.laby;

import java.util.ArrayList;

public class Echappatoire {
    private ArrayList<int[]> escpNv1;
    private ArrayList<int[]> escpNv2;
    private ArrayList<int[]> escpNv3;


    public Echappatoire(){
        this.escpNv1 = new ArrayList<>(9);
        this.escpNv2 = new ArrayList<>(9);
        this.escpNv3 = new ArrayList<>(9);

        for (int i = 0; i < 9; i++) {
            this.escpNv1.add(new int[]{-1, -1});
        }
        for (int i = 0; i < 9; i++) {
            this.escpNv2.add(new int[]{-1, -1});
        }
        for (int i = 0; i < 9; i++) {
            this.escpNv3.add(new int[]{-1, -1});
        }
    }


    public void afficher() {
        System.out.println("Niveau 0 :");
        for (int i = 0; i < escpNv1.size(); i++) {
            System.out.print("Échappatoire " + (char)('A' + i) + ": ");
            int[] coord = escpNv1.get(i);
            System.out.println("x = " + coord[0] + ", y = " + coord[1]);
        }

        System.out.println("Niveau 1 :");
        for (int i = 0; i < escpNv2.size(); i++) {
            System.out.print("Échappatoire " + (char)('A' + i) + ": ");
            int[] coord = escpNv2.get(i);
            System.out.println("x = " + coord[0] + ", y = " + coord[1]);
        }

        System.out.println("Niveau 2 :");
        for (int i = 0; i < escpNv3.size(); i++) {
            System.out.print("Échappatoire " + (char)('A' + i) + ": ");
            int[] coord = escpNv3.get(i);
            System.out.println("x = " + coord[0] + ", y = " + coord[1]);
        }
    }

    /**
     * methode determinerNiveau de la classe Echappatoire
     * qui permet de determiner l etage (niveau) du labyrinthe
     * dans lequel l escalier se trouve
     * @param x coordonnees en x
     * @param y coordonnees en y
     * @param niveau niveau du labyrinthe (etage)
     */
    public void determinerNiv(int x, int y, int niveau, char type) {
        switch (niveau) {
            case 0:
                this.escpNv1.set(typeToIndex(type), new int[]{x,y});
                break;
            case 1:
                this.escpNv2.set(typeToIndex(type), new int[]{x,y});
                break;
            case 2:
                this.escpNv3.set(typeToIndex(type), new int[]{x,y});
                break;
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
     * methode identifierEchap de la classe Echappatoire
     * qui permet d identifier l escalier couple a celui dont
     * les informations transmises en parametres (niveau et type)
     * @param niveau represente le niveau du labyrinthe
     * @param type represente le code de l escalier
     * @return l escalier faisant la transition entre les niveaux
     */
    public int[] identifierEchap(int niveau, char type) {
        System.out.println("niveau = " + niveau + ", type = " + type);
        int[] res = new int[]{-1, -1};
        switch (niveau) {
            case 0:
                if ((this.escpNv2.get(this.typeToIndex(type))[0]!=-1)&&(this.escpNv2.get(this.typeToIndex(type))[1] != -1)) {
                    res[0] = 1; res[1] = this.typeToIndex(type);
                }
                else {
                    res[0] = 2; res[1] = this.typeToIndex(type);
                }
                break;
            case 1:
                if ((this.escpNv1.get(this.typeToIndex(type))[0]!=-1)&&(this.escpNv1.get(this.typeToIndex(type))[1]!=-1)) {
                    res[0] = 0; res[1] = this.typeToIndex(type);
                }
                else {
                    res[0] = 2; res[1] = this.typeToIndex(type);
                }
                break;
            case 2:
                if((this.escpNv1.get(this.typeToIndex(type))[0]!=-1)&&(this.escpNv1.get(this.typeToIndex(type))[1]!=-1)) {
                    res[0] = 0; res[1] = this.typeToIndex(type);
                }
                else {
                    res[0] = 1; res[1] = this.typeToIndex(type);
                }
                break;
        }
        System.out.println("niveau = " + res[0] + ", type = " + this.indexToType(res[1]));
        return res;
    }

    /**
     * methode add de la classe Echappatoire
     * permet d ajouter les coordonnees aux listes en attributs
     * en leur attribuant un identifiant
     * @param x coordonnees en x
     * @param y coordonnees en y
     * @param niveau niveau du labyrinthe (etage)
     * @param type identifiant d une paire d escaliers
     */
    public void add(int x, int y, int niveau, char type){
        switch (type) {
            case 'A': this.determinerNiv(x, y, niveau,type); break;
            case 'B': this.determinerNiv(x, y, niveau,type); break;
            case 'C': this.determinerNiv(x, y, niveau,type); break;
            case 'D': this.determinerNiv(x, y, niveau,type); break;
            case 'E': this.determinerNiv(x, y, niveau,type); break;
            case 'F': this.determinerNiv(x, y, niveau,type); break;
            case 'G': this.determinerNiv(x, y, niveau,type); break;
            case 'H': this.determinerNiv(x, y, niveau,type); break;
            case 'I': this.determinerNiv(x, y, niveau,type); break;
        }
    }

    /**
     * methode getEscpNv1 de la classe Echappatoire
     * @return l attribut escpNv1 de la classe Echappatoire
     */
    public ArrayList<int[]> getEscpNv1() {
        return this.escpNv1;
    }

    /**
     * methode getEscpNv2 de la classe Echappatoire
     * @return l attribut escpNv2 de la classe Echappatoire
     */
    public ArrayList<int[]> getEscpNv(int nv) {
        ArrayList<int[]>[] tab = new ArrayList[] {this.escpNv1, this.escpNv2, this.escpNv3};
        return tab[nv];
    }

    /**
     * methode getEscpNv3 de la classe Echappatoire
     * @return l attribut escpNv3 de la classe Echappatoire
     */
    public ArrayList<int[]> getEscpNv3() {
        return this.escpNv3;
    }
}