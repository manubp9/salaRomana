import java.util.HashMap;
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
 * @author  Michael Kölling and David J. Barnes
 * @version 2011.07.31
 */
public class Room 
{
    private String description;
    private Room northExit;
    private Room southExit;
    private Room eastExit;
    private Room westExit;
    private Room southEastExit;
    private HashMap<String,Room> exits;
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
     * que tome como par�metro una cadena que represente una direcci�n 
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
        if(getExit("north") != null) {
            description += "north ";
        }
        if(getExit("east") != null) {
            description +="east ";
        }
        if(getExit("south") != null) {
            description +="south ";
        }
        if(getExit("west")!= null) {
            description += "west ";
        }
        if(getExit("southeast") != null) {
            description +="southeast ";
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
        return "Estas en la "+description+ "\n Salidas: "+ getExitString(); 
    }
}
