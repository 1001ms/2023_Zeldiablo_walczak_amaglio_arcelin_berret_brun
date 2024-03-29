package gameLaby.laby;

import java.io.IOException;

/**
 * charge et affiche un labyrinthe
 */
public class Main {
    public static void main(String[] args) throws IOException {

        // charge le labyrinthe
        Echappatoire escp =new Echappatoire();
        Labyrinthe laby = new Labyrinthe("labySimple/laby0A0.txt",0,escp,0);


        //affiche le labyrinthe charge
        for (int y = 0; y < laby.getLengthY(); y++) {
            // affiche la ligne
            for (int x = 0; x < laby.getLength(); x++) {
                if (laby.getMur(x, y))
                    System.out.print('X');
                else
                    System.out.print('.');
            }
            // saut de ligne
            System.out.println();
        }
    }
}
