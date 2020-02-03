package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

public class FightWindow implements ActionListener {
    JFrame frame;
    JPanel pane, buttons, image;
    JLabel zubat;
    JButton fight, run;

    public FightWindow() {
        frame = new JFrame("Battle!");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        pane = new JPanel();
        pane.setLayout(new BoxLayout(pane, BoxLayout.PAGE_AXIS));

        buttons = new JPanel();
        buttons.setLayout(new FlowLayout());

        fight = new JButton("FIGHT");
        fight.setActionCommand("fight");
        fight.addActionListener(this);
        buttons.add(fight);

        run = new JButton("RUN");
        run.setActionCommand("run");
        run.addActionListener(this);
        buttons.add(run);

        image = new JPanel();
        image.setLayout(new FlowLayout());

        zubat = new JLabel();
        zubat.setIcon(new ImageIcon("C:\\Users\\cenafan1211\\Desktop\\dnd\\zubat.png"));
        image.add(zubat);

        pane.add(buttons);
        pane.add(image);

        frame.setContentPane(pane);
        frame.setLocation(new Point(500, 500));
        frame.pack();
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equalsIgnoreCase("fight")) {
            PopupBuilder popupBuilder = new PopupBuilder("You killed the Zubat. Good job.");
            frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
        } else {
            PopupBuilder popupBuilder = new PopupBuilder("You ran from a simple fight. Are you proud?");
            frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
        }
    }
}
