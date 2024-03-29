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

    public static final char TORCHE = 'T';
    public static final char FANTOME = '&';
    public static final char TRESOR = '+';

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

    public ArrayList<Personnage> personnages;

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
     * numéro de map
     */
    public int nMap;

    /**
     * liste depots du labyrinthe
     */
    public ListeElements depots;

    /**
     * liste caisse du labyrinthe
     */

    public ListeElements caisses;

    public ArrayList<Torche> torches;

    public ArrayList<Fantome> fantomes;

    public Combat combat;

    public int[]tailleMax;

    public Personnage monstreEnCombat;

    public ArrayList<Tresor> tresors;

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
    public Labyrinthe(String nom,int nv,Echappatoire esc, int nMap) throws IOException {
        this.nMap = nMap;
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
        this.monstreEnCombat=null;
        this.monstres = new ArrayList<Monstre>();
        this.serpents = new ArrayList<Serpent>();
        this.personnages = new ArrayList<Personnage>();
        this.caisses = new ListeElements();
        this.depots = new ListeElements();
        this.torches=new ArrayList<Torche>();
        this.fantomes=new ArrayList<Fantome>();
        this.tresors=new ArrayList<Tresor>();

        // lecture des cases
        String ligne = bfRead.readLine();

        // stocke les indices courants
        int numeroLigne = 0;

        int m = 0;
        int s = 0;
        int f=0;
        // parcours le fichier
        while (ligne != null) {
            int colonne=0;
            // parcours de la ligne
            for (colonne=0; colonne < ligne.length(); colonne++) {
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
                        this.pj = new Aventurier(colonne, numeroLigne,100);
                        this.personnages.add(pj);
                        break;
                    case ESCAPE1:
                        // pas de mur
                        this.murs[colonne][numeroLigne] = false;
                        this.escapes.add(colonne, numeroLigne,this.nMap,this.nvLaby,'A');
                        break;
                    case ESCAPE2:
                        // pas de mur
                        this.murs[colonne][numeroLigne] = false;
                        this.escapes.add(colonne, numeroLigne,this.nMap,this.nvLaby,'B');
                        break;
                    case ESCAPE3:
                        // pas de mur
                        this.murs[colonne][numeroLigne] = false;
                        this.escapes.add(colonne, numeroLigne,this.nMap,this.nvLaby,'C');
                        break;
                    case ESCAPE4:
                        // pas de mur
                        this.murs[colonne][numeroLigne] = false;
                        this.escapes.add(colonne, numeroLigne,this.nMap,this.nvLaby,'D');
                        break;
                    case ESCAPE5:
                        // pas de mur
                        this.murs[colonne][numeroLigne] = false;
                        this.escapes.add(colonne, numeroLigne,this.nMap,this.nvLaby,'E');
                        break;
                    case ESCAPE6:
                        // pas de mur
                        this.murs[colonne][numeroLigne] = false;
                        this.escapes.add(colonne, numeroLigne,this.nMap,this.nvLaby,'F');
                        break;
                    case ESCAPE7:
                        // pas de mur
                        this.murs[colonne][numeroLigne] = false;
                        this.escapes.add(colonne, numeroLigne,this.nMap,this.nvLaby,'G');
                        break;
                    case ESCAPE8:
                        // pas de mur
                        this.murs[colonne][numeroLigne] = false;
                        this.escapes.add(colonne, numeroLigne,this.nMap,this.nvLaby,'H');
                        break;
                    case ESCAPE9:
                        // pas de mur
                        this.murs[colonne][numeroLigne] = false;
                        this.escapes.add(colonne, numeroLigne,this.nMap,this.nvLaby,'I');
                        break;
                    case MONSTRE:
                        // pas de mur
                        this.murs[colonne][numeroLigne] = false;
                        // ajoute PJ
                        this.monstres.add(new Monstre(colonne, numeroLigne,30));
                        this.personnages.add(monstres.get(m));
                        m++;
                        break;
                    case SERPENT:
                        // pas de mur
                        this.murs[colonne][numeroLigne] = false;
                        // ajoute PJ
                        this.serpents.add(new Serpent(colonne, numeroLigne,70));
                        this.personnages.add(serpents.get(s));
                        s++;
                        break;
                    case DEPOT:
                        // pas de mur
                        this.murs[colonne][numeroLigne] = false;
                        // ajoute PJ
                        depots.ajouter(new Depot(colonne, numeroLigne));
                        break;
                    case CAISSE:
                        // pas de mur
                        this.murs[colonne][numeroLigne] = false;
                        // ajoute PJ
                        caisses.ajouter(new Depot(colonne, numeroLigne));
                        break;
                    case TORCHE:
                        // pas de mur
                        this.murs[colonne][numeroLigne] = false;
                        // ajoute PJ
                        torches.add(new Torche(colonne, numeroLigne));
                        break;
                    case FANTOME:
                        // pas de mur
                        this.murs[colonne][numeroLigne] = false;
                        // ajoute PJ
                        fantomes.add(new Fantome(colonne, numeroLigne,20));
                        this.personnages.add(fantomes.get(f));
                        f++;
                        break;
                    case TRESOR:
                        // pas de mur
                        this.murs[colonne][numeroLigne] = false;
                        // ajoute PJ
                        tresors.add(new Tresor(colonne, numeroLigne));
                        break;

                    default:
                        throw new Error("caractere inconnu " + c);
                }
            }
            this.tailleMax= new int[]{colonne-1, ligne.length()-1};
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
    public boolean personnagePresent(int x, int y) {
        boolean trouve = false;
        for (Personnage e : personnages) {
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
    public Personnage elementPresentObject(int x, int y) {
        Personnage res = null;
        for (Personnage e : personnages) {
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

    public boolean caisseOuTrouPresent(int x, int y) {
        Boolean res = false;
        for(int i =0;i<caisses.getTaille();i++){
            if(caisses.etreElement(x,y))
                res=true;
        }
        if(res==false){
            for(int i =0;i<depots.getTaille();i++){
                if(depots.etreElement(x,y))
                    res=true;
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

        if ((elementPresentObject(x, y) instanceof Personnage)) {  //cas si il y a un mur, alors personnage de bouge pas, il prend les valeurs sauvegarder dans les variables tmp
            combat = new Combat(pj,elementPresentObject(x, y));
            Personnage monstreDuCombat = elementPresentObject(x,y);
            monstreEnCombat=monstreDuCombat;
            actualiserVieAventurier(combat); //on actualise la vie du monstre
            supprimerMonstre(monstreDuCombat); //on supprime le monstre du jeu
        } else if (this.murs[x][y]) {
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
        aventurierSurTorche();
        aventurierDansTrou();
        tresorTrouve();
    }

    /**
     * supprime le monstre
     * @param m le monstre à supprimer
     */
    public void supprimerMonstre(Personnage m){
        this.personnages.remove(m);
        if( m instanceof Monstre)
            this.monstres.remove(m);

        if(m instanceof  Serpent)
            this.serpents.remove(m);
        if(m instanceof Fantome)
            this.fantomes.remove(m);
    }

    /**
     * méthode actualiserVieAventurier actualise la vie de pj selon l'issu d'un combat
     * @param combat le combat actuel
     */
    public void actualiserVieAventurier(Combat combat){
        this.pj.setHP(combat.getPj().getHP());
    }

    /**
     * méthode tresorTrouve quand l'aventurier est sur le trésor le tresor est supprimer
     * de l'ArrayList<Tresor> trésors
     */
    public void tresorTrouve(){
        Tresor tresorASupprimer=null;
        for (Tresor t : tresors){
            if(t.estPresent(this.pj.getX(),this.pj.getY())) {
                this.pj.setNbTresors(this.pj.getNbTresors() + 1);
                tresorASupprimer=t;
            }
        }
        tresors.remove(tresorASupprimer);
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
     * méthode aventurierSurTorche supprime la torche si l'aventurier est dessus
     */
    public void aventurierSurTorche(){
        Torche torcheASupprimer=null;
            for (Torche t : torches) {
                if (t.estPresent(this.pj.getX(), this.pj.getY())) {
                    this.pj.setRayonTorche(Torche.RAYONTORCHE);
                    torcheASupprimer = t;
                }
            }
        this.torches.remove(torcheASupprimer);
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
                this.pj.setHP(0);
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
            if ((!this.murs[suivante[0]][suivante[1]]) && !personnagePresent(suivante[0], suivante[1])&& !caisseOuTrouPresent(suivante[0],suivante[1])) {
                // on met a jour personnage
                m.setX(suivante[0]);
                m.setY(suivante[1]);
            }
        }
    }

    /**
     * méthode deplacerMonstre permet de déplacer le Monstre de manière aléatoire
     */
    public void deplacerFantome() {
        String[] action = {HAUT, BAS, GAUCHE, DROITE};
        Random r = new Random();
        for (Fantome f : fantomes) {
            // case courante
            int[] courante = {f.getX(), f.getY()};

            // calcule case suivante
            int[] suivante = getSuivant(courante[0], courante[1], action[r.nextInt(action.length)]);

            // si c'est pas un mur, on effectue le deplacement
            if (suivante[0]>0&&suivante[0]<tailleMax[0]&&suivante[1]>0  &&suivante[1]<tailleMax[1] && !personnagePresent(suivante[0], suivante[1])) {
                // on met a jour personnage
                f.setX(suivante[0]);
                f.setY(suivante[1]);
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
            if ((!this.murs[suivante[0]][suivante[1]]) && !(personnagePresent(suivante[0], suivante[1]))&& !caisseOuTrouPresent(suivante[0],suivante[1])) {

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
        boolean res = false;

        if(pj.getHP()<=0||pj.getNbTresors()>=1)
            res=true;
        return res;
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

    /**
     * méthode player in escape
     * @return l'index du code de l'escalier jumeler ou -1 sinon
     */
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

    /**
     * getter de Combat
     * @return Combat
     */
    public Combat getCombat() {
        return combat;
    }
}
