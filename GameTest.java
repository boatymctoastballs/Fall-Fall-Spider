package ffs;

/**
 * Created by Christopher on 2016-04-20.
 */
public class GameTest {
    private GameTest(){}

    public static void main(String[] args){
        final int boardWidth = 10;
        final int boardHeight = 30;
        final Board board = new Board(boardWidth, boardHeight);
        final GameFrame frame = new GameFrame(board);
        board.tick(board);
    }
}
