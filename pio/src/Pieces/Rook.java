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

        int xRook = getxPieceCoordinate();
        int yRook = getyPieceCoordinate();

        if (xSquare == xRook || ySquare == yRook)
            if(xSquare == xRook)
                return isAnyPieceBetween(square, PieceMotion.vertical );
            else
                return isAnyPieceBetween(square, PieceMotion.horizontal );
        else
            return false;
    }

    @Override
    public void take(Piece piece) {

    }
}
