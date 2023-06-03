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
    public static final char ESCAPE1 = 'A';
    public static final char ESCAPE2 = 'B';
    public static final char ESCAPE3 = 'C';
    public static final char ESCAPE4 = 'D';
    public static final char ESCAPE5 = 'E';
    public static final char ESCAPE6 = 'F';
    public static final char ESCAPE7 = 'G';
    public static final char ESCAPE8 = 'H';
    public static final char ESCAPE9 = 'I';
    public static final char DEPOT = '$';
    public static final char CAISSE = '#';

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

    public ArrayList<Serpent> serpents;

    /**
     * attribut du monstre
     */
    public ArrayList<Monstre> monstres;

    /**
     * attribut Element
     */

    public ArrayList<Element> elements;

    /**
     * attribut des échappatoires
     */
    public Echappatoire escapes;

    /**
     * les murs du labyrinthe
     */
    public boolean[][] murs;
    /**
     * niveau du labyrinthe
     */
    public int nvLaby;

    /**
     * liste depots du labyrinthe
     */
    public ListeElements depots;

    /**
     * liste caisse du labyrinthe
     */

    public ListeElements caisses;

    public boolean combat;

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
    public Labyrinthe(String nom,int nv,Echappatoire esc) throws IOException {
        this.escapes=esc;
        // ouvrir fichier
        FileReader fichier = new FileReader(nom);
        BufferedReader bfRead = new BufferedReader(fichier);
        int nbLignes, nbColonnes;
        this.nvLaby=nv;
        // lecture nblignes
        nbLignes = Integer.parseInt(bfRead.readLine());
        // lecture nbcolonnes
        nbColonnes = Integer.parseInt(bfRead.readLine());

        // creation labyrinthe vide
        this.murs = new boolean[nbColonnes][nbLignes];
        this.pj = null;
        this.monstres = new ArrayList<Monstre>();
        this.serpents = new ArrayList<Serpent>();
        this.elements = new ArrayList<Element>();
        this.caisses = new ListeElements();
        this.depots = new ListeElements();

        // lecture des cases
        String ligne = bfRead.readLine();

        // stocke les indices courants
        int numeroLigne = 0;

        int m = 0;
        int s = 0;
        int d = 0;
        int ca = 0;
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
                    case ESCAPE1:
                        // pas de mur
                        this.murs[colonne][numeroLigne] = false;
                        this.escapes.add(colonne, numeroLigne,this.nvLaby,'A');
                        break;
                    case ESCAPE2:
                        // pas de mur
                        this.murs[colonne][numeroLigne] = false;
                        this.escapes.add(colonne, numeroLigne,this.nvLaby,'B');
                        break;
                    case ESCAPE3:
                        // pas de mur
                        this.murs[colonne][numeroLigne] = false;
                        this.escapes.add(colonne, numeroLigne,this.nvLaby,'C');
                        break;
                    case ESCAPE4:
                        // pas de mur
                        this.murs[colonne][numeroLigne] = false;
                        this.escapes.add(colonne, numeroLigne,this.nvLaby,'D');
                        break;
                    case ESCAPE5:
                        // pas de mur
                        this.murs[colonne][numeroLigne] = false;
                        this.escapes.add(colonne, numeroLigne,this.nvLaby,'E');
                        break;
                    case ESCAPE6:
                        // pas de mur
                        this.murs[colonne][numeroLigne] = false;
                        this.escapes.add(colonne, numeroLigne,this.nvLaby,'F');
                        break;
                    case ESCAPE7:
                        // pas de mur
                        this.murs[colonne][numeroLigne] = false;
                        this.escapes.add(colonne, numeroLigne,this.nvLaby,'G');
                        break;
                    case ESCAPE8:
                        // pas de mur
                        this.murs[colonne][numeroLigne] = false;
                        this.escapes.add(colonne, numeroLigne,this.nvLaby,'H');
                        break;
                    case ESCAPE9:
                        // pas de mur
                        this.murs[colonne][numeroLigne] = false;
                        this.escapes.add(colonne, numeroLigne,this.nvLaby,'I');
                        break;
                    case MONSTRE:
                        // pas de mur
                        this.murs[colonne][numeroLigne] = false;
                        // ajoute PJ
                        this.monstres.add(new Monstre(colonne, numeroLigne));
                        this.elements.add(monstres.get(m));
                        m++;
                        break;
                    case SERPENT:
                        // pas de mur
                        this.murs[colonne][numeroLigne] = false;
                        // ajoute PJ
                        this.serpents.add(new Serpent(colonne, numeroLigne));
                        this.elements.add(serpents.get(s));
                        s++;
                        break;
                    case DEPOT:
                        // pas de mur
                        this.murs[colonne][numeroLigne] = false;
                        // ajoute PJ
                        depots.ajouter(new Depot(colonne, numeroLigne));
                        this.elements.add(depots.getElementByIndice(d));
                        d++;
                        break;
                    case CAISSE:
                        // pas de mur
                        this.murs[colonne][numeroLigne] = false;
                        // ajoute PJ
                        caisses.ajouter(new Depot(colonne, numeroLigne));
                        this.elements.add(caisses.getElementByIndice(ca));
                        ca++;
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
     *
     * @param x coordonn yée x
     * @param y coordonnée
     * @return true si element present aux coordonnées entrées en param
     */
    public boolean elementPresent(int x, int y) {
        boolean trouve = false;
        for (Element e : elements) {
            if (e instanceof Serpent) {//si c'est un serpent on doit vérifier la position de tout son corp
                Serpent s = (Serpent) e;
                for (int i = 0; i < s.getCorp().size(); i++) {
                    int xserpent = s.getCorp().get(i).getX();
                    int yserpent = s.getCorp().get(i).getY();
                    if (xserpent == x && yserpent == y)
                        trouve = true;
                }
            } else { //sinon on vérifie seulement le x et y de l'element
                if (e.getX() == x && e.getY() == y)
                    trouve = true;
            }

        }
        return trouve;
    }

    /**
     * méthode elementPresent vérifie si un element est présent aux coordonnées x,y en paramètres
     *
     * @param x coordonn yée x
     * @param y coordonnée
     * @return true si element present aux coordonnées entrées en param
     */
    public Element elementPresentObject(int x, int y) {
        Element res = null;
        for (Element e : elements) {
            if (e instanceof Serpent) {//si c'est un serpent on doit vérifier la position de tout son corp
                Serpent s = (Serpent) e;
                for (int i = 0; i < s.getCorp().size(); i++) {
                    int xserpent = s.getCorp().get(i).getX();
                    int yserpent = s.getCorp().get(i).getY();
                    if (xserpent == x && yserpent == y)
                        res = e;
                }
            } else { //sinon on vérifie seulement le x et y de l'element
                if (e.getX() == x && e.getY() == y)
                    res = e;
            }

        }
        return res;
    }

    /**
     * deplace le personnage en fonction de l'action.
     * gere la collision avec les murs
     *
     * @param action une des actions possibles
     */
    public void deplacerPerso(String action) {
        //on récupère les coordonnées du personnage
        int x = this.pj.getX();
        int y = this.pj.getY();
        //on récupère les coordonnées générer par l'action voulu
        int[] suivant = getSuivant(x, y, action);

        //on attribue x et y à des variables temporaire si jamais l'action n'est pas possible
        int tmpX = x;
        int tmpY = y;

        // x et y prennent les valeurs de générer par getSuivant
        x = suivant[0];
        y = suivant[1];

        if ((elementPresentObject(x, y) instanceof Personnage) || (elementPresentObject(x, y) instanceof Serpent)) {  //cas si il y a un mur, alors personnage de bouge pas, il prend les valeurs sauvegarder dans les variables tmp
                combat = true;
        } else if (this.murs[x][y] || (elementPresentObject(x, y) instanceof Caisse)) {
            this.pj.setX(tmpX);
            this.pj.setY(tmpY);
        } else {
            boolean bool = false; //boolean qui permet de savoir si le perso se déplace ou non (true si il y a un obstacle)
            if (this.caisses.getElement(x, y) != null) { //si il y a une caisse

                Element caisse = this.caisses.getElement(x, y); //on récupère l'élement aux coordonnées (x,y)
                int indiceElem = this.caisses.indiceElement(x, y); //on récupère l'indice de cette élément dans la liste (this.caisses)

                //De même que pour personnage on sauvegarde les valeurs initial si l'action ne peut se dérouler correctement
                int xElemTmp = this.caisses.getElement(x, y).getX();
                int yElemTmp = this.caisses.getElement(x, y).getY();

                //on récupère les coordonnées générer par getSuivant
                int[] suivantElem = getSuivant(xElemTmp, yElemTmp, action);

                //on initialise les variables xElem et yElem avec les valeurs générer par getSuivant
                int xElem = suivantElem[0];
                int yElem = suivantElem[1];

                if (this.murs[xElem][yElem] || this.caisses.getElement(xElem, yElem) != null || elementPresentObject(xElem, yElem) instanceof Personnage) {
                    //si après la caisse il y a un mur OU une caisse alors la caisse ne se déplace aps
                    caisse.setX(xElemTmp);
                    caisse.setY(yElemTmp);
                    bool = true; //le personnage ne peut pas bouger il y a un obstacle donc il gardes ses valeurs initial
                } else {
                    //sinon la caisse se déplace
                    caisse.setX(xElem);
                    caisse.setY(yElem);
                }

                //on met à jour les coordonnées de la caisse déplacer
                this.caisses.setListeElements(indiceElem, caisse);
            }
            if (bool) { //si bool=true alors le personnage ne peut pas avancer donc il garde ses valeurs initial
                this.pj.setX(tmpX);
                this.pj.setY(tmpY);
            } else { //sinon il se déplace
                this.pj.setX(x);
                this.pj.setY(y);
            }
        }
        caisseSurTrou();
        aventurierDansTrou();
    }

    /**
     * méthode caisseSurTrou supprime caisse et depot lorsque la caisse est sur le depot
     */
    public void caisseSurTrou() {
        for (int i = 0; i < this.caisses.getTaille(); i++) {
            Element caisse = this.caisses.getElementByIndice(i);
            for (int j = 0; j < this.depots.getTaille(); j++) {
                Element depot = this.depots.getElementByIndice(j);
                if (caisse.getX() == depot.getX() && caisse.getY() == depot.getY()) {
                    // Les coordonnées de la caisse et du dépôt sont identiques, on les supprime des deux listes
                    this.caisses.supprimerElement(i);
                    this.depots.supprimerElement(j);
                    i--; // On décrémente i car un élément a été supprimé de la liste des caisses
                    break; // On sort de la boucle interne car on a trouvé une correspondance
                }
            }
        }
    }

    /**
     * méthode qui permet de savoir si l'aventurier est dans le trou
     * @return true si l'aventurier est sur le trou
     */
    public boolean aventurierDansTrou() {
        boolean res = false;
        int xPerso = this.pj.getX();
        int yPerso = this.pj.getY();

        for (int i = 0; i < this.depots.getTaille(); i++) {
            Element depot = this.depots.getElementByIndice(i);
            int xDepot = depot.getX();
            int yDepot = depot.getY();

            if (xPerso == xDepot && yPerso == yDepot) {
                // Les coordonnées du personnage correspondent à celles du dépôt
                System.out.println("Beh aled je meurs en fait"); // Le personnage est dans un trou
                this.pj = new Aventurier(-1, -1);
                res = true;
            }
        }
        return res;
    }


    /**
     * méthode deplacerMonstre permet de déplacer le Monstre de manière aléatoire
     */
    public void deplacerMonstre() {
        String[] action = {HAUT, BAS, GAUCHE, DROITE};
        Random r = new Random();
        for (Monstre m : monstres) {
            // case courante
            int[] courante = {m.getX(), m.getY()};

            // calcule case suivante
            int[] suivante = getSuivant(courante[0], courante[1], action[r.nextInt(action.length)]);

            // si c'est pas un mur, on effectue le deplacement
            if ((!this.murs[suivante[0]][suivante[1]]) && !elementPresent(suivante[0], suivante[1])) {
                // on met a jour personnage
                m.setX(suivante[0]);
                m.setY(suivante[1]);
            }
        }
    }

    /**
     * méthode deplacerSerpent permet de déplacer le Monstre de manière aléatoire
     */
    public void deplacerSerpent() {
        // Obtient les actions possibles
        String[] actions = {HAUT, BAS, GAUCHE, DROITE};
        Random random = new Random();
        for (Serpent s : serpents) {
            // Récupère la position courante de la tête du serpent
            int xTete = s.getCorp().get(0).getX();
            int yTete = s.getCorp().get(0).getY();

            // Calcule la case suivante
            String action = actions[random.nextInt(actions.length)];
            int[] suivante = getSuivant(xTete, yTete, action);

            // Vérifie si la case suivante est valide
            if ((!this.murs[suivante[0]][suivante[1]]) && !(elementPresent(suivante[0], suivante[1]))) {

                boolean peutAvancer = true;
                for (int i = 1; i < s.getCorp().size(); i++) {
                    int ix = s.getCorp().get(i).getX();
                    int iy = s.getCorp().get(i).getY();
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

                if (peutAvancer) {
                    // Met à jour la position de la tête du serpent
                    s.setX(suivante[0]);
                    s.setY(suivante[1]);
                }

                // Met à jour la position des autres parties du corps
                for (int i = s.getCorp().size() - 1; i > 0; i--) {
                    int xPrecedent = s.getCorp().get(i - 1).getX();
                    int yPrecedent = s.getCorp().get(i - 1).getY();
                    s.getCorp().get(i).setX(xPrecedent);
                    s.getCorp().get(i).setY(yPrecedent);
                }
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
     *
     * @param x
     * @param y
     * @return
     */
    public boolean getMur(int x, int y) {
        // utilise le tableau de boolean
        return this.murs[x][y];
    }

    public int playerInEscape(){
        int x = this.pj.getX();
        int y = this.pj.getY();
        for (int i = 0; i <escapes.getEscpNv(nvLaby).size(); i++) {
            if (escapes.getEscpNv(nvLaby).get(i)[0] == x && escapes.getEscpNv(nvLaby).get(i)[1] == y) {
                System.out.println(escapes.indexToType(i));
                return i;
            }
        }
        return -1;
    }



}
