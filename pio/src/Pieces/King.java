package Pieces;

import static java.lang.Math.abs;

public class King extends Piece implements PieceInterface{
    Square buttonSquare = null;
    ChessColor kingColor;
    private boolean moved;

    public King(Square kingPosition, ChessColor kingColor) {
        this.moved = false;
        this.buttonSquare = kingPosition;
        this.kingColor = kingColor;
        super.buttonSquare = this.buttonSquare;
    }

    @Override
    public boolean isAbleToMove(Square square) {
        int xDiff = square.getXSquareCoordinate() - buttonSquare.getXSquareCoordinate();
        int yDiff = square.getYSquareCoordinate() - buttonSquare.getYSquareCoordinate();

        if(abs(xDiff) <= 1 && abs(yDiff) <= 1 && (abs(xDiff) != 0 || xDiff != yDiff))
            return true;
        return false;
    }

    @Override
    public void take(Piece piece) {

    }
}
