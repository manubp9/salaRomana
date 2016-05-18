import java.util.Stack;
/**
 *  This class is the main class of the "World of Zuul" application. 
 *  "World of Zuul" is a very simple, text based adventure game.  Users 
 *  can walk around some scenery. That's all. It should really be extended 
 *  to make it more interesting!~:-)
 * 
 *  To play this game, create an instance of this class and call the "play"
 *  method.
 * 
 *  This main class creates and initialises all the others: it creates all
 *  rooms, creates the parser and starts the game.  It also evaluates and
 *  executes the commands that the parser returns.
 * 
 * @author  Michael KÃ¶lling and David J. Barnes
 * @version 2011.07.31
 */

public class Game 
{
    private Parser parser;
    private Player player;
    private Player lion;

    /**
     * Create the game and initialise its internal map.
     */
    public Game() 
    {
        parser = new Parser();
        player = new Player();
        lion = new Player();
        createRooms();
    }

    /**
     * Create all the rooms and link their exits together.
     * 
     */
    private void createRooms()
    {
        Room circulo,oeste,este,norte,sur,freedom;

        // create the rooms
        circulo = new Room("sala circular romana con puertas");
        circulo.addItem(new Item("antorcha",2.0f,true));
        oeste = new Room("sala oeste,hay una sillica pa sentarse uno");
        oeste.addItem(new Item("silla",20.0f,false));
        este = new Room("sala este,con refrigerio");
        este.addItem(new Item("agua",0.5f,true));
        norte = new Room("sala norte,sala con fuego");
        norte.addItem(new Item("fuego",0.0f,true));
        sur = new Room("sala sur");
        sur.addItem(new Item("espadon",3.5f,true));
        sur.addItem(new Item("colmillo",0.1f,true));
        freedom = new Room("sala libertad");
        
        // initialise room exits
        circulo.setExit("north",norte);
        circulo.setExit("east",este);
        circulo.setExit("south",sur);
        circulo.setExit("west",oeste);

        oeste.setExit("east",circulo);
        oeste.setExit("southeast",sur);
        oeste.setExit("west",freedom);

        este.setExit("west",circulo);

        norte.setExit("south",circulo);

        sur.setExit( "north",circulo);
        sur.setExit("northwest",oeste);
        
        freedom.setExit("east",oeste);

        player.setCurrentRoom(circulo);
        lion.setCurrentRoom(sur);
        lion.takeItem("colmillo");
    }

    /**
     *  Main play routine.  Loops until end of play.
     */
    public void play() 
    {            
        printWelcome();

        // Enter the main command loop.  Here we repeatedly read commands and
        // execute them until the game is over.

        boolean finished = false;
        while (!finished && player.getVivo()|| !finished && !player.getCurrentRoom().getDescription().equals("freedom")) {
            Command command = parser.getCommand();
            finished = processCommand(command);
            System.out.println(lion.getCurrentRoom().getDescription());

            if(player.getCurrentRoom().getDescription().contains("libertad"))
            {
                finished = true;
                System.out.println("Te has ganado la libertad");
                
            }
        }
        if(!player.getVivo())
        {
            System.out.println("Estas muerto");
        }
        System.out.println("Thank you for playing.  Good bye.");
    }

    /**
     * Print out the opening message for the player.
     */
    private void printWelcome()
    {
        System.out.println();
        System.out.println("Welcome to the World of Zuul!");
        System.out.println("World of Zuul is a new, incredibly boring adventure game.");
        System.out.println("Type 'aiuto' if you need help.");
        System.out.println();
        player.printLocationInfo();

        System.out.println();
    }

    /**
     * Given a command, process (that is: execute) the command.
     * @param command The command to be processed.
     * @return true If the command ends the game, false otherwise.
     */
    private boolean processCommand(Command command) 
    {
        boolean wantToQuit = false;

        if(command.isUnknown()) {
            System.out.println("I don't know what you mean...");
            return false;
        }

        Option commandWord = command.getCommandWord();
        switch(commandWord ) 
        {
            case HELP:
            printHelp();
            break;

            case GO: 
            player.goRoom(command);
            if(lion.getVivo())
            {
                lion.moveRandom();
            }
            if(player.getCurrentRoom() == lion.getCurrentRoom())
            {
                System.out.println("Un leon salvaje apareció");
                if(player.findItem("espadon"))
                {
                    System.out.println("Has derrotado al leon,la sala de la libertad esta abierta\n");
                    System.out.println("corre hacia tu libertad");
                    lion.setVivo();
                    lion.dropItem("colmillo");
                    player.takeItem("colmillo");
                    System.out.println("Has cogido el colmillo que demuestra que has matado al leon");

                }
                else
                {
                    player.setVivo();
                    System.out.println("Sin armas no puedes derrotar a un leon,estas muerto");

                }

            }
            break;

            case QUIT:
            wantToQuit = quit(command);
            break;

            case LOOK:
            player.printLocationInfo();
            break;

            case EAT:
            System.out.println("You have eaten now and you are not hungry any more");
            break;

            case BACK:
            player.volverAtras();
            lion.moveRandom();

            break;

            case TAKE:
            player.takeItem(command.getSecondWord());
            break;

            case DROP:
            player.dropItem(command.getSecondWord());
            break;

            case ITEMS:
            player.getInventario();
            break;

        }
        return wantToQuit;
    }
    // implementations of user commands:
    /**
     * Print out some help information.
     * Here we print some stupid, cryptic message and a list of the 
     * command words.
     */
    private void printHelp() 

    {
        System.out.println("You are lost. You are alone. You wander");
        System.out.println("around at the university.");
        System.out.println();
        System.out.println("Your command words are:");
        parser.printCommands();
    }

    /** 
     * "Quit" was entered. Check the rest of the command to see
     * whether we really quit the game.
     * @return true, if this command quits the game, false otherwise.
     */
    private boolean quit(Command command) 
    {
        if(command.hasSecondWord()) {
            System.out.println("Quit what?");
            return false;
        }
        else {
            return true;  // signal that we want to quit
        }
    }

}