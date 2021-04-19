package Pieces;

import javax.swing.*;
import java.awt.*;

public class Square {
    public JPanel squarePanel = new JPanel();
    private int ySquareCoordinate;
    private int xSquareCoordinate;
    private Color squareColor;
    private Piece squarePiece;

    public Square() {

    }

    public Square(int x, int y) {
        xSquareCoordinate = x;
        ySquareCoordinate = y;
    }

    public Square(int x, int y, int width, int height) {
        squarePanel.setSize(width/8, height/8);
        squarePanel.setLocation(  x * width/8, height - height/8 * (y+1));
        xSquareCoordinate = x;
        ySquareCoordinate = y;
    }

    //region X,Y Square properties
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
    //endregion

    //region SquareColor property
    public Color getSquareColor() {
        return squareColor;
    }

    public void setSquareColor(int i, int j) {
        if ((i % 2 == 0 && j % 2 == 0) || (i % 2 == 1 && j % 2 == 1))
            squareColor = new Color(181, 136, 99);
        else
            squareColor = new Color(241, 217, 181);
    }
    //endregion

    //region SquarePiece property
    public Piece getSquarePiece() {
        return squarePiece;
    }

    public void setSquarePiece(Piece piece) {
        squarePiece = piece;
    }
    //endregion

    public boolean equalsCoordinates(Square position) {
        return this.xSquareCoordinate == position.getXSquareCoordinate() &&
                this.ySquareCoordinate == position.getYSquareCoordinate();
    }
}