package models;

public interface ICreature {
    Direction getNextMove(GameField field); //TODO: any other args?
    boolean isDead();
    void interactWith(ICreature otherCreature);
}
