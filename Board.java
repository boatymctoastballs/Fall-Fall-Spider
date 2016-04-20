package ffs;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Christopher on 2016-04-20.
 */
public class Board {

    //Field
    private final int width;
    private final int height;
    private final ObjectType[][] board;
    private static final Random random = new Random();
    private Poly obstacle = null;
    private int obstacleX;
    private int obstacleY;
    private final ArrayList<BoardListener> listener = new ArrayList<BoardListener>();

    //getters and setters

    public int getWidth(){
        return this.width;
    }

    public int getHeight(){
        return this.height;
    }

    public void setObjectType(int x, int y, ObjectType objecttype){
        this.board[x][y] = objecttype;
    }

    public ObjectType getObjectType(int x, int y){
        return this.board[x][y];
    }


    Board(int width, int height){
        this.width = width;
        this.height = height;
        //width += 1;
        //height += 1;

        this.board = new ObjectType[width][height];
        for(int x = 0; x< width; x++){
            for(int y = 0; y<height; y++){
                this.board[x][y] = ObjectType.OUTSIDE;
            }
        }

        for(int x = 1; x< width-1; x++){
            for(int y = 1; y<height-1; y++){
                this.board[x][y] = ObjectType.EMPTY;
            }
        }
    }

    public void addBoardListener(BoardListener listener){
        this.listener.add(listener);
    }

    private void notifyListener(){
        for(BoardListener boardListener : listener){
            boardListener.boardChanged();
        }
    }


    public void tick(Board board){
        notifyListener();
    }



}
