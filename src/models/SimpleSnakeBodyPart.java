package models;

class SimpleSnakeBodyPart implements Creature, SnakeBodyPart {

    private final SnakeBodyPartSkeleton _skeleton;
    private static final CreatureType CREATURE_TYPE = CreatureType.SimpleSnakeBodyPart;

    public SimpleSnakeBodyPart(boolean isHead, Direction direction, Point location, Snake snake) {
        _skeleton = new SnakeBodyPartSkeleton(isHead, direction, location, snake);
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
    public boolean isHead() {
        return _skeleton.isHead();
    }


    @Override
    public void interactWith(Creature otherCreature) {
        if (otherCreature instanceof Apple){
            _skeleton.getSnake().incrementScore(10);
            _skeleton.getSnake().setLastBoost(CreatureType.Apple);
            SnakeBodyPart tail = getSnake().getTail();
            _skeleton.attachNewBodyPart(new SimpleSnakeBodyPart(
                    false,
                    Direction.None,
                    tail.getLocation(),
                    _skeleton.getSnake())
            );
        }
        else if (otherCreature instanceof Mushroom){
            _skeleton.getSnake().incrementScore(20);
            SnakeBodyPart tail = getSnake().getTail();
            _skeleton.attachNewBodyPart(new TailDiscardBodyPart(
                    Direction.None,
                    tail.getLocation(),
                    _skeleton.getSnake())
            );
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
    public Direction getCurrentDirection() {
        return _skeleton.getCurrentDirection();
    }

    @Override
    public void setCurrentDirection(Direction newDirection) {
        _skeleton.setCurrentDirection(newDirection);
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
    public SnakeBodyPartSkeleton getSkeleton() {
        return _skeleton;
    }

    @Override
    public Direction getPreviousDirection() {
        return _skeleton.getPreviousDirection();
    }

    @Override
    public String toString() {
        return String.format("%s at (%d, %d) with %s",
                CREATURE_TYPE.toString(),
                _skeleton.getLocation().getX(),
                _skeleton.getLocation().getY(),
                _skeleton.getCurrentDirection().toString());
    }
}
