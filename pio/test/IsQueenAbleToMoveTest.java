package test;

import ChessInterface.Chessboard;
import Pieces.Pawn;
import Pieces.Queen;
import Pieces.Square;
import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class IsQueenAbleToMoveTest {

    @Test
    public void QueenIncorrectMove_toDifferentSquare() {
        Square queenSquare = new Square(3, 5);
        Queen queen = new Queen(queenSquare, Color.BLACK);
        Square destination = new Square(2, 3);

        boolean result = queen.isAbleToMove(destination);
        boolean expected = false;

        assertEquals(expected, result);
    }

    @Test
    public void QueenIncorrectMove_toOwnSquare() {
        Square queenSquare = new Square(2, 5);
        Queen queen = new Queen(queenSquare, Color.BLACK);
        Square destination = new Square(2, 5);

        // add squares
        Chessboard.board[2][5] = queenSquare;
        Chessboard.board[2][5] = destination;

        // add pieces to these squares
        Chessboard.board[2][5].setSquarePiece(queen);

        boolean result = queen.isAbleToMove(destination);
        boolean expected = false;

        assertEquals(expected, result);
    }

    @Test
    public void QueenCorrectMove_vertical_noBetween() {
        Square queenSquare = new Square(2, 5);
        Queen queen = new Queen(queenSquare, Color.BLACK);
        Square destination = new Square(2, 7);

        // square between queen and destination is empty
        Square emptySquare = new Square(2, 6);

        // add squares
        Chessboard.board[2][5] = queenSquare;
        Chessboard.board[2][6] = emptySquare;
        Chessboard.board[2][7] = destination;

        // add pieces to these squares
        Chessboard.board[2][5].setSquarePiece(queen);

        boolean result = queen.isAbleToMove(destination);
        boolean expected = true;

        assertEquals(expected, result);
    }

    @Test
    public void QueenCorrectMove_vertical_isBetween() {
        Square queenSquare = new Square(2, 5);
        Queen queen = new Queen(queenSquare, Color.BLACK);
        Square destination = new Square(2, 7);

        // square between queen and destination is taken
        Square pawnSquare = new Square(2, 6);
        Pawn pawn = new Pawn(pawnSquare, Color.BLACK);

        // add squares
        Chessboard.board[2][5] = queenSquare;
        Chessboard.board[2][6] = pawnSquare;
        Chessboard.board[2][7] = destination;

        // add pieces to these squares
        Chessboard.board[2][6].setSquarePiece(pawn);
        Chessboard.board[2][5].setSquarePiece(queen);

        boolean result = queen.isAbleToMove(destination);
        boolean expected = false;

        assertEquals(expected, result);
    }

    @Test
    public void QueenCorrectMove_horizontal_noBetween() {
        Square queenSquare = new Square(2, 5);
        Queen queen = new Queen(queenSquare, Color.BLACK);
        Square destination = new Square(4, 5);

        // square between queen and destination is empty
        Square emptySquare = new Square(3, 5);

        // add squares
        Chessboard.board[2][5] = queenSquare;
        Chessboard.board[3][5] = emptySquare;
        Chessboard.board[4][5] = destination;

        // add pieces to these squares
        Chessboard.board[2][5].setSquarePiece(queen);

        boolean result = queen.isAbleToMove(destination);
        boolean expected = true;

        assertEquals(expected, result);
    }

    @Test
    public void QueenCorrectMove_horizontal_isBetween() {
        Square queenSquare = new Square(2, 5);
        Queen queen = new Queen(queenSquare, Color.BLACK);
        Square destination = new Square(4, 5);

        // square between queen and destination is taken
        Square pawnSquare = new Square(3, 5);
        Pawn pawn = new Pawn(pawnSquare, Color.BLACK);

        // add squares
        Chessboard.board[2][5] = queenSquare;
        Chessboard.board[3][5] = pawnSquare;
        Chessboard.board[4][5] = destination;

        // add pieces to these squares
        Chessboard.board[3][5].setSquarePiece(pawn);
        Chessboard.board[2][5].setSquarePiece(queen);

        boolean result = queen.isAbleToMove(destination);
        boolean expected = false;

        assertEquals(expected, result);
    }

    @Test
    public void QueenCorrectMove_diagonal_noBetween() {
        Square queenSquare = new Square(2, 5);
        Queen queen = new Queen(queenSquare, Color.BLACK);
        Square destination = new Square(4, 3);

        // square between queen and destination is empty
        Square emptySquare = new Square(3, 4);

        // add squares
        Chessboard.board[2][5] = queenSquare;
        Chessboard.board[3][4] = emptySquare;
        Chessboard.board[4][3] = destination;

        // add pieces to these squares
        Chessboard.board[2][5].setSquarePiece(queen);

        boolean result = queen.isAbleToMove(destination);
        boolean expected = true;

        assertEquals(expected, result);
    }

    @Test
    public void QueenCorrectMove_diagonal_isBetween() {
        Square queenSquare = new Square(2, 5);
        Queen queen = new Queen(queenSquare, Color.BLACK);
        Square destination = new Square(4, 3);

        // square between queen and destination is taken
        Square pawnSquare = new Square(3, 4);
        Pawn pawn = new Pawn(pawnSquare, Color.BLACK);

        // add squares
        Chessboard.board[2][5] = queenSquare;
        Chessboard.board[3][4] = pawnSquare;
        Chessboard.board[4][3] = destination;

        // add pieces to these squares
        Chessboard.board[3][4].setSquarePiece(pawn);
        Chessboard.board[2][5].setSquarePiece(queen);

        boolean result = queen.isAbleToMove(destination);
        boolean expected = false;

        assertEquals(expected, result);
    }

    @Test
    public void QueenInvalidDestinationSquareCoordinates() {
        Square queenSquare = new Square(2, 5);
        Queen queen = new Queen(queenSquare, Color.BLACK);
        Square destination = new Square(14, 3);

        boolean result = queen.isAbleToMove(destination);
        boolean expected = false;

        assertEquals(expected, result);
    }
}