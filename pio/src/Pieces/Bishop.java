package Pieces;

public class Bishop extends Piece implements PieceInterface{
    public Bishop() {

    }

    @Override
    public boolean isAbleToMove(Square square) {
        return false;
    }

    @Override
    public void take(Piece piece) {

    }
}
