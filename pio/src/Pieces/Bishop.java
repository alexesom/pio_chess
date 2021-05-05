package Pieces;

import javax.swing.*;
import java.awt.*;

import static java.lang.Math.abs;

public class Bishop extends Piece implements PieceInterface {
    JLabel whiteBishop = new JLabel(new StretchIcon("piecesIcons/whitebishop.png"));
    JLabel blackBishop = new JLabel(new StretchIcon("piecesIcons/blackbishop.png"));

    public Bishop(Square bishopPosition, Color bishopColor) {
        super(bishopPosition, bishopColor);

        if (pieceColor == Color.BLACK) {
            setPieceImage(blackBishop);
        } else if (pieceColor == Color.WHITE) {
            setPieceImage(whiteBishop);
        }
    }

    @Override
    public boolean isAbleToMove(Square square) {
        int xDiff = abs(square.getXSquareCoordinate() - getxPieceCoordinate());
        int yDiff = abs(square.getYSquareCoordinate() - getyPieceCoordinate());

        if (xDiff == yDiff)
            return isAnyPieceBetween(square, PieceMotion.diagonal);
        else
            return false;
    }

    @Override
    public void take(Piece piece) {

    }
}
