
/**
 * Monster Class
 */
public class Monster extends Character
{
    public Monster(){
        super();
    }
    
    /**
     * Method for random room movement
     */
    public void moveMonster(Room[] rooms){
        int x = (int)(Math.random() * (rooms.length - 1));
        currentRoom = rooms[x];
    }
}
