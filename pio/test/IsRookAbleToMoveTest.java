package test;

import ChessInterface.Chessboard;
import Pieces.Pawn;
import Pieces.Rook;
import Pieces.Square;
import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class IsRookAbleToMoveTest {

    @Test
    public void RookIncorrectMove_toDifferentSquare() {
        Square rookSquare = new Square(7, 0);
        Rook rook = new Rook(rookSquare, Color.BLACK);
        Square destination = new Square(4, 5);

        boolean result = rook.isAbleToMove(destination);
        boolean expected = false;

        assertEquals(expected, result);
    }

    @Test
    public void RookIncorrectMove_toOwnSquare() {
        Square rookSquare = new Square(7, 0);
        Rook rook = new Rook(rookSquare, Color.BLACK);
        Square destination = new Square(7, 0);

        boolean result = rook.isAbleToMove(destination);
        boolean expected = false;

        assertEquals(expected, result);
    }

    @Test
    public void RookCorrectMove_vertical_noBetween() {
        Square rookSquare = new Square(7, 0);
        Rook rook = new Rook(rookSquare, Color.BLACK);
        Square destination = new Square(7, 2);

        // square between rook and destination is empty
        Square emptySquare = new Square(7, 1);

        // add squares
        Chessboard.board[7][2] = destination;
        Chessboard.board[7][1] = emptySquare;
        Chessboard.board[7][0] = rookSquare;

        // add pieces to these squares
        Chessboard.board[7][0].setSquarePiece(rook);

        boolean result = rook.isAbleToMove(destination);
        boolean expected = true;

        assertEquals(expected, result);
    }

    @Test
    public void RookCorrectMove_vertical_isBetween() {
        Square rookSquare = new Square(7, 0);
        Rook rook = new Rook(rookSquare, Color.BLACK);
        Square destination = new Square(7, 2);

        // place another black piece on rook's way
        Square pawnSquare = new Square(7, 1);
        Pawn pawn = new Pawn(pawnSquare, Color.BLACK);

        // add squares
        Chessboard.board[7][2] = destination;
        Chessboard.board[7][1] = pawnSquare;
        Chessboard.board[7][0] = rookSquare;

        // add pieces to these squares
        Chessboard.board[7][1].setSquarePiece(pawn);
        Chessboard.board[7][0].setSquarePiece(rook);

        boolean result = rook.isAbleToMove(destination);
        boolean expected = false;

        assertEquals(expected, result);
    }

    @Test
    public void RookCorrectMove_horizontal_noBetween() {
        Square rookSquare = new Square(5, 3);
        Rook rook = new Rook(rookSquare, Color.BLACK);
        Square destination = new Square(7, 3);

        // square between rook and destination is empty
        Square emptySquare = new Square(6, 3);

        // add squares
        Chessboard.board[7][3] = destination;
        Chessboard.board[6][3] = emptySquare;
        Chessboard.board[5][3] = rookSquare;

        // add pieces to these squares
        Chessboard.board[5][3].setSquarePiece(rook);

        boolean result = rook.isAbleToMove(destination);
        boolean expected = true;

        assertEquals(expected, result);
    }

    @Test
    public void RookCorrectMove_horizontal_isBetween() {
        Square rookSquare = new Square(5, 3);
        Rook rook = new Rook(rookSquare, Color.BLACK);
        Square destination = new Square(7, 3);

        // square between rook and destination is empty
        Square pawnSquare = new Square(6, 3);
        Pawn pawn = new Pawn(pawnSquare, Color.BLACK);

        // add squares
        Chessboard.board[7][3] = destination;
        Chessboard.board[6][3] = pawnSquare;
        Chessboard.board[5][3] = rookSquare;

        // add pieces to these squares
        Chessboard.board[6][3].setSquarePiece(pawn);
        Chessboard.board[5][3].setSquarePiece(rook);

        boolean result = rook.isAbleToMove(destination);
        boolean expected = false;

        assertEquals(expected, result);
    }

    @Test
    public void RookInvalidDestinationSquareCoordinates() {
        Square rookSquare = new Square(7, 0);
        Rook rook = new Rook(rookSquare, Color.BLACK);

        Square destination = new Square(-2, 5);

        boolean result = rook.isAbleToMove(destination);
        boolean expected = false;

        assertEquals(expected, result);
    }
}