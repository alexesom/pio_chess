package ChessInterface;

import Pieces.King;
import Pieces.Piece;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class PieceList {
    public static King whiteKing = null;
    public static King blackKing = null;
    static List<Piece> whitePieces = new ArrayList<>();
    static List<Piece> blackPieces = new ArrayList<>();

    public static void addWhitePiece(Piece piece) {
        whitePieces.add(piece);
    }

    public static void addBlackPiece(Piece piece) {
        blackPieces.add(piece);
    }

    public static void addListPiece(Piece piece, Color pieceColor) {
        if (pieceColor == Color.white) {
            whitePieces.add(piece);
        } else if (pieceColor == Color.black) {
            blackPieces.add(piece);
        } else System.err.print("Trying to add non black/white piece to List!");
    }


    public static List<Piece> getBlackPieces() {
        return blackPieces;
    }

    public static List<Piece> getWhitePieces() {
        return whitePieces;
    }

    public static List<Piece> getOppositeColorPieces(Color color) {
        assert (color == Color.WHITE || color == Color.BLACK);
        if (color == Color.BLACK) {
            return whitePieces;
        }
        else {
            return blackPieces;
        }
    }

    public static List<Piece> getColorPieces(Color color) {
        assert (color == Color.WHITE || color == Color.BLACK);
        if (color == Color.WHITE) {
            return whitePieces;
        }
        else {
            return blackPieces;
        }
    }

    public static King getKing(Color kingColor) {
        assert (kingColor == Color.WHITE || kingColor == Color.BLACK);
        if (kingColor == Color.WHITE) {
            return whiteKing;
        }
        return blackKing;
    }
}

