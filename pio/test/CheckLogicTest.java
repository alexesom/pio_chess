package test;

import ChessInterface.CheckLogic;
import ChessInterface.Chessboard;
import ChessInterface.Game;
import ChessInterface.PieceList;
import Pieces.*;

import org.junit.Assert;
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
        PieceList.whitePieces.clear();
        PieceList.blackPieces.clear();
        PieceList.whiteKing = null;
        Chessboard.board  = new Square[8][8];
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
    @Test
    public void shouldSayThatKingCannotMove() {
        Square kingSquare = new Square(0, 0);
        Square rookSquare = new Square(0, 6);
        Square secRookSquare = new Square(1, 6);
        PieceList.whiteKing = new King(kingSquare, Color.WHITE);
        PieceList.addListPiece(new Rook(rookSquare, Color.BLACK), Color.BLACK);
        PieceList.addListPiece(new Rook(secRookSquare, Color.BLACK), Color.BLACK);
        CheckLogic.figuresChecking = 2;
        Game.current_turn = Color.white;
        Chessboard.board[0][0] = kingSquare;
        Chessboard.board[0][6] = rookSquare;
        Chessboard.board[1][6] = secRookSquare;
        Chessboard.board[1][0] = new Square(1, 0);
        Chessboard.board[1][1] = new Square(1, 1);
        Chessboard.board[0][1] = new Square(0, 1);

        assertFalse(CheckLogic.canKingMove());
    }

    @Test
    public void shouldSayThatKingCanMove() {
        Square kingSquare = new Square(0, 0);
        Square rookSquare = new Square(0, 5);
        Square secRookSquare = new Square(0, 1);
        PieceList.whiteKing = new King(kingSquare, Color.WHITE);
        PieceList.addListPiece(new Rook(rookSquare, Color.BLACK), Color.BLACK);
        PieceList.addListPiece(new Rook(secRookSquare, Color.BLACK), Color.BLACK);
        CheckLogic.figuresChecking = 1;
        Game.current_turn = Color.white;
        Chessboard.board[0][0] = kingSquare;
        Chessboard.board[0][5] = rookSquare;
        Chessboard.board[0][1] = secRookSquare;
        Chessboard.board[1][0] = new Square(1, 0);
        Chessboard.board[1][1] = new Square(1, 1);

        assertTrue(CheckLogic.canKingMove());
    }

    @Test
    public void KingDoesNotExistException(){
        Square kingSquare = new Square(0, 0);
        Square rookSquare = new Square(0, 6);
        Square secRookSquare = new Square(1, 0);
        PieceList.addListPiece(new Rook(rookSquare, Color.BLACK), Color.BLACK);
        PieceList.addListPiece(new Rook(secRookSquare, Color.BLACK), Color.BLACK);
        CheckLogic.figuresChecking = 2;
        Game.current_turn = Color.white;
        Chessboard.board[0][0] = kingSquare;
        Chessboard.board[0][6] = rookSquare;
        Chessboard.board[1][0] = secRookSquare;
        Chessboard.board[1][1] = new Square(1, 1);
        Chessboard.board[0][1] = new Square(0, 1);
        try {
            CheckLogic.canKingMove();
        }
        catch (NullPointerException exception){
            Assert.assertEquals("Cannot invoke \"Pieces.King.getyPieceCoordinate()\" because \"checkedKing\" is null", exception.getMessage());
        }
    }

    @Test
    public void shouldSayThatKingCanBeProtected() {
        Square kingSquare = new Square(0, 0);
        Square rookSquare = new Square(0, 6);
        rookSquare.setSquarePiece(new Rook(rookSquare, Color.BLACK));
        Square bishopSquare = new Square(1, 1);
        PieceList.whiteKing = new King(kingSquare, Color.WHITE);
        PieceList.addListPiece(rookSquare.getSquarePiece(), Color.BLACK);
        PieceList.addListPiece(new Bishop(bishopSquare, Color.WHITE), Color.WHITE);
        CheckLogic.figuresChecking = 1;
        CheckLogic.checkingSquare = rookSquare;
        Game.current_turn = Color.BLACK;
        Chessboard.board[0][0] = kingSquare;
        Chessboard.board[0][6] = rookSquare;
        Chessboard.board[1][0] = new Square(1, 0);
        Chessboard.board[1][1] = bishopSquare;
        Chessboard.board[0][1] = new Square(0, 1);
        Chessboard.board[0][2] = new Square(0, 2);
        Chessboard.board[0][3] = new Square(0, 3);
        Chessboard.board[0][4] = new Square(0, 4);
        Chessboard.board[0][5] = new Square(0, 5);

        assertTrue(CheckLogic.canKingBeProtected());
    }

    @Test
    public void shouldSayThatKingCannotBeProtected() {
        Square kingSquare = new Square(0, 0);
        Square rookSquare = new Square(0, 6);
        rookSquare.setSquarePiece(new Rook(rookSquare, Color.BLACK));
        Square pawnSquare = new Square(2, 2);
        PieceList.whiteKing = new King(kingSquare, Color.WHITE);
        PieceList.addListPiece(rookSquare.getSquarePiece(), Color.BLACK);
        PieceList.addListPiece(new Pawn(pawnSquare, Color.WHITE), Color.WHITE);
        CheckLogic.figuresChecking = 1;
        CheckLogic.checkingSquare = rookSquare;
        Game.current_turn = Color.BLACK;
        Chessboard.board[0][0] = kingSquare;
        Chessboard.board[0][6] = rookSquare;
        Chessboard.board[1][0] = new Square(1, 0);
        Chessboard.board[1][1] = pawnSquare;
        Chessboard.board[0][1] = new Square(0, 1);
        Chessboard.board[0][2] = new Square(0, 2);
        Chessboard.board[0][3] = new Square(0, 3);
        Chessboard.board[0][4] = new Square(0, 4);
        Chessboard.board[0][5] = new Square(0, 5);
        Chessboard.board[2][3] = new Square(2, 3);
        Chessboard.board[2][4] = new Square(2, 4);


        assertFalse(CheckLogic.canKingBeProtected());
    }
    @Test
    public void KingCannotBeProtected() {
        CheckLogic.figuresChecking = 2;
        assertFalse(CheckLogic.canKingBeProtected());
    }
}