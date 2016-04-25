package FFS;

import javax.swing.*;
import java.awt.*;
import java.util.EnumMap;

/**
 * Created by Hans on 22/03/14.
 */
public class GameComponent extends JComponent implements BoardListener {
    private final Board board;
    private EnumMap<SquareType, Color> enumMap;

    public GameComponent(Board board) {
        this.board = board;


        EnumMap<SquareType, Color> enumMap =  new EnumMap<SquareType, Color>(SquareType.class);
        enumMap.put(SquareType.SHORT, Color.ORANGE);
        enumMap.put(SquareType.LONG, Color.YELLOW);
        enumMap.put(SquareType.DROPLET, Color.BLUE);
        enumMap.put(SquareType.SPIDER, Color.BLACK);
        this.enumMap = enumMap;
    }

    @Override
    public Dimension getPreferredSize() {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        screenSize.setSize(screenSize.getWidth() - 100, screenSize.getHeight() - 100);
        return screenSize;
    }

    @Override
    protected void paintComponent (Graphics g){
        for (int x = 0; x < board.getWidth() ; x++) {
            for (int y = 0; y < board.getHeight() ; y++) {
                SquareType square = board.pos(x,y);
                Color color =enumMap.get(square);
                if (color !=null) {
                    g.setColor(color);   //indicates non-null surface
                    g.fillRect(x * 22, y * 22, 21, 21);
                }
                else{
                    g.setColor(Color.GRAY);  //indicates null surface
                    g.fillRect (x*22,y*22,21,21);
                }
            }

        }
    }

    @Override
    public void boardChanged() {
        System.out.println("heja erik"+" "+ Board.score);
        repaint();

    }
}