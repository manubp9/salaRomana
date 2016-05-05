import java.util.HashMap;
/**
 * This class is part of the "World of Zuul" application. 
 * "World of Zuul" is a very simple, text based adventure game.  
 * 
 * This class holds an enumeration of all command words known to the game.
 * It is used to recognise commands as they are typed in.
 *
 * @author  Michael Kölling and David J. Barnes
 * @version 2011.07.31
 */

public class CommandWords
{
    // a constant array that holds all valid command words
    private HashMap<String,Option> validCommands;

    /**
     * Constructor - initialise the command words.
     */
    public CommandWords()
    {

        validCommands = new HashMap<String,Option>();
        Option[] option = Option.values();
        for(int i = 0;i< option.length;i++ )
        {
            validCommands.put(option[i].getNombre(),option[i]);
        }
        
        
        

    }

    /**
     * Check whether a given String is a valid command word. 
     * @return true if a given string is a valid command,
     * false if it isn't.
     */
    public boolean isCommand(String aString)
    {

        // if we get here, the string was not found in the commands
        return validCommands.containsKey(aString);
    }

    /**
     * Print all valid commands to System.out
     */
    public void showAll()
    {
        String comandos = "";
        for(String clave:validCommands.keySet())
        {
            comandos += clave + " ";
        }
        System.out.println(comandos);
    }

    /**
     * Return the object Option associated with a word.
     * @param commandWord The word to look up (as a string).
     * @return the object Option correspondng to the paramater commandWord, or the object Option.UNKNOWN
     *         if it is not a valid command word
     */
    public Option getCommandWord(String commandWord)
    {
        Option objeto = Option.UNKNOWN;
        if(validCommands.containsKey(commandWord))
        {
            objeto = validCommands.get(commandWord);
        }
        return objeto;
    }
}