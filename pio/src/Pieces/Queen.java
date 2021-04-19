package Pieces;

import static java.lang.Math.abs;

public class Queen extends Piece implements PieceInterface{


    public Queen(Square queenPosition, ChessColor queenColor) {
        super(queenPosition, queenColor);

        if (pieceColor == ChessColor.BLACK) {
            setPieceImage(blackQueen);
        }
        else if (pieceColor == ChessColor.WHITE) {
            setPieceImage(whiteQueen);
        }
    }

    @Override
    public boolean isAbleToMove(Square square) {
        int xSquare = square.getXSquareCoordinate();
        int ySquare = square.getYSquareCoordinate();
        int xDiff = abs(square.getXSquareCoordinate() - pieceSquare.getXSquareCoordinate());
        int yDiff = abs(square.getYSquareCoordinate() - pieceSquare.getYSquareCoordinate());

        int xQueen = pieceSquare.getXSquareCoordinate();
        int yQueen = pieceSquare.getYSquareCoordinate();

        return (xSquare == xQueen || ySquare == yQueen) ||
                (xDiff == yDiff);
    }

    @Override
    public void take(Piece piece) {

    }
}
