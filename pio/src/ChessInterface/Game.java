package ChessInterface;

import Pieces.*;

public class Game {

    public static ChessColor current_turn;

    public static void main(String args[]) {
        current_turn = ChessColor.WHITE;
        new GameInterface();
    }

    public static void nextTurn() {
        if (current_turn == ChessColor.WHITE) {
            current_turn = ChessColor.BLACK;
        } else if (current_turn == ChessColor.BLACK) {
            current_turn = ChessColor.WHITE;
        }
    }
}
