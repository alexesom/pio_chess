package test;

import ChessInterface.Chessboard;
import Pieces.Knight;
import Pieces.Square;
import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class IsKnightAbleToMoveTest {

    @Test
    public void KnightIncorrectMove_toDifferentSquare() {
        Square knightSquare = new Square(7, 0);
        Knight knight = new Knight(knightSquare, Color.BLACK);
        Square destination = new Square(1, 1);

        boolean result = knight.isAbleToMove(destination);
        boolean expected = false;

        assertEquals(expected, result);
    }

    @Test
    public void KnightIncorrectMove_toOwnSquare() {
        Square knightSquare = new Square(7, 0);
        Knight knight = new Knight(knightSquare, Color.BLACK);
        Square destination = new Square(7, 0);

        boolean result = knight.isAbleToMove(destination);
        boolean expected = false;

        assertEquals(expected, result);
    }

    @Test
    public void KnightCorrectMove() {
        Square knightSquare = new Square(7, 0);
        Knight knight = new Knight(knightSquare, Color.BLACK);
        Square destination = new Square(6, 2);

        // add squares
        Chessboard.board[6][2] = destination;
        Chessboard.board[7][0] = knightSquare;

        // add pieces to these squares
        Chessboard.board[7][0].setSquarePiece(knight);

        boolean result = knight.isAbleToMove(destination);
        boolean expected = true;

        assertEquals(expected, result);
    }

    @Test
    public void KnightInvalidDestinationSquareCoordinates() {
        Square knightSquare = new Square(7, 0);
        Knight knight = new Knight(knightSquare, Color.BLACK);
        Square destination = new Square(100, -2);

        boolean result = knight.isAbleToMove(destination);
        boolean expected = false;

        assertEquals(expected, result);
    }
}
