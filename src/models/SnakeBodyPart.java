package models;
abstract class SnakeBodyPart {

    public abstract boolean isDead();
    public abstract boolean isHead();
    public abstract Direction getDirection();
    public abstract SnakeBodyPart getNextBodyPart();
    public abstract SnakeBodyPart getPrecedingBodyPart();
    public abstract void attachNew(SnakeBodyPart bodyPart);
    protected abstract void attachToPreceding(SnakeBodyPart bodyPart);
    protected abstract void deattachFromPrecedingBodyPart();
    public abstract void deattachNextBodyPart();
}
