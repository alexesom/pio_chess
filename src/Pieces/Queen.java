package Pieces;

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
        return true;
    }

    @Override
    public void take(Piece piece) {

    }
}
