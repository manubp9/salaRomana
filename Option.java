
/**
 * Enumeration class Option - write a description of the enum class here
 * 
 * @author (your name here)
 * @version (version number or date here)
 */
public enum Option
{
   

    GO("go"), 

    QUIT("quit"), 

    HELP("help"), 

    LOOK("look"), 

    EAT("eat"), 

    BACK("back"), 

    TAKE("take"), 

    DROP("take"), 

    ITEMS("items"), 

    UNKNOWN("unknown");
     private String nombre;

    private Option(String nombre)
    {
        this.nombre = nombre;
    }

    public String getNombre()
    {
        return nombre;
    }

}

