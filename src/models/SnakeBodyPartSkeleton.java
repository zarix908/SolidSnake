package models;

class SnakeBodyPartSkeleton implements ISnakeBodyPart {
    public SnakeBodyPartSkeleton(boolean isHead, Direction direction, Point location, Snake snake) {
        _isDead = false;
        _isHead = isHead;
        _direction = direction;
        _location = location;
        _snake = snake;
    }


    private boolean _isDead;

    @Override
    public boolean isDead(){
        return _isDead;
    }

    public void setIsDead(boolean isDead){
        _isDead = isDead;
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

    public void setDirection(Direction direction) {
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

    public void setNextBodyPart(ISnakeBodyPart nextBodyPart) {
        _nextBodyPart = nextBodyPart;
    }


    private ISnakeBodyPart _precedingBodyPart = null;

    @Override
    public ISnakeBodyPart getPrecedingBodyPart(){
        return _precedingBodyPart;
    }

    public void setPrecedingBodyPart(ISnakeBodyPart precedingBodyPart) {
        _precedingBodyPart = precedingBodyPart;
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
        deattachNextBodyPart();
    }
}
