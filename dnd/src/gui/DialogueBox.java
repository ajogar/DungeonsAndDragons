package gui;

import javax.swing.*;
import java.awt.*;

public class DialogueBox {
    JFrame frame;
    JPanel pane;
    static JLabel messages;

    public DialogueBox() {
        frame = new JFrame("Messages");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        pane = new JPanel();

        frame.setPreferredSize(new Dimension(469, 180));
        frame.setLocation(new Point(1050, 593));

        messages = new JLabel("Welcome to Dungeons and Dragons!");
        pane.add(messages);

        frame.setContentPane(pane);
        frame.pack();
        frame.setVisible(true);
    }

    public static void updateMessage(String newMessage) {
        messages.setText(newMessage);
    }

}
