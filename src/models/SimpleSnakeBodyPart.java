package models;

class SimpleSnakeBodyPart extends SnakeBodyPart implements ICreature {
    
    private boolean _isHead;
    
    private boolean _isDead;
    
    private Direction _direction;
    
    private SnakeBodyPart _nextBodyPart;
    private SnakeBodyPart _preceedingBodyPart;
    
    public SimpleSnakeBodyPart(boolean isHead, Direction direction)
    {
        _isDead = false;
        _isHead = isHead;
        _direction = direction;
    }
    
    @Override
    public Direction getNextMove(GameField field) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean isDead() {
        return false;
    }

    private void deathProcedure(){
        _isDead = true;
        deattachNextBodyPart();
        _preceedingBodyPart.deattachNextBodyPart();
    }

    @Override
    public void interactWith(ICreature otherCreature) {
        deathProcedure();
    }

    @Override
    public boolean isHead() {
        return _isHead;
    }



    @Override
    public Direction getDirection() {
        if(_isDead){
            return Direction.None;
        }
        return _direction;
    }

    @Override
    public SnakeBodyPart getNextBodyPart() {
        return _nextBodyPart;
    }

    @Override
    public SnakeBodyPart getPrecedingBodyPart() {
        return _preceedingBodyPart;
    }

    @Override
    public void attachNew(SnakeBodyPart bodyPart) {
        if (_nextBodyPart != null) {
            _nextBodyPart.attachNew(bodyPart);
        } else {
            _nextBodyPart = bodyPart;
            bodyPart.attachToPreceding(this);
        }
    }

    @Override
    protected void attachToPreceding(SnakeBodyPart bodyPart) {
        if(_isHead){
            throw new UnsupportedOperationException();
        }
        _preceedingBodyPart = bodyPart;
    }

    @Override
    protected void deattachFromPrecedingBodyPart() {
        _preceedingBodyPart = null;
    }

    @Override
    public void deattachNextBodyPart() {
        if (_nextBodyPart == null) {
            return;
        }
        _nextBodyPart.deattachFromPrecedingBodyPart();
        _nextBodyPart = null;
    }
}
