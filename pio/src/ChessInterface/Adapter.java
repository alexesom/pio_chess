package ChessInterface;

import Pieces.Pawn;
import Pieces.Piece;
import Pieces.Square;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Adapter extends MouseAdapter {
    boolean squareWasEmpty = false;
    private JLayeredPane myLayeredPane;
    private JPanel clickedPanel;
    private JPanel disappearPanel;
    private JLayeredPane capturedWhite;
    private JLayeredPane capturedBlack;
    private Point clickPoint;
    private int capturedWhiteFigures = 0;
    private int capturedBlackFigures = 0;

    public Adapter(JLayeredPane layer, JLayeredPane capturedWhite, JLayeredPane capturedBlack) {
        myLayeredPane = layer;
        this.capturedBlack = capturedBlack;
        this.capturedWhite = capturedWhite;
    }

    @Override
    public void mousePressed(MouseEvent e) {
        Piece selectedPiece;
        int xPiece;
        int yPiece;
        Square selectedSquare;
        Square destinationSquare;

        clickPoint = e.getPoint();

        if (clickedPanel != null) {
            clickPoint.x = e.getX() / 70 * 70 + 10;
            clickPoint.y = e.getY() / 70 * 70 + 10;
            try {
                disappearPanel = (JPanel) myLayeredPane.getComponentAt(clickPoint);
            } catch (ClassCastException ex) { // the clicked square didn't have another panel on it
                squareWasEmpty = true;
                // find the Piece that was selected to move and the Square that was selected as its destination
                selectedPiece = getPieceAtCoordinates(clickedPanel.getX(), clickedPanel.getY());
                selectedSquare = getSquareAtCoordinates(clickedPanel.getX(), clickedPanel.getY());
                destinationSquare = getSquareAtCoordinates(clickPoint.x, clickPoint.y);

                // move both Piece and the Panel if the move is legal
                if (selectedPiece.isAbleToMove(destinationSquare)) {
                    Chessboard.movePieceInSquares(selectedSquare, destinationSquare);
                    moveSelectedPanelTo(clickPoint);

                    Game.nextTurn();
                } else {
                    System.out.println("Illegal move!");
                }
            }

            if (squareWasEmpty == false) { // a square containing a different panel was clicked
                clickPoint.x = e.getX() / 70 * 70 + 10;
                clickPoint.y = e.getY() / 70 * 70 + 10;
                //robię dwa razy clickPoint ale z jakiegoś powodu nie działa odpowiednio jeśli dam to w try {}
                //dodałam to tutaj bo potrzebuję koordynatów docelowego pola


                // find the Piece that was selected to move and the Square that was selected as its destination
                selectedPiece = getPieceAtCoordinates(clickedPanel.getX(), clickedPanel.getY());
                xPiece = selectedPiece.getxPieceCoordinate();
                yPiece = selectedPiece.getyPieceCoordinate();
                selectedSquare = getSquareAtCoordinates(clickedPanel.getX(), clickedPanel.getY());
                destinationSquare = getSquareAtCoordinates(clickPoint.x, clickPoint.y);

                // move both Piece and the Panel if the move is legal
                if (selectedPiece.isAbleToMove(destinationSquare) &&
                        !(Chessboard.board[xPiece][yPiece].getSquarePiece() instanceof Pawn)) {

                    Chessboard.movePieceInSquares(selectedSquare, destinationSquare); //move teraz jest w Chessboard
                    clickedPanel.setLocation(disappearPanel.getX(), disappearPanel.getY());
                    if (clickedPanel != disappearPanel) {
                        clickedPanel.setLocation(disappearPanel.getX(), disappearPanel.getY());
                        myLayeredPane.remove(disappearPanel);
                        if (disappearPanel.getBackground() == Color.white) {
                            capturedWhiteFigures++;
                            if (capturedWhiteFigures <= 8)
                                disappearPanel.setLocation(10 + 70 * (capturedWhiteFigures - 1), 10);
                            else
                                disappearPanel.setLocation(10 + 70 * (capturedWhiteFigures - 9), 80);
                            capturedWhite.add(disappearPanel);
                        } else {
                            capturedBlackFigures++;
                            if (capturedBlackFigures <= 8)
                                disappearPanel.setLocation(10 + 70 * (capturedBlackFigures - 1), 10);
                            else
                                disappearPanel.setLocation(10 + 70 * (capturedBlackFigures - 9), 80);
                            capturedBlack.add(disappearPanel);
                        }
                        System.out.println(disappearPanel.getLocation());
                    }

                    Game.nextTurn();
                } else if (selectedPiece.isAbleToMove(destinationSquare) &&
                        selectedPiece instanceof Pawn &&
                        Chessboard.EnPassant.isEmpty()) {
                    Pawn selectedPawn = (Pawn) selectedPiece;
                    if (selectedPawn.enPassantFlag) {

                    }
                } else
                    System.out.println("Illegal move!");

            }

            clickedPanel = null;
            clickPoint = null;
            squareWasEmpty = false;
        } else {
            try {
                clickedPanel = (JPanel) myLayeredPane.getComponentAt(e.getPoint());
            } catch (ClassCastException exception) {
                System.out.println("Choose Panel!");
            }
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (!e.getPoint().equals(clickPoint)) {
            clickedPanel = null;
        }
        clickPoint = null;
    }

    private void moveSelectedPanelTo(Point p) {
        if (clickedPanel != null) {
            int x = p.x;
            int y = p.y;
            clickedPanel.setLocation(x, y);
        }
    }

    /*
    returns the game logic Square at given panel coordinates
    */
    private Square getSquareAtCoordinates(int x, int y) {
        int sqx = (x - 10) / 70;
        int sqy = (500 - y) / 70;
        return Chessboard.board[sqx][sqy];
    }

    /*
    returns the Piece at given panel coordinates
     */
    private Piece getPieceAtCoordinates(int x, int y) {
        int sqx = (x - 10) / 70;
        int sqy = (500 - y) / 70;
        return Chessboard.board[sqx][sqy].getSquarePiece();
    }
}

