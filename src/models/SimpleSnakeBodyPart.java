package models;

class SimpleSnakeBodyPart implements ICreature, ISnakeBodyPart {

    private final SnakeBodyPartSkeleton _skeleton;
    private static final CreatureType CREATURE_TYPE = CreatureType.SimpleSnake;

    public SimpleSnakeBodyPart(boolean isHead, Direction direction, Point location, Snake snake) {
        _skeleton = new SnakeBodyPartSkeleton(isHead, direction, location, snake);
    }
    
    @Override
    public void makeMove(ICreature[][] field) {
        _skeleton.makeMove(field);
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
        if (otherCreature instanceof Apple){
            _skeleton.getSnake().incrementScore(10);
        }
        else if (otherCreature instanceof SimpleSnakeBodyPart) {
            _skeleton.setIsDead();
        }
        else {
            throw new UnsupportedOperationException();
        }
    }

    @Override
    public void cleanUp() {
        if(!_skeleton.isDead()){
            throw new UnsupportedOperationException("You can't dump the body before killing it!");
        }
        _skeleton.deattachNextBodyPart();
        _skeleton.getPrecedingBodyPart().getPrecedingBodyPart();
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
    public void setDirection(Direction newDirection) {
        _skeleton.setDirection(newDirection);
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
