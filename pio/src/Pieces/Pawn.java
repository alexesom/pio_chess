package Pieces;

public class Pawn extends Piece implements PieceInterface{
    private boolean promoted;
    private boolean passedTwo;

    public Pawn(boolean promoted, boolean passedTwo) {
        this.promoted = promoted;
        this.passedTwo = passedTwo;
    }

    public byte enPassant(Piece piece) {
        return 0;
    }

    @Override
    public byte move(Square square) {
        return 0;
    }

    @Override
    public void take(Piece piece) {

    }
}
