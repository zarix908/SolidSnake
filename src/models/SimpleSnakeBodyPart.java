package models;

class SimpleSnakeBodyPart implements ICreature, ISnakeBodyPart {

    private final SnakeBodyPartSkeleton _skeleton;
    private static final CreatureType CREATURE_TYPE = CreatureType.SimpleSnake;

    public SimpleSnakeBodyPart(boolean isHead, Direction direction, Point location, Snake snake) {
        _skeleton = new SnakeBodyPartSkeleton(isHead, direction, location, snake);
    }
    
    @Override
    public Direction getNextMove(ICreature[][] field) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean isDead() {
        return _skeleton.isDead();
    }

    @Override
    public boolean isHead() {
        return _skeleton.isHead();
    }


    @Override
    public void interactWith(ICreature otherCreature) {
        //_skeleton.setIsDead(true);
        //TODO: implement
        throw new UnsupportedOperationException();
    }

    @Override
    public void cleanUp() {
        throw new UnsupportedOperationException();
        //TODO: implement
    }

    @Override
    public Point getLocation() {
        return _skeleton.getLocation();
    }

    @Override
    public CreatureType getCreatureType() {
        return CREATURE_TYPE;
    }

    @Override
    public Direction getDirection() {
        return _skeleton.getDirection();
    }

    @Override
    public Snake getSnake() {
        return _skeleton.getSnake();
    }

    @Override
    public ISnakeBodyPart getNextBodyPart() {
        return _skeleton.getNextBodyPart();
    }

    @Override
    public ISnakeBodyPart getPrecedingBodyPart() {
        return _skeleton.getPrecedingBodyPart();
    }

    @Override
    public void attachNewBodyPart(ISnakeBodyPart bodyPart) {
        _skeleton.attachNewBodyPart(bodyPart);
    }

    @Override
    public void deattachNextBodyPart() {
        _skeleton.deattachNextBodyPart();
    }
}
