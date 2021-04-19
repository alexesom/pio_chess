package Pieces;

public class Rook extends Piece implements PieceInterface{
    Square buttonSquare = null;
    ChessColor rookColor;
    private boolean moved;

    public Rook(Square rookPosition, ChessColor rookColor) {
        this.moved = false;
        this.buttonSquare = rookPosition;
        this.rookColor = rookColor;
        super.setPieceSquare(this.buttonSquare);
    }

    @Override
    public boolean isAbleToMove(Square square) {
        int xSquare = square.getXSquareCoordinate();
        int ySquare = square.getYSquareCoordinate();

        int xRook = buttonSquare.getXSquareCoordinate();
        int yRook = buttonSquare.getYSquareCoordinate();

        return xSquare == xRook || ySquare == yRook;
    }

    @Override
    public void take(Piece piece) {

    }
}
