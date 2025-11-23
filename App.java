public class App {
    private static Board pitch = new Board();
    private static Team player = new Team(pitch);
    public static void main(String[] args){
        pitch.display();
        player.play();
    }
}
