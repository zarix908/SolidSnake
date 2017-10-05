package models;

public interface ICreature {
    Direction getNextMove(ICreature[][] field); //TODO: any other args?
    boolean isDead();
    void interactWith(ICreature otherCreature);
    void cleanUp();
    Point getLocation();
    Direction getDirection();
    CreatureType getCreatureType();
}
