package org.chrishood.mapping;

/**
 * Created by Chris on 10/2/2015.
 */
public class Tile {
    private boolean passable;
    private char display;
    private short color;

    public boolean isPassable() {
        return passable;
    }

    public char getDisplay() {
        return display;
    }

    public short getColor() {
        return color;
    }

    public Tile(boolean passable, char display, short color) {
        this.passable = passable;
        this.display = display;
        this.color = color;
    }

    public Tile() {
    }
}
