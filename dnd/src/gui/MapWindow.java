package gui;

import entities.Player;
import util.Game;

import javax.swing.*;
import java.awt.*;

public class MapWindow {
    JFrame frame;
    JPanel panel;
    static JButton[][] buttons = new JButton[10][10];

    public MapWindow(Game game) {
        frame = new JFrame("Map");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        panel = new JPanel();
        panel.setLayout(new GridLayout(10, 10, 1, 1));

        initButtons(game);

        frame.setLocation(new Point(1050, 100));

        frame.setContentPane(panel);
        frame.pack();
        frame.setVisible(true);
    }

    private void initButtons(Game game) {
        Player player = game.getPlayer();
        String startingDungeon = player.getStartingDungeon();
        int x = Integer.parseInt(startingDungeon.split(":")[0]);
        int y = Integer.parseInt(startingDungeon.split(":")[1]);

        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                buttons[i][j] = new JButton();
                if (startingDungeon.equalsIgnoreCase(i + ":" + j)) {
                    buttons[i][j].setText("N");
                }
                buttons[i][j].setPreferredSize(new Dimension(45, 45));
                buttons[i][j].setEnabled(false);
                panel.add(buttons[i][j]);
            }
        }
        buttons[x][y].setEnabled(true);
    }

    public static void updateMap(int x, int y, String type) {
        buttons[x][y].setText(type);

        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                buttons[i][j].setEnabled(false);
            }
        }
        buttons[x][y].setEnabled(true);
    }

    public static void updateMap(int x, int y) {

        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                buttons[i][j].setEnabled(false);
            }
        }
        buttons[x][y].setEnabled(true);
    }

    public static void setEnable(int x, int y) {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                buttons[i][j].setEnabled(false);
            }
        }
        buttons[x][y].setEnabled(true);
    }
}
