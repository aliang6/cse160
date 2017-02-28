import java.util.List;

/**
 * Poker Hand
 * 
 * @author Andy Liang
 * @version February 21, 2017
 */

public class PokerHand
{
    private Card[] hand;
    
    public PokerHand(List<Card> cards){
        hand[0] = cards.get(0);
        hand[1] = cards.get(1);
        hand[2] = cards.get(2);
        hand[3] = cards.get(3);
        hand[4] = cards.get(4);
    }
    
    public String toString(){
        return hand[0].toString() + hand[1].toString() + hand[2].toString() + hand[3].toString() + hand[4].toString();
    }
}
