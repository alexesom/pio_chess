package ChessInterface;
import Pieces.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Adapter extends MouseAdapter {
    private JLayeredPane myLayeredPane;
    private JPanel clickedPanel;
    private JPanel disappearPanel;
    private Point clickPoint;
    boolean squareWasEmpty = false;
//    private Point destinationPoint = new Point();

    public Adapter(JLayeredPane layer) {
        myLayeredPane = layer;
    }

    /*
    komentarze po polsku dodane tymczasowo do komunikacji
    komentarze po angielsku docelowo są finalne
    */
    @Override
    public void mousePressed(MouseEvent e) {
        clickPoint = e.getPoint(); // klikasz myszką i bierze to ten punkt
        if (clickedPanel != null) { // jezeli to jest miejsce bez panela i panel jest zaznaczony to :
            //gdzies trzeba dodac warunek zeby nie dalo sie ruszyc na to samo pole
            try {

                disappearPanel = (JPanel) myLayeredPane.getComponentAt(clickPoint);
            }
            catch (ClassCastException ex) { // the clicked square didn't have another panel on it
                squareWasEmpty = true;

                // uses the fact that getX/getY returns an integer to round the coordinates
                clickPoint.x = e.getX() / 70 * 70 + 10;
                clickPoint.y = e.getY() / 70 * 70 + 10;

                // find the Piece that was selected to move and the Square that was selected as its destination
                Piece selectedPiece = getPieceAtCoordinates(clickedPanel.getX(), clickedPanel.getY());
                Square selectedSquare = getSquareAtCoordinates(clickedPanel.getX(), clickedPanel.getY());
                Square destinationSquare = getSquareAtCoordinates(clickPoint.x, clickPoint.y);


                // move both Piece and the Panel if the move is legal
                if (selectedPiece.isAbleToMove(destinationSquare)) {
                    selectedSquare.move(destinationSquare);
                    moveSelectedPanelTo(clickPoint); // przesun panel do tego punktu
                    // pass the turn to the next player
                    Game.nextTurn();
                } else {
                    System.out.println("Illegal move!");
                }
            }

            if (!squareWasEmpty) { // a square containing a different panel was clicked
                clickPoint.x = e.getX() / 70 * 70 + 10;
                clickPoint.y = e.getY() / 70 * 70 + 10;
                //robię dwa razy clickPoint ale z jakiegoś powodu nie działa odpowiednio jeśli dam to w try {}
                //dodałam to tutaj bo potrzebuję koordynatów docelowego pola


                // find the Piece that was selected to move and the Square that was selected as its destination
                Piece selectedPiece = getPieceAtCoordinates(clickedPanel.getX(), clickedPanel.getY());
                Square selectedSquare = getSquareAtCoordinates(clickedPanel.getX(), clickedPanel.getY());
                Square destinationSquare = getSquareAtCoordinates(clickPoint.x, clickPoint.y);

                // move both Piece and the Panel if the move is legal
                if (selectedPiece.isAbleToMove(destinationSquare)) {
                    selectedSquare.move(destinationSquare);
                    clickedPanel.setLocation(disappearPanel.getX(), disappearPanel.getY());

                    // get rid of the taken piece
                    disappearPanel.setLocation(500, 500);
                    // z jakiegos powodu disappearPanel nie znika jak najedziesz na niego inną figurą
                    // musimy poprawić logikę zbijania
                    disappearPanel.removeMouseListener(this);
                    //mouse listener też nie wygląda na to że się usuwa

                    // pass the turn to the next player
                    Game.nextTurn();
                } else {
                    System.out.println("Illegal move!");
                }
            }
            clickedPanel = null; // ustaw ze teraz bedzie wybierac ponownie panel do przesunięcia
            clickPoint = null;  // i punkt w który chcesz przesunąć też
            squareWasEmpty = false;
        } else {
            try {
                clickedPanel = (JPanel) myLayeredPane.getComponentAt(e.getPoint());  //
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
    private Pieces.Square getSquareAtCoordinates(int x, int y) {
        int sqx = (x - 10) / 70;
        int sqy = (500 - y) / 70;
        return Chessboard.board[sqx][sqy];
    }

    /*
    returns the Piece at given panel coordinates
     */
    private Pieces.Piece getPieceAtCoordinates(int x, int y) {
        int sqx = (x - 10) / 70;
        int sqy = (500 - y) / 70;
        return Chessboard.board[sqx][sqy].getSquarePiece();
    }
}
