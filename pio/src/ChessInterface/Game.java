package ChessInterface;

import java.awt.*;

public class Game {

    public static Color current_turn;
    public static GameInterface gameInterface;

    public static void main(String args[]) {
        current_turn = Color.WHITE;
        gameInterface = new GameInterface();
    }

    public static void nextTurn() {
        if (current_turn == Color.WHITE) {
            current_turn = Color.BLACK;
            gameInterface.name1.setForeground(new Color(102, 205, 99));
            gameInterface.name2.setForeground(Color.BLACK);
        } else if (current_turn == Color.BLACK) {
            current_turn = Color.WHITE;
            gameInterface.name2.setForeground(new Color(102, 205, 99));
            gameInterface.name1.setForeground(Color.BLACK);
        }
        CheckLogic.checkLoop();
        CheckLogic.highlightCheck();
        if (CheckLogic.gameEnded) {
            if(Game.current_turn == Color.WHITE) {
                GamePanels.endGamePanel(GameInterface.player1.name);
            }
            else {
                GamePanels.endGamePanel(GameInterface.player2.name);
            }

            GamePanels.endGamePanel.setVisible(true);
            Adapter.enable = false;
        }
    }
}