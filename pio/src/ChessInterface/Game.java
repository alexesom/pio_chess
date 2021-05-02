package ChessInterface;

import java.awt.*;

public class Game {

    public static Color current_turn;

    public static void main(String args[]) {
        current_turn = Color.WHITE;
        new GameInterface();
    }

    public static void nextTurn() {
        if (current_turn == Color.WHITE) {
            current_turn = Color.BLACK;
        } else if (current_turn == Color.BLACK) {
            current_turn = Color.WHITE;
        }
    }
}
