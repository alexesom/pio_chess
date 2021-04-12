package chessPIO;

import javax.swing.*;
import java.awt.*;

public class Square {
    public JButton square = new JButton();
    private int xSquareCoordinate;
    private int ySquareCoordinate;
    private String squareColor;

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

    public void setColor(String squareColor) {
        if (squareColor.equals("black")) {
            square.setBackground(Color.black);
            this.squareColor = "black";
        }
        else {
            square.setBackground(Color.white);
            this.squareColor = "white";
        }
    }

    public String getColor() {
        return squareColor;
    }
}