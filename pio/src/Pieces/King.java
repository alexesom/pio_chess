package Pieces;

import static java.lang.Math.abs;
import java.awt.Color;

public class King extends Piece implements PieceInterface{
    private boolean moved = false;

    public King(Square kingPosition, Color kingColor) {
        super(kingPosition, kingColor);

        if (pieceColor == Color.BLACK) {
            setPieceImage(blackKing);
        }
        else if (pieceColor == Color.WHITE) {
            setPieceImage(whiteKing);
        }
    }

    @Override
    public boolean isAbleToMove(Square square) {
        int xDiff = square.getXSquareCoordinate() - getxPieceCoordinate();
        int yDiff = square.getYSquareCoordinate() - getyPieceCoordinate();

        if(abs(xDiff) <= 1 && abs(yDiff) <= 1 && (abs(xDiff) != 0 || xDiff != yDiff))
            return true;
        return false;
    }

    @Override
    public void take(Piece piece) {

    }

    @Override
    public boolean isAbleToCastle(){
        return true;
    }
}
