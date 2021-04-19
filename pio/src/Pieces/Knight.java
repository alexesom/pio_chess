package Pieces;

import javax.swing.*;

import static java.lang.Math.abs;

public class Knight extends Piece implements PieceInterface{

    public Knight(Square knightPosition, ChessColor knightColor) {

        super(knightPosition, knightColor);

        if (pieceColor == ChessColor.BLACK) {
            setPieceImage(blackKnight);
        }
        else if (pieceColor == ChessColor.WHITE) {
            setPieceImage(whiteKnight);
        }
    }

    @Override
    public boolean isAbleToMove(Square square) {
        int xDiff = abs(square.getXSquareCoordinate() - pieceSquare.getXSquareCoordinate());
        int yDiff = abs(square.getYSquareCoordinate() - pieceSquare.getYSquareCoordinate());
        int totalDiff = xDiff + yDiff;

        return totalDiff == 3 && (xDiff == 2 || xDiff == 1);
    }

    @Override
    public void take(Piece piece) {

    }
}
