package gameLaby.laby;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.RadialGradient;
import javafx.scene.paint.Stop;
import moteurJeu.DessinJeu;
import moteurJeu.Jeu;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class LabyDessin implements DessinJeu {

    public final static int TAILLE=50;

    /**
     * affichage d'un jeu de type arkanoid
     *
     * @param canvas dessin dans lequel dessin
     */
    @Override
    public void dessinerJeu(Jeu jeu, Canvas canvas) throws FileNotFoundException {
        LabyJeu labyJeu = (LabyJeu) jeu;
        Labyrinthe labyrinthe = labyJeu.getLabyrinthe();

        // Récupérer un pinceau pour dessiner
        GraphicsContext gc = canvas.getGraphicsContext2D();



        // Dessiner le personnage et les autres éléments du jeu
        dessinerMurSol(gc, labyrinthe);
        dessinerDepot(gc, labyrinthe);
        dessinerAventurier(gc, labyrinthe);
        dessinerMonstre(gc, labyrinthe);
        dessinerSerpent(gc, labyrinthe);
        dessinerCaisse(gc, labyrinthe);

        // Dessiner la zone éclairée autour du personnage
        Aventurier personnage = labyrinthe.pj;
        int px = personnage.getX();
        int py = personnage.getY();
        int rayonEclairage = 125; // Définir le rayon d'éclairage souhaité

        // Dessiner un cercle transparent avec une ombre autour du personnage
        double centerX = (py * TAILLE) + (TAILLE / 2);
        double centerY = (px * TAILLE) + (TAILLE / 2);
        RadialGradient gradient = new RadialGradient(0, 0, centerX, centerY, rayonEclairage,
                false, CycleMethod.NO_CYCLE, new Stop(0, Color.TRANSPARENT), new Stop(1, Color.BLACK));
        gc.setFill(gradient);
        gc.fillRect(0, 0, 21*30, 21*30);//changer selon la taille de laby
    }


    public void dessinerMurSol(GraphicsContext gc, Labyrinthe labyrinthe) throws FileNotFoundException {
        FileInputStream  inputStreamMur = new FileInputStream("zeldiablo/images/mur2.png");
        Image mur = new Image(inputStreamMur);
        FileInputStream  inputStreamSol = new FileInputStream("zeldiablo/images/sol.png");
        Image sol = new Image(inputStreamSol);
        for (int y = 0; y < labyrinthe.getLengthY(); y++) {
            // affiche la ligne
            for (int x = 0; x < labyrinthe.getLength(); x++) {
                if (labyrinthe.getMur(x, y)) {
                    gc.drawImage(mur,y*TAILLE,x*TAILLE,TAILLE,TAILLE);
                } else {
                    gc.drawImage(sol,y*TAILLE,x*TAILLE,TAILLE,TAILLE);
                }
            }
        }
        dessinerDepot(gc,labyrinthe);
        dessinerAventurier(gc,labyrinthe);
        dessinerMonstre(gc,labyrinthe);
        dessinerEscaliers(gc,labyrinthe);
        dessinerSerpent(gc,labyrinthe);
        dessinerCaisse(gc,labyrinthe);
    }

    public void dessinerAventurier(GraphicsContext gc, Labyrinthe labyrinthe) throws FileNotFoundException {
        Aventurier personnage = labyrinthe.pj;
        FileInputStream  inputStreamAventurier = new FileInputStream("zeldiablo/images/aventurier.png");
        Image aventurier = new Image(inputStreamAventurier);
        int px = personnage.getX();
        int py = personnage.getY();
        gc.drawImage(aventurier, py*TAILLE, px*TAILLE, TAILLE, TAILLE);
    }

    public void dessinerMonstre(GraphicsContext gc, Labyrinthe labyrinthe) throws FileNotFoundException {
        FileInputStream  inputStreamMonstre = new FileInputStream("zeldiablo/images/monstre.png");
        Image monstre = new Image(inputStreamMonstre);
        for(Monstre m : labyrinthe.monstres) {
            int px = m.getX();
            int py = m.getY();
            gc.drawImage(monstre, py*TAILLE, px*TAILLE, TAILLE, TAILLE);
        }
    }

    public void dessinerSerpent(GraphicsContext gc, Labyrinthe labyrinthe) throws FileNotFoundException {
        FileInputStream  inputStreamTeteS = new FileInputStream("zeldiablo/images/teteS.png");
        Image teteS = new Image(inputStreamTeteS);
        FileInputStream  inputStreamCorpS = new FileInputStream("zeldiablo/images/corpS.png");
        Image corpS = new Image(inputStreamCorpS);

        for (Serpent s : labyrinthe.serpents) {
            // Parcours chaque partie du corps du serpent
            for (int i = 1; i < s.getCorp().size(); i++) {
                int corpx = s.getCorp().get(i).getX();
                int corpy = s.getCorp().get(i).getY();
                gc.drawImage(corpS,corpy * TAILLE, corpx * TAILLE, TAILLE, TAILLE);
            }
            int x = s.getCorp().get(0).getX();
            int y = s.getCorp().get(0).getY();

            gc.drawImage(teteS,y * TAILLE, x * TAILLE, TAILLE, TAILLE);
        }
    }

    public void dessinerCaisse(GraphicsContext gc, Labyrinthe labyrinthe) throws FileNotFoundException {
        FileInputStream  inputStreamCaisse = new FileInputStream("zeldiablo/images/image 8.png");
        Image caisse = new Image(inputStreamCaisse);
        for (int i=0;i<labyrinthe.caisses.getTaille();i++) {
            int px = labyrinthe.caisses.getElementByIndice(i).getX();
            int py = labyrinthe.caisses.getElementByIndice(i).getY();
            gc.drawImage(caisse, py*TAILLE, px*TAILLE, TAILLE, TAILLE);
        }
    }

    public void dessinerDepot(GraphicsContext gc, Labyrinthe labyrinthe) throws FileNotFoundException {
        FileInputStream  inputStreamTrou = new FileInputStream("zeldiablo/images/image 7.png");
        Image trou = new Image(inputStreamTrou);
        for (int i=0;i<labyrinthe.depots.getTaille();i++) {
            int px = labyrinthe.depots.getElementByIndice(i).getX();
            int py = labyrinthe.depots.getElementByIndice(i).getY();
            gc.drawImage(trou, py*TAILLE, px*TAILLE, TAILLE, TAILLE);
        }
    }

    private void dessinerEscaliers(GraphicsContext gc, Labyrinthe labyrinthe) {
        Echappatoire ec = labyrinthe.escapes;
        for(int i=0; i<ec.getEscpNv(labyrinthe.nvLaby).size();i++){
            int px = ec.getEscpNv(labyrinthe.nvLaby).get(i)[0];
            int py = ec.getEscpNv(labyrinthe.nvLaby).get(i)[1];
            gc.setFill(Color.YELLOW);
            gc.fillRect(py*TAILLE,px*TAILLE,TAILLE,TAILLE);
        }
    }

}
