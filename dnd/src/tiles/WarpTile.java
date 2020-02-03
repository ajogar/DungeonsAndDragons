package tiles;

import javax.swing.*;

public class WarpTile extends Tile {
    public WarpTile(int x, int y, ImageIcon image) {
        super(x, y, image);
        type = "warp";
    }
}
