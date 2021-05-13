package test;

import Pieces.King;
import Pieces.Square;
import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class IsKingAbleToMoveTest {
    @Test
    public void KingIncorrectMove_toDifferentSquare() {
        Square kingSquare = new Square(3, 5);
        King king = new King(kingSquare, Color.BLACK);
        Square destination = new Square(7, 3);

        boolean result = king.isAbleToMove(destination);
        boolean expected = false;

        assertEquals(expected, result);
    }

    @Test
    public void KingIncorrectMove_toOwnSquare() {
        Square kingSquare = new Square(3, 5);
        King king = new King(kingSquare, Color.BLACK);
        Square destination = new Square(3, 5);

        boolean result = king.isAbleToMove(destination);
        boolean expected = false;

        assertEquals(expected, result);
    }

    @Test
    public void KingCorrectMove_horizontal() {
        Square kingSquare = new Square(3, 5);
        King king = new King(kingSquare, Color.BLACK);
        Square destination = new Square(4, 5);

        boolean result = king.isAbleToMove(destination);
        boolean expected = true;

        assertEquals(expected, result);
    }

    @Test
    public void KingCorrectMove_vertical() {
        Square kingSquare = new Square(3, 5);
        King king = new King(kingSquare, Color.BLACK);
        Square destination = new Square(3, 6);

        boolean result = king.isAbleToMove(destination);
        boolean expected = true;

        assertEquals(expected, result);
    }

    @Test
    public void KingCorrectMove_diagonal() {
        Square kingSquare = new Square(3, 5);
        King king = new King(kingSquare, Color.BLACK);
        Square destination = new Square(4, 6);

        boolean result = king.isAbleToMove(destination);
        boolean expected = true;

        assertEquals(expected, result);
    }

    @Test
    public void KnightInvalidDestinationSquareCoordinates() {
        Square kingSquare = new Square(3, 5);
        King king = new King(kingSquare, Color.BLACK);
        Square destination = new Square(40, 5);

        boolean result = king.isAbleToMove(destination);
        boolean expected = false;

        assertEquals(expected, result);
    }
}