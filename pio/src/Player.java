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
        Pieces.Piece piece = origin.getSquarePiece();
        if (piece == null){
            return 1;
        }
        if (!piece.isAbleToMove(destination)) {
            return 2;
        }
        else {
            if (destination.getSquarePiece() == null)
                piece.move(destination);
            else
                piece.take(destination.getSquarePiece());
            Game.nextTurn();
            return 0;
        }
    }


}
