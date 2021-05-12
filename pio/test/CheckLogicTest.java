package test;

import ChessInterface.CheckLogic;
import ChessInterface.Chessboard;
import ChessInterface.Game;
import ChessInterface.PieceList;
import Pieces.King;
import Pieces.Knight;
import Pieces.Piece;
import Pieces.Square;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.awt.*;

import static ChessInterface.CheckLogic.isChecked;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CheckLogicTest {

    @AfterEach
    void resetFiguresChecking() {
        CheckLogic.resetFiguresChecking();
    }

    /*
    tests for the isChecked() method
     */
    @Test
    void notChecked() {
        Game.current_turn = Color.WHITE;
        PieceList.whiteKing = new King(new Square(0, 0), Color.WHITE);

        boolean result = isChecked();
        assertFalse(result, "isChecked returned true while king is not checked, expected false");
    }

    @Test
    void whiteKingChecked() {
        Game.current_turn = Color.WHITE;
        Square checkedSquare = new Square(0, 0);
        PieceList.whiteKing = new King(checkedSquare, Color.WHITE);
        checkedSquare.setSquarePiece(PieceList.whiteKing);
        Chessboard.board = new Square[1][1];
        Chessboard.board[0][0] = checkedSquare;

        Piece checkingPiece = new Knight(new Square(2, 1), Color.BLACK);
        PieceList.addListPiece(checkingPiece, checkingPiece.getPieceColor());

        boolean result = isChecked();
        PieceList.removePiece(checkingPiece);
        assertTrue(result, "isChecked() returned false while king was checked, expected true");
    }

    @Test
    void blackKingChecked() {
        Game.current_turn = Color.BLACK;
        Square checkedSquare = new Square(0, 0);
        PieceList.blackKing = new King(checkedSquare, Color.BLACK);
        checkedSquare.setSquarePiece(PieceList.blackKing);
        Chessboard.board = new Square[1][1];
        Chessboard.board[0][0] = checkedSquare;

        Piece checkingPiece = new Knight(new Square(2, 1), Color.WHITE);
        PieceList.addListPiece(checkingPiece, checkingPiece.getPieceColor());

        boolean result = isChecked();
        PieceList.removePiece(checkingPiece);
        assertTrue(result, "isChecked() returned false while king was checked, expected true");
    }

    /*
    tests for the isChecked(Square, Color) method
     */
    @Test
    void squareNotChecked() {
        Game.current_turn = Color.WHITE;
        Square kingSquare = new Square(0, 0);
        PieceList.whiteKing = new King(kingSquare, Color.WHITE);

        boolean result = isChecked(kingSquare, Color.WHITE);
        assertFalse(result, "isChecked returned true while king is not checked, expected false");
    }

    @Test
    void whiteKingSquareChecked() {
        Game.current_turn = Color.WHITE;
        Square checkedSquare = new Square(0, 0);
        PieceList.whiteKing = new King(checkedSquare, Color.WHITE);
        checkedSquare.setSquarePiece(PieceList.whiteKing);
        Chessboard.board = new Square[3][3];
        Chessboard.board[0][0] = checkedSquare;

        Square checkingSquare = new Square(2, 1);
        Piece checkingPiece = new Knight(checkingSquare, Color.BLACK);
        PieceList.addListPiece(checkingPiece, checkingPiece.getPieceColor());
        Chessboard.board[2][1] = checkingSquare;

        boolean result = isChecked(checkedSquare, PieceList.whiteKing.getPieceColor());
        PieceList.removePiece(checkingPiece);
        assertTrue(result, "isChecked() returned false while king square was checked, expected true");
    }

    @Test
    void blackKingSquareChecked() {
        Game.current_turn = Color.BLACK;
        Square checkedSquare = new Square(0, 0);
        PieceList.blackKing = new King(checkedSquare, Color.BLACK);
        checkedSquare.setSquarePiece(PieceList.blackKing);
        Chessboard.board = new Square[3][3];
        Chessboard.board[0][0] = checkedSquare;

        Square checkingSquare = new Square(2, 1);
        Piece checkingPiece = new Knight(checkingSquare, Color.WHITE);
        PieceList.addListPiece(checkingPiece, checkingPiece.getPieceColor());
        Chessboard.board[2][1] = checkingSquare;

        boolean result = isChecked(checkedSquare, PieceList.blackKing.getPieceColor());
        PieceList.removePiece(checkingPiece);
        assertTrue(result, "isChecked() returned false while king square was checked, expected true");
    }

    @Test
    void emptySquareCheckedByWhite() {
        Square checkedSquare = new Square(2, 1);

        Square checkingSquare = new Square(0, 0);
        Piece checkingPiece = new Knight(checkingSquare, Color.BLACK);
        PieceList.addListPiece(checkingPiece, checkingPiece.getPieceColor());
        Chessboard.board = new Square[1][1];
        Chessboard.board[0][0] = checkingSquare;

        boolean result = isChecked(checkedSquare, Color.WHITE);
        PieceList.removePiece(checkingPiece);
        assertTrue(result, "isChecked() returned false while square was checked, expected true");
    }

    @Test
    void emptySquareCheckedByBlack() {
        Square checkedSquare = new Square(2, 1);

        Square checkingSquare = new Square(0, 0);
        Piece checkingPiece = new Knight(checkingSquare, Color.WHITE);
        PieceList.addListPiece(checkingPiece, checkingPiece.getPieceColor());
        Chessboard.board = new Square[1][1];
        Chessboard.board[0][0] = checkingSquare;

        boolean result = isChecked(checkedSquare, Color.BLACK);
        PieceList.removePiece(checkingPiece);
        assertTrue(result, "isChecked() returned false while square was checked, expected true");
    }

    @Test
    void blackCheckingOwnSquare() {
        Square checkedSquare = new Square(2, 1);

        Square checkingSquare = new Square(0, 0);
        Piece checkingPiece = new Knight(checkingSquare, Color.BLACK);
        PieceList.addListPiece(checkingPiece, checkingPiece.getPieceColor());
        Chessboard.board = new Square[1][1];
        Chessboard.board[0][0] = checkingSquare;

        boolean result = isChecked(checkedSquare, Color.BLACK);
        PieceList.removePiece(checkingPiece);
        assertFalse(result, "isChecked() returned true for black checking own square, expected false");
    }

    @Test
    void whiteCheckingOwnSquare() {
        Square checkedSquare = new Square(2, 1);

        Square checkingSquare = new Square(0, 0);
        Piece checkingPiece = new Knight(checkingSquare, Color.WHITE);
        PieceList.addListPiece(checkingPiece, checkingPiece.getPieceColor());
        Chessboard.board = new Square[1][1];
        Chessboard.board[0][0] = checkingSquare;

        boolean result = isChecked(checkedSquare, Color.WHITE);
        PieceList.removePiece(checkingPiece);
        assertFalse(result, "isChecked() returned true for black checking own square, expected false");
    }
}