package org.chrishood.util;

/**
 * Created by Chris on 10/5/2015.
 */
public class Point {
    int x, y;

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Point(Point point) {
        x = point.x;
        y = point.y;
    }

    @Override
    public boolean equals(Object that) {
        if (!(that instanceof Point)) return false;
        Point instance = (Point) that;
        return instance.x == this.x && instance.y == this.y;
    }

    @Override
    public int hashCode() {
        return x * y;
    }
}
