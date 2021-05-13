package test;

import ChessInterface.Chessboard;
import Pieces.Bishop;
import Pieces.Pawn;
import Pieces.Square;
import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class IsBishopAbleToMoveTest {

    @Test
    public void BishopIncorrectMove_toDifferentSquare() {
        Square bishopSquare = new Square(7, 0);
        Bishop bishop = new Bishop(bishopSquare, Color.BLACK);
        Square destination = new Square(4, 5);

        boolean result = bishop.isAbleToMove(destination);
        boolean expected = false;

        assertEquals(expected, result);
    }

    @Test
    public void BishopIncorrectMove_toOwnSquare() {
        Square bishopSquare = new Square(7, 0);
        Bishop bishop = new Bishop(bishopSquare, Color.BLACK);
        Square destination = new Square(7, 0);

        boolean result = bishop.isAbleToMove(destination);
        boolean expected = false;

        assertEquals(expected, result);
    }

    @Test
    public void BishopCorrectMove_noBetween() {
        Square bishopSquare = new Square(5, 4);
        Bishop bishop = new Bishop(bishopSquare, Color.BLACK);
        Square destination = new Square(7, 2);

        // square between bishop and destination is empty
        Square emptySquare = new Square(6, 3);

        // add squares
        Chessboard.board[7][2] = destination;
        Chessboard.board[6][3] = emptySquare;
        Chessboard.board[5][4] = bishopSquare;

        // add pieces to these squares
        Chessboard.board[5][4].setSquarePiece(bishop);

        boolean result = bishop.isAbleToMove(destination);
        boolean expected = true;

        assertEquals(expected, result);
    }

    @Test
    public void BishopCorrectMove_isBetween() {
        Square bishopSquare = new Square(5, 4);
        Bishop bishop = new Bishop(bishopSquare, Color.BLACK);
        Square destination = new Square(7, 2);

        // place another black piece between bishop and destination
        Square pawnSquare = new Square(6, 3);
        Pawn pawn = new Pawn(pawnSquare, Color.BLACK);

        // add squares
        Chessboard.board[7][2] = destination;
        Chessboard.board[6][3] = pawnSquare;
        Chessboard.board[5][4] = bishopSquare;

        // add pieces to these squares
        Chessboard.board[6][3].setSquarePiece(pawn);
        Chessboard.board[5][4].setSquarePiece(bishop);

        boolean result = bishop.isAbleToMove(destination);
        boolean expected = false;

        assertEquals(expected, result);
    }

    @Test
    public void BishopInvalidDestinationSquareCoordinates() {
        Square bishopSquare = new Square(5, 4);
        Bishop bishop = new Bishop(bishopSquare, Color.BLACK);
        Square destination = new Square(12, 2);

        boolean result = bishop.isAbleToMove(destination);
        boolean expected = false;

        assertEquals(expected, result);
    }
}