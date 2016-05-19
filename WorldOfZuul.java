
/**
 * Write a description of class WorldOfZuul here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class WorldOfZuul
{
    // instance variables - replace the example below with your own
    private static Game juego;

    /**
     * Crea un juego y lo inicia
     */
    public static void main(String[] args)
    {
        juego = new Game();
        juego.play();
    }
}
