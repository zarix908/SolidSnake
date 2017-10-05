package models;

public interface ISnakeBodyPart {
    boolean isDead();

    boolean isHead();

    Point getLocation();

    Direction getDirection();

    Snake getSnake();

    ISnakeBodyPart getNextBodyPart();

    ISnakeBodyPart getPrecedingBodyPart();

    void attachNewBodyPart(ISnakeBodyPart bodyPart);

    void deattachNextBodyPart();
}
