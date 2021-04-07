package Pieces;

public class King extends Piece implements PieceInterface{
    private boolean moved;

    public King() {
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
