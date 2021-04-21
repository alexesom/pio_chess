package ChessInterface;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UsernameBox implements ActionListener {
    private JTextField enterName = new JTextField(10);
    public JPanel usernamePanel = new JPanel();
    private JTextArea displayName = new JTextArea(1,13);
    private JButton button = new JButton("Accept");
    public String name;
    private int playerNumber;

    public void setPlayerNumber(int playerNumber) {
        this.playerNumber = playerNumber;
    }

    public int getPlayerNumber() {
        return playerNumber;
    }

    public JPanel createUserPanel(int x,int y,int w,int h) {
        usernamePanel.setBounds(x,y,w,h);
        usernamePanel.setBackground(Color.gray);

        usernamePanel.add(enterName);

        button.addActionListener(this);
        button.setBackground(new Color(115, 203, 98));

        usernamePanel.add(button);

        displayName.setFont(displayName.getFont().deriveFont(16.0F));
        displayName.setBackground(Color.gray);
        displayName.setLineWrap(true);
        displayName.setEditable(false);
        usernamePanel.add(displayName);

        return usernamePanel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source == button) {
            displayName.setText("");
            name = enterName.getText();

            displayName.setText("Player " + getPlayerNumber() + ": " + name);
            System.out.println(name + "f");

            name = name.trim();

            if (name.length() > 20) {
                name = name.substring(0,19);

            }
        }
    }
}