package org.chrishood.gameobject.components;

import org.chrishood.gameobject.GameObject;
import org.chrishood.gameobject.messages.GameObjectMessage;
import org.chrishood.gameobject.messages.MovedMessage;
import org.chrishood.util.Point;

/**
 * Created by Chris on 10/8/2015.
 * <p>
 * The Location Component is used in order to determine the location of a GameObject.
 */
public class LocationComponent implements IGameComponent {
    private GameObject owner;
    private Point floorLocation;
    private int floor;
    
    public void move(Point newLocation, int newFloor) {
        MovedMessage message = new MovedMessage(floorLocation, newLocation, floor, newFloor);
        floor = newFloor;
        floorLocation = newLocation;
        owner.broadcast("moved", new GameObjectMessage<>(message, this));
    }

    public int getFloor() {
        return floor;
    }

    public void setFloor(int floor) {
        MovedMessage message = new MovedMessage(floorLocation, floorLocation, this.floor, floor);
        this.floor = floor;
        owner.broadcast("moved", new GameObjectMessage<>(message, this));
    }

    public Point getFloorLocation() {
        return floorLocation;
    }

    public void setFloorLocation(Point floorLocation) {
        MovedMessage message = new MovedMessage(this.floorLocation, floorLocation, floor, floor);
        this.floorLocation = floorLocation;
        owner.broadcast("moved", new GameObjectMessage<>(message, this));
    }

    public LocationComponent(GameObject owner, Point floorLocation, int floor) {
        this.owner = owner;
        this.floorLocation = floorLocation;
        this.floor = floor;
    }

    @Override
    public String getId() {
        return this.getClass().getName();
    }

    @Override
    public GameObject getGameObject() {
        return owner;
    }

    @Override
    public void receiveMessage(String topic, GameObjectMessage message) {

    }
}
