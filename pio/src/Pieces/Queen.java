package Pieces;

import javax.swing.*;

public class Queen extends Piece implements PieceInterface{
    Square buttonSquare = null;
    Color queenColor;

    public Queen(Square queenPosition, Color queenColor) {
        this.buttonSquare = queenPosition;
        this.queenColor = queenColor;
        super.buttonSquare = this.buttonSquare;
    }

    public Square getQueenPosition() {
        return buttonSquare;
    }

    @Override
    public boolean isAbleToMove(Square square) {
        return true;
    }

    @Override
    public void take(Piece piece) {

    }
}
