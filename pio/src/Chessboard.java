import Pieces.Piece;
import Pieces.Square;

import java.util.List;

public class Chessboard {
    private static Square[][] board;
    private static List<Piece> pieces;

    public Chessboard(List<Piece> pieces, Square[][] board) {
        Chessboard.pieces = pieces;
        Chessboard.board = board;
    }

    void createNewBoard() {

    }

    public static Piece findPiece(Square coordinates){
        for (Piece i : pieces) {
            if (i.position == coordinates) {
                return i;
            }
        }
        return null;
    }

}
