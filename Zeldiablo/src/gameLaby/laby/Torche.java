package gameLaby.laby;

public class Torche implements Element{

    private Coordonnee coordonnee;
    public final static int RAYONTORCHE=200;
    public Torche(int x, int y){
        this.coordonnee=new Coordonnee(x,y);
    }

    @Override
    public int getX() {
        return coordonnee.getX();
    }

    @Override
    public int getY() {
        return coordonnee.getY();
    }

    @Override
    public void setX(int x) {
        coordonnee.setX(x);
    }

    @Override
    public void setY(int y) {
        coordonnee.setY(y);
    }

    public boolean estPresent(int x, int y){
        return coordonnee.estPresent(x,y);
    }
}
