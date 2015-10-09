package org.chrishood.model.mappingsystem;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Chris on 10/2/2015.
 */
public class TileSet {
    private List<Tile> tiles;

    public Tile getTile(int index) {
        return tiles.get(index);
    }

    public TileSet(Collection<String> tileIds) {
        this.tiles = new ArrayList<>();
        tiles.addAll(tileIds.stream().map(tileId -> TileRepository.getInstance().getTile(tileId)).collect(Collectors.toList()));
    }

    public TileSet(InputStream source) {
        this.tiles = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(source))) {
            String str;
            while ((str = reader.readLine()) != null) {
                tiles.add(TileRepository.getInstance().getTile(str));
            }
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }
}
