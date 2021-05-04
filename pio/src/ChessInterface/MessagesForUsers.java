package ChessInterface;

import javax.swing.*;

public class MessagesForUsers {
    private static ImageIcon imageIcon1 = new ImageIcon("pio/message1.png");
    private static JLabel message1 = new JLabel(imageIcon1);
    private static ImageIcon imageIcon2 = new ImageIcon("pio/message2.png");
    private static JLabel message2 = new JLabel(imageIcon2);
    private static ImageIcon imageIcon3 = new ImageIcon("pio/message3.png");
    private static JLabel message3 = new JLabel(imageIcon3);
    private static ImageIcon imageIcon4 = new ImageIcon("pio/message4.png");
    private static JLabel message4 = new JLabel(imageIcon4);
    private static int delay = 1500; // Delay in milliseconds
    public static JPanel messagePanel = new JPanel();

    private static void createMessagePanel() {
        messagePanel.setOpaque(false);
        messagePanel.setVisible(false);
        messagePanel.setBounds(480, 640, 500, 200);
    }

    public static void createMessage(JLabel messageName) {
        createMessagePanel();

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
        createMessage(message1);
    }

    public static void createMessage2() {
        createMessage(message2);
    }

    public static void createMessage3() {
        createMessage(message3);
    }

    public static void createMessage4() {
        createMessage(message4);
    }
}
