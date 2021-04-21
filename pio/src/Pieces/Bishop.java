package Pieces;

import static java.lang.Math.abs;
import java.awt.Color;

public class Bishop extends Piece implements PieceInterface{

    public Bishop(Square bishopPosition, Color bishopColor) {
        super(bishopPosition, bishopColor);

        if (pieceColor == Color.BLACK) {
            setPieceImage(blackBishop);
        }
        else if (pieceColor == Color.WHITE) {
            setPieceImage(whiteBishop);
        }
    }

    @Override
    public boolean isAbleToMove(Square square) {
        int xDiff = abs(square.getXSquareCoordinate() - getxPieceCoordinate());
        int yDiff = abs(square.getYSquareCoordinate() - getyPieceCoordinate());

        if(xDiff == yDiff)
            return isAnyPieceBetween(square, PieceMotion.diagonal);
        else
            return false;
    }

    @Override
    public void take(Piece piece) {

    }
}
