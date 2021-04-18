package Pieces;

public class Pawn extends Piece implements PieceInterface{
    Square buttonSquare = null;
    ChessColor pawnColor;
    private boolean promoted;
    private boolean passedTwo;

    public Pawn(Square pawnPosition, ChessColor pawnColor) {
        this.buttonSquare = pawnPosition;
        this.pawnColor = pawnColor;
        super.buttonSquare = this.buttonSquare;
    }

    public byte enPassant(Piece piece) {
        return 0;
    }

    @Override
    public boolean isAbleToMove(Square square) {
        int xSquare = square.getXSquareCoordinate();
        int ySquare = square.getYSquareCoordinate();

        int xPawn = buttonSquare.getXSquareCoordinate();
        int yPawn = buttonSquare.getYSquareCoordinate();

        if (pawnColor == ChessColor.WHITE) {
            if (yPawn == 1 && ((ySquare == 2 || ySquare == 3) && xSquare == xPawn)) {
                return true;
            } else if (yPawn != 1 && (ySquare == yPawn + 1 && xPawn == xSquare)) {
                return true;
            }
        } else {
            if (yPawn == 6 && ((ySquare == 5 || ySquare == 4) && xSquare == xPawn)) {
                return true;
            } else if (yPawn != 6 && (ySquare == yPawn - 1 && xPawn == xSquare)) {
                return true;
            }
        }

        return false;
    }

    @Override
    public void take(Piece piece) {

    }
}
