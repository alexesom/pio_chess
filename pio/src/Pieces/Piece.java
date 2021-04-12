package Pieces;

public class Piece {
    private byte type;
    private boolean isWhite;
    public Square buttonSquare;

    public Piece() {

    }

    public byte move(Square destination) {

        return 0;
    }

    public void take(Piece piece) {

    }

    public Square getButtonSquare() {
        return buttonSquare;
    }

    public void setButtonSquare(Square buttonSquare) {
        this.buttonSquare = buttonSquare;
    }

    public boolean isAbleToMove() {

        return true;
    }
}
