package models;

class SnakeBodyPartSkeleton implements ISnakeBodyPart {
    SnakeBodyPartSkeleton(boolean isHead, Direction direction, Point location, Snake snake) {
        _isDead = false;
        _isHead = isHead;
        _direction = direction;
        _location = location;
        _snake = snake;
    }

    @Override
    public void makeMove(ICreature[][] field) {
        if(_precedingBodyPart.isDead())
            _isDead = true;
        switch (_direction){
            case Up:
                _location = new Point(_location.getX(), _location.getY() - 1);
                break;
            case Down:
                _location = new Point(_location.getX(), _location.getY() + 1);
                break;
            case Left:
                _location = new Point(_location.getX() - 1, _location.getY());
                break;
            case Right:
                _location = new Point(_location.getX() + 1, _location.getY());
                break;
            default:
                throw new UnsupportedOperationException("Snake must go somewhere!");
        }
        _direction = _precedingBodyPart.getDirection();
    }

    private boolean _isDead;
    @Override
    public boolean isDead(){
        return _isDead;
    }

    @Override
    public void interactWith(ICreature otherCreature) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void cleanUp() {

    }

    void setIsDead(){
        _isDead = true;
    }


    private boolean _isHead;

    @Override
    public boolean isHead(){
        return _isHead;
    }


    private Point _location;

    @Override
    public Point getLocation(){
        return _location;
    }

    public void setLocation(Point location){
        _location = location;
    }


    private Direction _direction;

    @Override
    public Direction getDirection(){
        return _direction;
    }

    @Override
    public CreatureType getCreatureType() {
        return null;
    }

    public void setDirection(Direction direction) {
        if(!_isHead)
            return;
        _direction = direction;
    }


    private Snake _snake;

    @Override
    public Snake getSnake(){
        return _snake;
    }


    private ISnakeBodyPart _nextBodyPart = null;

    @Override
    public ISnakeBodyPart getNextBodyPart(){
        return _nextBodyPart;
    }

    private ISnakeBodyPart _precedingBodyPart = null;

    @Override
    public ISnakeBodyPart getPrecedingBodyPart(){
        return _precedingBodyPart;
    }


    @Override
    public void attachNewBodyPart(ISnakeBodyPart bodyPart){
        if (_nextBodyPart != null) {
            _nextBodyPart.attachNewBodyPart(bodyPart);
        } else {
            _nextBodyPart = bodyPart;
            SnakeBodyPartSkeleton asSkeleton = (SnakeBodyPartSkeleton)bodyPart;
            asSkeleton.attachToPrecedingBodyPart(this);
        }
    }

    private void attachToPrecedingBodyPart(SnakeBodyPartSkeleton bodyPart){
        if(_isHead){
            throw new UnsupportedOperationException();
        }
        _precedingBodyPart = bodyPart;
    }

    @Override
    public void deattachNextBodyPart() {
        if (_nextBodyPart == null) {
            return;
        }
        SnakeBodyPartSkeleton asSkeleton = (SnakeBodyPartSkeleton)_nextBodyPart;
        asSkeleton.deattachFromPrecedingBodyPart();
        _nextBodyPart = null;
    }

    private void deattachFromPrecedingBodyPart() {
        _isDead = true;
        _precedingBodyPart = null;
    }
}
