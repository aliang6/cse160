
/**
 * Ogre Class - Hits player and causes damage
 */
public class Ogre extends Monster
{
    public Ogre(){
        super();
    }
    
    public void hit(Player player){
        System.out.println("The ogre hit you with his giant fists!");
        player.minusHealth();
    }
}
