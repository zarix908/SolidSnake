package models;

public interface Creature {
    void makeMove(Creature[][] field); //TODO: any other args?
    boolean isDead();
    void interactWith(Creature otherCreature);
    void cleanUp();
    Point getLocation();
    Direction getDirection();
    CreatureType getCreatureType();
}
