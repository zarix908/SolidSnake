package models;

class Apple implements Creature {

    Apple(Point location, int turnCreated, int turnsAlive){
        _location = location;
        _turnCreated = turnCreated;
        _turnsAlive = turnsAlive;
    }

    private final int _turnCreated;
    private final int _turnsAlive;

    @Override
    public void makeMove(Creature[][] field, int currentTurn) {
        if(_turnCreated + _turnsAlive == currentTurn){
            _isDead = true;
        }
    }

    private boolean _isDead = false;
    @Override
    public boolean isDead() {
        return _isDead;
    }

    @Override
    public void interactWith(Creature otherCreature) {
        if (otherCreature instanceof SnakeBodyPart)
            _isDead = true;
        else{
            throw new UnsupportedOperationException("This apple doesn't know how to behave in these " +
                    "circumstances WutFace");
        }
    }

    @Override
    public void cleanUp() {
    }

    private Point _location;
    @Override
    public Point getLocation() {
        return _location;
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
