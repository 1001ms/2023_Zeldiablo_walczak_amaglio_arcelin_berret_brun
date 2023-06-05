import gameLaby.laby.Caisse;
import gameLaby.laby.Element;
import gameLaby.laby.ListeElements;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ListeElementTest {

    @Test
    public void testAjouter() {
        ListeElements listeElements = new ListeElements();
        Caisse caisse = new Caisse(10, 20);
        listeElements.ajouter(caisse);
        Assertions.assertEquals(1, listeElements.getTaille(), "La taille de la liste après l'ajout d'un élément n'est pas la valeur attendue");
    }

    @Test
    public void testSetListeElements() {
        ListeElements listeElements = new ListeElements();
        Caisse caisse1 = new Caisse(10, 20);
        Caisse caisse2 = new Caisse(30, 40);
        listeElements.ajouter(caisse1);
        listeElements.setListeElements(0, caisse2);
        Assertions.assertEquals(caisse2, listeElements.getElementByIndice(0), "L'élément à l'indice spécifié n'a pas été mis à jour correctement");
    }

    @Test
    public void testGetElement() {
        ListeElements listeElements = new ListeElements();
        Caisse caisse = new Caisse(10, 20);
        listeElements.ajouter(caisse);
        Element element = listeElements.getElement(10, 20);
        Assertions.assertEquals(caisse, element, "L'élément retourné par la méthode getElement() ne correspond pas à l'élément attendu");
    }

    @Test
    public void testEtreElement() {
        ListeElements listeElements = new ListeElements();
        Caisse caisse = new Caisse(10, 20);
        listeElements.ajouter(caisse);
        boolean estPresent = listeElements.etreElement(10, 20);
        Assertions.assertTrue(estPresent, "La méthode etreElement() ne retourne pas true pour des coordonnées correspondantes");
    }

    @Test
    public void testIndiceElement() {
        ListeElements listeElements = new ListeElements();
        Caisse caisse = new Caisse(10, 20);
        listeElements.ajouter(caisse);
        int indice = listeElements.indiceElement(10, 20);
        Assertions.assertEquals(0, indice, "L'indice de l'élément ne correspond pas à la valeur attendue");
    }

    @Test
    public void testSupprimerElement() {
        ListeElements listeElements = new ListeElements();
        Caisse caisse = new Caisse(10, 20);
        listeElements.ajouter(caisse);
        listeElements.supprimerElement(0);
        Assertions.assertEquals(0, listeElements.getTaille(), "La taille de la liste après la suppression d'un élément n'est pas la valeur attendue");
    }

    @Test
    public void testCaisseGetX() {
        Caisse caisse = new Caisse(10, 20);
        Assertions.assertEquals(10, caisse.getX(), "La valeur retournée par getX() pour la classe Caisse ne correspond pas à la valeur attendue");
    }

    @Test
    public void testCaisseGetY() {
        Caisse caisse = new Caisse(10, 20);
        Assertions.assertEquals(20, caisse.getY(), "La valeur retournée par getY() pour la classe Caisse ne correspond pas à la valeur attendue");
    }

    @Test
    public void testCaisseSetX() {
        Caisse caisse = new Caisse(10, 20);
        caisse.setX(30);
        Assertions.assertEquals(30, caisse.getX(), "La valeur de x après avoir appelé setX() pour la classe Caisse ne correspond pas à la valeur attendue");
    }

    @Test
    public void testCaisseSetY() {
        Caisse caisse = new Caisse(10, 20);
        caisse.setY(40);
        Assertions.assertEquals(40, caisse.getY(), "La valeur de y après avoir appelé setY() pour la classe Caisse ne correspond pas à la valeur attendue");
    }
}
