package Pieces;

import ChessInterface.*;
import javax.swing.*;
import java.awt.*;

public class Piece {
    protected Square pieceSquare;
    protected ChessColor pieceColor;
    protected int xPieceCoordinate;
    protected int yPieceCoordinate;

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

    enum PieceMotion {
        diagonal, horizontal, vertical
    }

    public boolean isAnyPieceBetween(Square destinationSquare, PieceMotion motion) {
        try{
            int xSquare = getxPieceCoordinate();
            int xDestinationSquare = destinationSquare.getXSquareCoordinate();
            int ySquare = getyPieceCoordinate();
            int yDestinationSquare = destinationSquare.getYSquareCoordinate();

            switch (motion) {
                case diagonal:
                    int yCounter = 0;
                    int xCounter = 0;
                    if(xSquare < xDestinationSquare) {
                        xCounter = xSquare;
                        if(ySquare < yDestinationSquare) {
                            yCounter = ySquare;
                            while(xCounter != xDestinationSquare && yCounter != yDestinationSquare) {
                                if(Chessboard.board[xCounter][yCounter].getSquarePiece() != null)
                                    return  false;
                                xCounter++;
                                yCounter++;
                            }
                        }
                        else {
                            yCounter = yDestinationSquare;
                            while(xCounter != xDestinationSquare && yCounter != ySquare) {
                                if(Chessboard.board[xCounter][yCounter].getSquarePiece() != null)
                                    return  false;
                                xCounter++;
                                yCounter--;
                            }
                        }
                    }
                    else {
                        xCounter = xDestinationSquare;
                        if (ySquare < yDestinationSquare) {
                            yCounter = ySquare;
                            while (xCounter != xSquare && yCounter != yDestinationSquare) {
                                if (Chessboard.board[xCounter][yCounter].getSquarePiece() != null)
                                    return false;
                                xCounter--;
                                yCounter++;
                            }
                        } else {
                            yCounter = yDestinationSquare;
                            while (xCounter != xSquare && yCounter != ySquare) {
                                if (Chessboard.board[xCounter][yCounter].getSquarePiece() != null)
                                    return false;
                                xCounter--;
                                yCounter--;
                            }
                        }
                    }
                    return true;

                case horizontal:
                    if(xSquare < xDestinationSquare) {
                        for(int i = xSquare; i < xDestinationSquare; i++ ) {
                            if(Chessboard.board[i][ySquare].getSquarePiece() != null)
                                return false;
                        }
                    }
                    else {
                        for(int i = xDestinationSquare; i < xSquare; i--) {
                            if(Chessboard.board[i][ySquare].getSquarePiece() != null)
                                return false;
                        }
                    }
                    return true;


                case vertical:
                    if(ySquare < yDestinationSquare){
                        for(int i = ySquare; i < yDestinationSquare; i++ ) {
                            if(Chessboard.board[xSquare][i].getSquarePiece() != null)
                                return false;
                        }
                    }
                    else {
                        for (int i = yDestinationSquare; i < ySquare; i--) {
                            if (Chessboard.board[xSquare][i].getSquarePiece() != null)
                                return false;
                        }
                    }
                    return true;
            }
        }
        catch (Exception ex)
        {

        }
        return  true;
    }

    public int getxPieceCoordinate() {
        return xPieceCoordinate;
    }
    public void setxPieceCoordinate(int xPieceCoordinate) {
        this.xPieceCoordinate = xPieceCoordinate;
    }
    public int getyPieceCoordinate() {
        return xPieceCoordinate;
    }
    public void setyPieceCoordinate(int yPieceCoordinate) {
        this.yPieceCoordinate = yPieceCoordinate;
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
