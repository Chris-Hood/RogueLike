package org.chrishood.gameobject.messages;

import org.chrishood.util.Point;

/**
 * Created by Chris on 10/8/2015.
 */
public class MovedMessage {
    private Point oldLocation;
    private Point newLocation;
    private int oldFloor;
    private int newFloor;

    public int getOldFloor() {
        return oldFloor;
    }

    public int getNewFloor() {
        return newFloor;
    }

    public Point getOldLocation() {
        return oldLocation;
    }

    public Point getNewLocation() {
        return newLocation;
    }

    public MovedMessage(Point oldLocation, Point newLocation, int oldFloor, int newFloor) {
        this.oldFloor = oldFloor;
        this.newFloor = newFloor;
        this.oldLocation = oldLocation;
        this.newLocation = newLocation;
    }
}
