package Pieces;

public class Knight extends Piece implements PieceInterface{
    Square buttonSquare = null;
    ChessColor knightColor;

    public Knight(Square knightPosition, ChessColor knightColor) {
        this.buttonSquare = knightPosition;
        this.knightColor = knightColor;
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
