package models;

class SimpleSnakeBodyPart implements ICreature, ISnakeBodyPart {

    private final SnakeBodyPartSkeleton _skeleton;
    private static final CreatureType CREATURE_TYPE = CreatureType.SimpleSnakeBodyPart;

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
            ISnakeBodyPart tail = getSnake().getTail();
            _skeleton.attachNewBodyPart(new SimpleSnakeBodyPart(
                    false,
                    Direction.None,
                    tail.getLocation(),
                    _skeleton.getSnake()));
        }
        else if (otherCreature instanceof Mushroom){
            _skeleton.getSnake().incrementScore(20);
            ISnakeBodyPart tail = getSnake().getTail();
            _skeleton.attachNewBodyPart(new TailDiscardBodyPart(
                    Direction.None,
                    tail.getLocation(),
                    _skeleton.getSnake()));
        }
        else if (otherCreature instanceof SimpleSnakeBodyPart) {
            _skeleton.setIsDead();
        }
        else if (otherCreature instanceof  TailDiscardBodyPart){
        }
        else {
            throw new UnsupportedOperationException(String.format("DA FAK MADAFAKA?!" +
                        " I DON'T KNOW HOW TO SPEAK TO WHAMEN!" +
                        "(This (%s) doesn't know hot to interact with (%s))",
                    this.toString(),
                    otherCreature.toString()));
        }
    }

    @Override
    public void cleanUp() {
        _skeleton.cleanUp();
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

    @Override
    public String toString() {
        return String.format("%s at (%d, %d) with %s",
                CREATURE_TYPE.toString(),
                _skeleton.getLocation().getX(),
                _skeleton.getLocation().getY(),
                _skeleton.getDirection().toString());
    }
}
