package models;

import java.util.HashMap;
import java.util.Map;

class SnakeBodyPartSkeleton implements SnakeBodyPart {
    SnakeBodyPartSkeleton(boolean isHead, Direction direction, Point location, Snake snake) {
        _isDead = false;
        _isHead = isHead;
        _direction = direction;
        _location = location;
        _snake = snake;
    }

    @Override
    public void makeMove(Creature[][] field) {
        if(_precedingBodyPart != null && _precedingBodyPart.isDead()){
            _isDead = true;
            return;
        }
        //TODO: relocate this into usable from all places structure
        //Maybe location = next.location would be better
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
            case None:
                break;
            default:
                throw new UnsupportedOperationException("Snake must go somewhere!");
        }
        if(!_isHead){
            _direction = _precedingBodyPart.getDirection();
        }
    }

    private boolean _isDead;
    @Override
    public boolean isDead(){
        return _isDead;
    }

    @Override
    public void interactWith(Creature otherCreature) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void cleanUp() {
        if(!_isDead){
            throw new UnsupportedOperationException("You can't dump the body before killing it!");
        }
        deattachNextBodyPart();
        if (_precedingBodyPart != null) {
            _precedingBodyPart.deattachNextBodyPart();
        }
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

    public void setDirection(Direction direction) {
        if(!_isHead)
            return;
        if(direction != ILLEGAL_TURN.get(direction) || direction != Direction.None){
            _direction = direction;
        }
    }

    private static final Map<Direction, Direction> ILLEGAL_TURN = new HashMap<>(){
        {
            put(Direction.Up, Direction.Down);
            put(Direction.Down, Direction.Up);
            put(Direction.Left, Direction.Right);
            put(Direction.Right, Direction.Left);
        }
    };

    @Override
    public CreatureType getCreatureType() {
        return null;
    }


    private Snake _snake;

    @Override
    public Snake getSnake(){
        return _snake;
    }


    private SnakeBodyPart _nextBodyPart = null;

    @Override
    public SnakeBodyPart getNextBodyPart(){
        return _nextBodyPart;
    }

    private SnakeBodyPart _precedingBodyPart = null;

    @Override
    public SnakeBodyPart getPrecedingBodyPart(){
        return _precedingBodyPart;
    }


    @Override
    public void attachNewBodyPart(SnakeBodyPart bodyPart){
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
