 

public class CommandWords
{
    private final String[] aValidCommands;
    /**
     * constructeur
     */
    public CommandWords(){
        this.aValidCommands= new String[9];
        this.aValidCommands[0]= "go";
        this.aValidCommands[1]= "help";
        this.aValidCommands[2]= "quit";
        this.aValidCommands[3]= "look";
        this.aValidCommands[4]= "eat";
        this.aValidCommands[5]= "back";
        this.aValidCommands[6]= "test";
        this.aValidCommands[7]= "take";
        this.aValidCommands[8]= "drop";
    }
    private static final String[] sValidCommands = {"go","quit", "help", "look","eat","back","test","drop","take"};
    public boolean isCommand (final String vString){
        for (int vI=0;vI<this.aValidCommands.length; vI++)
        if (this.aValidCommands[vI].equals(vString)){
            return true;
        }
        return false;
    }
    public String getCommandList(){
        String vaCommands= "";
        for (String command:aValidCommands){
            vaCommands += command +" ";
        }
        return vaCommands;
    }
} // Command
