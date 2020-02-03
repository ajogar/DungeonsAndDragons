package util;

import dungeons.*;
import entities.Player;
import gui.*;
import tiles.Tile;

import javax.swing.*;
import java.util.Arrays;
import java.util.Random;

import static java.awt.event.KeyEvent.VK_T;

public class Game {
    private Dungeon[][] gameBoard = new Dungeon[10][10];
    private boolean arrow, dragon;
    private int fight = 0, trap = 0, rope = 0;
    Player player;
    char direction;

    public Game() {
        initBoard();
    }

    private void initBoard() {
        Random rand = new Random();
        for (int i = 0; i < 10; i++) {
            //debug System.out.println();
            for (int j = 0; j < 10; j++) {
                int r = rand.nextInt(5);

                gameBoard[i][j] = new Dungeon(i, j);

                if ((i != 0 && j != 0) && (i != 9 && j != 9)) {
                    if (r == 0  && !arrow) {
                        gameBoard[i][j] = new ArrowDungeon(i, j);
                        arrow = true;
                        //debug System.out.print("a ");
                    } else if (r == 1 && !dragon) {
                        gameBoard[i][j] = new DragonDungeon(i, j);
                        dragon = true;
                        //debug System.out.print("d ");
                    } else if (r == 2 && fight < 5) {
                        gameBoard[i][j] = new FightDungeon(i, j);
                        fight++;
                        //debug System.out.print("f ");
                    } else if (r == 3 && trap < 10) {
                        gameBoard[i][j] = new TrapDungeon(i, j);
                        trap++;
                        //debug System.out.print("t ");
                    } else if (r == 4 && rope < 1) {
                        gameBoard[i][j] = new RopeDungeon(i, j);
                        rope++;
                        //debug System.out.print("r ");
                    }
                }
            }
        }
        scatterPlayer();

        if (checkBoard()) {
            resetValues();
            initBoard();
        } else {
            //debug
            printBoard();
            printDungeon();
        }


    }

    private void printDungeon() {
        String[] args = player.getDungeon().split(":");
        int pDX = Integer.parseInt(args[0]);
        int pDY = Integer.parseInt(args[1]);

        Tile[][] tiles = gameBoard[pDX][pDY].getTileset();

        System.out.println(gameBoard[pDX][pDY].getLocation());
        System.out.println(gameBoard[pDX][pDY].getType());
        for (int i = 0; i < 10; i++) {
            System.out.println();
            for (int j = 0; j < 10; j++) {
                if (tiles[i][j].getType().equalsIgnoreCase("warp")) {
                    System.out.print("w ");
                } else if (tiles[i][j].getLocation().equalsIgnoreCase(player.getTile())) {
                    System.out.print("p ");
                } else {
                    System.out.print("n ");
                }
            }
        }
    }

    private void movePlayer(String d) {
        String[] args = player.getDungeon().split(":");
        int pDX = Integer.parseInt(args[0]);
        int pDY = Integer.parseInt(args[1]);

        Tile[][] tiles = gameBoard[pDX][pDY].getTileset();

        args = player.getTile().split(":");
        int pTX = Integer.parseInt(args[0]);
        int pTY = Integer.parseInt(args[1]);

        if (d.equalsIgnoreCase("up")) {
            if (pTX == 0) {
                if (tiles[pTX][pTY].getType().equalsIgnoreCase("warp")) {
                    if (pDX == 0) {
                        player.setDungeon(9, pDY);
                        DungeonWindow.updateDungeon(9, pDY, new ImageIcon("C:\\Users\\cenafan1211\\Desktop\\dnd\\player back.png"));
                    } else {
                        player.setDungeon(pDX - 1, pDY);
                        DungeonWindow.updateDungeon(pDX - 1, pDY, new ImageIcon("C:\\Users\\cenafan1211\\Desktop\\dnd\\player back.png"));
                    }
                    player.setTile(8, pTY);
                    DungeonWindow.updatePlayer(8, pTY, new ImageIcon("C:\\Users\\cenafan1211\\Desktop\\dnd\\player back.png"));
                    System.out.println();
                    printBoard();
                    checkSurroundings();
                }
            } else {
                player.setTile(pTX - 1, pTY);
                pTX -= 1;
                DungeonWindow.updatePlayer(pTX, pTY, new ImageIcon("C:\\Users\\cenafan1211\\Desktop\\dnd\\player back.png"));
            }
        } else if (d.equalsIgnoreCase("down")) {
            if (pTX == 9) {
                if (tiles[pTX][pTY].getType().equalsIgnoreCase("warp")) {
                    if (pDX == 9) {
                        player.setDungeon(0, pDY);
                        DungeonWindow.updateDungeon(0, pDY, new ImageIcon("C:\\Users\\cenafan1211\\Desktop\\dnd\\player front.png"));
                    } else {
                        player.setDungeon(pDX + 1, pDY);
                        DungeonWindow.updateDungeon(pDX + 1, pDY, new ImageIcon("C:\\Users\\cenafan1211\\Desktop\\dnd\\player front.png"));
                    }
                    player.setTile(1, pTY);
                    DungeonWindow.updatePlayer(1, pTY, new ImageIcon("C:\\Users\\cenafan1211\\Desktop\\dnd\\player front.png"));
                    System.out.println();
                    printBoard();
                    checkSurroundings();
                }
            } else {
                player.setTile(pTX + 1, pTY);
                pTX += 1;
                DungeonWindow.updatePlayer(pTX, pTY, new ImageIcon("C:\\Users\\cenafan1211\\Desktop\\dnd\\player front.png"));
            }
        } else if (d.equalsIgnoreCase("right")) {
            if (pTY == 9) {
                if (tiles[pTX][pTY].getType().equalsIgnoreCase("warp")) {
                    if (pDY == 9) {
                        player.setDungeon(pDX, 0);
                        DungeonWindow.updateDungeon(pDX, 0, new ImageIcon("C:\\Users\\cenafan1211\\Desktop\\dnd\\player right.png"));
                    } else {
                        player.setDungeon(pDX, pDY + 1);
                        DungeonWindow.updateDungeon(pDX, pDY + 1, new ImageIcon("C:\\Users\\cenafan1211\\Desktop\\dnd\\player right.png"));
                    }
                    player.setTile(pTX, 1);
                    DungeonWindow.updatePlayer(pTX, 1, new ImageIcon("C:\\Users\\cenafan1211\\Desktop\\dnd\\player right.png"));
                    System.out.println();
                    printBoard();
                    checkSurroundings();
                }
            } else {
                player.setTile(pTX, pTY + 1);
                pTY += 1;
                DungeonWindow.updatePlayer(pTX, pTY, new ImageIcon("C:\\Users\\cenafan1211\\Desktop\\dnd\\player right.png"));
            }
        } else if (d.equalsIgnoreCase("left")) {
            if (pTY == 0) {
                if (tiles[pTX][pTY].getType().equalsIgnoreCase("warp")) {
                    if (tiles[pTX][pTY].getType().equalsIgnoreCase("warp")) {
                        if (pDY == 0) {
                            player.setDungeon(pDX, 9);
                            DungeonWindow.updateDungeon(pDX, 9, new ImageIcon("C:\\Users\\cenafan1211\\Desktop\\dnd\\player left.png"));
                        } else {
                            player.setDungeon(pDX, pDY - 1);
                            DungeonWindow.updateDungeon(pDX, pDY - 1, new ImageIcon("C:\\Users\\cenafan1211\\Desktop\\dnd\\player right.png"));
                        }
                        player.setTile(pTX, 9);
                        DungeonWindow.updatePlayer(pTX, 9, new ImageIcon("C:\\Users\\cenafan1211\\Desktop\\dnd\\player right.png"));
                        System.out.println();
                        printBoard();
                        checkSurroundings();
                    }
                }
            } else {
                player.setTile(pTX, pTY - 1);
                pTY -= 1;
                DungeonWindow.updatePlayer(pTX, pTY, new ImageIcon("C:\\Users\\cenafan1211\\Desktop\\dnd\\player left.png"));
            }
        }

        MapWindow.setEnable(pDX, pDY);
        System.out.println();
        checkItem();
        printDungeon();
    }

    private void printBoard() {
        StringBuilder debug = new StringBuilder();
        for (int i = 0; i < 10; i++) {
            debug.append("\n");
            for (int j = 0; j < 10; j++) {
                String d;
                if (gameBoard[i][j].isHasArrow()) {
                    d = "a";
                } else if (gameBoard[i][j].isHasTrap()) {
                    d = "t";
                } else if (gameBoard[i][j].isHasDragon()) {
                    d = "d";
                } else if (gameBoard[i][j].isHasFight()) {
                    d = "f";
                } else if (gameBoard[i][j].isHasRope()) {
                    d = "r";
                } else {
                    d = "n";
                }
                if (player.getDungeon().equalsIgnoreCase(LocationBuilder.buildLocation(i, j))) {
                    d = d.toUpperCase();
                }

                debug.append(d).append(" ");
            }
        }
        System.out.println(debug.toString());
    }

    private void resetValues() {
        arrow = false;
        dragon = false;
        trap = 0;
        rope = 0;
        fight = 0;
    }

    private boolean checkBoard() {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if (gameBoard[i][j].isHasArrow() || gameBoard[i][j].isHasDragon()) {
                    if (gameBoard[i + 1][j].isHasTrap() && gameBoard[i - 1][j].isHasTrap()
                            && gameBoard[i][j + 1].isHasTrap() && gameBoard[i][j - 1].isHasTrap()) {
                        return true;
                    }
                } else if (gameBoard[i][j].isHasArrow() && !gameBoard[i][j].isHasDragon()) {
                    if ((gameBoard[i + 1][j].isHasDragon() || gameBoard[i - 1][j].isHasDragon()
                            || gameBoard[i][j + 1].isHasDragon() || gameBoard[i][j - 1].isHasDragon())) {
                        return true;
                    }
                } else if (gameBoard[i][j].isHasTrap()) {
                    if (gameBoard[i+1][j].isHasTrap() || gameBoard[i-1][j].isHasTrap()
                            || gameBoard[i][j+1].isHasTrap() || gameBoard[i][j-1].isHasTrap()) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private void scatterPlayer() {
        int x = new Random().nextInt(10);
        int y = new Random().nextInt(10);

        if (!gameBoard[x][y].getType().equalsIgnoreCase("generic")) {
            scatterPlayer();
        } else {
            player = new Player(x, y, 4, 4);
        }
    }

    private void throwBall() {
        if (!player.hasArrow()) {
            PopupBuilder popupBuilder = new PopupBuilder("You do not have a ball to throw! Find the master ball in the dungeon.");
        } else {
            JOptionPane.showConfirmDialog(null, "Are you sure you want to throw the ball in this direction?");
            int x = Integer.parseInt(player.getDungeon().split(":")[0]);
            int y = Integer.parseInt(player.getDungeon().split(":")[1]);

            switch (direction) {
                case 'u':
                    if (gameBoard[x-1][y].isHasDragon()) {
                        PopupBuilder popupBuilder = new PopupBuilder("Congratulations! You caught Girantina!");
                        MapWindow.updateMap(x-1,y,"D");
                        DungeonWindow.close();
                    } else {
                        PopupBuilder popupBuilder = new PopupBuilder("That was the wrong dungeon! Game Over!");
                        DungeonWindow.close();
                    }
                    break;
                case 'd':
                    if (gameBoard[x+1][y].isHasDragon()) {
                        PopupBuilder popupBuilder = new PopupBuilder("Congratulations! You caught Girantina!");
                        MapWindow.updateMap(x+1,y,"D");
                        DungeonWindow.close();
                    } else {
                        PopupBuilder popupBuilder = new PopupBuilder("That was the wrong dungeon! Game Over!");
                        DungeonWindow.close();
                    }
                case 'l':
                    if (gameBoard[x][y-1].isHasDragon()) {
                        PopupBuilder popupBuilder = new PopupBuilder("Congratulations! You caught Girantina!");
                        MapWindow.updateMap(x,y-1,"D");
                        DungeonWindow.close();
                    } else {
                        PopupBuilder popupBuilder = new PopupBuilder("That was the wrong dungeon! Game Over!");
                        DungeonWindow.close();
                    }
                case 'r':
                    if (gameBoard[x][y+1].isHasDragon()) {
                        PopupBuilder popupBuilder = new PopupBuilder("Congratulations! You caught Girantina!");
                        MapWindow.updateMap(x,y+1,"D");
                        DungeonWindow.close();
                    } else {
                        PopupBuilder popupBuilder = new PopupBuilder("That was the wrong dungeon! Game Over!");
                        DungeonWindow.close();
                    }
            }
        }
    }

    public void processKey(int k) {
        switch (k) {
            case 0x26:
                movePlayer("up");
                direction = 'u';
                break;
            case 0x28:
                movePlayer("down");
                direction = 'd';
                break;
            case 0x27:
                movePlayer("right");
                direction = 'r';
                break;
            case 0x25:
                movePlayer("left");
                direction = 'l';
                break;
            case VK_T:
                throwBall();
        }
    }

    private void checkItem() {
        int x = Integer.parseInt(player.getDungeon().split(":")[0]);
        int y = Integer.parseInt(player.getDungeon().split(":")[1]);
        if (gameBoard[x][y].isHasArrow()) {
            MapWindow.updateMap(x, y, "A");
            if (player.getTile().equalsIgnoreCase("4:4")) {
                player.setArrow(true);
                gameBoard[x][y].setHasArrow(false);
            }
        } else if (gameBoard[x][y].isHasRope()) {
            MapWindow.updateMap(x, y, "R");
            if (player.getTile().equalsIgnoreCase("4:4")) {
                player.setRope(true);
                gameBoard[x][y].setHasRope(false);
            }
        } else {
            if (gameBoard[x][y].isHasFight()) {
                MapWindow.updateMap(x, y, "F");
                FightWindow fightWindow = new FightWindow();
                gameBoard[x][y].setHasFight(false);
            } else if (gameBoard[x][y].isHasTrap()) {
                if (player.hasRope()) {
                    MapWindow.updateMap(x, y, "T");
                    int sX = Integer.parseInt(player.getStartingDungeon().split(":")[0]);
                    int sY = Integer.parseInt(player.getStartingDungeon().split(":")[1]);
                    MapWindow.updateMap(sX, sY);
                    System.out.println("saved");
                    PopupBuilder popupBuilder = new PopupBuilder("You fell into a trap! Your rope saved you and you were transported to the start.");
                    player.setDungeon(sX, sY);
                    player.setTile(4, 4);
                    DungeonWindow.updatePlayer(4, 4, new ImageIcon("C:\\Users\\cenafan1211\\Desktop\\dnd\\player front.png"));
                    player.setRope(false);
                } else {
                    MapWindow.updateMap(x, y, "T");
                    System.out.println("ded homie");
                    PopupBuilder popupBuilder = new PopupBuilder("You fell into a trap! You didn't have a rope, so you died. Game over!");
                    DungeonWindow.close();
                }
            } else if (gameBoard[x][y].isHasDragon()) {
                MapWindow.updateMap(x, y, "D");
                PopupBuilder popupBuilder = new PopupBuilder("You walked into the dungeon with Girantina and he killed you. Game over!");
                DungeonWindow.close();
            }
        }
        if (getDungeon(x, y).getType().equalsIgnoreCase("generic")) {
            MapWindow.updateMap(x, y, "N");
        }
    }

    public Player getPlayer() {
        return player;
    }

    public Dungeon getDungeon(int x, int y) {
        return gameBoard[x][y];
    }

    private void checkSurroundings() {
        String[] args = player.getDungeon().split(":");
        int pDX = Integer.parseInt(args[0]);
        int pDY = Integer.parseInt(args[1]);

        if ((pDX != 0 && pDY != 0) && (pDX != 9 && pDY != 9)) {
            if (gameBoard[pDX-1][pDY].isHasArrow() || gameBoard[pDX+1][pDY].isHasArrow() || gameBoard[pDX][pDY+1].isHasArrow() || gameBoard[pDX][pDY-1].isHasArrow()) {
                DialogueBox.updateMessage("There is a master ball nearby!");
            } else if (gameBoard[pDX-1][pDY].isHasDragon() || gameBoard[pDX+1][pDY].isHasDragon() || gameBoard[pDX][pDY+1].isHasDragon() || gameBoard[pDX][pDY-1].isHasDragon()) {
                DialogueBox.updateMessage("Girantina is nearby!");
            } else if (gameBoard[pDX-1][pDY].isHasTrap() || gameBoard[pDX+1][pDY].isHasTrap() || gameBoard[pDX][pDY+1].isHasTrap() || gameBoard[pDX][pDY-1].isHasTrap()) {
                DialogueBox.updateMessage("Careful! There is a trap nearby!");
            } else {
                DialogueBox.updateMessage("Smells like fish.");
            }
        } else if (pDX == 0 && pDY != 0) {
            if (gameBoard[pDX+1][pDY].isHasArrow() || gameBoard[pDX][pDY+1].isHasArrow() || gameBoard[pDX][pDY-1].isHasArrow()) {
                DialogueBox.updateMessage("There is a master ball nearby!");
            } else if (gameBoard[pDX+1][pDY].isHasDragon() || gameBoard[pDX][pDY+1].isHasDragon() || gameBoard[pDX][pDY-1].isHasDragon()) {
                DialogueBox.updateMessage("Girantina is nearby!");
            } else if (gameBoard[pDX+1][pDY].isHasTrap() || gameBoard[pDX][pDY+1].isHasTrap() || gameBoard[pDX][pDY-1].isHasTrap()) {
                DialogueBox.updateMessage("Careful! There is a trap nearby!");
            } else {
                DialogueBox.updateMessage("Smells like fish.");
            }
        } else if (pDX != 0 && pDY == 0) {
            if (gameBoard[pDX-1][pDY].isHasArrow() || gameBoard[pDX+1][pDY].isHasArrow() || gameBoard[pDX][pDY+1].isHasArrow()) {
                DialogueBox.updateMessage("There is a master ball nearby!");
            } else if (gameBoard[pDX-1][pDY].isHasDragon() || gameBoard[pDX+1][pDY].isHasDragon() || gameBoard[pDX][pDY+1].isHasDragon()) {
                DialogueBox.updateMessage("Girantina is nearby!");
            } else if (gameBoard[pDX-1][pDY].isHasTrap() || gameBoard[pDX+1][pDY].isHasTrap() || gameBoard[pDX][pDY+1].isHasTrap()) {
                DialogueBox.updateMessage("Careful! There is a trap nearby!");
            } else {
                DialogueBox.updateMessage("Smells like fish.");
            }
        } else if (pDX == 9) {
            if (gameBoard[pDX-1][pDY].isHasArrow() || gameBoard[pDX][pDY+1].isHasArrow() || gameBoard[pDX][pDY-1].isHasArrow()) {
                DialogueBox.updateMessage("There is a master ball nearby!");
            } else if (gameBoard[pDX-1][pDY].isHasDragon() || gameBoard[pDX][pDY+1].isHasDragon() || gameBoard[pDX][pDY-1].isHasDragon()) {
                DialogueBox.updateMessage("Girantina is nearby!");
            } else if (gameBoard[pDX-1][pDY].isHasTrap() || gameBoard[pDX][pDY+1].isHasTrap() || gameBoard[pDX][pDY-1].isHasTrap()) {
                DialogueBox.updateMessage("Careful! There is a trap nearby!");
            } else {
                DialogueBox.updateMessage("Smells like fish.");
            }
        } else if (pDY == 9) {
            if (gameBoard[pDX-1][pDY].isHasArrow() || gameBoard[pDX+1][pDY].isHasArrow() ||  gameBoard[pDX][pDY-1].isHasArrow()) {
                DialogueBox.updateMessage("There is a master ball nearby!");
            } else if (gameBoard[pDX-1][pDY].isHasDragon() || gameBoard[pDX+1][pDY].isHasDragon() || gameBoard[pDX][pDY-1].isHasDragon()) {
                DialogueBox.updateMessage("Girantina is nearby!");
            } else if (gameBoard[pDX-1][pDY].isHasTrap() || gameBoard[pDX+1][pDY].isHasTrap() || gameBoard[pDX][pDY-1].isHasTrap()) {
                DialogueBox.updateMessage("Careful! There is a trap nearby!");
            } else {
                DialogueBox.updateMessage("Smells like fish.");
            }
        }

    }
}
