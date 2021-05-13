package Pieces;

import javax.swing.*;
import java.awt.Color;

public class Rook extends Piece implements PieceInterface{
    private boolean moved = false;
    JLabel whiteRook = new JLabel(new StretchIcon("piecesIcons/whiterook.png"));
    JLabel blackRook = new JLabel(new StretchIcon("piecesIcons/blackrook.png"));

    public Rook(Square rookPosition, Color rookColor) {
        super(rookPosition, rookColor);

        if (pieceColor == Color.BLACK) {
            setPieceImage(blackRook);
        }
        else if (pieceColor == Color.WHITE) {
            setPieceImage(whiteRook);
        }
    }

    public boolean isMoved() { return  moved;}

    @Override
    public boolean isAbleToMove(Square square) {
        int xSquare = square.getXSquareCoordinate();
        int ySquare = square.getYSquareCoordinate();
        int xRook = getxPieceCoordinate();
        int yRook = getyPieceCoordinate();

        if (xSquare == xRook || ySquare == yRook) {
            if (xSquare == xRook && ySquare == yRook)
                return false;

            if (xSquare == xRook)
                return isAnyPieceBetween(square, PieceMotion.vertical);
            else
                return isAnyPieceBetween(square, PieceMotion.horizontal);
        }
        else
            return false;
    }
    @Override
    public void move() {
        moved = true;
    }

    @Override
    public boolean isAbleToCastle() {
        return !moved;
    }
}
