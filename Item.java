
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
    private boolean puedeCargar;
    private boolean encendido;

    /**
     * Constructor for objects of class Item
     */
    public Item(String nombre,float peso,boolean puedeCargar)
    {
        this.nombre = nombre;
        this.puedeCargar = puedeCargar;
        this.peso = peso;
        encendido = false;
    }

    /**
     * devuelve si se puede cargar con ese objeto
     */
    public boolean getCargable(){
        return puedeCargar;
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

    /**
     * devuelve todos los datos del item
     */
    public String toString()
    {
        return getNombre()+" que pesa  "+getPeso()+"kg`s";
    }
}
