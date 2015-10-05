package layout;

import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.IOException;
import java.io.InputStream;
import java.io.InvalidObjectException;
import java.util.HashMap;

/**
 * Created by Chris on 10/3/2015.
 */
public class TileRepository {

    private static final TileRepository instance = new TileRepository();

    protected TileRepository() {
    }

    public static TileRepository getInstance() {
        return instance;
    }

    private boolean initialized = false;
    private HashMap<String, Tile> tiles;

    public boolean isInitialized() {
        return initialized;
    }

    public synchronized void initialize(InputStream source) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        JsonParser parser = mapper.getJsonFactory().createJsonParser(source);
        JsonNode rootNode = parser.readValueAsTree();
        String[] tileIds = mapper.readValue(rootNode.get("ids"), String[].class);
        Tile[] tileArray = mapper.readValue(rootNode.get("tiles"), Tile[].class);

        if (tileIds.length != tileArray.length) {
            throw new InvalidObjectException("Miscount between Ids and tiles.");
        }
        tiles = new HashMap<>();

        for (int i = 0; i < tileIds.length; i++) {
            tiles.put(tileIds[i], tileArray[i]);
        }
        initialized = true;
    }

    public Tile getTile(String name) {
        if (!initialized) {
            throw new IllegalStateException("TileRepository has not been initialized yet.");
        }
        return tiles.get(name);
    }

    public Tile[] getTileArray(int y, int x) {
        return new Tile[y * x];
    }
}
