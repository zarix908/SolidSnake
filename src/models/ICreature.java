package models;

public interface ICreature {
    Direction getNextMove(Game field); //TODO: any other args?
    boolean isDead();
    void interactWith(ICreature otherCreature);
    void cleanUp();
    Point getLocation();
}
