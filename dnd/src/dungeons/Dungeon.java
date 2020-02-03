package dungeons;

import tiles.Tile;
import tiles.WarpTile;
import util.GlobalVars;
import util.LocationBuilder;

import javax.swing.*;

public class Dungeon {
    String location;
    String type;
    Tile[][] tileset = new Tile[10][10];

    protected boolean
            hasArrow = false,
            hasDragon = false,
            hasTrap = false,
            hasFight = false,
            hasRope = false;

    public Dungeon(int x, int y) {
        location = LocationBuilder.buildLocation(x, y);
        type = "generic";
        initDungeon();
    }

    public String getLocation() {
        return location;
    }

    public String getType() {
        return type;
    }

    private void initDungeon() {
        //System.out.println();
        //System.out.println(getLocation());
        for (int i = 0; i < 10; i++) {
            //System.out.println();
            for (int j = 0; j < 10; j++) {
                if(i == 0 && (j == 4 || j == 5)) {
                    tileset[i][j] = new WarpTile(i, j, new ImageIcon(GlobalVars.tile1));
                    //System.out.print("u ");
                } else if (i == 9 && (j == 4 || j == 5)) {
                    tileset[i][j] = new WarpTile(i, j, new ImageIcon(GlobalVars.tile1));
                    //System.out.print("d ");
                } else if (j == 0 && (i == 4 || i == 5)) {
                    tileset[i][j] = new WarpTile(i, j, new ImageIcon(GlobalVars.tile1));
                    //System.out.print("l ");
                } else if (j == 9 && (i == 4 || i == 5)) {
                    tileset[i][j] = new WarpTile(i, j, new ImageIcon(GlobalVars.tile1));
                    //System.out.print("r ");
                } else {
                    tileset[i][j] = new Tile(i, j, new ImageIcon(GlobalVars.tile1));
                    //System.out.print("n ");
                }
            }
        }
    }

    public boolean isHasArrow() {
        return hasArrow;
    }

    public boolean isHasDragon() {
        return hasDragon;
    }

    public boolean isHasTrap() {
        return hasTrap;
    }

    public boolean isHasFight() {
        return hasFight;
    }

    public boolean isHasRope() {
        return hasRope;
    }

    public Tile[][] getTileset() {
        return tileset;
    }

    public void setHasArrow(boolean hasArrow) {
        this.hasArrow = hasArrow;
    }

    public void setHasFight(boolean hasFight) {
        this.hasFight = hasFight;
    }

    public void setHasRope(boolean hasRope) {
        this.hasRope = hasRope;
    }
}
