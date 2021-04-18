package Pieces;

import javax.swing.*;
import java.awt.*;

public class Square {
    public JPanel squarePanel = new JPanel();
    private int ySquareCoordinate;
    private int xSquareCoordinate;
    private Color squareColor;

    public Square(int x, int y, int width, int height) {
        squarePanel.setSize(width/8, height/8);
        squarePanel.setLocation(  x * width/8, height - height/8 * (y+1));
        xSquareCoordinate = x;
        ySquareCoordinate = y;
    }

    public int getXSquareCoordinate() {
        return xSquareCoordinate;
    }

    public int getYSquareCoordinate() {
        return ySquareCoordinate;
    }

    public void setXSquareCoordinate(int x) {
        xSquareCoordinate = x;
    }

    public void setYSquareCoordinate(int y) {
        ySquareCoordinate = y;
    }

    public Color getSquareColor() {
        return squareColor;
    }

    public void setSquareColor(int i, int j) {
        if ((i % 2 == 0 && j % 2 == 0) || (i % 2 == 1 && j % 2 == 1)) squareColor = Color.black;
        else squareColor = Color.white;

    }

}