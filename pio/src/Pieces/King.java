package Pieces;

public class King extends Piece implements PieceInterface{
    private boolean moved;

    public King() {
        this.moved = false;
    }

    @Override
    public boolean isAbleToMove(Square square) {
        return true;
    }

    @Override
    public void take(Piece piece) {

    }
}
