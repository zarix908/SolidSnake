package models;

class SimpleSnakeBodyPart extends SnakeBodyPart implements ICreature {

    public SimpleSnakeBodyPart(boolean isHead, Direction direction, Point location, Snake snake) {
        super(isHead, direction, location, snake);
    }
    
    @Override
    public Direction getNextMove(GameField field) {
        throw new UnsupportedOperationException();
    }

    private void deathProcedure(){
        _isDead = true;
        _direction = Direction.None;
        deattachNextBodyPart();
        _precedingBodyPart.deattachNextBodyPart();
    }

    @Override
    public void interactWith(ICreature otherCreature) {
        deathProcedure();
        throw new UnsupportedOperationException();
    }
}
