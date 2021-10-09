package controller;

import javafx.scene.media.AudioClip;
import model.GameManager;
import model.thread.Dropping;
import view.FrameTetris;
import view.PanelTetris;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Controller implements KeyListener {


    public FrameTetris frame;
    public PanelTetris panel;
    public GameManager gameManager;
    public Controller() {
        panel=new PanelTetris(this);

        frame=new FrameTetris(panel,this);
        frame.setVisible(true);
        //panel.addKeyListener(this);
        gameManager=new GameManager(this,panel);

        gameManager.init();

        Dropping dropping=new Dropping(this);
        dropping.run();



        //gameManager.addObserver(panel);
        //panel.addKeyListener(this);
      //  frame.addKeyListener(this);

       // gameManager.addObserver(panel);

    }


    @Override
    public void keyTyped(KeyEvent e) {

    }

    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP:
                gameManager.rotate(-1);
                break;
            case KeyEvent.VK_DOWN:
                gameManager.rotate(+1);
                break;
            case KeyEvent.VK_LEFT:
                gameManager.move(-1);
                break;
            case KeyEvent.VK_RIGHT:
                gameManager.move(+1);
                break;
            case KeyEvent.VK_SPACE:
                gameManager.dropDown();
                gameManager.score += 1;
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
