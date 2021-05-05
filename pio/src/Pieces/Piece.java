package Pieces;

import ChessInterface.Chessboard;
import ChessInterface.PieceList;

import javax.swing.*;
import java.awt.*;

public class Piece {
    public JPanel panel = new JPanel();
    protected Color pieceColor;
    protected int xPieceCoordinate;
    protected int yPieceCoordinate;


    /*
    Using Piece() constructor is not recommended,
    use particular piece subclass constructor instead.
     */
    public Piece(Square square, Color color) {
        setxPieceCoordinate(square.getXSquareCoordinate());
        setyPieceCoordinate(square.getYSquareCoordinate());

        pieceColor = color;
        panel.setBackground(pieceColor);
        panel.setBounds(10 + 70 * getxPieceCoordinate(), 500 - 70 * getyPieceCoordinate(), 50, 50);
    }


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

    public void setPieceCoordinates(int x, int y){
        xPieceCoordinate = x;
        yPieceCoordinate = y;
    }

    protected void setPieceImage(JLabel image) {
        panel.setOpaque(false);
        image.setPreferredSize(new Dimension(panel.getWidth() - 5, panel.getHeight() - 5));
        panel.add(image);
    }

    public boolean isAnyPieceBetween(Square destinationSquare, PieceMotion motion) {
        try {
            //PieceList.checkedSquaresPath.clear();

            int xSquare = getxPieceCoordinate();
            int xDestinationSquare = destinationSquare.getXSquareCoordinate();
            int ySquare = getyPieceCoordinate();
            int yDestinationSquare = destinationSquare.getYSquareCoordinate();

            switch (motion) {
                case diagonal -> {
                    int yCounter;
                    int xCounter;
                    if (xSquare < xDestinationSquare) {
                        xCounter = xSquare + 1;
                        if (ySquare < yDestinationSquare) {
                            yCounter = ySquare + 1;
                            while (xCounter != xDestinationSquare && yCounter != yDestinationSquare) {
                                if (Chessboard.board[xCounter][yCounter].getSquarePiece() != null) {
                                    return false;
                                }
                                PieceList.checkedSquaresPath.add(Chessboard.board[xCounter][yCounter]);
                                xCounter++;
                                yCounter++;
                            }
                        } else {
                            yCounter = ySquare - 1;
                            while (xCounter != xDestinationSquare && yCounter != yDestinationSquare) {
                                if (Chessboard.board[xCounter][yCounter].getSquarePiece() != null) {
                                    return false;
                                }
                                PieceList.checkedSquaresPath.add(Chessboard.board[xCounter][yCounter]);
                                xCounter++;
                                yCounter--;
                            }
                        }
                    } else {
                        xCounter = xSquare - 1;
                        if (ySquare < yDestinationSquare) {
                            yCounter = ySquare + 1;
                            while (xCounter != xDestinationSquare && yCounter != yDestinationSquare) {
                                if (Chessboard.board[xCounter][yCounter].getSquarePiece() != null) {
                                    return false;
                                }
                                PieceList.checkedSquaresPath.add(Chessboard.board[xCounter][yCounter]);
                                xCounter--;
                                yCounter++;
                            }
                        } else {
                            yCounter = ySquare - 1;
                            while (xCounter != xDestinationSquare && yCounter != yDestinationSquare) {
                                if (Chessboard.board[xCounter][yCounter].getSquarePiece() != null) {
                                    return false;
                                }
                                PieceList.checkedSquaresPath.add(Chessboard.board[xCounter][yCounter]);
                                xCounter--;
                                yCounter--;
                            }
                        }
                    }
                    return true;
                }
                case horizontal -> {
                    if (xSquare < xDestinationSquare) {
                        for (int i = xSquare + 1; i < xDestinationSquare; i++) {
                            if (Chessboard.board[i][ySquare].getSquarePiece() != null) {
                                return false;
                            }
                            PieceList.checkedSquaresPath.add(Chessboard.board[i][ySquare]);
                        }
                    } else {
                        for (int i = xDestinationSquare + 1; i < xSquare; i++) {
                            if (Chessboard.board[i][ySquare].getSquarePiece() != null) {
                                return false;
                            }
                            PieceList.checkedSquaresPath.add(Chessboard.board[i][ySquare]);
                        }
                    }
                    return true;
                }
                case vertical -> {
                    if (Chessboard.board[xSquare][ySquare].getSquarePiece() instanceof Pawn &&
                            Chessboard.board[xSquare][ySquare].getSquarePiece().getPieceColor() == Color.WHITE) {
                        yDestinationSquare++;
                    } else if (Chessboard.board[xSquare][ySquare].getSquarePiece() instanceof Pawn &&
                            Chessboard.board[xSquare][ySquare].getSquarePiece().getPieceColor() == Color.BLACK) {
                        yDestinationSquare--;
                    }
                    if (ySquare < yDestinationSquare) {
                        for (int i = ySquare + 1; i < yDestinationSquare; i++) {
                            if (Chessboard.board[xSquare][i].getSquarePiece() != null) {
                                return false;
                            }
                            PieceList.checkedSquaresPath.add(Chessboard.board[xSquare][i]);
                        }
                    } else {
                        for (int i = yDestinationSquare + 1; i < ySquare; i++) {
                            if (Chessboard.board[xSquare][i].getSquarePiece() != null) {
                                return false;
                            }
                            PieceList.checkedSquaresPath.add(Chessboard.board[xSquare][i]);
                        }
                    }
                    return true;
                }
            }
        } catch (Exception ex) {
            System.out.println("Error in isAnyPieceBetween");
        }
        return true;
    }

    public void take(Piece piece) {

    }

    public Color getPieceColor() {
        return pieceColor;
    }

    public boolean isAbleToMove(Square square) {

        return false;
    }
    public void move() {

    }
    public boolean isAbleToCastle() {

        return false;
    }
}
