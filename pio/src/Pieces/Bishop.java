package Pieces;

import static java.lang.Math.abs;

public class Bishop extends Piece implements PieceInterface{
    Square buttonSquare = null;
    ChessColor bishopColor;

    public Bishop(Square bishopPosition, ChessColor bishopColor) {
        this.buttonSquare = bishopPosition;
        this.bishopColor = bishopColor;
        super.setPieceSquare(this.buttonSquare);
    }

    @Override
    public boolean isAbleToMove(Square square) {
        int xDiff = abs(square.getXSquareCoordinate() - buttonSquare.getXSquareCoordinate());
        int yDiff = abs(square.getYSquareCoordinate() - buttonSquare.getYSquareCoordinate());

        return xDiff == yDiff;
    }

    @Override
    public void take(Piece piece) {

    }
}
