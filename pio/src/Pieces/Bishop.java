package Pieces;

public class Bishop extends Piece implements PieceInterface{
    Square buttonSquare = null;
    Color bishopColor;

    public Bishop(Square bishopPosition, Color bishopColor) {
        this.buttonSquare = bishopPosition;
        this.bishopColor = bishopColor;
        super.buttonSquare = this.buttonSquare;
    }

    @Override
    public boolean isAbleToMove(Square square) {
        return false;
    }

    @Override
    public void take(Piece piece) {

    }
}
