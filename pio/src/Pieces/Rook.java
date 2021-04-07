package Pieces;

public class Rook extends Piece implements PieceInterface{
    private boolean moved;

    public Rook() {
        this.moved = false;
    }

    @Override
    public byte move(Square square) {
        return 0;
    }

    @Override
    public void take(Piece piece) {

    }
}
