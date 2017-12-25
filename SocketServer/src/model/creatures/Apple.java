package model.creatures;

import model.utils.Direction;
import model.utils.Point;

public class Apple implements Creature {

    private boolean isDead = false;
    private final int turnCreated;
    private final int turnsAlive;


    public Apple(Point location, int turnCreated, int turnsAlive){
        this.location = location;
        this.turnCreated = turnCreated;
        this.turnsAlive = turnsAlive;
    }

    @Override
    public void makeMove(Creature[][] field, int currentTurn) {
        if(turnCreated + turnsAlive == currentTurn){
            isDead = true;
        }
    }

    @Override
    public boolean isDead() {
        return isDead;
    }

    @Override
    public void interactWith(Creature otherCreature) throws IllegalArgumentException {
        CreatureType type = otherCreature.getCreatureType();
        if (CreatureTypeValidator.isSnake(type)) {
            isDead = true;
            return;
        }
        throw new IllegalArgumentException("This apple doesn't know how to behave in these " +
                    "circumstances WutFace");
    }

    @Override
    public void cleanUp() {
    }

    private final Point location;
    @Override
    public Point getLocation() {
        return location;
    }

    @Override
    public Direction getCurrentDirection() {
        return Direction.None;
    }

    @Override
    public CreatureType getCreatureType() {
        return CreatureType.Apple;
    }

    @Override
    public Direction getPreviousDirection() {
        return Direction.None;
    }
}
