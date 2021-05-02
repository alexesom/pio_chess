package ChessInterface;

import javax.swing.*;
import java.awt.*;

public class SquareBacklight extends JPanel {
    Graphics2D g;
    private int lineSize = 40;
    private int position = lineSize / 2;
    private Color backlightColor;

    public SquareBacklight(Color backlightColor) {
        this.backlightColor = backlightColor;
    }

    public void paint(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(backlightColor);
        g2.setStroke(new BasicStroke(lineSize));
        g2.drawRect(position, position,70 - lineSize,70 - lineSize);
    }
}