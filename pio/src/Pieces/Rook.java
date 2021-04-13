package Pieces;

public class Rook extends Piece implements PieceInterface{
    Square buttonSquare = null;
    Color rookColor;
    private boolean moved;

    public Rook(Square rookPosition, Color rookColor) {
        this.moved = false;
        this.buttonSquare = rookPosition;
        this.rookColor = rookColor;
        super.buttonSquare = this.buttonSquare;
    }

    @Override
    public boolean isAbleToMove(Square square) {
        return true;
    }

    @Override
    public void take(Piece piece) {

    }
}
