package gameLaby.laby;

import java.util.ArrayList;

public class ListeElements {
    //déclarations des attributs
    private ArrayList<Element> listeE;

    /**
     * Constructeur
     */
    public ListeElements() {
        listeE = new ArrayList<>();
    }

    /**
     * méthode ajouter permet d'ajouter un element e à la liste
     *
     * @param e Element à ajouter
     */
    public void ajouter(Element e) {
        this.listeE.add(e);
    }

    /**
     * méthode setListeElements permet de maj un élement à l'indice i avec la valeur de e
     *
     * @param i indice de l'élement à maj
     * @param e maj de l'élement
     */
    public void setListeElements(int i, Element e) {
        this.listeE.set(i, e);
    }

    /**
     * méthode getElement
     *
     * @param x abscisse
     * @param y ordonnée
     * @return élement aux coordonnées (x,y)
     */
    public Element getElement(int x, int y) {
        Element elem = null;
        for (int i = 0; i < this.listeE.size(); i++) {
            if (listeE.get(i).getX() == x && listeE.get(i).getY() == y) {
                elem = listeE.get(i);
            }
        }
        return elem;
    }

    /**
     * méthode etreElement
     *
     * @param x abscisse
     * @param y ordonnée
     * @return true si un élément est présente aux coordonnées (x,y)
     */
    public boolean etreElement(int x, int y) {
        Boolean bool = false;
        for (int i = 0; i < this.listeE.size(); i++) {
            if (listeE.get(i).getX() == x && listeE.get(i).getY() == y) {
                bool = true;
            }
        }
        return bool;
    }

    /**
     * méthode indiceElement permet de récupérer l'indice d'un élément possédant les coordonées (x,y)
     *
     * @param x abscisse
     * @param y ordonnée
     * @return un int correspondant à l'indice de l'élement correspondant ou -1 si l'élement n'existe pas
     */
    public int indiceElement(int x, int y) {
        int indice = -1;
        for (int i = 0; i < this.listeE.size(); i++) {
            if (listeE.get(i).getX() == x && listeE.get(i).getY() == y) {
                indice = i;
            }
        }
        return indice;
    }

    /**
     * méthode getTaille
     *
     * @return la taille de la liste
     */
    public int getTaille() {
        return this.listeE.size();
    }

    /**
     * méthode getElementByIndice
     *
     * @param i indice de l'élement
     * @return l'élément de la liste à l'indice i
     */
    public Element getElementByIndice(int i) {
        return this.listeE.get(i);
    }

    public void supprimerElement(int i) {
        this.listeE.remove(i);
    }
}