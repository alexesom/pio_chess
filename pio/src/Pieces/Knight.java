package Pieces;

import static java.lang.Math.abs;

public class Knight extends Piece implements PieceInterface{
    Square buttonSquare = null;
    ChessColor knightColor;

    public Knight(Square knightPosition, ChessColor knightColor) {
        this.buttonSquare = knightPosition;
        this.knightColor = knightColor;
        super.buttonSquare = this.buttonSquare;
    }

    @Override
    public boolean isAbleToMove(Square square) {
        int xDiff = abs(square.getXSquareCoordinate() - buttonSquare.getXSquareCoordinate());
        int yDiff = abs(square.getYSquareCoordinate() - buttonSquare.getYSquareCoordinate());
        int totalDiff = xDiff + yDiff;

        return totalDiff == 3 && (xDiff == 2 || xDiff == 1);
    }

    @Override
    public void take(Piece piece) {

    }
}
