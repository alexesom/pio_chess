import java.util.List;

public class Chessboard {
    private static Square[][] board;
    private List<Piece> pieces;

    public Chessboard(List<Piece> pieces, Square[][] board) {
        this.pieces = pieces;
        Chessboard.board = board;
    }

    void createNewBoard() {

    }

}
