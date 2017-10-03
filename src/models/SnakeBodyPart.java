package models;
abstract class SnakeBodyPart {
    public SnakeBodyPart(boolean isHead, Direction direction, Point location, Snake snake) {
        _isDead = false;
        _isHead = isHead;
        _direction = direction;
        _location = location;
        _snake = snake;
    }

    protected boolean _isDead;
    public boolean isDead(){
        return _isDead;
    }

    protected boolean _isHead;
    public boolean isHead(){
        return _isHead;
    }

    protected Point _location;
    public Point getLocation(){
        return _location;
    }

    protected Direction _direction;
    public Direction getDirection(){
        return _direction;
    }

    protected Snake _snake;
    public Snake getSnake(){
        return _snake;
    }

    protected SnakeBodyPart _nextBodyPart = null;
    public SnakeBodyPart getNextBodyPart(){
        return _nextBodyPart;
    }

    protected SnakeBodyPart _precedingBodyPart = null;
    public SnakeBodyPart getPrecedingBodyPart(){
        return _precedingBodyPart;
    }

    public void attachNewBodyPart(SnakeBodyPart bodyPart){
        if (_nextBodyPart != null) {
            _nextBodyPart.attachNewBodyPart(bodyPart);
        } else {
            _nextBodyPart = bodyPart;
            bodyPart.attachToPrecedingBodyPart(this);
        }
    }

    private void attachToPrecedingBodyPart(SnakeBodyPart bodyPart){
        if(_isHead){
            throw new UnsupportedOperationException();
        }
        _precedingBodyPart = bodyPart;
    }

    public void deattachNextBodyPart() {
        if (_nextBodyPart == null) {
            return;
        }
        _nextBodyPart.deattachFromPrecedingBodyPart();
        _nextBodyPart = null;
    }

    private void deattachFromPrecedingBodyPart() {
        _isDead = true;
        _precedingBodyPart = null;
        deattachNextBodyPart();
    }
}
