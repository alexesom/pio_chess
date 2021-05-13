package test;

import static org.junit.Assert.assertEquals;
import ChessInterface.Adapter;
import ChessInterface.Chessboard;
import ChessInterface.Game;
import java.awt.*;
import Pieces.*;
import org.junit.jupiter.api.Test;

class isAnyPieceBetweenTest {

    @Test
     void testCorrectVertical(){
        //given
        Game.main(null);
        Square originSquare = Chessboard.getBoardSquare(2,1);
        Square destinationSquare = Chessboard.getBoardSquare(2, 3);

        //when
        try {
            Chessboard.tryMove(originSquare, destinationSquare);
        } catch (Exception e) {}
        boolean result = Piece.isBetween;

        //then
        boolean expected = true;
        assertEquals(expected, result);

    }
    @Test
    void testIncorrectVertical(){
        //given
        Game.main(null);
        Square originSquare = Chessboard.getBoardSquare(0,0);
        Square destinationSquare = Chessboard.getBoardSquare(5, 0);

        //when
        try {
            Chessboard.tryMove(originSquare, destinationSquare);
        } catch (Exception e) {}
        boolean result = Piece.isBetween;

        //then
        boolean expected = false;
        assertEquals(expected, result);

    }

    @Test
    void testCorrectHorizontal(){
        //given
        Game.main(null);
        Square originSquare = Chessboard.getBoardSquare(1,0);
        Square destinationSquare = Chessboard.getBoardSquare(2, 2);
        try {
            Chessboard.tryMove(originSquare, destinationSquare);
        } catch (Exception e) {}
        Game.current_turn = Color.WHITE;
        originSquare = Chessboard.getBoardSquare(0,0);
        destinationSquare = Chessboard.getBoardSquare(1, 0);

        //when
        try {
            Chessboard.tryMove(originSquare, destinationSquare);
        } catch (Exception e) {}
        boolean result = Piece.isBetween;

        //then
        boolean expected = true;
        assertEquals(expected, result);
    }
    @Test
    void testIncorrectHorizontal(){
        //given
        Game.main(null);
        Square originSquare = Chessboard.getBoardSquare(1,0);
        Square destinationSquare = Chessboard.getBoardSquare(0, 2);
        try {
            Chessboard.tryMove(originSquare, destinationSquare);
        } catch (Exception e) {}
        Game.current_turn = Color.WHITE;
        originSquare = Chessboard.getBoardSquare(2,1);
        destinationSquare = Chessboard.getBoardSquare(2, 2);
        try {
            Chessboard.tryMove(originSquare, destinationSquare);
        } catch (Exception e) {}
        Game.current_turn = Color.WHITE;
        originSquare = Chessboard.getBoardSquare(3,0);
        destinationSquare = Chessboard.getBoardSquare(1, 2);
        try {
            Chessboard.tryMove(originSquare, destinationSquare);
        } catch (Exception e) {}
        Game.current_turn = Color.WHITE;
        originSquare = Chessboard.getBoardSquare(0,0);
        destinationSquare = Chessboard.getBoardSquare(3, 0);
        //when
        try {
            Chessboard.tryMove(originSquare, destinationSquare);
        } catch (Exception e) {}
        boolean result = Piece.isBetween;

        //then
        boolean expected = false;
        assertEquals(expected, result);
    }

    @Test
    void testCorrectDiagonal(){
        //given
        Game.main(null);
        Square originSquare = Chessboard.getBoardSquare(1,1);
        Square destinationSquare = Chessboard.getBoardSquare(1, 3);
        try {
            Chessboard.tryMove(originSquare, destinationSquare);
        } catch (Exception e) {}
        Game.current_turn = Color.WHITE;
        originSquare = Chessboard.getBoardSquare(2,0);
        destinationSquare = Chessboard.getBoardSquare(0, 2);
        //when
        try {
            Chessboard.tryMove(originSquare, destinationSquare);
        } catch (Exception e) {}
        boolean result = Piece.isBetween;

        //then
        boolean expected = true;
        assertEquals(expected, result);
    }

    @Test
    void testIncorrectDiagonal(){
        //given
        Square originSquare = Chessboard.getBoardSquare(2,0);
        Square destinationSquare = Chessboard.getBoardSquare(0, 2);
        //when
        try {
            Chessboard.tryMove(originSquare, destinationSquare);
        } catch (Exception e) {}
        boolean result = Piece.isBetween;

        //then
        boolean expected = false;
        assertEquals(expected, result);
    }
}
