
import java.util.Arrays;
import java.util.Collections;

public class Deck {
    private Card[] cards = new Card[52];
    private int cardToDeal;
    private boolean lastCard;

    public Deck(){
        String[] Suits ={"Hearts", "Diamonds", "Spades","Clubs"};
        int[] Value = {2,3,4,5,6,7,8,9,10,11,12,13,14};
        String[] Ranks = {"2","3","4","5","6","7","8","9","10","Jack","Queen","King","Ace"};
        int i = 0;
        //while (i < cards.length){
            for(String s:Suits){
                for (int t = 0; t< 13; t++ ){
                    cards[i] = new Card(Value[t], s, Ranks[t]);
                    i++;
                }
            }
        //}
        cardToDeal = cards.length;
        lastCard = false;
    }
    public void shuffle(){
        Collections.shuffle(Arrays.asList(cards));
    }
    public Card deal(){
        if (cardToDeal >=0){
        cardToDeal--;
        return cards[cardToDeal];}
        lastCard = true;
        return null;
    }
    public boolean getLastCard(){
        return lastCard;
    }
}
