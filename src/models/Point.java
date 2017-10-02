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
}
