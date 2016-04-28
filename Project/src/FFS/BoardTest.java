package FFS;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * Created by Hans on 26/03/14.
 */
public class BoardTest {
    public static void main(String[] args) {
        final Board board = new Board(15,29);
        final GameFrame frame = new GameFrame(board);
        //frame.setSize(800,720);
        //frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //frame.setResizable(false);
        //frame.setVisible(true);
        //final Action doOneStep = new AbstractAction(){
          //  public void actionPerformed(ActionEvent e){
         //       board.tick(board);
          //  }
       // };
       // final Timer clockTimer = new Timer (150, doOneStep);
        //clockTimer.setCoalesce(true);
       // clockTimer.start();
    }
}
