package org.chrishood.mapping;

import org.chrishood.gameobject.GameObject;
import org.chrishood.gameobject.components.IGameComponent;
import org.chrishood.gameobject.components.LocationComponent;
import org.chrishood.gameobject.messages.GameObjectMessage;
import org.chrishood.gameobject.messages.IMessageConsumer;
import org.chrishood.gameobject.messages.MovedMessage;
import org.chrishood.util.Point;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Chris on 10/8/2015.
 */
public class Dungeon implements IMessageConsumer {
    List<TileMap> floors;
    List<HashMap<Point, LocationComponent>> gameObjects;

    public Dungeon(List<TileMap> floors) {
        this.floors = floors;
        this.gameObjects = new ArrayList<>();
        for (int i = 0; i < floors.size(); i++) {
            gameObjects.add(new HashMap<>());
        }
    }

    @Override
    public void receiveMessage(String topic, GameObjectMessage message) {
        if (topic.equals("moved") && message.getContentType() == MovedMessage.class) {
            MovedMessage moved = (MovedMessage) message.getContents();
            if (gameObjects.get(moved.getOldFloor()).get(moved.getOldLocation()) == null
                || gameObjects.get(moved.getNewFloor()).get(moved.getNewLocation()) != null) {
                throw new IllegalStateException();
            }
            LocationComponent locationComponent = getLocationComponent(message.getSource());

            gameObjects.get(moved.getOldFloor()).remove(moved.getOldLocation());
            gameObjects.get(moved.getNewFloor()).put(moved.getNewLocation(), locationComponent);
        }
    }

    public void addGameObject(GameObject object) {
        LocationComponent locationComponent = getLocationComponent(object);
        gameObjects.get(locationComponent.getFloor())
                   .put(new Point(locationComponent.getFloorLocation()), locationComponent);

        object.subscribe("moved", this);
    }

    public boolean moveGameObject(GameObject object, Point newLocation, int floor) {
        if (!open(newLocation, floor)) return false;

        LocationComponent locationComponent = getLocationComponent(object);
        locationComponent.move(newLocation, floor);
        return true;
    }

    public boolean open(Point location, int floor) {
        return floors.get(floor).getTile(location.getY(), location.getX()).isPassable() &&
               gameObjects.get(floor).get(location) == null;
    }

    public TileMap getFloor(int level) {
        return floors.get(level);
    }

    public GameObject getObjectAt(int floor, Point location) {
        return gameObjects.get(floor).get(location).getGameObject();
    }

    private LocationComponent getLocationComponent(GameObject object) {
        IGameComponent gameComponent = object.getGameComponent(LocationComponent.class.getName());
        if (!(gameComponent instanceof LocationComponent))
            throw new IllegalArgumentException("object does not have a LocationComponent.");
        return (LocationComponent) gameComponent;
    }
}
