package view;

import controller.Controller;
import javafx.scene.layout.Pane;
import model.thread.Dropping;

import javax.swing.*;
import java.awt.*;
import java.util.Observable;
import java.util.Observer;

public class FrameTetris extends JFrame  {


    private PanelTetris panelTetris;
    public FrameTetris(PanelTetris panel, Controller controller) {
        this.setTitle("TETRIS ");
        this.setSize(12*40+10, 26*23+25);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setContentPane(panel);
        this.setVisible(true);
        this.setAlwaysOnTop(true);
        this.addKeyListener(controller);
        panelTetris=panel;





    }


}
