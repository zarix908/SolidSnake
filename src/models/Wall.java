package models;

class Wall implements Creature {

    private final Point _location;

    Wall(Point location){
        _location = location;
    }

    @Override
    public void makeMove(Creature[][] field) {
    }

    @Override
    public boolean isDead() {
        return false;
    }

    @Override
    public void interactWith(Creature otherCreature) {
    }

    @Override
    public void cleanUp() {
    }

    @Override
    public Point getLocation() {
        return _location;
    }

    @Override
    public Direction getDirection() {
        return Direction.None;
    }

    @Override
    public CreatureType getCreatureType() {
        return CreatureType.Wall;
    }
}
