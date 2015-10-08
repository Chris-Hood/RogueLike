package org.chrishood.gameobject;

import org.chrishood.util.Point;

/**
 * Created by Chris on 10/8/2015.
 */
public class LocationComponent implements IGameComponent {
    private GameObject owner;
    private Point floorLocation;
    private int floor;

    public int getFloor() {
        return floor;
    }

    public void setFloor(int floor) {
        this.floor = floor;
    }

    public Point getFloorLocation() {
        return floorLocation;
    }

    public void setFloorLocation(Point floorLocation) {
        this.floorLocation = floorLocation;
    }

    public LocationComponent(GameObject owner, Point floorLocation, int floor) {
        this.owner = owner;
        this.floorLocation = floorLocation;
        this.floor = floor;
    }

    @Override
    public String getId() {
        return null;
    }

    @Override
    public GameObject getGameObject() {
        return owner;
    }

    @Override
    public void receiveMessage(String topic, GameObjectMessage message) {

    }
}
