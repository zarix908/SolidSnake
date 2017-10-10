package models;

public interface SnakeBodyPart extends Creature {
    boolean isDead();
    boolean isHead();
    Point getLocation();
    Direction getDirection();
    void setDirection(Direction newDirection);
    Snake getSnake();
    SnakeBodyPart getNextBodyPart();
    SnakeBodyPart getPrecedingBodyPart();
    void attachNewBodyPart(SnakeBodyPart bodyPart);
    void deattachNextBodyPart();
}
