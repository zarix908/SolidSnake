package models;

import java.util.concurrent.ThreadLocalRandom;

public class Point {

    public Point(int x, int y){
        _x = x;
        _y = y;
    }

    private int _x;

    public int getX(){
        return _x;
    }

    private int _y;

    public int getY(){
        return _y;
    }

    @Override
    public boolean equals(Object obj) {
        Point otherPoint = obj instanceof Point ? ((Point) obj) : null;
        return otherPoint != null && _x == otherPoint._x && _y == otherPoint._y;
    }

    @Override
    public int hashCode() {
        return _x * 6291469 + _y;
    }

    public boolean isInBounds(int width, int height){
        return _x >= 0 && _y >= 0 && _x < width && _y < height;
    }

    public static Point generateRandomInBounds(int x1, int x2,
                                               int y1, int y2,
                                               int borderX,
                                               int borderY){
        //TODO: args checking
        int randomX = ThreadLocalRandom.current().
                nextInt(x1 + borderX, x2 - borderX + 1);
        int randomY = ThreadLocalRandom.current().
                nextInt(y1 + borderY, y2 - borderY + 1);
        return new Point(randomX, randomY);
    }
}
