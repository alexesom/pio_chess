package Pieces;

import javax.swing.*;
import java.awt.*;

public class Square {
    public JPanel squarePanel = new JPanel();
    private int ySquareCoordinate;
    private int xSquareCoordinate;
    private Color squareColor;
    private Piece squarePiece;

    public Square(int x, int y, int width, int height) {
        squarePanel.setSize(width/8, height/8);
        squarePanel.setLocation(  x * width/8, height - height/8 * (y+1));
        xSquareCoordinate = x;
        ySquareCoordinate = y;
    }

    public Square() {

    }

    public Square(int x, int y) {
        xSquareCoordinate = x;
        ySquareCoordinate = y;
    }

    public Square(int x, int y, JButton button) {
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

    public void setSquareColor(int i, int j) {
        if ((i % 2 == 0 && j % 2 == 0) || (i % 2 == 1 && j % 2 == 1)) squareColor = new Color(181, 136, 99);
        else squareColor = new Color(241, 217, 181);
    }

    public Color getSquareColor() {
        return squareColor;
    }

    public void setSquarePiece(Piece piece) {
        squarePiece = piece;
    }

    public Piece getSquarePiece() {
        return squarePiece;
    }


    /*@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Square)) return false;
        Square square1 = (Square) o;
        return xSquareCoordinate == square1.xSquareCoordinate &&
                ySquareCoordinate == square1.ySquareCoordinate && Objects.equals(button, square1.button) &&
                squareColor == square1.squareColor;
    }*/

    public boolean equalsCoordinates(Square position) {
        return this.xSquareCoordinate == position.getXSquareCoordinate() &&
                this.ySquareCoordinate == position.getYSquareCoordinate();
    }

    //@Override
    /*public int hashCode() {
        return Objects.hash(button, xSquareCoordinate, ySquareCoordinate, squareColor);
    }*/
}