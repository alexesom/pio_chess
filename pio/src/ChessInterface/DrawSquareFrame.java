package ChessInterface;

import javax.swing.*;
import java.awt.*;

public class DrawSquareFrame extends JPanel {
    Graphics2D g;
    int lineSize = 40;
    int position = lineSize / 2;

    public void paint(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(new Color(91, 189, 116));
        g2.setStroke(new BasicStroke(lineSize));
        g2.drawRect(position, position,70 - lineSize,70 - lineSize);
    }
}