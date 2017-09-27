package models;

import jdk.jshell.spi.ExecutionControl;

public class SimpleSnakeBodyPart implements ISnakeBodyPart {

    private SimpleSnakeBodyPart _previousBodyPart = null;
    private SimpleSnakeBodyPart _nextBodyPart = null;
    private Direction _direction;
    private boolean _isDead;

    public SimpleSnakeBodyPart(Direction startDirection){
        _direction = startDirection;
    }

    @Override
    public boolean isHead() {
        return _previousBodyPart == null;
    }

    @Override
    public ISnakeBodyPart getNextBodyPart() {
        return _nextBodyPart;
    }

    @Override
    public void attachNew(ISnakeBodyPart bodyPart) {
        if (_nextBodyPart != null)
            _nextBodyPart.attachNew(bodyPart);
        if (!(bodyPart instanceof SimpleSnakeBodyPart))
            throw new UnsupportedOperationException();
        _nextBodyPart = (SimpleSnakeBodyPart)bodyPart;
        bodyPart.attachToPrevious(this);

    }

    @Override
    public void attachToPrevious(ISnakeBodyPart bodyPart) {

    }

    @Override
    public Direction getNextMove(GameField field) {
        return _direction;
        //TODO: Add change of direction depending on user input / previousBodyPart direction
    }

    @Override
    public boolean isDead() {
        return _isDead;
    }

    @Override
    public void interactWith(ICreature otherCreature) {

    }
}
