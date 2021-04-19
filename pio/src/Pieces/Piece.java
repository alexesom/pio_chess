package Pieces;

import javax.swing.*;
import java.awt.*;

public class Piece {
    protected Square pieceSquare;
    protected ChessColor pieceColor;

    public JPanel panel = new JPanel();

    JLabel whitePawn =  new JLabel(new StretchIcon("piecesIcons/whitepawn.png"));
    JLabel blackPawn =  new JLabel(new StretchIcon("piecesIcons/blackpawn.png"));
    JLabel whiteRook =  new JLabel(new StretchIcon("piecesIcons/whiterook.png"));
    JLabel blackRook =  new JLabel(new StretchIcon("piecesIcons/blackrook.png"));
    JLabel whiteKnight =  new JLabel(new StretchIcon("piecesIcons/whiteknight.png"));
    JLabel blackKnight =  new JLabel(new StretchIcon("piecesIcons/blackknight.png"));
    JLabel whiteBishop =  new JLabel(new StretchIcon("piecesIcons/whitebishop.png"));
    JLabel blackBishop =  new JLabel(new StretchIcon("piecesIcons/blackbishop.png"));
    JLabel whiteQueen = new JLabel(new StretchIcon("piecesIcons/whitequeen.png"));
    JLabel blackQueen =  new JLabel(new StretchIcon("piecesIcons/blackqueen.png"));
    JLabel whiteKing =  new JLabel(new StretchIcon("piecesIcons/whiteking.png"));
    JLabel blackKing =  new JLabel(new StretchIcon("piecesIcons/blackking.png"));


    /*
    Using Piece() constructor is not recommended,
    use particular piece subclass constructor instead.
     */
    public Piece(Square square, ChessColor color) {
        pieceSquare = square;
        pieceSquare.setSquarePiece(this);

        pieceColor = color;
        panel.setBackground(Color.blue);
        panel.setBounds(pieceSquare.getXPanelPosition(),pieceSquare.getYPanelPosition(),50,50);
    }
    protected void setPieceImage(JLabel image){
        panel.setOpaque(false);
        image.setPreferredSize(new Dimension(panel.getWidth()-5, panel.getHeight()-5));
        panel.add(image);
    }


    public void take(Piece piece) {

    }

    public Square getPieceSquare() {
        return pieceSquare;
    }

    /*
    moves the piece to a new square
    and sets the original and new Square.SquarePiece values accordingly
     */
    public void move (Square newSquare) {
        pieceSquare.setSquarePiece(null);
        pieceSquare = newSquare;
        pieceSquare.setSquarePiece(this);
    }

    public boolean isAbleToMove(Square square) {

        return true;
    }
}
