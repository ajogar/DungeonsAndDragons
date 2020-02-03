package gui;

import dungeons.Dungeon;
import entities.Player;
import tiles.Tile;
import util.Game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;

public class DungeonWindow implements KeyListener {
    static JFrame frame;
    JPanel bg;
    static JLabel[][] background = new JLabel[10][10];
    JLayeredPane pane;

    MapWindow map = new MapWindow(game);
    DialogueBox dialogueBox = new DialogueBox();
    static Game game = new Game();

    public DungeonWindow() {
        frame = new JFrame("Dungeons and Dragons");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.addKeyListener(this);

        bg = new JPanel();
        bg.setBackground(Color.WHITE);
        bg.setLayout(new GridLayout(10, 10, 0, 0));

        pane = new JLayeredPane();

        initBackground();

        frame.setLocation(new Point(400, 100));

        frame.setContentPane(bg);
        frame.pack();
        frame.setVisible(true);
    }

    private void initBackground() {
        Player player = game.getPlayer();
        int x = Integer.parseInt(player.getStartingDungeon().split(":")[0]);
        int y = Integer.parseInt(player.getStartingDungeon().split(":")[1]);

        Dungeon dungeon = game.getDungeon(x, y);
        Tile[][] tiles = dungeon.getTileset();

        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                ImageIcon tile = tiles[i][j].getImage();
                background[i][j] = new JLabel(tile);

                if (i == 4 && j == 4) {
                    background[i][j].setIcon(new ImageIcon("C:\\Users\\cenafan1211\\Desktop\\dnd\\player front.png"));
                }
                bg.add(background[i][j]);
            }
        }
    }

    public static void updatePlayer(int pTX, int pTY, ImageIcon pDir) {
        Player player = game.getPlayer();
        int x = Integer.parseInt(player.getDungeon().split(":")[0]);
        int y = Integer.parseInt(player.getDungeon().split(":")[1]);
        Tile[][] tile = game.getDungeon(x, y).getTileset();

        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                ImageIcon art = tile[i][j].getImage();
                background[i][j].setIcon(art);

                if (i == 4 && j == 4) {
                    if (game.getDungeon(x, y).isHasArrow()) {
                        background[i][j].setIcon(new ImageIcon("C:\\Users\\cenafan1211\\Desktop\\dnd\\master balls.png"));
                    } else if (game.getDungeon(x, y).isHasRope()) {
                        background[i][j].setIcon(new ImageIcon("C:\\Users\\cenafan1211\\Desktop\\dnd\\rope.png"));
                    } else if (game.getDungeon(x, y).isHasDragon()) {
                        background[i][j].setIcon(new ImageIcon("C:\\Users\\cenafan1211\\Desktop\\dnd\\girantina.png"));
                    }
                }

                if (pTX == i && pTY == j) {
                    background[i][j].setIcon(pDir);
                }

            }
        }
    }

    public static void updateDungeon(int pDX, int pDY, ImageIcon pDir) {
        Dungeon dungeon = game.getDungeon(pDX, pDY);
        Tile[][] tileset = dungeon.getTileset();

        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                ImageIcon image = tileset[i][j].getImage();
                background[i][j].setIcon(image);

                if (game.getPlayer().getTile().equalsIgnoreCase(i + ":" + j)) {
                    background[i][j].setIcon(pDir);
                }
                if (i == 4 && j == 4) {
                    if (game.getDungeon(pDX, pDY).isHasArrow()) {
                        background[i][j].setIcon(new ImageIcon("C:\\Users\\cenafan1211\\Desktop\\dnd\\master balls.png"));
                    } else if (game.getDungeon(pDX, pDY).isHasRope() && (!game.getPlayer().hasRope())) {
                        background[i][j].setIcon(new ImageIcon("C:\\Users\\cenafan1211\\Desktop\\dnd\\rope.png"));
                    } else if (game.getDungeon(pDX, pDY).isHasDragon()) {
                        background[i][j].setIcon(new ImageIcon("C:\\Users\\cenafan1211\\Desktop\\dnd\\girantina.png"));
                    }
                }
            }
        }
    }

    public static void close() {
        frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
    }

    @Override
    public void keyTyped(KeyEvent e) {


    }

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        game.processKey(keyCode);
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
