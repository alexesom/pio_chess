package Pieces;

import javax.swing.*;

import static java.lang.Math.abs;
import java.awt.Color;

public class Knight extends Piece implements PieceInterface{
    JLabel whiteKnight = new JLabel(new StretchIcon("piecesIcons/whiteknight.png"));
    JLabel blackKnight = new JLabel(new StretchIcon("piecesIcons/blackknight.png"));

    public Knight(Square knightPosition, Color knightColor) {
        super(knightPosition, knightColor);

        if (pieceColor == Color.BLACK) {
            setPieceImage(blackKnight);
        }
        else if (pieceColor == Color.WHITE) {
            setPieceImage(whiteKnight);
        }
    }

    @Override
    public boolean isAbleToMove(Square square) {
        int xDiff = abs(square.getXSquareCoordinate() - getxPieceCoordinate());
        int yDiff = abs(square.getYSquareCoordinate() - getyPieceCoordinate());
        int totalDiff = xDiff + yDiff;

        return totalDiff == 3 && (xDiff == 2 || xDiff == 1);
    }

    @Override
    public void take(Piece piece) {

    }
}
