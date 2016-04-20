package ffs;

import org.omg.CORBA.OBJECT_NOT_EXIST;

import javax.swing.*;
import java.awt.*;
import java.util.AbstractMap;
import java.util.EnumMap;

/**
 * Created by Christopher on 2016-04-20.
 */
public class GameComponent extends JComponent implements BoardListener {
    private final Board board;
    private final AbstractMap<ObjectType, Color> enumMap;

    GameComponent(Board board){
        this.board = board;
        AbstractMap<ObjectType, Color> enumMap = new EnumMap<ObjectType, Color>(ObjectType.class);
        //enumMap.put(ObjectType.EMPTY, Color.WHITE);
        enumMap.put(ObjectType.OUTSIDE, Color.CYAN);
        enumMap.put(ObjectType.SPIDER, Color.BLACK);
        enumMap.put(ObjectType.SHORT, Color.GREEN);
        enumMap.put(ObjectType.LONG, Color.RED);
        enumMap.put(ObjectType.MEDIUM, Color.BLUE);

        this.enumMap = enumMap;
    }

    @Override
    public Dimension getPreferredSize(){
        return Toolkit.getDefaultToolkit().getScreenSize();
    }

    //@Override
    protected void PaintComponent(Graphics g){
        final int blockSizeX = 35;
        final int blockSizeY = 35;
        final int blockSizeFillX = 34;
        final int blockSizeFillY = 34;

        for(int row = 0; row < board.getWidth(); row++){
            for(int col = 0; col < board.getHeight(); col++){
                ObjectType object = board.getObjectType(col, row);
                Color color = enumMap.get(object);
                if(color != null){
                    g.setColor(color);
                    g.fillRect(col*blockSizeX,row*blockSizeY, blockSizeFillX, blockSizeFillY);
                }
                else{
                    g.setColor(Color.GRAY);
                    g.fillRect(col*blockSizeX,row*blockSizeY, blockSizeFillX, blockSizeFillY);
                }
            }
        }

    }

    @Override
    public void boardChanged(){
        repaint();
    }
}
