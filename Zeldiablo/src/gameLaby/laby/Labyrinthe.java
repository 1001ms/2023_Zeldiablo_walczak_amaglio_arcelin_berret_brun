package gameLaby.laby;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

/**
 * classe labyrinthe. represente un labyrinthe avec
 * <ul> des murs </ul>
 * <ul> un personnage (x,y) </ul>
 */
public class Labyrinthe {

    /**
     * Constantes char
     */
    public static final char MUR = 'X';
    public static final char MONSTRE = 'M';
    public static final char SERPENT = 'S';
    public static final char PJ = 'P';
    public static final char VIDE = '.';

    /**
     * constantes actions possibles
     */
    public static final String HAUT = "Haut";
    public static final String BAS = "Bas";
    public static final String GAUCHE = "Gauche";
    public static final String DROITE = "Droite";

    /**
     * attribut du personnage
     */
    public Aventurier pj;

    public Serpent serpent;

    /**
     * attribut du monstre
     */
    public Monstre monstre;

    /**
     * attribut Element
     */

    public ArrayList<Element> elements;

    /**
     * les murs du labyrinthe
     */
    public boolean[][] murs;

    /**
     * retourne la case suivante selon une actions
     *
     * @param x      case depart
     * @param y      case depart
     * @param action action effectuee
     * @return case suivante
     */
    static int[] getSuivant(int x, int y, String action) {
        switch (action) {
            case HAUT:
                // on monte une ligne
                x--;
                break;
            case BAS:
                // on descend une ligne
                x++;
                break;
            case DROITE:
                // on augmente colonne
                y++;
                break;
            case GAUCHE:
                // on augmente colonne
                y--;
                break;
            default:
                throw new Error("action inconnue");
        }
        return new int[]{x, y};
    }


    /**
     * charge le labyrinthe
     *
     * @param nom nom du fichier de labyrinthe
     * @return labyrinthe cree
     * @throws IOException probleme a la lecture / ouverture
     */
    public Labyrinthe(String nom) throws IOException {
        // ouvrir fichier
        FileReader fichier = new FileReader(nom);
        BufferedReader bfRead = new BufferedReader(fichier);

        int nbLignes, nbColonnes;
        // lecture nblignes
        nbLignes = Integer.parseInt(bfRead.readLine());
        // lecture nbcolonnes
        nbColonnes = Integer.parseInt(bfRead.readLine());

        // creation labyrinthe vide
        this.murs = new boolean[nbColonnes][nbLignes];
        this.pj = null;
        this.monstre=null;
        this.serpent=null;
        this.elements=new ArrayList<Element>();

        // lecture des cases
        String ligne = bfRead.readLine();

        // stocke les indices courants
        int numeroLigne = 0;

        // parcours le fichier
        while (ligne != null) {

            // parcours de la ligne
            for (int colonne = 0; colonne < ligne.length(); colonne++) {
                char c = ligne.charAt(colonne);
                switch (c) {
                    case MUR:
                        this.murs[colonne][numeroLigne] = true;
                        break;
                    case VIDE:
                        this.murs[colonne][numeroLigne] = false;
                        break;
                    case PJ:
                        // pas de mur
                        this.murs[colonne][numeroLigne] = false;
                        // ajoute PJ
                        this.pj = new Aventurier(colonne, numeroLigne);
                        this.elements.add(pj);
                        break;
                    case MONSTRE:
                        // pas de mur
                        this.murs[colonne][numeroLigne] = false;
                        // ajoute PJ
                        this.monstre = new Monstre(colonne, numeroLigne);
                        this.elements.add(monstre);
                        break;
                    case SERPENT:
                        // pas de mur
                        this.murs[colonne][numeroLigne] = false;
                        // ajoute PJ
                        this.serpent = new Serpent(colonne, numeroLigne);
                        this.elements.add(serpent);
                        break;

                    default:
                        throw new Error("caractere inconnu " + c);
                }
            }

            // lecture
            ligne = bfRead.readLine();
            numeroLigne++;
        }

        // ferme fichier
        bfRead.close();
    }


    /**
     * méthode elementPresent vérifie si un element est présent aux coordonnées x,y en paramètres
     * @param x coordonn yée x
     * @param y coordonnée
     * @return true si element present aux coordonnées entrées en param
     */
    public boolean elementPresent(int x, int y){
        boolean trouve = false;
        for(Element e : elements){
            if(e instanceof Serpent){//si c'est un serpent on doit vérifier la position de tout son corp
                Serpent s = (Serpent) e;
                for(int i =0;i<s.getCorp().size();i++){
                    int xserpent=s.getCorp().get(i).getX();
                    int yserpent=s.getCorp().get(i).getY();
                    if(xserpent==x&&yserpent==y)
                        trouve=true;
                }
            }else{ //sinon on vérifie seulement le x et y de l'element
                if(e.getX()==x&&e.getY()==y)
                    trouve=true;
            }

        }
        return trouve;
    }

    /**
     * deplace le personnage en fonction de l'action.
     * gere la collision avec les murs
     *
     * @param action une des actions possibles
     */
    public void deplacerPerso(String action) {
        // case courante
        int[] courante = {this.pj.getX(), this.pj.getY()};

        // calcule case suivante
        int[] suivante = getSuivant(courante[0], courante[1], action);

        // si c'est pas un mur, on effectue le deplacement
        if ((!this.murs[suivante[0]][suivante[1]])&&!elementPresent(suivante[0],suivante[1])) {
            // on met a jour personnage
            this.pj.setX(suivante[0]);
            this.pj.setY(suivante[1]);
        }
    }

    /**
     * méthode deplacerMonstre permet de déplacer le Monstre de manière aléatoire
     */
    public void deplacerMonstre() {
        String[] action = {HAUT,BAS,GAUCHE,DROITE};
        Random r = new Random();

        // case courante
        int[] courante = {this.monstre.getX(), this.monstre.getY()};

        // calcule case suivante
        int[] suivante = getSuivant(courante[0], courante[1], action[r.nextInt(action.length)]);

        // si c'est pas un mur, on effectue le deplacement
        if ((!this.murs[suivante[0]][suivante[1]])&&!elementPresent(suivante[0],suivante[1])){
            // on met a jour personnage
            this.monstre.setX(suivante[0]);
            this.monstre.setY(suivante[1]);
        }
    }

    /**
     * méthode deplacerSerpent permet de déplacer le Monstre de manière aléatoire
     */
    public void deplacerSerpent() {
        // Obtient les actions possibles
        String[] actions = {HAUT, BAS, GAUCHE, DROITE};
        Random random = new Random();

        // Récupère la position courante de la tête du serpent
        int xTete = this.serpent.getCorp().get(0).getX();
        int yTete = this.serpent.getCorp().get(0).getY();

        // Calcule la case suivante
        String action = actions[random.nextInt(actions.length)];
        int[] suivante = getSuivant(xTete, yTete, action);

        // Vérifie si la case suivante est valide
        if ((!this.murs[suivante[0]][suivante[1]])&&!elementPresent(suivante[0],suivante[1])) {

            boolean peutAvancer=true;
            for (int i = 1; i < serpent.getCorp().size(); i++) {
                int ix = serpent.getCorp().get(i).getX();
                int iy = serpent.getCorp().get(i).getY();
                if (suivante[0] == ix && suivante[1] == iy) {
                    peutAvancer = false;
                    break; // Sortir de la boucle dès qu'une collision est détectée
                }
            }

            //**** NOTE
            // pour le rendre intelligent faire un tab des actions dès qu'une action est faite
            //mais qu'il ne peux pas ou delete l'action du tableau
            //si tableau vide on skip
            //****

            if(peutAvancer) {
                    // Met à jour la position de la tête du serpent
                    this.serpent.setX(suivante[0]);
                    this.serpent.setY(suivante[1]);
                }

            // Met à jour la position des autres parties du corps
            for (int i = this.serpent.getCorp().size() - 1; i > 0; i--) {
                int xPrecedent = this.serpent.getCorp().get(i - 1).getX();
                int yPrecedent = this.serpent.getCorp().get(i - 1).getY();
                this.serpent.getCorp().get(i).setX(xPrecedent);
                this.serpent.getCorp().get(i).setY(yPrecedent);
            }
        }
    }


    /**
     * jamais fini
     *
     * @return fin du jeu
     */
    public boolean etreFini() {
        return false;
    }

    // ##################################
    // GETTER
    // ##################################

    /**
     * return taille selon Y
     *
     * @return
     */
    public int getLengthY() {
        return murs[0].length;
    }

    /**
     * return taille selon X
     *
     * @return
     */
    public int getLength() {
        return murs.length;
    }

    /**
     * return mur en (i,j)
     * @param x
     * @param y
     * @return
     */
    public boolean getMur(int x, int y) {
        // utilise le tableau de boolean
        return this.murs[x][y];
    }
}
