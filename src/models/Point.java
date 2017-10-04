package models;

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
}
