package org.chrishood.mapping;

/**
 * Created by Chris on 10/2/2015.
 */
public class TileMap {
    private final int xSize;
    private final int ySize;
    private Tile[] tiles;

    public int getxSize() {
        return xSize;
    }

    public int getySize() {
        return ySize;
    }

    public Tile getTile(int y, int x) {
        assertBounds(y, x);
        return tiles[get1DimOffset(y, x)];
    }

    public void setTile(int y, int x, Tile tile) {
        assertBounds(y, x);
        tiles[get1DimOffset(y, x)] = tile;
    }

    public TileMap(int ySize, int xSize) {
        this.ySize = ySize;
        this.xSize = xSize;
        this.tiles = TileRepository.getInstance().getTileArray(ySize, xSize);
    }

    private void assertBounds(int y, int x) {
        if (y >= ySize || y < 0) {
            throw new IllegalArgumentException("y is outside of allowable bounds of the map: " + y + " ySize: " + ySize);
        }
        if (x >= xSize || x < 0) {
            throw new IllegalArgumentException("y is outside of allowable bounds of the map: " + x + " ySize: " + xSize);
        }
    }

    private int get1DimOffset(int y, int x) {
        return y * xSize + x;
    }
}
