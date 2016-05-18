import java.util.ArrayList;
import java.util.Stack;
import java.util.Random;

/**
 * Write a description of class Player here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Player
{
    // instance variables - replace the example below with your own

    public static final float PESOMAX = 5;
    private ArrayList<Item> mochila;
    private Room currentRoom;
    private Stack<Room> backRoom;
    private float pesoActual;
    private boolean luz;
    private boolean vivo;
    private boolean libre;

    /**
     * Constructor for objects of class Player
     */
    public Player()
    {
        currentRoom = null;
        backRoom = new Stack<Room>();
        mochila = new ArrayList<>();
        pesoActual = 0;
        luz =false;
        vivo = true;
        libre = false;
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
        else if(!getLibertad() && currentRoom.getDescription().contains("oeste")&& direction.equals("west"))
        {
             System.out.println("Esta puerta esta cerrada hasta hasta  que no demuestres la muerte de la bestia");
             
        }
        else {
            backRoom.push(currentRoom);
            currentRoom = nextRoom;
            printLocationInfo();

        }
    }

    /**
     * metodo para evitar la repeticion de codigo
     */
    public void printLocationInfo()
    {
        if (currentRoom.getDescription().contains("sala sur") && !getLuz()){
            System.out.println("Esta sala está a oscuras, necesitas tener luz para ver lo que hay");          
        }
        else{
            System.out.println (currentRoom.getLongDescription());
        }
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
        if(objeto !=null){
            if(pesoActual<PESOMAX - objeto.getPeso()&&objeto.getCargable())
            {
                if(findItem("antorcha")==true && objeto.getNombre()==("fuego"))

                {
                    mochila.add(objeto);
                    luz = true;   
                    pesoActual += objeto.getPeso();
                    System.out.println("Has encendido la antorcha, tienes luz");
                    pesoActual += objeto.getPeso();
                }
                else{
                    mochila.add(objeto);
                    currentRoom.borrarObjeto(objeto);
                    pesoActual += objeto.getPeso();
                }
            }else 
            {
                System.out.println("No puedes llevar ese objeto");
            }
        }
        else
        {
            System.out.println("No hay ningun objeto con ese nombre");
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
     * busca un objeto de la mochila
     */
    public boolean findItem(String nombreObjeto)
    {
        boolean encontrado = false;
        for(int i = 0;i<mochila.size();i++)
        {
            if(mochila.get(i).getNombre().equals(nombreObjeto))
            {
                encontrado = true;
            }
        }
        return encontrado;

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
     * devuelve si la antorcha esta encendida y puedes alumbrar la sala
     */

    public boolean getLuz()
    {
        return luz;
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

    /**
     * metodo para moverse de forma aleatoria por las salas del juego
     */
    public void moveRandom()
    {
        Random rnd = new Random();
        String[] salidas = currentRoom.getExitString().split(" ") ;   
        setCurrentRoom(currentRoom.getExit(salidas[rnd.nextInt(salidas.length)]));

    }

    /**
     * devuelve la habitacion actual
     */
    public Room getCurrentRoom()
    {
        return currentRoom;
    }

    /**
     * devuelve si el jugador esta vivo o no
     */
    public boolean getVivo()
    {
        return vivo;
    }
    /**
     * cambia el valor de vivo
     */
    public void setVivo()
    {
        vivo = !vivo;
    }
    /**
     * devuelve si puedes tener acceso a la libertad
     */
    public boolean getLibertad()
    {
        if(findItem("colmillo"))
        {
            libre = true;
        }
        return libre;
    }
    
    

}