package models;

public interface SnakeBodyPart extends Creature {
    boolean isDead();
    boolean isHead();
    Point getLocation();
    Direction getCurrentDirection();
    void setCurrentDirection(Direction newDirection);
    Snake getSnake();
    SnakeBodyPart getNextBodyPart();
    SnakeBodyPart getPrecedingBodyPart();
    void attachNewBodyPart(SnakeBodyPart bodyPart);
    void deattachNextBodyPart();
    SnakeBodyPartSkeleton getSkeleton();
    Direction getPreviousDirection();
}
