package Pieces;

import ChessInterface.Chessboard;

import java.awt.*;

public class Pawn extends Piece implements PieceInterface {
    public boolean enPassantFlag = false;
    private boolean promoted = false;
    private boolean passedTwo = false;

    public Pawn(Square pawnPosition, Color pawnColor) {
        super(pawnPosition, pawnColor);

        if (pieceColor == Color.BLACK) {
            setPieceImage(blackPawn);
        } else if (pieceColor == Color.WHITE) {
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

        if (this.enPassantFlag) {
            this.enPassantFlag = false;
            Chessboard.EnPassant.makeNull();
        }

        if (pieceColor == Color.WHITE) {
            if (!promoted && ySquare == yPawn + 1 && xSquare == xPawn) {
                promoted = true;
                return isAnyPieceBetween(square, PieceMotion.vertical);
            }

            if (promoted && ySquare == yPawn + 1 && xPawn == xSquare) {
                return isAnyPieceBetween(square, PieceMotion.vertical);
            }

            if (!promoted && ySquare == yPawn + 2 && xSquare == xPawn) {
                this.enPassantFlag = true;
                Chessboard.EnPassant.setEnPassantPawn(this);
                Chessboard.EnPassant.setEnPassantSquare(Chessboard.board[xPawn][yPawn]);
                return isAnyPieceBetween(square, PieceMotion.vertical);
            }

            if (Chessboard.board[xPawn + 1][yPawn].getSquarePiece() instanceof Pawn) {

            }
        } else {
            if (!promoted && ((ySquare == yPawn - 1 || ySquare == yPawn - 2) && xSquare == xPawn)) {
                promoted = true;
                return isAnyPieceBetween(square, PieceMotion.vertical);
            } else if (promoted && (ySquare == yPawn - 1 && xPawn == xSquare)) {
                return isAnyPieceBetween(square, PieceMotion.vertical);
            }
        }

        return false;
    }

    @Override
    public void take(Piece piece) {

    }
}
