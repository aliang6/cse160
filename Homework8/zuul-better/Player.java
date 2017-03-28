
/**
 * Player Class
 * 
 * @author Andy
 * @version 03061027
 */
public class Player extends Character
{
    private int maxWeight, currentWeight, health;
    private boolean alive;
    
    /**
     * Constructor
     */
    public Player(int maxWeight, int health){
        super();
        this.maxWeight = maxWeight;
        this.health = health;
        alive = true;
    }
    
    /**
     * Player accessors
     */
    
    public int getCurrentWeight(){
        return currentWeight;
    }
    
    public int getMaxWeight(){
        return maxWeight;
    }
    
    public int getHealth(){
        return health;
    }
    
    public boolean living(){
        return alive;
    }
    
    /**
     * Health modifier
     */
    public void minusHealth(){
        health -= 2;
        if(health <= 0){
            alive = false;
        }
    }
    
    /**
     * Check if player has an item
     */
    
    public boolean hasItem(String item){
        return (items.get(item) != null);
    }
    
    public void take(String key, Item itemToAdd){
        items.put(key, itemToAdd);
        currentWeight += itemToAdd.getWeight();
    }
    
    public void drop(String key){
        currentWeight -= items.get(key).getWeight();
        items.remove(key);
    }
    
    public void loseItem(String key){
        items.remove(key);
    }
    
    public void eatCookie(){
        items.remove("cookie");
        maxWeight += 10000;
    }
}
