package Pieces;

public class Rook extends Piece implements PieceInterface{
    private boolean moved = false;

    public Rook(Square rookPosition, ChessColor rookColor) {
        super(rookPosition, rookColor);

        if (pieceColor == ChessColor.BLACK) {
            setPieceImage(blackRook);
        }
        else if (pieceColor == ChessColor.WHITE) {
            setPieceImage(whiteRook);
        }
    }

    @Override
    public boolean isAbleToMove(Square square) {
        int xSquare = square.getXSquareCoordinate();
        int ySquare = square.getYSquareCoordinate();

        int xRook = pieceSquare.getXSquareCoordinate();
        int yRook = pieceSquare.getYSquareCoordinate();

        return xSquare == xRook || ySquare == yRook;
    }

    @Override
    public void take(Piece piece) {

    }
}
