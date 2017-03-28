
/**
 * Nettles Class - Stings player and makes them sleep for a number of turns
 */
public class Nettles extends Monster
{
    public Nettles(){
        super();
    }
    
    /**
     * Make the player sleep for a number of turns
     */
    public int sting(int turns){
        return turns += (int)(Math.random() * 4 + 1);
    }
}
