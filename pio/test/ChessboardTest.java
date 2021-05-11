package test;

import ChessInterface.Chessboard;
import ChessInterface.Game;
import ChessInterface.PieceList;
import Pieces.*;
import org.junit.jupiter.api.Test;

import static ChessInterface.Chessboard.tryMove;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

import java.awt.*;

class ChessboardTest {

    @Test
    void tryMovingPiece() {
        Square originSquare = new Square(0,0);
        originSquare.setSquarePiece(new Knight(originSquare, Color.WHITE));
        Square destinationSquare = new Square (2, 1);
        Game.current_turn = Color.WHITE;
        try {
            tryMove(originSquare, destinationSquare);
            assert true;
        } catch (Exception e) {
            assert false;
        }
    }
    @Test
    void tryMovingNullPiece() {
        Square originSquare = new Square(0,0);
        Square destinationSquare = new Square (1, 1);

        Exception thrown = assertThrows(Exception.class, () -> tryMove(originSquare, destinationSquare),
                "expected tryMove() to throw, but it didn't");
        assertTrue(thrown.getMessage().contains("tryMove exception: piece is null(!)"),
                "expected tryMove() to throw piece is null exception, but got a different one");
    }

    @Test
    void tryMovingOtherPlayersPiece() {
        Square originSquare = new Square(0,0);
        originSquare.setSquarePiece(new Pawn(originSquare, Color.BLACK));
        Square destinationSquare = new Square (1, 1);
        Game.current_turn = Color.WHITE;

        Exception thrown = assertThrows(Exception.class, () -> tryMove(originSquare, destinationSquare),
                "expected tryMove() to throw, but it didn't");
        assertTrue(thrown.getMessage().contains("tryMove exception: moving other player's piece"),
                "expected tryMove() to throw moving other player's piece exception, but got a different one");
    }

    @Test
    void tryMakingIllegalMove() {
        Square originSquare = new Square(0,0);
        originSquare.setSquarePiece(new Pawn(originSquare, Color.WHITE));
        Square destinationSquare = new Square (1, 1);
        Game.current_turn = Color.WHITE;

        Exception thrown = assertThrows(Exception.class, () -> tryMove(originSquare, destinationSquare),
                "expected tryMove() to throw, but it didn't");
        assertTrue(thrown.getMessage().contains("tryMove exception: illegal move"),
                "expected tryMove() to throw illegal move exception, but got a different one");
    }

    @Test
    void tryTakingOwnPiece() {
        Square originSquare = new Square(0,0);
        originSquare.setSquarePiece(new Pawn(originSquare, Color.WHITE));
        Square destinationSquare = new Square (0, 1);
        destinationSquare.setSquarePiece(new Piece(destinationSquare, Color.WHITE));
        Game.current_turn = Color.WHITE;

        Exception thrown = assertThrows(Exception.class, () -> tryMove(originSquare, destinationSquare),
                "expected tryMove() to throw, but it didn't");
        assertTrue(thrown.getMessage().contains("tryMove exception: taking own piece"),
                "expected tryMove() to throw taking own piece exception, but got a different one");
    }
    @Test
    void tryCheckingOwnKing() {
        Square originSquare = new Square(0,1);
        originSquare.setSquarePiece(new Knight(originSquare, Color.WHITE));
        Square destinationSquare = new Square (2, 2);
        Game.current_turn = Color.WHITE;
        Square kingSquare = new Square(0,0);
        PieceList.whiteKing = new King (kingSquare, Color.WHITE);
        PieceList.whiteKing.setPieceCoordinates(0,0);
        Chessboard.board = new Square[1][1];
        Chessboard.board[0][0] = kingSquare;
        PieceList.addListPiece(new Queen (new Square (0,2), Color.BLACK), Color.BLACK);

        Exception thrown = assertThrows(Exception.class, () -> tryMove(originSquare, destinationSquare),
                "expected tryMove() to throw, but it didn't");
        assertTrue(thrown.getMessage().contains("tryMove exception: own king checked after move"),
                "expected tryMove() to throw own king checked after move exception, but got a different one");
    }

    // attempts to move Pawn B2 to B3
    @Test
    void tryMovingPieceInGame() {
        Game.main(null);
        Square originSquare = Chessboard.getBoardSquare(1,1);
        Square destinationSquare = Chessboard.getBoardSquare(1,2);
        try {
            tryMove(originSquare, destinationSquare);
            assert true;
        } catch (Exception e) {
            assert false;
        }
    }

}