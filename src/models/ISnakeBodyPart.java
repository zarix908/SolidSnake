package models;

public interface ISnakeBodyPart extends ICreature {
    boolean isHead();
    ISnakeBodyPart getNextBodyPart();
    void attachNew(ISnakeBodyPart bodyPart);
    void attachToPrevious(ISnakeBodyPart bodyPart);
}
