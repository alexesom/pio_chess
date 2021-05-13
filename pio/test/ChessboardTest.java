package test;

import ChessInterface.*;
import Pieces.*;
import org.junit.jupiter.api.Test;

import java.awt.*;

import static ChessInterface.Chessboard.tryMove;
import static org.junit.jupiter.api.Assertions.*;

class ChessboardTest {

    @Test
    void tryMovingNullPiece() {
        Square originSquare = new Square(0, 0);
        Square destinationSquare = new Square(1, 1);

        Exception thrown = assertThrows(Exception.class, () -> tryMove(originSquare, destinationSquare),
                "expected tryMove() to throw, but it didn't");
        assertTrue(thrown.getMessage().contains("tryMove exception: piece is null(!)"),
                "expected tryMove() to throw piece is null exception, but got a different one");
    }

    @Test
    void tryMovingOtherPlayersPiece() {
        Square originSquare = new Square(0, 0);
        originSquare.setSquarePiece(new Pawn(originSquare, Color.BLACK));
        Square destinationSquare = new Square(1, 1);
        Game.current_turn = Color.WHITE;

        Exception thrown = assertThrows(Exception.class, () -> tryMove(originSquare, destinationSquare),
                "expected tryMove() to throw, but it didn't");
        assertTrue(thrown.getMessage().contains("tryMove exception: moving other player's piece"),
                "expected tryMove() to throw moving other player's piece exception, but got a different one");
    }

    @Test
    void tryMakingIllegalMove() {
        Square originSquare = new Square(0, 0);
        originSquare.setSquarePiece(new Pawn(originSquare, Color.WHITE));
        Square destinationSquare = new Square(1, 1);
        Game.current_turn = Color.WHITE;

        Exception thrown = assertThrows(Exception.class, () -> tryMove(originSquare, destinationSquare),
                "expected tryMove() to throw, but it didn't");
        assertTrue(thrown.getMessage().contains("tryMove exception: illegal move"),
                "expected tryMove() to throw illegal move exception, but got a different one");
    }

    @Test
    void tryTakingOwnPiece() {
        Square originSquare = new Square(0, 0);
        originSquare.setSquarePiece(new Pawn(originSquare, Color.WHITE));
        Square destinationSquare = new Square(0, 1);
        destinationSquare.setSquarePiece(new Piece(destinationSquare, Color.WHITE));
        Game.current_turn = Color.WHITE;

        Exception thrown = assertThrows(Exception.class, () -> tryMove(originSquare, destinationSquare),
                "expected tryMove() to throw, but it didn't");
        assertTrue(thrown.getMessage().contains("tryMove exception: taking own piece"),
                "expected tryMove() to throw taking own piece exception, but got a different one");
    }

    @Test
    void tryCheckingOwnKing() {
        Square originSquare = new Square(0, 1);
        originSquare.setSquarePiece(new Knight(originSquare, Color.WHITE));
        Square destinationSquare = new Square(2, 2);
        Game.current_turn = Color.WHITE;
        Square kingSquare = new Square(0, 0);
        PieceList.whiteKing = new King(kingSquare, Color.WHITE);
        PieceList.whiteKing.setPieceCoordinates(0, 0);
        Chessboard.board = new Square[1][1];
        Chessboard.board[0][0] = kingSquare;
        PieceList.addListPiece(new Queen(new Square(0, 2), Color.BLACK), Color.BLACK);

        Exception thrown = assertThrows(Exception.class, () -> tryMove(originSquare, destinationSquare),
                "expected tryMove() to throw, but it didn't");
        assertTrue(thrown.getMessage().contains("tryMove exception: own king checked after move"),
                "expected tryMove() to throw own king checked after move exception, but got a different one");
    }

    // attempts to move Pawn B2 to B3
    @Test
    void tryMovingPieceInGame() {
        Chessboard.board = new Square[8][8];
        Game.main(null);
        Chessboard testChessboard = new Chessboard();
        Square originSquare = Chessboard.getBoardSquare(1, 1);
        Square destinationSquare = Chessboard.getBoardSquare(1, 2);
        try {
            tryMove(originSquare, destinationSquare);
            assert true;
        } catch (Exception e) {
            assert false;
        }
    }

    @Test
    void checkPromotionIfPromotedTest() {
        Chessboard.board = new Square[8][8];
        Game.main(null);
        Chessboard testChessboard = new Chessboard();
        Adapter testAdapter = Game.gameInterface.chessboard.mouseAdapter;
        Square destinationSquare = new Square(0, 7);
        Pawn testPawn = new Pawn(destinationSquare, Color.WHITE);
        destinationSquare.setSquarePiece(testPawn);
        Chessboard.board[0][7].setSquarePiece(testPawn);

        testAdapter.checkPromotion(destinationSquare);

        assertFalse((Adapter.enable && testAdapter.promotionSquare == null),
                "checkPromotionIfPromotedTest: expected change of parameters by checkPromotion but there were no change");
        Adapter.enable = true;
    }

    @Test
    void checkPromotionIfNotPromotedTest() {
        Chessboard.board = new Square[8][8];
        Game.main(null);
        Game.gameInterface.chessboard = new Chessboard();
        Adapter testAdapter = Game.gameInterface.chessboard.mouseAdapter;
        Square destinationSquare = new Square(0, 4);
        Pawn testPawn = new Pawn(destinationSquare, Color.WHITE);
        destinationSquare.setSquarePiece(testPawn);
        Chessboard.board[0][4].setSquarePiece(testPawn);

        testAdapter.checkPromotion(destinationSquare);

        assertTrue((Adapter.enable && testAdapter.promotionSquare == null),
                "checkPromotionIfNotPromotedTest: expected change of parameters by checkPromotion but there were no change");
    }

    @Test
    void checkPromotionIfAnotherFigure() {
        Chessboard.board = new Square[8][8];
        Game.main(null);
        Game.gameInterface.chessboard = new Chessboard();
        Adapter testAdapter = Game.gameInterface.chessboard.mouseAdapter;
        Square destinationSquare = new Square(0, 4);
        Knight testPawn = new Knight(destinationSquare, Color.WHITE);
        destinationSquare.setSquarePiece(testPawn);
        Chessboard.board[0][4].setSquarePiece(testPawn);

        testAdapter.checkPromotion(destinationSquare);

        assertTrue((Adapter.enable && testAdapter.promotionSquare == null),
                "checkPromotionIfAnotherFigure: expected change of parameters by checkPromotion but there were no change");
    }


    @Test
    void canAttackerBeTakenIfNotChecked() {
        Chessboard.board = new Square[8][8];
        Game.main(null);
        Game.gameInterface.chessboard = new Chessboard();

        assertTrue((CheckLogic.canAttackerBeTaken()),
                "canAttackerBeTakenIfNotChecked: expected true values but was false");
    }

    @Test
    void canAttackerBeTakenIfDoubleChecked() {
        Chessboard.board = new Square[8][8];
        Game.main(null);
        Game.gameInterface.chessboard = new Chessboard();
        CheckLogic.setFiguresChecking(2);
        CheckLogic.setCheckingSquare(Chessboard.getBoardSquare(2, 2));

        assertFalse((CheckLogic.canAttackerBeTaken()),
                "canAttackerBeTakenIfDoubleChecked: expected false value but was true");
    }
}