
/**
 * Write a description of class Monster here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
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
        int x = (int)(Math.random() * rooms.length);
        currentRoom = rooms[x];
    }
}
