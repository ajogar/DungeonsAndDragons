package entities;

import util.LocationBuilder;

public class Player {
    private String dungeon, tile, startingDungeon;
    private boolean arrow, rope = true;

    public Player(int x, int y, int w, int z) {
        dungeon = LocationBuilder.buildLocation(x, y);
        startingDungeon = dungeon;
        tile = LocationBuilder.buildLocation(w, z);
    }

    public String getDungeon() {
        return dungeon;
    }

    public String getTile() {
        return tile;
    }

    public void setTile(int x, int y) {
        tile = LocationBuilder.buildLocation(x, y);
    }

    public void setDungeon(int x, int y) {
        dungeon = LocationBuilder.buildLocation(x, y);
    }

    public void setArrow(boolean b) {
        arrow = b;
    }

    public void setRope(boolean b) {
        rope = b;
    }

    public boolean hasArrow() {
        return arrow;
    }

    public boolean hasRope() {
        return rope;
    }

    public String getStartingDungeon() {
        return startingDungeon;
    }

}
