
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Team {
    private Library library; 
    private ArrayList<Card> discard;
    private Library C_library; 
    private ArrayList<Card> C_discard;
    private Card[] currentRow;
    private Card[] C_currentRow;
    private Card[][] board; 
    private int currentR; 
    private int C_currentR;
    private Card currentCard;
    private Scanner scan = new Scanner(System.in); 
    private boolean goal;
    private Random rand; 

    
    public Team(Board pitch){
        C_currentR = pitch.getPcurrentRow(); // starts on the first row of the computer's side 
        currentR = C_currentR+ 1;
        board = pitch.getBoard();
        currentRow = board[currentR];
        C_currentRow = board[C_currentR];
// find a way to store half the board 
        library = new Library();
        library.shuffle();
        discard = new ArrayList<>();
        C_library = new Library();
        C_library.shuffle();
        C_discard = new ArrayList<>();
        goal = false;
        rand = new Random(); 
    }

    public void displayRow(Card[] row){
        String text = "-";
        int totalWidth = 10;
        int padding = (totalWidth - row.length) / 2;
        for (int i = 0; i<row.length ; i++){
        System.out.print(String.format("%" + padding + "s%s%" + padding + "s", "", text, ""));}
    }

    public void play(){
        while (!goal && !library.getLastCard()){
            if (currentR < board.length -1){
                displayRow(currentRow);
                System.out.println("\nPick a player from the row");            
                int compare = scan.nextInt() - 1; // pick which card to go against 
                while(!(compare >= 0 && compare < currentRow.length) ){// Input is not valid 
                    compare = scan.nextInt() -1 ;
                    System.out.println("Make sure you're picking a player on the pitch");            
                }
                currentCard = library.deal(); 
                System.out.println("\nSelecting a player from your squad");            
                if (currentCard.getValue() >= currentRow[compare].getValue()){
                    currentR++;
                    System.out.println("Well done, you can now advance");
                    discard.add(currentCard);
                    currentRow = (currentR < board.length-1) ? board[currentR]: board[board.length-1]; // makes sure index is in bounds
                }
                else{
                    System.out.println("Oh no you couldn't get past the defence");
                    compPlay();
                }
            }
            else if(currentR == board.length-1){
                    // score against the goalie 
                System.out.println("\nTake your shot");
                currentCard = library.deal();
                if (currentCard.getValue() >= currentRow[0].getValue()){
                    currentR++; // index out of bounds to show end of turn 
                    System.out.println("GOAL");
                    discard.add(currentRow[0]);
                    goal = true; 
                }
                else{ 
                    System.out.println("Unlucky shot, seems as though the home advantage is not working in their favour");
                    System.out.println("Computer now has possesion");
                    compPlay();
                }
            }
        }
    }

    public void compPlay(){
        while(!goal && !C_library.getLastCard()){
            if (C_currentR> 0){
                System.out.println("\nLet's see if this player can handle the pressure");
                int compare = rand.nextInt(C_currentRow.length);
                currentCard = C_library.deal();
                System.out.println("Looks like it's going to be a one v one, who is going to come out on top");
                if (currentCard.getValue()>= C_currentRow[compare].getValue()){
                    System.out.println("\nAnd it seems as though they can do it, home team just can't measure up");
                    C_currentR--;
                    C_discard.add(currentCard);
                    System.out.println("Computer advances through player's defence");
                    C_currentRow = (C_currentR > 0) ? board[C_currentR]: board[0]; // makes sure index is in bounds                
                }
                else{
                    System.out.println("\nAnd the home team is an inpenatrable wall, possession to the home team");
                    play();
                }
            }
            else if (C_currentR ==0){
                System.out.println("And the away team is making a run for it, will this be making the scoresheet?");
                currentCard = C_library.deal();
                System.out.println("THEY SHOOT");
                if (currentCard.getValue() >= C_currentRow[0].getValue()){
                    System.out.println("\nTHEY SCORE!!!!");
                    C_currentR --; // end of a turn
                    discard.add(currentCard);
                    System.out.println("Great performance from the away team's attacker, hope we'll be seeing more of them in the second half");
                    goal = true;
                }else{
                    System.out.println("\nAnd they've squandered it, great performance from the goalie");
                    play();
                }
            }
        }
    }
}
