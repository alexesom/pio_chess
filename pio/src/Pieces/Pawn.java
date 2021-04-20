package Pieces;

public class Pawn extends Piece implements PieceInterface{
    private boolean promoted = false;
    private boolean passedTwo = false;

    public Pawn(Square pawnPosition, ChessColor pawnColor) {
        super(pawnPosition, pawnColor);

        if (pieceColor == ChessColor.BLACK) {
            setPieceImage(blackPawn);
        }
        else if (pieceColor == ChessColor.WHITE) {
            setPieceImage(whitePawn);
        }
    }

    public byte enPassant(Piece piece) {
        return 0;
    }

    @Override
    public boolean isAbleToMove(Square square) {
        int xSquare = square.getXSquareCoordinate();
        int ySquare = square.getYSquareCoordinate();

        int xPawn = getxPieceCoordinate();
        int yPawn = getyPieceCoordinate();

        if (pieceColor == ChessColor.WHITE) {
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
