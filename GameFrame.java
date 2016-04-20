package ffs;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Christopher on 2016-04-20.
 */
public class GameFrame extends JFrame {
    GameFrame(Board board){
        super("Fall Fall Spuder");
        final GameComponent game = new GameComponent(board);
        board.addBoardListener( game);
        this.add(game, BorderLayout.CENTER);
        createMenu();

        this.pack();
        this.setVisible(true);
    }

    private void createMenu() {
        final JMenu file = new JMenu("File");
        file.addSeparator();
        JMenuItem exit = file.add(new JMenuItem("exit"));
        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int option = JOptionPane.showConfirmDialog(null, "exit", "choose", JOptionPane.YES_NO_OPTION);
                System.exit(0);
            }
        });

        file.add(exit);
        final JMenuBar menuBar = new JMenuBar();
        menuBar.add(file);
        this.setJMenuBar(menuBar);
    }

}
