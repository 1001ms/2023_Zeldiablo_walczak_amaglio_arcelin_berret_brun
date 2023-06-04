package gameLaby.laby;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.*;
import javafx.scene.text.TextAlignment;
import moteurJeu.DessinJeu;
import moteurJeu.Jeu;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;

public class LabyDessin implements DessinJeu {

    public final static int TAILLE=50;

    /**
     * affichage d'un jeu de type arkanoid
     *
     * @param canvas dessin dans lequel dessin
     */
    @Override
    public void dessinerJeu(Jeu jeu, Canvas canvas) throws FileNotFoundException {
        FileInputStream  inputStreamGameOver = new FileInputStream("zeldiablo/images/gameOver.png");
        Image gameOver = new Image(inputStreamGameOver);
        LabyJeu labyJeu = (LabyJeu) jeu;
        Labyrinthe labyrinthe = labyJeu.getLabyrinthe();

        // Récupérer un pinceau pour dessiner
        GraphicsContext gc = canvas.getGraphicsContext2D();

        if(!labyrinthe.etreFini()) {
            // Dessiner le personnage et les autres éléments du jeu
            dessinerMurSol(gc, labyrinthe);
            dessinerEscaliers(gc,labyrinthe);
            dessinerDepot(gc, labyrinthe);
            dessinerMonstre(gc, labyrinthe);
            dessinerSerpent(gc, labyrinthe);
            dessinerCaisse(gc, labyrinthe);
            dessinerAventurier(gc, labyrinthe);



            // Dessiner la zone éclairée autour du personnage
            Aventurier personnage = labyrinthe.pj;
            int px = personnage.getX();
            int py = personnage.getY();

            double rayonEclairage = labyrinthe.pj.getRayonTorche(); // Définir le rayon d'éclairage souhaité

            // Dessine un cercle transparent avec une ombre autour du personnage
            double centerX = (py * TAILLE) + (TAILLE / 2);
            double centerY = (px * TAILLE) + (TAILLE / 2);
            RadialGradient gradient = new RadialGradient(0, 0, centerX, centerY, rayonEclairage,
                    false, CycleMethod.NO_CYCLE, new Stop(0, Color.TRANSPARENT), new Stop(1, Color.BLACK));
            gc.setFill(gradient);
            gc.fillRect(0, 0, 20 * 30, 20 * 30);//changer selon la taille de laby

            dessinerTorche(gc,labyrinthe);
            dessinerFantome(gc,labyrinthe);
        }else {

            gc.drawImage(gameOver,0,0,600,600);
        }
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
    }

    public void dessinerAventurier(GraphicsContext gc, Labyrinthe labyrinthe) throws FileNotFoundException {


        Aventurier personnage = labyrinthe.pj;
        FileInputStream  inputStreamAventurier = new FileInputStream("zeldiablo/images/aventurier.png");
        Image aventurier = new Image(inputStreamAventurier);
        int px = personnage.getX();
        int py = personnage.getY();
        int maxVie = 100;
        double vie = labyrinthe.pj.getHP(); // Récupérer la vie actuelle du personnage
        double barWidth = (double) vie / maxVie * TAILLE; // Calculer la largeur relative de la barre de vie
        double barX = py * TAILLE; // Coordonnée X de la barre de vie (même position horizontale que le personnage)
        double barY = (px - 0.2) * TAILLE - 5; // Coordonnée Y de la barre de vie (au-dessus du personnage, ajustez selon vos besoins)
        double barHeight = 5; // Hauteur de la barre de vie
        gc.setFill(Color.DARKGREY); // Couleur rouge pour la barre de vie
        gc.fillRect(barX, barY, 1*TAILLE, barHeight); // Dessiner le rectangle de la barre de vie
        gc.setFill(Color.RED); // Couleur rouge pour la barre de vie
        gc.fillRect(barX, barY, barWidth, barHeight); // Dessiner le rectangle de la barre de vie

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
        FileInputStream  inputStreamCorpS = new FileInputStream("zeldiablo/images/corpS2.png");
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
        FileInputStream  inputStreamCaisse = new FileInputStream("zeldiablo/images/caisse.png");
        Image caisse = new Image(inputStreamCaisse);
        for (int i=0;i<labyrinthe.caisses.getTaille();i++) {
            int px = labyrinthe.caisses.getElementByIndice(i).getX();
            int py = labyrinthe.caisses.getElementByIndice(i).getY();
            gc.drawImage(caisse, py*TAILLE, px*TAILLE, TAILLE, TAILLE);
        }
    }

    public void dessinerDepot(GraphicsContext gc, Labyrinthe labyrinthe) throws FileNotFoundException {
        FileInputStream  inputStreamTrou = new FileInputStream("zeldiablo/images/trou.png");
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

    private void dessinerTorche(GraphicsContext gc, Labyrinthe labyrinthe) throws FileNotFoundException {
        ArrayList<Torche> torches = labyrinthe.torches;
        FileInputStream inputStreamTorche = new FileInputStream("zeldiablo/images/torche.png");
        Image torche = new Image(inputStreamTorche);

        for (Torche t : torches) {
            int px = t.getX();
            int py = t.getY();
            double rayonTorche = Torche.RAYONTORCHE; // Récupérer le rayon de la torche

            // Coordonnées du centre de la torche
            double centerX = (py * TAILLE) + (TAILLE / 2);
            double centerY = (px * TAILLE) + (TAILLE / 2);

            // Dessiner le halo lumineux en utilisant un gradient radial
            RadialGradient gradient = new RadialGradient(
                    0, 0, centerX, centerY, rayonTorche,
                    false, CycleMethod.NO_CYCLE,
                    new Stop(0, Color.rgb(62,41,52)), new Stop(0.3, Color.TRANSPARENT)
            );

            gc.setFill(gradient);
            gc.fillRect((py * TAILLE) - rayonTorche, (px * TAILLE) - rayonTorche, TAILLE + (rayonTorche * 2), TAILLE + (rayonTorche * 2));

            // Dessiner l'image de la torche
            gc.drawImage(torche, py * TAILLE, px * TAILLE, TAILLE, TAILLE);
        }
    }

    public void dessinerFantome(GraphicsContext gc, Labyrinthe labyrinthe) throws FileNotFoundException {
        FileInputStream inputStreamFantome = new FileInputStream("zeldiablo/images/fantome.png");
        Image fantome = new Image(inputStreamFantome);
        for (Fantome f : labyrinthe.fantomes) {
            int px = f.getX();
            int py = f.getY();

            // Définir l'opacité de l'image du fantôme
            gc.setGlobalAlpha(0.3); // Opacité réduite à 30%

            // Dessiner l'image du fantôme
            gc.drawImage(fantome, py * TAILLE, px * TAILLE, TAILLE, TAILLE);

            // Dessiner le halo lumineux en utilisant un gradient radial
            double rayonHalo = 1.5 * TAILLE; // Rayon du halo lumineux
            double centerX = (py * TAILLE) + (TAILLE / 2); // Coordonnées du centre du fantôme
            double centerY = (px * TAILLE) + (TAILLE / 2);

            RadialGradient gradient = new RadialGradient(
                    0, 0, centerX, centerY, rayonHalo,
                    false, CycleMethod.NO_CYCLE,
                    new Stop(0, Color.WHITE), new Stop(0.6, Color.TRANSPARENT)
            );

            gc.setFill(gradient);
            gc.fillRect((py * TAILLE) - rayonHalo, (px * TAILLE) - rayonHalo, TAILLE + (rayonHalo * 2), TAILLE + (rayonHalo * 2));

            // Rétablir l'opacité par défaut
            gc.setGlobalAlpha(1.0); // Rétablir l'opacité à 100%
        }
    }

}
