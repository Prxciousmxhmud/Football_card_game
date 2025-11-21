import java.util.Scanner;

public class Board {
    private String[][] board;
    private Card[][] cardBoard;
    private int formation;
    private Scanner scan = new Scanner(System.in); 
    private int p_currentRow; // what row the player can play against 
    private Deck d;

    public Board(int formation){
        d = new Deck();
        d.shuffle();
        System.out.println("1. 4-2-3-1 \n2. 3-4-2-1 \n3. 4-3-3 \n4. 4-4-2 \n5. 4-1-4-1 \n6. 4-4-1-1 \n7. 3-5-2");
        while (!(formation > 0 && formation <8) ) { 
            formation = scan.nextInt();
        }
        this.formation = formation;
        board = new String[][]{{" - "},{" - ", " - " , " - " , " - "},{" - ", " - "},{" - ", " - ", " - "}, {" - "},{" - "},{" - ", " - ", " - "},{" - ", " - "}, {" - ", " - " , " - " , " - "},{" - "} };
        cardBoard = new Card[][]{{d.deal()}, {d.deal(), d.deal(),d.deal(),d.deal()},{d.deal(),d.deal()},{d.deal(),d.deal(),d.deal()},{d.deal()},{d.deal()},{d.deal(),d.deal(),d.deal()},{d.deal(),d.deal()},{d.deal(),d.deal(),d.deal(),d.deal()},{d.deal()}};
        p_currentRow = 4; 

    }

    public void display(){
        // computer mirrors formation 
        switch (formation){
            case 1:
            // 4-2-3-1
            central(board);
            // non-essential 
            case 2: 
            // 3-4-2-1
            board = new String[][]{{}};
            central(board);
            p_currentRow = 4;
            case 3: 
            // 4-3-3
            board = new String[][]{{}};
            central(board);
            p_currentRow = 3;

            case 4: 
            // 4-4-2
            board = new String[][]{{}};
            central(board);
            p_currentRow = 3;        
            case 5: 
            // 4-1-4-1
            board = new String[][]{{}};
            central(board);
            p_currentRow = 4;         
            case 6: 
            // 4-4-1-1
            board = new String[][]{{}};
            central(board);
            p_currentRow = 4;     
            case 7: 
            // 3-5-2
            board = new String[][]{{}};
            central(board);
            p_currentRow = 3;
            default:

        }
    }

    public void central(String[][] board){
        for (String[] Board1 : board) {
            // left padding 
            for (int i = 0; i< (5 - Board1.length);i++){
                System.out.print(" ");}
            for (String b: Board1){
            System.out.print(b);
            }
            System.out.println("\n");
        }
    }

    public int getPcurrentRow(){
        return p_currentRow;
    }
    
    public Card[][] getBoard(){
        return cardBoard;
    }
}



