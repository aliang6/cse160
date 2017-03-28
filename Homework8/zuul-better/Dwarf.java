
/**
 * Dwarf Class - Steals Items
 */
public class Dwarf extends Monster
{
    public Dwarf(){
        super();
    }
    
    public void stealItem(Player player, String[] items){
        if(player.getMap().isEmpty()){
          System.out.println("You had no items. The dwarf wasn't able to steal anything and ran away.");
        }
        else{
            for(int i = 0; i < items.length; i++){
                if(player.hasItem(items[i])){
                    System.out.println("The dwarf took your " + items[i]);
                    this.items.put(items[i], player.getMap().get(items[i])); // Give Dwarf the item
                    player.loseItem(items[i]);
                    return;
                }
            }
        }
    }
}
