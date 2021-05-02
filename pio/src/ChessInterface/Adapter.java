package ChessInterface;

import Pieces.Square;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import static java.lang.Math.abs;

public class Adapter extends MouseAdapter {
    private final JLayeredPane myLayeredPane;
    private final JLayeredPane capturedWhite;
    private final JLayeredPane capturedBlack;
    public JPanel backlightPanel;
    boolean squareWasEmpty = false;
    boolean castling = false;
    private JPanel clickedPanel;
    private JPanel disappearPanel;
    private Point clickPoint;
    private int capturedWhiteFigures = 0;
    private int capturedBlackFigures = 0;


    public Adapter(JLayeredPane layer, JLayeredPane capturedWhite, JLayeredPane capturedBlack, JPanel backlightPanel) {
        myLayeredPane = layer;
        this.capturedBlack = capturedBlack;
        this.capturedWhite = capturedWhite;
        this.backlightPanel = backlightPanel;
    }

    @Override
    public void mousePressed(MouseEvent e) {
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
                selectedSquare = getSquareAtCoordinates(clickedPanel.getX(), clickedPanel.getY());
                destinationSquare = getSquareAtCoordinates(clickPoint.x, clickPoint.y);

                // move both Piece and the Panel if the move is legal
                try {
                    Chessboard.tryMove(selectedSquare, destinationSquare);
                    moveSelectedPanelTo(clickPoint);
                } catch (Exception ez) {
                    System.out.println(ez);
                }
            }

            if (!squareWasEmpty) { // a square containing a different panel was clicked
                clickPoint.x = e.getX() / 70 * 70 + 10;
                clickPoint.y = e.getY() / 70 * 70 + 10;
                //robię dwa razy clickPoint ale z jakiegoś powodu nie działa odpowiednio jeśli dam to w try {}
                //dodałam to tutaj bo potrzebuję koordynatów docelowego pola


                // find the Piece that was selected to move and the Square that was selected as its destination
                selectedSquare = getSquareAtCoordinates(clickedPanel.getX(), clickedPanel.getY());
                destinationSquare = getSquareAtCoordinates(clickPoint.x, clickPoint.y);
                // move both Piece and the Panel if the move is legal
                if (Chessboard.tryCastling(selectedSquare, destinationSquare)) {
                    JPanel kingPanel;
                    JPanel rookPanel;
                    if (disappearPanel.getX() == 4) {
                        kingPanel = disappearPanel;
                        rookPanel = clickedPanel;
                    } else {
                        kingPanel = clickedPanel;
                        rookPanel = disappearPanel;
                    }
                    if ((abs(kingPanel.getX() - rookPanel.getX())) / 70 == 4) {
                        longCastling(kingPanel, rookPanel);
                    } else {
                        shortCastling(kingPanel, rookPanel);
                    }
                    castling = true;
                    Game.nextTurn();
                } else try {
                    Chessboard.tryMove(selectedSquare, destinationSquare);
                    moveSelectedPanelTo(clickPoint);

                    if (!castling) {
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
                    }
                } catch (Exception ez) {
                    System.out.println(ez);
                }
            }

            castling = false;
            clickedPanel = null;
            clickPoint = null;
            squareWasEmpty = false;
        } else {
            try {
                clickedPanel = (JPanel) myLayeredPane.getComponentAt(e.getPoint());
                backlightPanel.setBounds(clickedPanel.getX() + 60, clickedPanel.getY() + 30, 80, 80);
                backlightPanel.setVisible(true);
            } catch (ClassCastException exception) {
                System.out.println("Choose Panel!");
            }
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (!e.getPoint().equals(clickPoint)) {
            clickedPanel = null;
            backlightPanel.setVisible(false);
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

    private void longCastling(JPanel kingPanel, JPanel rookPanel) {
        kingPanel.setLocation(2 * 70 + 10, kingPanel.getY());
        rookPanel.setLocation(3 * 70 + 10, rookPanel.getY());
    }

    private void shortCastling(JPanel kingPanel, JPanel rookPanel) {
        kingPanel.setLocation(6 * 70 + 10, kingPanel.getY());
        rookPanel.setLocation(5 * 70 + 10, rookPanel.getY());
    }
}

