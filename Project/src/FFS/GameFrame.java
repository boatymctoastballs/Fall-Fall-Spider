package FFS;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Hans on 22/03/14.
 */
public class GameFrame extends JFrame {


    public GameFrame(final Board board){
        super("FFS");
        setFocusable(true);
        final GameComponent gameComponent = new GameComponent(board);
        this.setLayout(new BorderLayout());
        board.addBoardListener(gameComponent);
        this.add(gameComponent,BorderLayout.CENTER);




        JLabel scoreLabel = new JLabel("Score: " + board.getScore());
        this.add(scoreLabel,BorderLayout.NORTH);




        createMenu();
        this.pack();
        this.setSize(800,720);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setVisible(true);
        final Action doOneStep = new AbstractAction(){
            public void actionPerformed(ActionEvent e){
                board.tick();
                gameComponent.boardChanged();
            }
        };
        final Timer clockTimer = new Timer (150, doOneStep);
        clockTimer.setCoalesce(true);
        clockTimer.start();



        gameComponent.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("LEFT"),("doActionMapL"));
        gameComponent.getActionMap().put("doActionMapL",board.moveLeft);

        gameComponent.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("RIGHT"),("doActionMapR"));
        gameComponent.getActionMap().put("doActionMapR",board.moveRight);
    }


    private void createMenu(){
        final JMenu file = new JMenu("File");
        file.addSeparator();
        JMenuItem exit = file.add(new JMenuItem("exit"));
        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int option = JOptionPane.showConfirmDialog(null, "exit", "choose", JOptionPane.YES_NO_OPTION);
                if(option == JOptionPane.YES_OPTION){
                    System.exit(0);
                }
            }
        });
        file.add(exit);
        final JMenuBar bar = new JMenuBar();
        bar.add(file);
        this.setJMenuBar(bar);

    }

}

