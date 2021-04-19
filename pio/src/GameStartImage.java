import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class GameStartImage extends JPanel{
    private BufferedImage pict;

    public GameStartImage() {
        try {
            pict = ImageIO.read(new File("C:\\Users\\01159365\\IdeaProjects\\PIO\\pio_chess\\pio\\szachy.png"));
        } catch (IOException e) {
            System.err.println("Starting image error");
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.drawImage(pict, 0, 0, 1150, 800, this);
    }
}