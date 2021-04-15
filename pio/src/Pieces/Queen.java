package Pieces;

import static java.lang.Math.abs;

public class Queen extends Piece implements PieceInterface{
    Square buttonSquare = null;
    ChessColor queenColor;

    public Queen(Square queenPosition, ChessColor queenColor) {
        this.buttonSquare = queenPosition;
        this.queenColor = queenColor;
        super.buttonSquare = this.buttonSquare;
    }

    public Square getQueenPosition() {
        return buttonSquare;
    }

    @Override
    public boolean isAbleToMove(Square square) {
        int xSquare = square.getXSquareCoordinate();
        int ySquare = square.getYSquareCoordinate();
        int xDiff = abs(square.getXSquareCoordinate() - buttonSquare.getXSquareCoordinate());
        int yDiff = abs(square.getYSquareCoordinate() - buttonSquare.getYSquareCoordinate());

        int xQueen = buttonSquare.getXSquareCoordinate();
        int yQueen = buttonSquare.getYSquareCoordinate();

        return (xSquare == xQueen || ySquare == yQueen) ||
                (xDiff == yDiff);
    }

    @Override
    public void take(Piece piece) {

    }
}
