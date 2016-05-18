
/**
 * Write a description of class Luz here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Luz
{
    // instance variables - replace the example below with your own
    public boolean luz;

    /**
     * Constructor for objects of class Luz
     */
    public Luz()
    {
        luz = false;
    }

    /**
     * devuelve si la antorcha esta encendida y puedes alumbrar la sala
     */

    public boolean getLuz()
    {
        return luz;
    }
    /**
     * enciende la luz de la antorcha
     */
    public void luzOn()
    {
        luz = true;
        
    }
}
