package Pieces;

import javax.swing.*;
import java.awt.*;

public class Piece {
    private Square pieceSquare;
    private JPanel piecePanel;
    private int xPieceCoordinate;
    private int yPieceCoordinate;
    private ChessColor pieceColor;


    public Piece() {
        piecePanel = new JPanel();
    }

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
