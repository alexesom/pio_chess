package Pieces;

import javax.swing.*;
import java.util.Objects;

public class Square {
    public JButton button = new JButton();
    private int xSquareCoordinate;
    private int ySquareCoordinate;
    private Color squareColor;
    private Piece squarePiece;

    public Square() {

    }

    public Square(int x, int y) {
        xSquareCoordinate = x;
        ySquareCoordinate = y;
    }


    public Square(int x, int y, JButton button) {
        xSquareCoordinate = x;
        ySquareCoordinate = y;
        this.button = button;
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

    public void setColor(Color squareColor) {
        if (squareColor.equals(Color.WHITE)) {
            button.setBackground(java.awt.Color.darkGray);
            this.squareColor =  Color.BLACK;
        }
        else {
            button.setBackground(java.awt.Color.lightGray);
            this.squareColor = Color.WHITE;
        }
    }

    public Color getColor() {
        return squareColor;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Square)) return false;
        Square square1 = (Square) o;
        return xSquareCoordinate == square1.xSquareCoordinate &&
                ySquareCoordinate == square1.ySquareCoordinate && Objects.equals(button, square1.button) &&
                squareColor == square1.squareColor;
    }

    public boolean equalsCoordinates(Square position) {
        return this.xSquareCoordinate == position.getXSquareCoordinate() &&
                this.ySquareCoordinate == position.getYSquareCoordinate();
    }

    @Override
    public int hashCode() {
        return Objects.hash(button, xSquareCoordinate, ySquareCoordinate, squareColor);
    }
}