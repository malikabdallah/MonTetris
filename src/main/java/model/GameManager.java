package model;

import controller.Controller;
import view.PanelTetris;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Observable;

public class GameManager extends Observable {

    private Controller controller;

    public GameManager(Controller controller, PanelTetris panelTetris) {
        this.addObserver(panelTetris);
        this.controller = controller;
    }



    public Point pieceOrigin;
    public Point nextPiece;
    public int currentPiece;
    public int nextpiece;
    public int rotation;
    public  int rotationBis=0;
    public ArrayList<Integer> nextPieces = new ArrayList<Integer>();

    public long score;
    public Color[][] well;

    // Creates a border around the well and initializes the dropping piece
    public void init() {
        well = new Color[12][24];
        for (int i = 0; i < 12; i++) {
            for (int j = 0; j < 23; j++) {
                if (i == 0 || i == 11 || j == 22) {
                    well[i][j] = Color.GRAY;
                } else {
                    well[i][j] = Color.BLACK;
                }
            }
        }
        newPiece();
    }

    // Put a new, random piece into the dropping position
    public void newPiece() {
        pieceOrigin = new Point(5, 2);
        nextPiece=new Point(14,0);
        rotation = 0;

        if (nextPieces.isEmpty()) {
            Collections.addAll(nextPieces, 0, 1, 2, 3, 4, 5, 6);
            Collections.shuffle(nextPieces);
        }
        currentPiece = nextPieces.get(0);
        nextPieces.remove(0);
        if (nextPieces.isEmpty()) {
            Collections.addAll(nextPieces, 0, 1, 2, 3, 4, 5, 6);
            Collections.shuffle(nextPieces);
        }
        nextpiece=nextPieces.get(0);
        //nextPieces.remove(0);

    }

    // Collision test for the dropping piece
    private boolean collidesAt(int x, int y, int rotation) {

        for (Point p : FormsConstantes.Tetraminos[currentPiece][rotation]) {
            if (well[p.x + x][p.y + y] != Color.BLACK) {
                return true;
            }
        }
        return false;
    }

    // Rotate the piece clockwise or counterclockwise
    public void rotate(int i) {
        int newRotation = (rotation + i) % 4;
        if (newRotation < 0) {
            newRotation = 3;
        }
        if (!collidesAt(pieceOrigin.x, pieceOrigin.y, newRotation)) {
            rotation = newRotation;
        }
        //controller.panel.repaint();
        setChanged();
        notifyObservers();
    }

    // Move the piece left or right
    public void move(int i) {
        if (!collidesAt(pieceOrigin.x + i, pieceOrigin.y, rotation)) {
            pieceOrigin.x += i;
        }
        //controller.panel.repaint();
        setChanged();
        notifyObservers();
    }

    // Drops the piece one line or fixes it to the well if it can't drop
    public void dropDown() {
        if (!collidesAt(pieceOrigin.x, pieceOrigin.y + 1, rotation)) {
            pieceOrigin.y += 1;
        } else {
            fixToWell();
        }
        //controller.panel.repaint();
        setChanged();
        notifyObservers();
    }

    // Make the dropping piece part of the well, so it is available for
    // collision detection.
    public void fixToWell() {
        for (Point p : FormsConstantes.Tetraminos[currentPiece][rotation]) {
            well[pieceOrigin.x + p.x][pieceOrigin.y + p.y] = ColorConstantes.tetraminoColors[currentPiece];
        }
        clearRows();
        newPiece();
    }

    public void deleteRow(int row) {
        for (int j = row-1; j > 0; j--) {
            for (int i = 1; i < 11; i++) {
                well[i][j+1] = well[i][j];
            }
        }
    }

    // Clear completed rows from the field and award score according to
    // the number of simultaneously cleared rows.
    public void clearRows() {
        boolean gap;
        int numClears = 0;

        for (int j = 21; j > 0; j--) {
            gap = false;
            for (int i = 1; i < 11; i++) {
                if (well[i][j] == Color.BLACK) {
                    gap = true;
                    break;
                }
            }
            if (!gap) {
                deleteRow(j);
                j += 1;
                numClears += 1;
            }
        }

        switch (numClears) {
            case 1:
                score += 100;
                break;
            case 2:
                score += 300;
                break;
            case 3:
                score += 500;
                break;
            case 4:
                score += 800;
                break;
        }
    }

    public void activeChange() {
        this.setChanged();
        notifyObservers();
    }
}
