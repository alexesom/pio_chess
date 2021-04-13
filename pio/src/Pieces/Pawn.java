package Pieces;

public class Pawn extends Piece implements PieceInterface{
    Square buttonSquare = null;
    Color pawnColor;
    private boolean promoted;
    private boolean passedTwo;

    public Pawn(Square pawnPosition, Color pawnColor) {
        this.buttonSquare = pawnPosition;
        this.pawnColor = pawnColor;
        super.buttonSquare = this.buttonSquare;
    }

    public byte enPassant(Piece piece) {
        return 0;
    }

    @Override
    public boolean isAbleToMove(Square square) {

        return true;
    }

    @Override
    public void take(Piece piece) {

    }
}
