public class Library extends Deck{
    private Card[] cards = new Card[15];
    private int cardToDeal;
    private int lastCard;
    public Library(){
        super();
        super.shuffle();
        for (int i=0; i < cards.length;i++){
            cards[i] = super.deal();
        }

    }

}
