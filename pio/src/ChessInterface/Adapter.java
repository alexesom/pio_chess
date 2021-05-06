package ChessInterface;

import Pieces.Piece;
import Pieces.Square;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import static java.lang.Math.abs;

public class Adapter extends MouseAdapter {
    public static boolean enable = true;
    private final JLayeredPane myLayeredPane;
    private final JLayeredPane capturedWhite;
    private final JLayeredPane capturedBlack;
    public JPanel backlightPanel;
    public Square promotionSquare;
    boolean squareWasEmpty = false;
    boolean castling = false;
    private JPanel clickedPanel;
    private JPanel disappearPanel;
    private Point clickPoint;
    private int capturedWhiteFigures = 0;
    private int capturedBlackFigures = 0;
    private JPanel whitePromotionPanel;
    private JPanel blackPromotionPanel;

    public Adapter(JLayeredPane layer, JLayeredPane capturedWhite, JLayeredPane capturedBlack, JPanel backlightPanel, JPanel whitePromotionPanel, JPanel blackPromotionPanel) {
        myLayeredPane = layer;
        this.capturedBlack = capturedBlack;
        this.capturedWhite = capturedWhite;
        this.backlightPanel = backlightPanel;
        this.whitePromotionPanel = whitePromotionPanel;
        this.blackPromotionPanel = blackPromotionPanel;
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (!enable)
            return;

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
                    enPassant(selectedSquare, destinationSquare);
                    Chessboard.tryMove(selectedSquare, destinationSquare);
                    moveSelectedPanelTo(clickPoint);
                    checkPromotion(destinationSquare);
                } catch (Exception ez) {
                    exceptionHandler(selectedSquare, destinationSquare, ez);
                }
            }

            if (!squareWasEmpty) { // a square containing a different panel was clicked
                clickPoint.x = e.getX() / 70 * 70 + 10;
                clickPoint.y = e.getY() / 70 * 70 + 10;

                // find the Piece that was selected to move and the Square that was selected as its destination
                selectedSquare = getSquareAtCoordinates(clickedPanel.getX(), clickedPanel.getY());
                destinationSquare = getSquareAtCoordinates(clickPoint.x, clickPoint.y);
                if (selectedSquare.getSquarePiece().getPieceColor() == Game.current_turn &&
                        Chessboard.tryCastling(selectedSquare, destinationSquare)) {
                    JPanel kingPanel;
                    JPanel rookPanel;
                    Piece movingPiece;
                    Piece destinationPiece;
                    if (disappearPanel.getX() / 70 == 4) {
                        kingPanel = disappearPanel;
                        rookPanel = clickedPanel;
                        movingPiece = destinationSquare.getSquarePiece();
                        destinationPiece = selectedSquare.getSquarePiece();
                    } else {
                        kingPanel = clickedPanel;
                        rookPanel = disappearPanel;
                        destinationPiece = destinationSquare.getSquarePiece();
                        movingPiece = selectedSquare.getSquarePiece();
                    }
                    Square finalKing;
                    Square finalRook;

                    if ((abs(kingPanel.getX() - rookPanel.getX())) / 70 == 4) {
                        longCastling(kingPanel, rookPanel);
                        finalKing = getSquareAtCoordinates(2 * 70 + 10, clickedPanel.getY());
                        finalRook = getSquareAtCoordinates(3 * 70 + 10, clickedPanel.getY());
                        movingPiece.setxPieceCoordinate(2);
                        movingPiece.setyPieceCoordinate((500 - clickedPanel.getY()) / 70);
                        destinationPiece.setyPieceCoordinate((500 - clickedPanel.getY()) / 70);
                        destinationPiece.setxPieceCoordinate(3);


                    } else {
                        shortCastling(kingPanel, rookPanel);
                        finalKing = getSquareAtCoordinates(6 * 70 + 10, clickedPanel.getY());
                        finalRook = getSquareAtCoordinates(5 * 70 + 10, clickedPanel.getY());
                        movingPiece.setxPieceCoordinate(6);
                        movingPiece.setyPieceCoordinate((500 - clickedPanel.getY()) / 70);
                        destinationPiece.setyPieceCoordinate((500 - clickedPanel.getY()) / 70);
                        destinationPiece.setxPieceCoordinate(5);
                    }

                    finalKing.setSquarePiece(movingPiece);
                    finalRook.setSquarePiece(destinationPiece);
                    selectedSquare.setSquarePiece(null);
                    destinationSquare.setSquarePiece(null);

                    castling = true;
                    Game.nextTurn();
                } else try {
                    Chessboard.tryMove(selectedSquare, destinationSquare);
                    moveSelectedPanelTo(clickPoint);
                    checkPromotion(destinationSquare);

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
                    exceptionHandler(selectedSquare, destinationSquare, ez);
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
                MessagesForUsers.createMessage1();
            }
        }
    }
    // bez pozytywnego myślenia program nie zadziała


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

    private JPanel getPanelAtCoordinates(int x, int y) {
        int sqx = (x * 70) + 10;
        int sqy = 500 - (y * 70);
        return ((JPanel) myLayeredPane.getComponentAt(new Point(sqx, sqy)));
    }

    private void checkPromotion(Square destinationSquare) {
        /* check whether the promotion conditions are met */
        int x = destinationSquare.getXSquareCoordinate();
        int y = destinationSquare.getYSquareCoordinate();
        if (Chessboard.board[x][y].getSquarePiece() instanceof Pieces.Pawn && (y == 7 || y == 0)) {
            enable = false;
            promotionSquare = destinationSquare;
            if (Game.current_turn == Color.black) {
                whitePromotionPanel.setVisible(true);
                whitePromotionPanel.setEnabled(true);
            } else {
                blackPromotionPanel.setVisible(true);
                blackPromotionPanel.setEnabled(true);
            }
            clickedPanel.setVisible(false);
            myLayeredPane.remove(clickedPanel);
        }
    }

    public void promote(Piece promotionPiece, JLabel newLabel) {
        Piece removedPiece = promotionSquare.getSquarePiece();
        Color pieceColor;
        if (Game.current_turn == Color.white)
            pieceColor = Color.black;
        else
            pieceColor = Color.white;
        PieceList.removeListPiece(removedPiece, pieceColor);
        promotionSquare.setSquarePiece(promotionPiece);
        JPanel panel = new JPanel();
        panel.setOpaque(false);
        newLabel.setPreferredSize(new Dimension(panel.getWidth() - 5, panel.getHeight() - 5));
        panel.add(newLabel);
        myLayeredPane.add(panel);
        PieceList.addListPiece(removedPiece, pieceColor);

        CheckLogic.checkLoop();
        CheckLogic.highlightCheck();
        if (CheckLogic.gameEnded) {
            //endGame();
        }
        enable = true;
    }

    private void longCastling(JPanel kingPanel, JPanel rookPanel) {
        kingPanel.setLocation(2 * 70 + 10, kingPanel.getY());
        rookPanel.setLocation(3 * 70 + 10, rookPanel.getY());
    }

    private void shortCastling(JPanel kingPanel, JPanel rookPanel) {
        kingPanel.setLocation(6 * 70 + 10, kingPanel.getY());
        rookPanel.setLocation(5 * 70 + 10, rookPanel.getY());
    }

    private void exceptionHandler(Square originSquare, Square destinationSquare, Exception exceptionMessage) {
        Piece movingPiece = originSquare.getSquarePiece();
        Piece pieceAtDestination = destinationSquare.getSquarePiece();
        Color chosenPieceColor = movingPiece.getPieceColor();

        if (Game.current_turn != chosenPieceColor) {
            MessagesForUsers.createMessage2(); // trying to move another players piece
        } else if (pieceAtDestination != null && pieceAtDestination.getPieceColor() == Game.current_turn) {
            MessagesForUsers.createMessage3(); // trying to take own piece
        } else if (!movingPiece.isAbleToMove(destinationSquare)) {
            MessagesForUsers.createMessage4(); // illegal move
        } else
            MessagesForUsers.createMessage5(); //System.err.println("exceptionHandler unhandled: " + exceptionMessage);
    }
    private void enPassant(Square selectedSquare, Square destinationSquare) {
        try {
            if (!Chessboard.EnPassant.enPassantMove(selectedSquare, destinationSquare)) {
                Chessboard.EnPassant.makeNull();
                if (Chessboard.promotedSquare != null)
                    Chessboard.promotedSquare.enPassantSquareFlag = false;
            }

            Chessboard.EnPassant.enPassantMove(selectedSquare, destinationSquare);
            Chessboard.tryMoveChecker(selectedSquare, destinationSquare);

            if (Chessboard.EnPassant.getEnPassantSquare() != null || Chessboard.EnPassant.getEnPassantPawn() != null) {
                if (Chessboard.EnPassant.enPassantMove(selectedSquare, destinationSquare)) {
                    JPanel enPassantPanel = Chessboard.EnPassant.getEnPassantPawn().panel;
                    if (selectedSquare.getSquarePiece().getPieceColor() == Color.WHITE) {
                        Chessboard.board[Chessboard.EnPassant.getEnPassantPawn().getxPieceCoordinate()]
                                [Chessboard.EnPassant.getEnPassantPawn().getyPieceCoordinate()].setSquarePiece(null);
                        myLayeredPane.remove(enPassantPanel);

                        capturedBlackFigures++;
                        if (capturedBlackFigures <= 8)
                            enPassantPanel.setLocation(10 + 70 * (capturedBlackFigures - 1), 10);
                        else
                            enPassantPanel.setLocation(10 + 70 * (capturedBlackFigures - 9), 80);
                        capturedBlack.add(enPassantPanel);

                        Chessboard.EnPassant.makeNull();
                    } else {
                        myLayeredPane.remove(enPassantPanel);

                        capturedWhiteFigures++;
                        if (capturedWhiteFigures <= 8)
                            enPassantPanel.setLocation(10 + 70 * (capturedWhiteFigures - 1), 10);
                        else
                            enPassantPanel.setLocation(10 + 70 * (capturedWhiteFigures - 9), 80);
                        capturedWhite.add(enPassantPanel);

                        Chessboard.EnPassant.makeNull();
                    }
                }
            }
        }  catch (Exception ez) {
        exceptionHandler(selectedSquare, destinationSquare, ez);
        }
    }
}



