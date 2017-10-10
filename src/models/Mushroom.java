package models;

class Mushroom implements ICreature {

    Mushroom(Point location){
        _location = location;
    }

    @Override
    public void makeMove(ICreature[][] field) {
    }

    private boolean _isDead = false;
    @Override
    public boolean isDead() {
        return _isDead;
    }

    @Override
    public void interactWith(ICreature otherCreature) {
        if (otherCreature instanceof ISnakeBodyPart)
            _isDead = true;
        else{
            throw new UnsupportedOperationException("This mushroom doesn't know how to behave in these " +
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
    public Direction getDirection() {
        return Direction.None;
    }

    @Override
    public CreatureType getCreatureType() {
        return CreatureType.Mushroom;
    }
}
