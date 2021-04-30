package Pieces;

import ChessInterface.*;
import javax.swing.*;
import java.awt.*;
import java.awt.Color;

public class Piece {
    protected Color pieceColor;
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
    public Piece(Square square, Color color) {
        setxPieceCoordinate(square.getXSquareCoordinate());
        setyPieceCoordinate(square.getYSquareCoordinate());

        pieceColor = color;
        panel.setBackground(pieceColor);
        panel.setBounds(10 + 70* getxPieceCoordinate(), 500-70* getyPieceCoordinate(),50,50);
    }

    /*
    converts X coordinate on the board to position on the JPanel
    */
    public int getxPieceCoordinate() {
        return xPieceCoordinate;
    }

    /*
    converts Y coordinate on the board to position on the JPanel
    */
    public int getyPieceCoordinate() {
        return yPieceCoordinate;
    }

    protected void setPieceImage(JLabel image){
        panel.setOpaque(false);
        image.setPreferredSize(new Dimension(panel.getWidth()-5, panel.getHeight()-5));
        panel.add(image);
    }

    public boolean isAnyPieceBetween(Square destinationSquare, PieceMotion motion) {
        try {
            int xSquare = getxPieceCoordinate();
            int xDestinationSquare = destinationSquare.getXSquareCoordinate();
            int ySquare = getyPieceCoordinate();
            int yDestinationSquare = destinationSquare.getYSquareCoordinate();

            switch (motion) {
                case diagonal:
                    int yCounter = 0;
                    int xCounter = 0;
                    if(xSquare < xDestinationSquare) {
                        xCounter = xSquare+1;
                        if(ySquare < yDestinationSquare) {
                            yCounter = ySquare+1;
                            while(xCounter != xDestinationSquare && yCounter != yDestinationSquare) {
                                if(Chessboard.board[xCounter][yCounter].getSquarePiece() != null)
                                    return  false;
                                xCounter++;
                                yCounter++;
                            }
                        }
                        else {
                            yCounter = ySquare-1;
                            while(xCounter != xDestinationSquare && yCounter != yDestinationSquare) {
                                if(Chessboard.board[xCounter][yCounter].getSquarePiece() != null)
                                    return  false;
                                xCounter++;
                                yCounter--;
                            }
                        }
                    }
                    else {
                        xCounter = xSquare - 1;
                        if (ySquare < yDestinationSquare) {
                            yCounter = ySquare + 1;
                            while (xCounter != xDestinationSquare && yCounter != yDestinationSquare) {
                                if (Chessboard.board[xCounter][yCounter].getSquarePiece() != null)
                                    return false;
                                xCounter--;
                                yCounter++;
                            }
                        } else {
                            yCounter = ySquare - 1;
                            while (xCounter != xDestinationSquare && yCounter != yDestinationSquare) {
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
                        for(int i = xSquare+1; i < xDestinationSquare; i++ ) {
                            if(Chessboard.board[i][ySquare].getSquarePiece() != null)
                                return false;
                        }
                    }
                    else {
                        for(int i = xDestinationSquare+1; i < xSquare; i++) {
                            if(Chessboard.board[i][ySquare].getSquarePiece() != null)
                                return false;
                        }
                    }
                    return true;


                case vertical:
                    if(Chessboard.board[xSquare][ySquare].getSquarePiece().equals("pawn")) {
                        yDestinationSquare++;
                    }
                    if(ySquare < yDestinationSquare){
                        for(int i = ySquare+1; i < yDestinationSquare; i++ ) {
                            if(Chessboard.board[xSquare][i].getSquarePiece() != null)
                                return false;
                        }
                    }
                    else {
                        for (int i = yDestinationSquare+1; i < ySquare; i++) {
                            if (Chessboard.board[xSquare][i].getSquarePiece() != null)
                                return false;
                        }
                    }
                    return true;
            }
        }
        catch (Exception ex)
        {
            System.out.println("Error in isAnyPieceBetween");
        }
        return  true;
    }

    public void setxPieceCoordinate(int xPieceCoordinate) {
        this.xPieceCoordinate = xPieceCoordinate;
    }
    public void setyPieceCoordinate(int yPieceCoordinate) {
        this.yPieceCoordinate = yPieceCoordinate;
    }

    public void take(Piece piece) {

    }

    public Color getPieceColor() {
        return pieceColor;
    }

    public boolean isAbleToMove(Square square) {

        return false;
    }
    public boolean isAbleToCastle() {

        return false;
    }
}
