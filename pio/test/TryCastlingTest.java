package test;

import ChessInterface.Chessboard;
import ChessInterface.Game;
import Pieces.Square;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TryCastlingTest {
    @Test
    void tryCastlingIfFiguresBetween() {
        Chessboard.board = new Square[8][8];
        Game.main(null);
        Game.gameInterface.chessboard = new Chessboard();
        Square rookSquare = Chessboard.board[0][0];
        Square kingSquare = Chessboard.board[4][0];

        assertFalse(Chessboard.tryCastling(rookSquare, kingSquare),
                "tryCastlingIfFiguresBetween: expected return value of tryCastling false but was true");
    }

    @Test
    void tryCastlingIfNoFiguresBetween() {
        Chessboard.board = new Square[8][8];
        Game.main(null);
        Game.gameInterface.chessboard = new Chessboard();
        Square rookSquare = Chessboard.board[0][0];
        Square kingSquare = Chessboard.board[4][0];

        for (int i = 1; i < 4; i++)
            Chessboard.board[i][0].setSquarePiece(null);

        assertTrue(Chessboard.tryCastling(rookSquare, kingSquare),
                "tryCastlingIfNoFiguresBetween: expected return value of tryCastling true but was false");
    }

    @Test
    void tryCastlingIfRookWasMoved() {
        Chessboard.board = new Square[8][8];
        Game.main(null);
        Game.gameInterface.chessboard = new Chessboard();

        for (int i = 1; i < 4; i++) {
            Chessboard.board[i][0].setSquarePiece(null);
            Chessboard.board[0][i].setSquarePiece(null);
        }

        try {
            Chessboard.tryMove(Chessboard.board[0][0], Chessboard.board[1][0]);
        } catch (Exception e) {
            Square rookSquare = Chessboard.board[1][0];
            Square kingSquare = Chessboard.board[4][0];

            assertFalse(Chessboard.tryCastling(rookSquare, kingSquare),
                    "tryCastlingIfRookWasMoved: expected return value of tryCastling true but was false");
        }
    }
}
