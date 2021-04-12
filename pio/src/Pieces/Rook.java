package Pieces;

public class Rook extends Piece implements PieceInterface{
    private boolean moved;

    public Rook() {
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
