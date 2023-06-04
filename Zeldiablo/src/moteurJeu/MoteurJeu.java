package moteurJeu;

//https://github.com/zarandok/megabounce/blob/master/MainCanvas.java

import gameLaby.laby.Labyrinthe;
import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.beans.property.LongProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;

import javazoom.jl.player.Player;
import org.w3c.dom.Text;

import java.io.BufferedInputStream;
import java.io.FileInputStream;

import  java.lang.*;
import java.io.*;
import java.io.FileNotFoundException;

import java.io.File;
import java.net.MalformedURLException;


// copied from: https://gist.github.com/james-d/8327842
// and modified to use canvas drawing instead of shapes

public class MoteurJeu extends Application {

    /**
     * gestion du temps : nombre de frame par secondes et temps par iteration
     */
    private static double FPS = 100;
    private static double dureeFPS = 1000 / (FPS + 1);

    /**
     * taille par defaut
     */
    private static double WIDTH = 800;
    private static double HEIGHT = 600;

    /**
     * statistiques sur les frames
     */
    public final FrameStats frameStats = new FrameStats();

    private int frameCount = 0;

    /**
     * jeu en Cours et renderer du jeu
     */
    private static Jeu jeu = null;
    private static DessinJeu dessin = null;

    /**
     * touches appuyee entre deux frame
     */
    Clavier controle = new Clavier();

    /**
     * lancement d'un jeu
     *
     * @param jeu    jeu a lancer
     * @param dessin dessin du jeu
     */
    public static void launch(Jeu jeu, DessinJeu dessin) {
        // le jeu en cours et son afficheur
        MoteurJeu.jeu = jeu;
        MoteurJeu.dessin = dessin;

        // si le jeu existe, on lance le moteur de jeu
        if (jeu != null)
            launch();
    }

    /**
     * frame par secondes
     *
     * @param FPSSouhaitees nombre de frames par secondes souhaitees
     */
    public static void setFPS(int FPSSouhaitees) {
        FPS = FPSSouhaitees;
        dureeFPS = 1000 / (FPS + 1);
    }

    public static void setTaille(double width, double height) {
        WIDTH = width;
        HEIGHT = height;
    }
    /****************************/
    private void lancerJeu (Stage primaryStage) throws IOException {


        // initialisation du canvas de dessin et du container
        Canvas canvas = new Canvas();
        final Pane canvasContainer = new Pane(canvas);
        canvasContainer.setPrefSize(21 * 30, 21 * 30);

        // ajout des statistiques en bas de la fenetre
        BorderPane root = new BorderPane();
        root.setCenter(canvasContainer);
        root.setMinSize(canvasContainer.getHeight(), canvasContainer.getWidth());

        HBox attack = new HBox();
        attack.setAlignment(Pos.CENTER);
        attack.setSpacing(20);
        Button atk = new Button("Attaque au corps à corps");
        Button atk2 = new Button("Attaque explosive");
        Button soin = new Button("Utiliser potion de soin");

        attack.getChildren().addAll(atk, atk2, soin);


        FileInputStream inputStreamMonstre = new FileInputStream("Zeldiablo/images/monstre.png");
        Image monstre = new Image(inputStreamMonstre);
        ImageView IVm = new ImageView(monstre);


        FileInputStream inputStreamPerso = new FileInputStream("zeldiablo/images/perso2.png");
        Image perso = new Image(inputStreamPerso);
        ImageView IVp = new ImageView(perso);

        ProgressBar pokemonHPBar = new ProgressBar();
        pokemonHPBar.setMaxWidth(Double.MAX_VALUE);
        pokemonHPBar.setProgress(jeu.getLaby().pj.getHP()/100); // PV à 100% au départ

        ProgressBar opponentHPBar = new ProgressBar();
        opponentHPBar.setMaxWidth(Double.MAX_VALUE);
        opponentHPBar.setProgress(0.5); // PV à 100% au départ
        pokemonHPBar.setStyle("-fx-accent: red;");
        opponentHPBar.setStyle("-fx-accent: red;");



        // Création du GridPane et configuration des cellules
        GridPane combatI = new GridPane();
        combatI.setGridLinesVisible(true);
        combatI.setHgap(10);
        combatI.setVgap(10);

        combatI.add(IVp, 0, 0);
        combatI.add(pokemonHPBar, 0, 1);

        combatI.add(IVm, 1, 0);
        combatI.add(opponentHPBar, 1, 1);

        combatI.add(attack, 0, 3, combatI.getColumnCount(), 1);


        // Création de la scène et affichage
        Image bg = new Image(new File("zeldiablo/images/background.jpg").toURI().toString());

        ImageView bgImageView = new ImageView(bg);
        bgImageView.setPreserveRatio(false);
        bgImageView.setFitWidth(combatI.getWidth());
        bgImageView.setFitHeight(combatI.getHeight());


        BackgroundImage bgImg = new BackgroundImage(bgImageView.getImage(), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, false, false, true, false));

        Background backgrnd = new Background(bgImg);

        combatI.setBackground(backgrnd);


        combatI.setPadding(new Insets(20));


        HBox superRoot = new HBox();
        superRoot.setSpacing(50);
        superRoot.setPadding(new Insets(50));
        superRoot.setMinHeight(canvasContainer.getHeight());
        superRoot.setMinWidth(canvasContainer.getWidth());

        superRoot.getChildren().addAll(root, combatI);
        canvas.widthProperty().bind(superRoot.widthProperty());
        canvas.heightProperty().bind(superRoot.heightProperty());
        //FOND ---------------------------------
        Image bgFond = new Image(new File("zeldiablo/images/backgroundFOND.jpeg").toURI().toString());
        ImageView bgFondImageView = new ImageView(bgFond);
        bgFondImageView.setPreserveRatio(false);
        bgFondImageView.setFitWidth(superRoot.getWidth());
        bgFondImageView.setFitHeight(superRoot.getHeight());

        BackgroundImage bgFondImg = new BackgroundImage(bgFondImageView.getImage(), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, false, false, false, true));

        Background backgrndFond = new Background(bgFondImg);

        superRoot.setBackground(backgrndFond);


        // creation de la scene
        Scene scene = new Scene(superRoot, WIDTH, HEIGHT);
        primaryStage.setScene(scene);
        primaryStage.setMaximized(true);
        primaryStage.show();

        // listener clavier
        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                controle.appuyerTouche(event);
            }
        });

        // timeline pour le déplacement du monstre ---------------------------------------------------------------------
        Timeline monstreTimeline = new Timeline(
                new KeyFrame(Duration.seconds(0.5), event -> {
                    // Déplacement du monstre
                    try {
                        jeu.getLabyrinthe().deplacerMonstre();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }

                    // Redessiner le jeu
                    try {
                        dessin.dessinerJeu(jeu, canvas);
                    } catch (FileNotFoundException e) {
                        throw new RuntimeException(e);
                    }
                })
        );
        monstreTimeline.setCycleCount(Timeline.INDEFINITE); // Exécution indéfinie
        monstreTimeline.play(); // Démarrer la timeline du monstre

        // timeline pour le déplacement du serpent ---------------------------------------------------------------------
        Timeline serpentTimeline = new Timeline(
                new KeyFrame(Duration.seconds(0.2), event -> {
                    // Déplacement du serpent
                    try {
                        Labyrinthe labyrinthe = jeu.getLabyrinthe();
                        labyrinthe.deplacerSerpent();
                        labyrinthe.deplacerFantome();

                        /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////à optimiser
                        int hpAventurier=jeu.getLabyrinthe().pj.getHP();

                        double val =labyrinthe.pj.getRayonTorche() - 1;

                        if(val<=0) {
                            labyrinthe.pj.setRayonTorche(0.01);
                        }else {
                            System.out.println(labyrinthe.pj.getRayonTorche());
                            labyrinthe.pj.setRayonTorche(val);
                        }


                        if(labyrinthe.pj.getRayonTorche()<=0.01) {
                            if(labyrinthe.pj.getHP()>0)
                                labyrinthe.pj.setHP(hpAventurier-1);

                            pokemonHPBar.setProgress(80/100); // PV à 100% au départ
                        }
                        //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }

                    // Redessiner le jeu
                    try {
                        dessin.dessinerJeu(jeu, canvas);
                    } catch (FileNotFoundException e) {
                        throw new RuntimeException(e);
                    }
                })
        );
        serpentTimeline.setCycleCount(Timeline.INDEFINITE); // Exécution indéfinie
        serpentTimeline.play(); // Démarrer la timeline du monstre


        scene.setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                controle.relacherTouche(event);
            }
        });


        // creation du listener souris
        canvas.addEventHandler(MouseEvent.MOUSE_CLICKED,
                new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        if (event.getClickCount() == 2) {
                            jeu.init();
                        }
                    }
                });

        // lance la boucle de jeu
        startAnimation(canvas);
    }
     /***********************$/
    //#################################
    // SURCHARGE Application
    //#################################


    @Override
    /**
     * creation de l'application avec juste un canvas et des statistiques
     */
    public void start(Stage primaryStage) throws IOException, FileNotFoundException {
        primaryStage.setTitle("Menu du Jeu");
        // Création des boutons
        Button jouerButton = new Button("Jouer");
        jouerButton.setPrefWidth(200); // Définir la largeur préférée du bouton
        jouerButton.setPrefHeight(50); // Définir la hauteur préférée du bouton

        Button quitterButton = new Button("Quitter");
        quitterButton.setPrefWidth(200); // Définir la largeur préférée du bouton
        quitterButton.setPrefHeight(50);



        jouerButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                // Lancer le jeu
                try {
                    lancerJeu(primaryStage);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        quitterButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                // Quitter le jeu
                primaryStage.close();
            }
        });

        // Création du conteneur pour les boutons
        VBox menuBox = new VBox();
        menuBox.setAlignment(Pos.CENTER);
        menuBox.setSpacing(20);
        menuBox.getChildren().addAll(jouerButton, quitterButton);

        // Création de la scène du menu
        BorderPane menuPane = new BorderPane();
        menuPane.setCenter(menuBox);

        //fond accueil du jeu
        Image BGaccueil = new Image(new File("zeldiablo/images/accueil.png").toURI().toString());
        ImageView BGaccueilImageView = new ImageView(BGaccueil);
        BGaccueilImageView.setFitWidth(menuPane.getWidth());
        BGaccueilImageView.setFitHeight(menuPane.getHeight());
        BackgroundImage bgFondImg = new BackgroundImage(BGaccueilImageView.getImage(), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, false, false, false, true));
        Background backgrndFond = new Background(bgFondImg);
        menuPane.setBackground(backgrndFond);

        //nouvelle scène
        Scene menuScene = new Scene(menuPane, WIDTH, HEIGHT);
        primaryStage.setScene(menuScene);
        primaryStage.setMaximized(true);
        primaryStage.show();
    }

    /**
     * gestion de l'animation (boucle de jeu)
     *
     * @param canvas le canvas sur lequel on est synchronise
     */
    private void startAnimation(final Canvas canvas) throws IOException {
        frameCount++;
        if (frameCount == 1) {
            //Parcour de la liste des fichiers texts et ajout dans la liste des labyrinthes
            File dir  = new File("Zeldiablo/labySimple");
            File[] liste = dir.listFiles();
            String name;
            int n1;
            char n2;
            for(File item : liste){
                if(item.isFile())
                {
                    name = item.getName();
                    if(name.endsWith(".txt"))
                    {
                        try {
                            System.out.println(name);
                            name = name.substring(4, 7);
                            n1 = Integer.parseInt(name.substring(0, 1));
                            n2 = name.substring(1, 2).charAt(0);
                            jeu.newLaby(("Zeldiablo/labySimple/laby"+n1+n2+".txt"), n1,jeu.getLaby().escapes);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
            jeu = jeu.newLaby("Zeldiablo/labySimple/laby0A.txt", 0,jeu.getLaby().escapes);
        }

        // stocke la derniere mise e jour
        final LongProperty lastUpdateTime = new SimpleLongProperty(0);

        // timer pour boucle de jeu
        final AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long timestamp) {

                // si jamais passe dans la boucle, initialise le temps
                if (lastUpdateTime.get() == 0) {
                    lastUpdateTime.set(timestamp);
                }

                // mesure le temps ecoule depuis la derniere mise a jour
                long elapsedTime = timestamp - lastUpdateTime.get();
                double dureeEnMilliSecondes = elapsedTime / 1_000_000.0;


                // si le temps ecoule depasse le necessaire pour FPS souhaite
                if (dureeEnMilliSecondes > dureeFPS) {
                    // met a jour le jeu en passant les touches appuyees
                    jeu.update(dureeEnMilliSecondes / 1_000., controle);
                    try {
                        if(jeu.getLaby().playerInEscape() != -1) {
                            System.out.println("+ niveau : " + jeu.getLaby().nvLaby + " type : " + jeu.getLaby().escapes.indexToType(jeu.getLaby().playerInEscape()));
                            int[] res = jeu.getLaby().escapes.identifierEchap(jeu.getLaby().nvLaby,jeu.getLaby().escapes.indexToType(jeu.getLaby().playerInEscape()));
                            System.out.println("+ niveau : " + res[0] + " type : " + jeu.getLaby().escapes.indexToType(res[1]));
                            String labS = "Zeldiablo/labySimple/laby" + (res[0]) + (jeu.getLaby().escapes.indexToType(res[1]))+".txt";
                            System.out.println(labS);
                            jeu = jeu.newLaby(labS, res[0],jeu.getLaby().escapes);

                        }

                        // dessine le jeu
                        try {
                            dessin.dessinerJeu(jeu, canvas);
                        } catch (FileNotFoundException e) {
                            throw new RuntimeException(e);
                        }
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }

                    // ajoute la duree dans les statistiques
                    frameStats.addFrame(elapsedTime);

                    // met a jour la date de derniere mise a jour
                    lastUpdateTime.set(timestamp);
                }

            }
        };

        // lance l'animation
        timer.start();
    }

    public FrameStats getFrameStats(){
        return frameStats;
    }
}