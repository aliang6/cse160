import java.util.ArrayList;
import java.util.Random;

/**
 * Poker Deck
 * 
 * @author Andy Liang
 * @version February 21, 2017
 */

public class PokerDeck
{
    private ArrayList<Card> deck = new ArrayList<Card>();
    private Random numberGen;
    private int counter = 51;
    
    public PokerDeck(){
        numberGen = new Random();
        constructDeck();
        shuffleDeck();
    }
    
    private void constructDeck(){
        for(int i = 0; i < 4; i++){
            for(int j = 0; j < 13; j++){
                deck.add(new Card(i, j));
            }
        }
    }
    
    private void shuffleDeck(){
        Card temp;
        int shuffleInt = 52;
        for(int i = 0; i < 51; i++){
            int cardToSwap = numberGen.nextInt(shuffleInt) + i;
            temp = deck.get(i);
            deck.set(i, deck.get(cardToSwap));
            deck.set(cardToSwap, temp);
            shuffleInt--;
        }
    }
    
    public boolean hasMoreCards(){
        return !deck.isEmpty();
    }
    
    public Card nextCard(){
        counter--;
        return deck.remove(counter + 1);
    }
    
    public String toString(){
        String ret = "";
        for(int secondCounter = counter; secondCounter > 0; secondCounter--){
            ret += deck.get(secondCounter);
        }
        System.out.println(ret);
        return ret;
    }
    
}
