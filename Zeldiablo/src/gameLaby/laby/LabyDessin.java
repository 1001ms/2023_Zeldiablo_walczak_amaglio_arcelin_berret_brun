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
                    gc.fillRect(y * 30, x * 30, 30, 30);
                } else {
                    gc.setFill(Color.WHITE);
                    gc.fillRect(y * 30, x * 30, 30, 30);
                }
            }
        }

        dessinerAventurier(gc,labyrinthe);
        dessinerMonstre(gc,labyrinthe);
        dessinerEscaliers(gc,labyrinthe);
    }

    private void dessinerAventurier(GraphicsContext gc, Labyrinthe labyrinthe) {
        Aventurier personnage = labyrinthe.pj;

        int px = personnage.getX();
        int py = personnage.getY();
        gc.setFill(Color.RED);
        gc.fillOval(py*30,px*30,30,30);
    }

    private void dessinerMonstre(GraphicsContext gc, Labyrinthe labyrinthe) {
        Monstre personnage = labyrinthe.monstre;

        int px = personnage.getX();
        int py = personnage.getY();
        gc.setFill(Color.GREEN);
        gc.fillOval(py*30,px*30,30,30);
    }

    private void dessinerEscaliers(GraphicsContext gc, Labyrinthe labyrinthe) {
        Echappatoire ec = labyrinthe.escapes;
        for(int i=0; i<ec.getEscpNv(labyrinthe.nvLaby).size();i++){
            int px = ec.getEscpNv(labyrinthe.nvLaby).get(i)[0];
            int py = ec.getEscpNv(labyrinthe.nvLaby).get(i)[1];
            gc.setFill(Color.YELLOW);
            gc.fillRect(py*30,px*30,30,30);
        }
    }

}
