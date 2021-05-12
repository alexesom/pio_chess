package Pieces;

import javax.swing.*;
import java.awt.*;

import static java.lang.Math.abs;

public class Queen extends Piece implements PieceInterface{
    JLabel whiteQueen = new JLabel(new StretchIcon("piecesIcons/whitequeen.png"));
    JLabel blackQueen = new JLabel(new StretchIcon("piecesIcons/blackqueen.png"));

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

        if(xSquare == xQueen && ySquare == yQueen) {
            return false;
        }
        else if(xSquare == xQueen) {
            return isAnyPieceBetween(square, PieceMotion.vertical );

        }
        else if(ySquare == yQueen) {
            return isAnyPieceBetween(square, PieceMotion.horizontal );
        }
        else if(xDiff == yDiff) {
            return isAnyPieceBetween(square, PieceMotion.diagonal);
        }
        else
            return false;
    }

    @Override
    public void take(Piece piece) {

    }
}
