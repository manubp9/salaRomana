
/**
 * Write a description of class Item here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Item
{
    // instance variables - replace the example below with your own
    private String nombre;
    private float peso;

    /**
     * Constructor for objects of class Item
     */
    public Item(String nombre,float peso)
    {
        this.nombre = nombre;
        this.peso = peso;
    }
    /**
     * devuelve el nombre del objeto
     */
    public String getNombre()
    {
        return nombre;
    }
    /**
     * devuelve el peso del objeto
     */
    public float getPeso()
    {
        return peso;
    }
    
}
