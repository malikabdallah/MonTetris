package view;

import controller.Controller;
import model.ColorConstantes;
import model.FormsConstantes;

import javax.swing.*;
import java.awt.*;
import java.util.Observable;
import java.util.Observer;

public class PanelTetris extends JPanel implements Observer  {

    public Controller controller;



    public PanelTetris( Controller controller){
        this.controller=controller;



        this.addKeyListener(controller);
    }

    private void drawPiece(Graphics g) {
        g.setColor(ColorConstantes.tetraminoColors[controller.gameManager.currentPiece]);
        for (Point p : FormsConstantes.Tetraminos[controller.gameManager.currentPiece][controller.gameManager.rotation]) {
            g.fillRect((p.x + controller.gameManager.pieceOrigin.x) * 26,
                    (p.y + controller.gameManager.pieceOrigin.y) * 26,
                    25, 25);
        }
    }


    private void drawNextPieces(Graphics g) {
        g.setColor(ColorConstantes.tetraminoColors[controller.gameManager.nextpiece]);
        for (Point p : FormsConstantes.Tetraminos[controller.gameManager.nextpiece][controller.gameManager.rotationBis]) {
            g.fillRect((p.x + controller.gameManager.nextPiece.x) * 26,
                    (p.y + controller.gameManager.nextPiece.y) * 26,
                    25, 25);
        }

    }


    @Override
    public void paintComponent(Graphics g)
    {

        // Paint the well
        g.fillRect(0, 0, 26*12, 26*23);
        for (int i = 0; i < 12; i++) {
            for (int j = 0; j < 23; j++) {
                g.setColor(controller.gameManager.well[i][j]);
                g.fillRect(26*i, 26*j, 25, 25);

            }
        }


        g.fillRect(338, 0, 26*5, 26*5);
        for (int i = 12; i < 17; i++) {
            for (int j = 23; j < 30; j++) {
                g.setColor(Color.black);
                g.fillRect(26*i, 26*j, 25, 25);

            }
        }
        drawNextPieces(g);



        // Display the score
        g.setColor(Color.WHITE);
        g.drawString("" + controller.gameManager.score, 19*12, 25);

        // Draw the currently falling piece
        drawPiece(g);
    }


    @Override
    public void update(Observable o, Object arg){

        repaint();
    }
}
