package Pieces;

import javax.swing.*;
import java.awt.*;

public class Piece {
    public Square buttonSquare;
    public JPanel label1 = new JPanel();

    public Piece() {

    }

    public Piece(int x, int y, Color color) {
        JLabel label = new JLabel(new Pieces.StretchIcon("pio_chess\\piecesIcons\\blackqueen.png"));
        label1.setOpaque(false);
        label1.setBounds(10,10,50,50);
        label.setPreferredSize(new Dimension(label1.getWidth(), label1.getHeight()));
        label1.add(label);
    }

    public byte move(Square destination) {

        return 0;
    }

    public void take(Piece piece) {

    }

    public Square getButtonSquare() {
        return buttonSquare;
    }

    public void setButtonSquare(Square buttonSquare) {
        this.buttonSquare = buttonSquare;
    }

    public boolean isAbleToMove(Square square) {

        return true;
    }
}
