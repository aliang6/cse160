
/**
 * Playing Cards
 * 
 * @author Andy Liang
 * @version February 21, 2017
 */
public class Card
{
    private String[] suits = {"Diamonds", "Clubs", "Hearts", "Spades"};
    private String[] ranks = {"A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K"};
    private int suit;
    private int rank;
    
    public Card(int suit, int rank){
        this.suit = suit;
        this.rank = rank;
    }
    
    public String getSuit(){
        return suits[suit];
    }
    
    public String getRank(){
        return ranks[rank];
    }
    
    public String toString(){
        return(ranks[rank] + suits[suit] + " ");
    }
}
