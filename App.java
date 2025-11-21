public class App {
    private static Board pitch = new Board(1);
    private static Team player = new Team(pitch);
    public static void main(String[] args){
        pitch.display();
        player.play();
    }
}
