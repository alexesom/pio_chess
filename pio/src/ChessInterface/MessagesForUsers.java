package ChessInterface;

import javax.swing.*;

public class MessagesForUsers {
    private static ImageIcon imageIcon1 = new ImageIcon("pio/message1.gif");
    private static JLabel message1 = new JLabel(imageIcon1);
    private static ImageIcon imageIcon2 = new ImageIcon("pio/message2.gif");
    private static JLabel message2 = new JLabel(imageIcon2);
    private static ImageIcon imageIcon3 = new ImageIcon("pio/message3.gif");
    private static JLabel message3 = new JLabel(imageIcon3);
    private static ImageIcon imageIcon4 = new ImageIcon("pio/message4.gif");
    private static JLabel message4 = new JLabel(imageIcon4);
    private static int delay = 1600; // Delay in milliseconds
    public static JPanel messagePanel = new JPanel();

    private static void createMessagePanel() {
        messagePanel.setOpaque(false);
        messagePanel.setVisible(false);
    }

    public static void createMessage(JLabel messageName, int xPosition, int yPosition) {
        createMessagePanel();

        messagePanel.setBounds(xPosition, yPosition, 500, 200);
        messagePanel.add(messageName);
        messagePanel.setVisible(true);

        Timer timer = new Timer(delay, e1 -> {
            messagePanel.setVisible(false);
            messagePanel.remove(messageName);
        });
        timer.start();
        timer.setRepeats(false);
    }

    public static void createMessage1() {
        createMessage(message1, 450, 600);
    }

    public static void createMessage2() {
        createMessage(message2, 550, 550);
    }

    public static void createMessage3() {
        createMessage(message3, 500, 600);
    }

    public static void createMessage4() {
        createMessage(message4, 500, 600);
    }
}
