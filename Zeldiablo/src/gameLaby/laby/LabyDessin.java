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
        dessinerDepot(gc,labyrinthe);
        dessinerAventurier(gc,labyrinthe);
        dessinerMonstre(gc,labyrinthe);
        dessinerSerpent(gc,labyrinthe);
        dessinerCaisse(gc,labyrinthe);
    }

    public void dessinerAventurier(GraphicsContext gc, Labyrinthe labyrinthe) throws FileNotFoundException {
        Aventurier personnage = labyrinthe.pj;
        FileInputStream  inputStreamAventurier = new FileInputStream("zeldiablo/images/aventurier.png");
        Image aventurier = new Image(inputStreamAventurier);
        int px = personnage.getX();
        int py = personnage.getY();
        gc.setFill(Color.RED);
        gc.drawImage(aventurier,py*30,px*30,30,30);

    }

    public void dessinerMonstre(GraphicsContext gc, Labyrinthe labyrinthe) throws FileNotFoundException {

        FileInputStream  inputStreamMonstre = new FileInputStream("zeldiablo/images/brocoli.png");
        Image monstre = new Image(inputStreamMonstre);
        for(Monstre m : labyrinthe.monstres) {
            int px = m.getX();
            int py = m.getY();
            gc.drawImage(monstre,py*30,px*30,30,30);
        }
    }


    public void dessinerSerpent(GraphicsContext gc, Labyrinthe labyrinthe) {

        for (Serpent s : labyrinthe.serpents) {

            // Parcours chaque partie du corps du serpent
            for (int i = 1; i < s.getCorp().size(); i++) {
                int corpx = s.getCorp().get(i).getX();
                int corpy = s.getCorp().get(i).getY();
                gc.setFill(Color.MEDIUMSPRINGGREEN);
                gc.fillOval(corpy * 30, corpx * 30, 30, 30);

            }
            int x = s.getCorp().get(0).getX();
            int y = s.getCorp().get(0).getY();
            gc.setFill(Color.GREEN);
            gc.fillOval(y * 30, x * 30, 30, 30);
        }
    }

    public void dessinerCaisse(GraphicsContext gc, Labyrinthe labyrinthe) throws FileNotFoundException {
        FileInputStream  inputStreamCaisse = new FileInputStream("zeldiablo/images/caisse.png");
        Image caisse = new Image(inputStreamCaisse);
        for (int i=0;i<labyrinthe.caisses.getTaille();i++) {

            // Parcours chaque partie du corps du serpent
            int px = labyrinthe.caisses.getElementByIndice(i).getX();
            int py = labyrinthe.caisses.getElementByIndice(i).getY();
            gc.drawImage(caisse,py*30,px*30,30,30);
        }
    }

    public void dessinerDepot(GraphicsContext gc, Labyrinthe labyrinthe) throws FileNotFoundException {
        FileInputStream  inputStreamTrou = new FileInputStream("zeldiablo/images/trou.png");
        Image trou = new Image(inputStreamTrou);
        for (int i=0;i<labyrinthe.depots.getTaille();i++) {

            // Parcours chaque partie du corps du serpent
            int px = labyrinthe.depots.getElementByIndice(i).getX();
            int py = labyrinthe.depots.getElementByIndice(i).getY();
            gc.drawImage(trou,py*30,px*30,30,30);
        }
    }
}
