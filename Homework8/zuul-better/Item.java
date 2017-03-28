
/**
 * Item Class for Game
 * 
 * @author Andy Liang 
 * @version 03062017
 */
public class Item
{
    private String description;
    private int weight;
    
    /**
     * Constructor
     */
    public Item(String description, int weight){
        this.description = description;
        this.weight = weight;
    }
    
    /**
     * Accessors
     */
    public String getDescription(){
        return description;
    }
    
    public int getWeight(){
        return weight;
    }
}