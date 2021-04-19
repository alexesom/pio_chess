package Pieces;

import static java.lang.Math.abs;

public class King extends Piece implements PieceInterface{
    private boolean moved = false;

    public King(Square kingPosition, ChessColor kingColor) {
        super(kingPosition, kingColor);

        if (pieceColor == ChessColor.BLACK) {
            setPieceImage(blackKing);
        }
        else if (pieceColor == ChessColor.WHITE) {
            setPieceImage(whiteKing);
        }
    }

    @Override
    public boolean isAbleToMove(Square square) {
        int xDiff = square.getXSquareCoordinate() - pieceSquare.getXSquareCoordinate();
        int yDiff = square.getYSquareCoordinate() - pieceSquare.getYSquareCoordinate();

        if(abs(xDiff) <= 1 && abs(yDiff) <= 1 && (abs(xDiff) != 0 || xDiff != yDiff))
            return true;
        return false;
    }

    @Override
    public void take(Piece piece) {

    }
}
