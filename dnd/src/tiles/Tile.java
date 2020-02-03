package tiles;

import util.LocationBuilder;

import javax.swing.*;

public class Tile {
    String location, type;
    ImageIcon image;

    public Tile(int x, int y, ImageIcon image) {
        location = LocationBuilder.buildLocation(x, y);
        type = "generic";
        this.image = image;
    }

    public String getLocation() {
        return location;
    }

    public String getType() {
        return type;
    }

    public ImageIcon getImage() {
        return image;
    }

}
