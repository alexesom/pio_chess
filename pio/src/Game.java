import Pieces.*;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class Game {

  //  public static ChessColor current_turn;

    public static void main(String args[]) {

        /*StretchIcon whiteQueen = new StretchIcon("piecesIcons/whitequeen.png");
        StretchIcon blackQueen = new StretchIcon("piecesIcons/blackqueen.png");
        StretchIcon whiteKing = new StretchIcon("piecesIcons/whiteking.png");
        StretchIcon blackKing = new StretchIcon("piecesIcons/blackking.png");
        StretchIcon whiteBishop = new StretchIcon("piecesIcons/whitebishop.png");
        StretchIcon blackBishop = new StretchIcon("piecesIcons/blackbishop.png");
        StretchIcon whiteRook = new StretchIcon("piecesIcons/whiterook.png");
        StretchIcon blackRook = new StretchIcon("piecesIcons/blackrook.png");
        StretchIcon whiteKnight = new StretchIcon("piecesIcons/whiteknight.png");
        StretchIcon blackKnight = new StretchIcon("piecesIcons/blackknight.png");
        StretchIcon whitePawn = new StretchIcon("piecesIcons/whitepawn.png");
        StretchIcon blackPawn = new StretchIcon("piecesIcons/blackpawn.png");
        List<Piece> list = new ArrayList<>();
        list.add(new Queen(new Square(3,0, new JButton(whiteQueen)), ChessColor.WHITE));
        list.add(new Queen(new Square(3,7, new JButton(blackQueen)), ChessColor.BLACK));
        list.add(new King(new Square(4,0, new JButton(whiteKing)), ChessColor.WHITE));
        list.add(new King(new Square(4,7, new JButton(blackKing)), ChessColor.BLACK));
        list.add(new Bishop(new Square(5,0, new JButton(whiteBishop)), ChessColor.WHITE));
        list.add(new Bishop(new Square(2,0, new JButton(whiteBishop)), ChessColor.WHITE));
        list.add(new Bishop(new Square(5,7, new JButton(blackBishop)), ChessColor.BLACK));
        list.add(new Bishop(new Square(2,7, new JButton(blackBishop)), ChessColor.BLACK));
        list.add(new Rook(new Square(7,0, new JButton(whiteRook)), ChessColor.WHITE));
        list.add(new Rook(new Square(0,0, new JButton(whiteRook)), ChessColor.WHITE));
        list.add(new Rook(new Square(7,7, new JButton(blackRook)), ChessColor.BLACK));
        list.add(new Rook(new Square(0,7, new JButton(blackRook)), ChessColor.BLACK));
        list.add(new Knight(new Square(6,0, new JButton(whiteKnight)), ChessColor.WHITE));
        list.add(new Knight(new Square(1,0, new JButton(whiteKnight)), ChessColor.WHITE));
        list.add(new Knight(new Square(6,7, new JButton(blackKnight)), ChessColor.BLACK));
        list.add(new Knight(new Square(1,7, new JButton(blackKnight)), ChessColor.BLACK));
        list.add(new Pawn(new Square(0,1, new JButton(whitePawn)), ChessColor.WHITE));
        list.add(new Pawn(new Square(1,1, new JButton(whitePawn)), ChessColor.WHITE));
        list.add(new Pawn(new Square(2,1, new JButton(whitePawn)), ChessColor.WHITE));
        list.add(new Pawn(new Square(3,1, new JButton(whitePawn)), ChessColor.WHITE));
        list.add(new Pawn(new Square(4,1, new JButton(whitePawn)), ChessColor.WHITE));
        list.add(new Pawn(new Square(5,1, new JButton(whitePawn)), ChessColor.WHITE));
        list.add(new Pawn(new Square(6,1, new JButton(whitePawn)), ChessColor.WHITE));
        list.add(new Pawn(new Square(7,1, new JButton(whitePawn)), ChessColor.WHITE));
        list.add(new Pawn(new Square(0,6, new JButton(blackPawn)), ChessColor.BLACK));
        list.add(new Pawn(new Square(1,6, new JButton(blackPawn)), ChessColor.BLACK));
        list.add(new Pawn(new Square(2,6, new JButton(blackPawn)), ChessColor.BLACK));
        list.add(new Pawn(new Square(3,6, new JButton(blackPawn)), ChessColor.BLACK));
        list.add(new Pawn(new Square(4,6, new JButton(blackPawn)), ChessColor.BLACK));
        list.add(new Pawn(new Square(5,6, new JButton(blackPawn)), ChessColor.BLACK));
        list.add(new Pawn(new Square(6,6, new JButton(blackPawn)), ChessColor.BLACK));
        list.add(new Pawn(new Square(7,6, new JButton(blackPawn)), ChessColor.BLACK));
        */

        new GameInterface();
    }
}
