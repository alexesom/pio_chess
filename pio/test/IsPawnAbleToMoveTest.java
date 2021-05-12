package test;

import ChessInterface.Chessboard;
import Pieces.Pawn;
import Pieces.Rook;
import Pieces.Square;
import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class IsPawnAbleToMoveTest {

    @Test
    public void PawnIncorrectMove_toDifferentSquare() {
        Square pawnSquare = new Square(3, 5);
        Pawn pawn = new Pawn(pawnSquare, Color.BLACK);
        Square destination = new Square(7, 3);

        boolean result = pawn.isAbleToMove(destination);
        boolean expected = false;

        assertEquals(expected, result);
    }

    @Test
    public void PawnIncorrectMove_toOwnSquare() {
        Square pawnSquare = new Square(3, 5);
        Pawn pawn = new Pawn(pawnSquare, Color.BLACK);
        Square destination = new Square(3, 5);

        boolean result = pawn.isAbleToMove(destination);
        boolean expected = false;

        assertEquals(expected, result);
    }

    @Test
    public void PawnCorrectMove_byTwo_firstMove_noBetween() {
        Square pawnSquare = new Square(6, 0);
        Pawn pawn = new Pawn(pawnSquare, Color.WHITE);
        pawn.promoted = false; // first move
        Square destination = new Square(6, 2);

        // empty square between pawn and destination
        Square emptySquare = new Square(6, 1);

        // add squares
        Chessboard.board[6][2] = destination;
        Chessboard.board[6][1] = emptySquare;
        Chessboard.board[6][0] = pawnSquare;

        // add pieces to these squares
        Chessboard.board[6][0].setSquarePiece(pawn);

        boolean result = pawn.isAbleToMove(destination);
        boolean expected = true;
        assertEquals(expected, result);
    }

    @Test
    public void PawnIncorrectMove_byTwo_firstMove_isBetween() {
        Square pawnSquare = new Square(6, 0);
        Pawn pawn = new Pawn(pawnSquare, Color.WHITE);
        pawn.promoted = false; // first move
        Square destination = new Square(6, 2);

        // place rook between pawn and destination
        Square rookSquare = new Square(6, 1);
        Rook rook = new Rook(pawnSquare, Color.BLACK);

        // add squares
        Chessboard.board[6][2] = destination;
        Chessboard.board[6][1] = rookSquare;
        Chessboard.board[6][0] = pawnSquare;

        // add pieces to these squares
        Chessboard.board[6][0].setSquarePiece(pawn);
        Chessboard.board[6][1].setSquarePiece(rook);

        boolean result = pawn.isAbleToMove(destination);
        boolean expected = false;
        assertEquals(expected, result);
    }

    @Test
    public void PawnCorrectMove_byOne_firstMove() {
        Square pawnSquare = new Square(6, 0);
        Pawn pawn = new Pawn(pawnSquare, Color.WHITE);
        pawn.promoted = false; // first move
        Square destination = new Square(6, 1);

        // add squares
        Chessboard.board[6][1] = destination;
        Chessboard.board[6][0] = pawnSquare;

        // add pieces to these squares
        Chessboard.board[6][0].setSquarePiece(pawn);

        boolean result = pawn.isAbleToMove(destination);
        boolean expected = true;
        assertEquals(expected, result);
    }

    @Test
    public void PawnIncorrectMove_byTwo_notFirstMove() {
        Square pawnSquare = new Square(6, 0);
        Pawn pawn = new Pawn(pawnSquare, Color.WHITE);
        pawn.promoted = true; // first move
        Square destination = new Square(6, 2);

        // empty square between pawn and destination
        Square emptySquare = new Square(6, 1);

        // add squares
        Chessboard.board[6][2] = destination;
        Chessboard.board[6][1] = emptySquare;
        Chessboard.board[6][0] = pawnSquare;

        // add pieces to these squares
        Chessboard.board[6][0].setSquarePiece(pawn);

        boolean result = pawn.isAbleToMove(destination);
        boolean expected = false;
        assertEquals(expected, result);
    }

    @Test
    public void PawnCorrectMove_byOne_notFirstMove() {
        Square pawnSquare = new Square(6, 0);
        Pawn pawn = new Pawn(pawnSquare, Color.WHITE);
        pawn.promoted = true; // first move
        Square destination = new Square(6, 1);

        // add squares
        Chessboard.board[6][1] = destination;
        Chessboard.board[6][0] = pawnSquare;

        // add pieces to these squares
        Chessboard.board[6][0].setSquarePiece(pawn);

        boolean result = pawn.isAbleToMove(destination);
        boolean expected = true;
        assertEquals(expected, result);
    }

    @Test
    public void PawnInvalidDestinationSquareCoordinates() {
        Square pawnSquare = new Square(3, 5);
        Pawn pawn = new Pawn(pawnSquare, Color.BLACK);
        Square destination = new Square(38, 5);

        boolean result = pawn.isAbleToMove(destination);
        boolean expected = false;

        assertEquals(expected, result);
    }
}