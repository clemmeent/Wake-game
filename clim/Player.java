import java.util.HashMap;
import java.util.Stack;
/**
 * Décrivez votre classe Player ici.
 *
 * @author (votre nom)
 * @version (un numéro de version ou une date)
 */
public class Player
{
    // variables d'instance - remplacez l'exemple qui suit par le vôtre
    private String aName;
    private double aWeight;
    private Room aLocalisation;
    private HashMap<String, Item> aInventary;
    private Stack<Room> aLastRooms;    
    private double aStrong;
    
    public Player(final String pName){
    this.aName = pName;
   
    this.aWeight = 700;
    this.aInventary = new HashMap<String,Item>();
    this.aLastRooms = new Stack<Room>();
    }
    public boolean lastRoomsIsEmpty(){
        return this.aLastRooms.empty();
    }
    public void takeItem(final String pStringItem, final Item pItem){
        this.aInventary.put(pStringItem, pItem);
    }
    public void dropItem(final String pStringItem){
        this.aInventary.remove(pStringItem);
    }
    public Item getItem(final String pItem){
        return this.aInventary.get(pItem);
    }
    public String getName(){
        return this.aName;
    }
    public void setName(final String pName){
        this.aName = pName;
    }
    public double getWeight(){
    return this.aWeight;
    }
    public void setWeight(final double pWeight){
        this.aWeight = pWeight;
    }
    public Room getLocalisation(){
        return this.aLocalisation;
    }
    public void setLocalisation(final Room pLocalisation){
        this.aLocalisation = pLocalisation;
    }
    public Room getLastRoom(){
        return this.aLastRooms.pop();
    }
    public void setLastRoom(final Room pLastRoom){
        this.aLastRooms.push(pLastRoom);
    }
    public double getStrong(){
        return this.aStrong;
    }
    public void setStrong(final double pStrong){
        this.aStrong = pStrong;
    } 
    public boolean canITake(final double pWeight){
    return (pWeight+this.aWeight <= this.aStrong) ;
    }
}
