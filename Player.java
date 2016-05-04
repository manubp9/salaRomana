import java.util.ArrayList;
import java.util.Stack;
/**
 * Write a description of class Player here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Player
{
    // instance variables - replace the example below with your own

    public static final float PESOMAX = 8;
    private ArrayList<Item> mochila;
    private Room currentRoom;
    private Stack<Room> backRoom;
    private float pesoActual;

    /**
     * Constructor for objects of class Player
     */
    public Player()
    {
        currentRoom = null;
        backRoom = new Stack<Room>();
        mochila = new ArrayList<>();
        pesoActual = 0;
    }

    /** 
     * Try to go in one direction. If there is an exit, enter
     * the new room, otherwise print an error message.
     */
    public void goRoom(Command command) 
    {
        if(!command.hasSecondWord()) {
            // if there is no second word, we don't know where to go...
            System.out.println("Go where?");
            return;
        }

        String direction = command.getSecondWord();

        // Try to leave current room.
        Room nextRoom = null;
        nextRoom = currentRoom.getExit(direction);
        if (nextRoom == null) {
            System.out.println("There is no door!");
        }
        else {
            backRoom.push(currentRoom);
            currentRoom = nextRoom;
            printLocationInfo();

            System.out.println();
        }
    }

    /**
     * metodo para evitar la repeticion de codigo
     */
    public void printLocationInfo()
    {
        System.out.println (currentRoom.getLongDescription());
    }

    /**
     * devuelve los objetos cargados
     */
    public ArrayList<Item> getObjetosCargados()
    {
        return mochila;
    }

    /**
     * carga un item a la lista
     */
    public void takeItem(String nombreObjeto)
    {
        Item objeto = currentRoom.buscarObjeto(nombreObjeto);
        if(pesoActual<PESOMAX - objeto.getPeso()&&objeto.getCargable())
        {
            mochila.add(objeto);
            currentRoom.borrarObjeto(objeto);
            pesoActual += objeto.getPeso();
        }
        else 
        {
            System.out.println("No puedes llevar ese objeto");
        }
    }

    /**
     * suleta un objeto de la mochila
     */
    public void dropItem(String nombreObjeto)
    {
        for(int i = 0;i<mochila.size();i++)
        {
            if(mochila.get(i).getNombre().equals(nombreObjeto))
            {
                currentRoom.addItem(mochila.get(i));
                pesoActual -=mochila.get(i).getPeso();
                mochila.remove(i);
            }
        }
    }

    /**
     * muestra la informacion de los objetos de la mochila
     */
    public void getInventario()
    {
        if(mochila.size()>0)
        {
            for(Item item:mochila)
            {
                System.out.println(item.toString()+"\n");
            }
        }
        else
        {
            System.out.println("Tu mochila esta vacia");
        }
    }

    /**
     * fija la habitacion actual
     */
    public void setCurrentRoom(Room habActual)
    {
        if(currentRoom !=null)
        {
            backRoom.push(currentRoom);
        }
        currentRoom = habActual;
    }

    /**
     * te retorna a la anterior habitacion
     */
    public void volverAtras()
    {
        if(!backRoom.empty())
        {
            currentRoom = backRoom.pop();
            printLocationInfo();
        }
        else
        {
            System.out.println("You can't go back");
            printLocationInfo();
        }
    }

    /**
     * devuelve el peso de los objetos cargados
     */
    public float getPesoActual()
    {
        float pesoCarga = 0;
        for (Item objeto:mochila){
            pesoCarga +=objeto.getPeso();

        }
        return pesoCarga;
    }
}
