import java.util.ArrayList;
import java.util.Set;
import java.util.HashMap;

/**
 * Character Class
 */
public class Character
{
    protected Room currentRoom;
    protected HashMap<String, Item> items;

    /**
     * Constructor for objects of class Character
     */
    public Character()
    {
        items = new HashMap<String, Item>();
    }
    
    /**
     * Accessors
     */
    public Room getCurrentRoom(){
        return currentRoom;
    }
    
    /**
     * Accessor for a single item
     */
    public Item getItem(String key){
        return items.get(key);
    }
    
    /**
     * Returns the item HashMap
     */
    public HashMap<String, Item> getMap(){
        return items;
    }
    
    /**
     * Modifiers
     */
    public void setNewRoom(Room newRoom){
        currentRoom = newRoom;
    }
    
    /**
     * Print out all the items in the character's inventory
     */
    public String printCharacterItems(){
        String returnItems = "Items:";
        Set<String> keys = items.keySet();
        for(String itemNames: keys){
            returnItems += " " + itemNames;
        }
        return returnItems;
    }
 
    
}
