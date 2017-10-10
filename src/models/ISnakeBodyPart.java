package models;

public interface ISnakeBodyPart extends ICreature {
    boolean isDead();
    boolean isHead();
    Point getLocation();
    Direction getDirection();
    void setDirection(Direction newDirection);
    Snake getSnake();
    ISnakeBodyPart getNextBodyPart();
    ISnakeBodyPart getPrecedingBodyPart();
    void attachNewBodyPart(ISnakeBodyPart bodyPart);
    void deattachNextBodyPart();
}
