package Pieces;

import javax.swing.*;
import java.awt.*;

public class Piece {
    private JPanel piecePanel;
    private int xPieceCoordinate;
    private int yPieceCoordinate;
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

    public Piece(int x, int y, ChessColor chessColor) {
        xPieceCoordinate = x;
        yPieceCoordinate = y;
        JLabel label = new JLabel(new StretchIcon("piecesIcons\\blackqueen.png"));
        piecePanel = new JPanel();
        pieceColor = chessColor;

        piecePanel.setOpaque(false);
        piecePanel.setBounds(10, 10,50,50);
        label.setPreferredSize(new Dimension(piecePanel.getWidth(), piecePanel.getHeight()));
        piecePanel.add(label);

        public Piece() {
    }

    /*
    współrzędna x Piece to współrzędna szachownicy A-B,
    a współrzędna y to 1-8 na szachownicy
    niewskazane jest stosowanie konstruktora Piece(), zamiast tego
    należy użyć konstruktorów poszczególnych figur (podklasy)
     */
    public Piece(Square square, ChessColor color) {
        pieceSquare = square;
        pieceSquare.setSquarePiece(this);

        int x = pieceSquare.getXSquareCoordinate();
        int y = pieceSquare.getYSquareCoordinate();

        pieceColor = color;
        panel.setBackground(Color.blue);
        panel.setBounds(10+70*(x),500-70*(y),50,50);
    }
      
    protected void setPieceImage(JLabel image) {
        panel.setOpaque(false);
        image.setPreferredSize(new Dimension(panel.getWidth()-5, panel.getHeight()-5));
        panel.add(image);
    }



    //region To Be Done in future
    public void move(Square destination) {

    }

    public void take(Piece piece) {

    }
    //endregion

    //region pieceSquare property
    public Square getPieceSquare() {
        return pieceSquare;
    }

    public void setPieceSquare(Square pieceSquare) {
        this.pieceSquare = pieceSquare;
    }
    //endregion

    //region piecePanel property

    public JPanel getPiecePanel() {
        return piecePanel;
    }

    public void setPiecePanel(JPanel piecePanel) {
        this.piecePanel = piecePanel;
    }

    //endregion

    //region X, Y Piece Coordinates property
    public int getxPieceCoordinate() {
        return xPieceCoordinate;
    }

    public void setxPieceCoordinate(int xPieceCoordinate) {
        this.xPieceCoordinate = xPieceCoordinate;
    }

    public int getyPieceCoordinate() {
        return yPieceCoordinate;
    }

    public void setyPieceCoordinate(int yPieceCoordinate) {
        this.yPieceCoordinate = yPieceCoordinate;
    }
    //endregion

    public boolean isAbleToMove(Square square) {

        return true;
    }
}
