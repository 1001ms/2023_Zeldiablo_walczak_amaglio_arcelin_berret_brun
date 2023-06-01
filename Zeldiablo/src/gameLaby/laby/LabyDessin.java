package gameLaby.laby;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import moteurJeu.DessinJeu;
import moteurJeu.Jeu;
import java.io.FileInputStream;
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
        FileInputStream  inputStreamMur = new FileInputStream("zeldiablo/images/mur.png");
        Image mur = new Image(inputStreamMur);
        FileInputStream  inputStreamSol = new FileInputStream("zeldiablo/images/sol.png");
        Image sol = new Image(inputStreamSol);
        for (int y = 0; y < labyrinthe.getLengthY(); y++) {
            // affiche la ligne
            for (int x = 0; x < labyrinthe.getLength(); x++) {
                if (labyrinthe.getMur(x, y)) {
                    gc.drawImage(mur,y*30,x*30,30,30);
                } else {
                    gc.drawImage(sol,y*30,x*30,30,30);
                }
            }
        }

        dessinerAventurier(gc,labyrinthe);
        dessinerMonstre(gc,labyrinthe);
        dessinerSerpent(gc,labyrinthe);
    }

    public void dessinerAventurier(GraphicsContext gc, Labyrinthe labyrinthe) {
        Aventurier personnage = labyrinthe.pj;

        int px = personnage.getX();
        int py = personnage.getY();
        gc.setFill(Color.RED);
        gc.fillOval(py*30,px*30,30,30);

    }

    public void dessinerMonstre(GraphicsContext gc, Labyrinthe labyrinthe) {
        Monstre personnage = labyrinthe.monstre;

        int px = personnage.getX();
        int py = personnage.getY();
        gc.setFill(Color.GREEN);
        gc.fillOval(py*30,px*30,30,30);

    }

    public void dessinerSerpent(GraphicsContext gc, Labyrinthe labyrinthe) {
        Serpent serpent = labyrinthe.serpent;


        // Parcours chaque partie du corps du serpent
        for (int i=1;i< serpent.getCorp().size();i++) {
            int corpx = serpent.getCorp().get(i).getX();
            int corpy = serpent.getCorp().get(i).getY();
            gc.setFill(Color.CORNFLOWERBLUE);
            gc.fillOval(corpy * 30, corpx * 30, 30, 30);

        }
        int x = serpent.getCorp().get(0).getX();
        int y = serpent.getCorp().get(0).getY();
        gc.setFill(Color.BLUE);
        gc.fillOval(y * 30, x * 30, 30, 30);
    }
}
