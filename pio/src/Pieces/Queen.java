package Pieces;

import static java.lang.Math.abs;
import java.awt.Color;

public class Queen extends Piece implements PieceInterface{


    public Queen(Square queenPosition, Color queenColor) {
        super(queenPosition, queenColor);

        if (pieceColor == Color.BLACK) {
            setPieceImage(blackQueen);
        }
        else if (pieceColor == Color.WHITE) {
            setPieceImage(whiteQueen);
        }
    }

    @Override
    public boolean isAbleToMove(Square square) {
        int xSquare = square.getXSquareCoordinate();
        int ySquare = square.getYSquareCoordinate();
        int xQueen = getxPieceCoordinate();
        int yQueen = getyPieceCoordinate();
        int xDiff = abs(square.getXSquareCoordinate() - xQueen);
        int yDiff = abs(square.getYSquareCoordinate() - yQueen);


        return (xSquare == xQueen || ySquare == yQueen) ||
                (xDiff == yDiff);
    }

    @Override
    public void take(Piece piece) {

    }
}
