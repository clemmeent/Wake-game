 


/**
 * Décrivez votre classe Command ici.
 *
 * @author (votre nom)
 * @version (un numéro de version ou une date)
 */
public class Command
{
    private String aCommandWord;
    private String aSecondWord;
    public String getCommandWord(){
        return this.aCommandWord;
    }
    public String getSecondWord(){
        return this.aSecondWord;
    }
    public Command (final String pCommandWord, final String pSecondWord){
        this.aCommandWord=pCommandWord;
        this.aSecondWord=pSecondWord;
    }
    public boolean isUnknown(){
        return (this.aCommandWord == null);
    }
    public boolean hasSecondWord(){
        if (this.aSecondWord==null){
            return false;
        }
        else{
            return true;
        }
    }
}