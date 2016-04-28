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
    private Poly obstacle = null;
    private int obstacleX;
    private int obstacleY;
    public static int score=0;
    private int cooldown=0;
    private int numberOfBlocks;
    private static Random random = new Random();
    private int life = 2;
    private int finalScore;


    public int getWidth() {  //get width of the board
        return width;
    }

    public int getHeight() { //get height of the board
        return height;
    }

    public int getScore() { return score; }


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
            for ( int row = 1; row<height-1; row++){
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


    public void tick(){ //function of every tick (timer)
        if (checkCollision()){
            loseCheck();
        }
        moveObstacle();
        spawnObstacle();


         //if there is no block on board, spawn block
            //blockX = myboard.getWidth()/2;
            //blockY = getHeight()/2;
            //this.block = Minomaker.getPoly(random.nextInt(Minomaker.getNumberOfTypes()));
        }

        //else {//if there is blocks
            //this.blockY--;
        //    moveObstacle();
            //spiderX++;
            //checkCollision();
                //do what
        //}
        //notifyListeners();

    public SquareType getSquareType(int x, int y){
        if(x>=0 || x< width-1 || y>=0 || y<height-1){
            return board[x][y];
        }
        return null;
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

    public void setSquareType(int x, int y, SquareType squareType){
        board[x][y] = squareType;
        notifyListeners();
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

    //Spawning random obstacle
    private void moveObstacle(){
        for (int row = 1; row<height-1; row++){
            for (int column = 1; column<width-1; column++){
                if (getSquareType(column, row) == SquareType.OBSTACLE) {
                    if (pos(column, row - 1) == SquareType.OUTSIDE) {
                        setSquareType(column, row, SquareType.EMPTY);
                    }
                    else {
                        setSquareType(column, row - 1, SquareType.OBSTACLE);
                        setSquareType(column, row, SquareType.EMPTY);


                    }
                }
            }
        }
        notifyListeners();
    }

    private void spawnObstacle(){
        boolean spawnNew = true;
        Random random = new Random();
        int counter = 0;

        for (int column = 1; column<width-2; column++){
            for (int row = height-6; row<height-2; row++){
                if(getSquareType(column, row) != SquareType.EMPTY){
                    spawnNew = false;
                    break;
                }
            }
        }
        if(spawnNew){
            score+=1;
            for (int x = 1; x<width-1; x++){
                if(random.nextInt(2)==1 && counter < width-2){
                    setSquareType(x,height-2,SquareType.OBSTACLE);
                    counter++;
                }
            }
        notifyListeners();
        }
    }

    private boolean checkCollision(){
        for(int x = 1; x < width-1; x++){
            if(getSquareType(spiderX,spiderY+1)==SquareType.OBSTACLE){
                return true;
            }
        }
        return false;
    }

    private void loseCheck(){
        if(life==0){
            gameOver();

            System.out.println("Game Over, noob");
        }
        else if(checkCollision()){
            this.life--;
            System.out.println(life);
        }
        System.out.println(score);
    }

    private void gameOver(){
        for (int column = 1; column<width-1; column++){
            for (int row = 1; row<height-1; row++){
                setSquareType(column,row,SquareType.OUTSIDE);
            }
        }
        finalScore = score;
        System.out.println(finalScore);

    }



}
