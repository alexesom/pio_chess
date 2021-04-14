package Pieces;

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
        return true;
    }

    @Override
    public void take(Piece piece) {

    }
}
