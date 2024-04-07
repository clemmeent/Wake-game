import java.util.HashMap;
import java.util.Set;

public class ItemList
{
    private HashMap<String, Item> aInventary;

    /**
     * Constructeur d'objets de classe ItemList
     */
    public ItemList()
    {
        this.aInventary = new HashMap<>();
    }

    public String getItemString(){
        String vReturnString = "";
        Set<String> vKeys = this.aInventary.keySet();
        for(String vItem : vKeys)
        {
            vReturnString += " a "+vItem+"\n";
        }
        return vReturnString;
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
}
