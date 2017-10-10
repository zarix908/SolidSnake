package models;

class TailDiscardBodyPart implements SnakeBodyPart {

    private static final CreatureType CREATURE_TYPE = CreatureType.TailDiscardBodyPart;

    private final SnakeBodyPartSkeleton _skeleton;

    TailDiscardBodyPart(Direction direction, Point location, Snake snake) {
        _skeleton = new SnakeBodyPartSkeleton(false, direction, location, snake);
    }

    @Override
    public void makeMove(Creature[][] field) {
        _skeleton.makeMove(field);
    }

    @Override
    public boolean isDead() {
        return _skeleton.isDead();
    }

    @Override
    public void interactWith(Creature otherCreature) {

    }

    @Override
    public void cleanUp() {
        _skeleton.cleanUp();
    }

    @Override
    public boolean isHead() {
        return _skeleton.isHead();
    }

    @Override
    public Point getLocation() {
        return _skeleton.getLocation();
    }

    @Override
    public Direction getDirection() {
        return _skeleton.getDirection();
    }

    @Override
    public CreatureType getCreatureType() {
        return CREATURE_TYPE;
    }

    @Override
    public void setDirection(Direction newDirection) {
        _skeleton.setDirection(newDirection);
    }

    @Override
    public Snake getSnake() {
        return _skeleton.getSnake();
    }

    @Override
    public SnakeBodyPart getNextBodyPart() {
        return _skeleton.getNextBodyPart();
    }

    @Override
    public SnakeBodyPart getPrecedingBodyPart() {
        return _skeleton.getPrecedingBodyPart();
    }

    @Override
    public void attachNewBodyPart(SnakeBodyPart bodyPart) {
        _skeleton.attachNewBodyPart(bodyPart);
    }

    @Override
    public void deattachNextBodyPart() {
        _skeleton.deattachNextBodyPart();
    }

    @Override
    public String toString() {
        return String.format("%s at (%d, %d) and with %s",
                CREATURE_TYPE.toString(),
                _skeleton.getLocation().getX(),
                _skeleton.getLocation().getY(),
                _skeleton.getDirection().toString());
    }
}
