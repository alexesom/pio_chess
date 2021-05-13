package Pieces;

import javax.swing.*;
import java.awt.*;

public class Pawn extends Piece implements PieceInterface {
    public boolean enPassantFlag = false;
    public boolean promoted = false;
    JLabel whitePawn = new JLabel(new StretchIcon("piecesIcons/whitepawn.png"));
    JLabel blackPawn = new JLabel(new StretchIcon("piecesIcons/blackpawn.png"));

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

        if (pieceColor == Color.WHITE) {

            if (ySquare == yPawn + 1 &&
                    xSquare == xPawn - 1 &&
                    xPawn - 1 >= 0 &&
                    square.enPassantSquareFlag) {
                return true;
            } //beat EnPassant

            if (ySquare == yPawn + 1 &&
                    xSquare == xPawn + 1 &&
                    xPawn + 1 <= 7 &&
                    square.enPassantSquareFlag) {
                return true;
            } //beat EnPassant

            if (ySquare == yPawn + 1 &&
                    xSquare == xPawn + 1 &&
                    xPawn + 1 <= 7 &&
                    square.getSquarePiece() != null &&
                    square.getSquarePiece().getPieceColor() != this.getPieceColor()) {
                return true;
            } //beat condition for right beat for WHITE

            if (ySquare == yPawn + 1 &&
                    xSquare == xPawn - 1 &&
                    xPawn - 1 >= 0 &&
                    square.getSquarePiece() != null &&
                    square.getSquarePiece().getPieceColor() != this.getPieceColor()) {
                return true;
            } //beat condition for left beat for WHITE

            if (!promoted && ySquare == yPawn + 1 && xSquare == xPawn) {
                return isAnyPieceBetween(square, PieceMotion.vertical);
            } //for not promoted move by 1 square

            if (!promoted && ySquare == yPawn + 2 && xSquare == xPawn) {
                return isAnyPieceBetween(square, PieceMotion.vertical);
            } //for not promoted move by 2 square

            if (promoted && ySquare == yPawn + 1 && xSquare == xPawn) {
                return isAnyPieceBetween(square, PieceMotion.vertical);
            } //for promoted move by 1 square

            if (promoted && ySquare != yPawn + 1 && xSquare == xPawn) {
                return false;
            } //for promoted move not by 1 square

        } else {

            if (ySquare == yPawn - 1 &&
                    xSquare == xPawn + 1 &&
                    xPawn + 1 <= 7 &&
                    square.enPassantSquareFlag) {
                return true;
            } //beat EnPassant

            if (ySquare == yPawn - 1 &&
                    xSquare == xPawn - 1 &&
                    xPawn - 1 >= 0 &&
                    square.enPassantSquareFlag) {
                return true;
            } //beat EnPassant

            if (ySquare == yPawn - 1 &&
                    xSquare == xPawn + 1 &&
                    xPawn + 1 <= 7 &&
                    square.getSquarePiece() != null &&
                    square.getSquarePiece().getPieceColor() != this.getPieceColor()) {
                return true;
            } //beat condition for right beat for WHITE

            if (ySquare == yPawn - 1 &&
                    xSquare == xPawn - 1 &&
                    xPawn - 1 >= 0 &&
                    square.getSquarePiece() != null &&
                    square.getSquarePiece().getPieceColor() != this.getPieceColor()) {
                return true;
            } //beat condition for left beat for WHITE

            if (!promoted && ySquare == yPawn - 1 && xSquare == xPawn) {
                return isAnyPieceBetween(square, PieceMotion.vertical);
            } //for not promoted move by 1 square

            if (!promoted && ySquare == yPawn - 2 && xSquare == xPawn) {
                return isAnyPieceBetween(square, PieceMotion.vertical);
            } //for not promoted move by 2 square

            if (promoted && ySquare == yPawn - 1 && xSquare == xPawn) {
                return isAnyPieceBetween(square, PieceMotion.vertical);
            } //for promoted move by 1 square

            if (promoted && ySquare != yPawn - 1 && xSquare == xPawn) {
                return false;
            } //for promoted move not by 1 square
        }

        return false;
    }

    @Override
    public void take(Piece piece) {

    }
}
