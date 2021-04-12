package Pieces;

public class Knight extends Piece implements PieceInterface{
    public Knight() {

    }

    @Override
    public boolean isAbleToMove(Square square) {

        return true;
    }

    @Override
    public void take(Piece piece) {

    }
}
