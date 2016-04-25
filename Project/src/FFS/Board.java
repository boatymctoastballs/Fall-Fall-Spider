package FFS;


import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import java.util.Random;

public class Board {
    public int width;
    public int height;
    public SquareType[][] board;
    private List<BoardListener> listener = new ArrayList<BoardListener>();
    private Poly spider;
    private int spiderX;
    private int spiderY;
    private Poly block;
    private int blockX;
    private int blockY;
    public static int score=0;
    private int cooldown=0;
    private int numberOfBlocks;
    private static Random random = new Random();

    public int getWidth() {  //get width of the board
        return width;
    }

    public int getHeight() { //get height of the board
        return height;
    }

    public Board(int width, int height) {  //the board, creates a board
        this.width = width;
        this.height = height;
        board = new SquareType[width][height];

        for (int column = 0; column<width; column++){ //makes all squares as OUTSIDE
            for ( int row = 0; row<height; row++){
                board[column][row]=SquareType.OUTSIDE;
            }
        }
        for (int column = 1; column<width-1; column++){ //makes inner squares as EMPTY
            for ( int row = 1; column<row-1; row++){
                board[column][row]=SquareType.EMPTY;
            }
        }

        //place spider
        spiderY = height / 2;
        spiderX = width / 2;
        SquareType[] types = SquareType.values();
        Poly e = Minomaker.getSpiderPoly();  //get spider poly
        this.spider = e;
    }

    private void notifyListeners(){ //function when to make changes
        for (BoardListener boardListener: listener){
            boardListener.boardChanged();
        }
    }

    public void addBoardListener(BoardListener bl) { //to add listeners
        listener.add(bl);

    }


    public void tick(Board myboard){ //function of every tick (timer)
        score+=10;

        if (block == null) { //if there is no block on board, spawn block
            blockX = myboard.getWidth()/2;
            blockY = getHeight()/2;
            this.block = Minomaker.getPoly(random.nextInt(Minomaker.getNumberOfTypes()));
        }

        else {//if there is blocks
            this.blockY--;

            //spiderX++;
            //checkCollision();
            notifyListeners();
                //do what

        }

    }

    public SquareType pos(int x, int y){
        if (spider == null){
            return board[x][y];
        }
        if (x<spiderX){
            return board[x][y];
        }
        if (x > spiderX+spider.getWidth()-1){
            return board[x][y];
        }
        if ( y<spiderY){
            return board[x][y];
        }
        if ( y>spiderY+spider.getHeight()-1){
            return board[x][y];
        }
        int polyX = x-spiderX;
        int polyY = y-spiderY;
        spider.setPosXY(polyX,polyY);
        if (spider.getSquare() != null){
            return spider.getSquare();
        }
        return board[x][y];
    }


    final Action moveRight = new AbstractAction() {
        @Override
        public void actionPerformed(ActionEvent e) {
            for(int x = 0; x<spider.getWidth();x++){
                for(int y = 0;y<spider.getHeight(); y++){
                    if(spider.getSquaretypeAtXY(x,y) == SquareType.EMPTY){
                        return;
                    }
                }
            }
            if (spiderX + spider.getWidth() < width){
                spiderX++;
                notifyListeners();

            }
        }
    };

    final Action moveLeft = new AbstractAction() {
        @Override
        public void actionPerformed(ActionEvent e) {
            for(int x=0; x<spider.getWidth();x++){
                for(int y = 0; y<spider.getHeight();y++){
                    //start
                    if(spider.getSquaretypeAtXY(x,y) == SquareType.EMPTY) {
                        return;
                    }
                }
            }
            if (spiderX > 0){
                spiderX--;
                notifyListeners();
            }
        }
    };

}
