package ChessInterface;

import Pieces.*;

import java.util.List;

public class PieceList {
    final King whiteKing;
    final King blackKing;
    List<Piece> whitePieces;
    List<Piece> blackPieces;

    public PieceList(King whiteKing, King blackKing) {
        this.whiteKing = whiteKing;
        this.blackKing = blackKing;
    }

    public void addWhitePiece(Piece piece) {
        whitePieces.add(piece);
    }

    public void addBlackPiece(Piece piece) {
        blackPieces.add(piece);
    }

    public King getBlackKing() {
        return blackKing;
    }


    public King getWhiteKing() {
        return whiteKing;
    }


    public List<Piece> getBlackPieces() {
        return blackPieces;
    }

    public List<Piece> getWhitePieces() {
        return whitePieces;
    }
}

