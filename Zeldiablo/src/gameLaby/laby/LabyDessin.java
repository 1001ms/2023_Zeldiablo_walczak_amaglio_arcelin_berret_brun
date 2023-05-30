package gameLaby.laby;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import moteurJeu.DessinJeu;
import moteurJeu.Jeu;
import java.io.FileNotFoundException;
public class LabyDessin implements DessinJeu {


    /**
     * affichage d'un jeu de type arkanoid
     *
     * @param canvas dessin dans lequel dessin
     */
    @Override
    public void dessinerJeu(Jeu jeu, Canvas canvas) throws FileNotFoundException {

        LabyJeu labyJeu = (LabyJeu) jeu;

        // recupere un pinceau pour dessiner
        GraphicsContext gc = canvas.getGraphicsContext2D();

        // dessin fond
        gc.setFill(Color.WHITE);
        gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
        // dessin laby
        Labyrinthe labyrinthe = labyJeu.getLabyrinthe();

        for (int y = 0; y < labyrinthe.getLengthY(); y++) {
            // affiche la ligne
            for (int x = 0; x < labyrinthe.getLength(); x++) {
                if (labyrinthe.getMur(x, y)) {
                    gc.setFill(Color.BLACK);
                    gc.fillRect(x * 30, y * 30, 30, 30);
                } else {
                    gc.setFill(Color.WHITE);
                    gc.fillRect(x * 30, y * 30, 30, 30);
                }
            }
        }

        dessinerPerso(gc,labyrinthe);
    }

    private void dessinerPerso(GraphicsContext gc, Labyrinthe labyrinthe) {
        Perso personnage = labyrinthe.pj;

        int px = personnage.getX();
        int py = personnage.getY();
        gc.setFill(Color.RED);
        gc.fillOval(py*30,px*30,30,30);
    }
}
