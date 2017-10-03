package models;

class SimpleSnakeBodyPart extends SnakeBodyPart implements ICreature {

    public SimpleSnakeBodyPart(boolean isHead, Direction direction, Point location, Snake snake) {
        super(isHead, direction, location, snake);
    }
    
    @Override
    public Direction getNextMove(Game field) {
        throw new UnsupportedOperationException();
    }



    @Override
    public void interactWith(ICreature otherCreature) {
        _isDead = true;
        throw new UnsupportedOperationException();
    }

    @Override
    public void cleanUp() {


    }
}
