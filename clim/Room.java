 
import java.util.HashMap;
import java.util.Set;
/**
 * renvoie les informations des rooms
 */
public class Room
{
    private String aDescription;
    private HashMap<String, Room>aExits;
    private String aImage;
    private HashMap<String, Item> aItems;
    public Room (final String pDescription,final String pImage){
        this.aDescription = pDescription;
        this.aImage=pImage;
        aExits= new HashMap<String,Room>();
    }//Room()
    /**
     * permet d'obtenir la description
     */
    public String getDescription(){
        return this.aDescription;
    }//getDescription()
    /**
     * permet d'acceder à une sortie
     */
    public Room getExit (String pdirection) { 
        return aExits.get(pdirection);
    }
    /**
     * renvoi des informations sur la position
     */
    public String getLongDescription(){
        return "You are "+this.aDescription+"\n"+getExitString();
    }
    /**
     * indique les sorties possibles
     */
    public String getExitString(){ 
    String vExits = "Exits : ";
    Set<String> keys=aExits.keySet();
    for(String exit : keys){
        vExits +=" "+ exit;
    }
    return vExits ;
    }
    public void addItem(final String pNomItem, final Item pItem )
    {
    this.aItems.put(pNomItem, pItem);
    }

    public Item getItem(String pItem){
        return this.aItems.get(pItem);
    }
    public void removeItem(final String pNomItem)
    {
    this.aItems.remove(pNomItem);
    }
    public void setExits(final String direction, final Room pNeighbor){
        aExits.put(direction, pNeighbor);
    }
    public String getImages(){
        return this.aImage;
    }
} // Room
