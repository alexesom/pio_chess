import Pieces.*;
import Pieces.Square;

public class Player {
    private boolean isWhite;

    public Player() {

    }

    public boolean isChecked() {

        return false;
    }

    public boolean isCheckmated() {

        return false;
    }

    public byte tryMove(Square origin, Square destination) {
        Pieces.Piece piece = Chessboard.findPiece(origin);
        if (piece == null){
            return 1;
        }
        if (!piece.isAbleToMove()) {
            return 2;
        }
        else {
            piece.move(destination);
            nextTurn();
            return 0;
        }
    }

    public void nextTurn() {
        if (Game.current_turn == Color.WHITE){
            Game.current_turn = Color.BLACK;
        }
        if (Game.current_turn == Color.BLACK){
            Game.current_turn = Color.WHITE;
        }
    }
}
