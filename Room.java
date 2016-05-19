import java.util.HashMap;
import java.util.ArrayList;
/**
 * Class Room - a room in an adventure game.
 *
 * This class is part of the "World of Zuul" application. 
 * "World of Zuul" is a very simple, text based adventure game.  
 *
 * A "Room" represents one location in the scenery of the game.  It is 
 * connected to other rooms via exits.  The exits are labelled north, 
 * east, south, west.  For each direction, the room stores a reference
 * to the neighboring room, or null if there is no exit in that direction.
 * 
 * @author  Michael KÃ¶lling and David J. Barnes
 * @version 2011.07.31
 */
public class Room 
{
    private String description;
    private HashMap<String,Room> exits;
    private ArrayList<Item> objetos;

    /**
     * Create a room described "description". Initially, it has
     * no exits. "description" is something like "a kitchen" or
     * "an open court yard".
     * @param description The room's description.
     */
    public Room(String description) 
    {
        this.description = description;
        exits = new HashMap<>();
        objetos = new ArrayList<>();

    }

    /**
     * Define an exit from this room.
     * @param direction The direction of the exit.
     * @param neighbor The room in the given direction.
     */
    public void setExit(String direction, Room neighbor) 
    {
        exits.put(direction,neighbor);
    }

    /**
     * @return The description of the room.
     */
    public String getDescription()
    {
        return description;
    }

    /**
     * que tome como parámetro una cadena que represente una dirección 
     * y devuelva el objeto de la clase Room asociado a esa salida o null si no hay salida.
     */ 
    public Room getExit(String direccion)
    {
        Room salida = null;
        salida = exits.get(direccion);
        return salida;
    }

    /**
     * Return a description of the room's exits.
     * For example: "Exits: north east west"
     *
     * @ return A description of the available exits.
     */
    public String getExitString()
    {
        String description = "";
        for(String clave: exits.keySet())
        {
            description += clave + " "; 
        }
        return description;
    }

    /**
     * Return a long description of this room, of the form:
     *     You are in the 'name of room'
     *     Exits: north west southwest
     * @return A description of the room, including exits.
     */
    public String getLongDescription()
    {
        String descripcion = "Estas en la "+description+ "\nSalidas: "+ getExitString() ;
        if(objetos.size() !=0)
        {
            descripcion += "\nAqui hay un/a ";
        }
        else
        {
            descripcion += "\nAqui no hay ningun objeto";
        }
        for(Item objeto:objetos)
        {
            descripcion += objeto.toString();

        }
        return  descripcion;
    }

    /**
     * añade un objeto de la clase item a la lista
     */
    public void  addItem(Item item)
    {
        objetos.add(item);
    }

    /**
     * coge un objeto de la sala
     */
    public Item buscarObjeto(String nombreObjeto)
    {
        boolean encontrado = false;
        Item objeto = null;
        for(int i =0; i <objetos.size() && !encontrado;i++)
        {
            if(objetos.get(i).getNombre().equals(nombreObjeto))
            {
                objeto =objetos.get(i);
                encontrado = true;
            }
        }
        return objeto;

    }

    /**
     * borra un objeto de la sala
     */
    public void borrarObjeto(Item item)
    {
        boolean encontrado = false;
        Item objeto = null;
        for(int i =0; i <objetos.size() && !encontrado;i++)
        {
            if(objetos.get(i)== item)
            {
                objeto =objetos.remove(i);
                encontrado = true;
            }
        }
    }
}
