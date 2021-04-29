package Pieces;

import javax.swing.*;
import java.awt.*;

public class Square {
    public JPanel squarePanel = new JPanel();
    private int xSquareCoordinate;
    private int ySquareCoordinate;
    private Color squareColor;
    private Piece squarePiece = null;

    public Square(int x, int y, int width, int height) {
        squarePanel.setSize(width/8, height/8);
        squarePanel.setLocation(  x * width/8, height - height/8 * (y+1));
        xSquareCoordinate = x;
        ySquareCoordinate = y;
    }


    public Square(int x, int y) {
        xSquareCoordinate = x;
        ySquareCoordinate = y;
    }

    //region X,Y SquareCoordinate property
    public int getXSquareCoordinate() {
        return xSquareCoordinate;
    }

    public void setXSquareCoordinate(int x) {
        xSquareCoordinate = x;
    }

    public int getYSquareCoordinate() {
        return ySquareCoordinate;
    }

    public void setYSquareCoordinate(int y) {
        ySquareCoordinate = y;
    }
    //endregion

    //region Square Color property
    public Color getSquareColor() {
        return squareColor;
    }

    public void setSquareColor(Color color) {squareColor = color;}
    //endregion

    //region Square Piece property
    public Piece getSquarePiece() {
        return squarePiece;
    }

    public void setSquarePiece(Piece piece) {
        squarePiece = piece;
    }
    //endregion

    public void setSquareColor(int i, int j) {
        if ((i % 2 == 0 && j % 2 == 0) || (i % 2 == 1 && j % 2 == 1)) squareColor = new Color(181, 136, 99);
        else squareColor = new Color(241, 217, 181);
    }

    //old implementation
    /*public void move (Square newSquare) {
        newSquare.setSquarePiece(this.getSquarePiece());
        newSquare.getSquarePiece().setxPieceCoordinate(newSquare.getXSquareCoordinate());
        newSquare.getSquarePiece().setyPieceCoordinate(newSquare.getYSquareCoordinate());
        this.setSquarePiece(null);
    }*/

    public boolean equalsCoordinates(Square position) {
        return this.xSquareCoordinate == position.getXSquareCoordinate() &&
                this.ySquareCoordinate == position.getYSquareCoordinate();
    }

}