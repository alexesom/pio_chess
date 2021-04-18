package Pieces;

import javax.swing.*;
import java.awt.*;

public class Piece {
    public JPanel label1 = new JPanel();

    public Piece(int x, int y, Color color) {
        label1.setOpaque(true);
        label1.setBackground(color);
        label1.setBounds(x,y,50,50);
    }
}
