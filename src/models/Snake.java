package models;

class Snake {

    private SnakeBodyPart head;
    private int _score;
    private CreatureType _lastBoost;

    public Snake(Point location, Direction startingDirection) {
        this.head = new SimpleSnakeBodyPart(true, startingDirection, location, this);
        _score = 0;
        _lastBoost = null;
    }
    SnakeBodyPart getHead() {
        return head;
    }
    SnakeBodyPart getTail(){
        SnakeBodyPart bodyPart = head;
        while (true){
            SnakeBodyPart temporaryBodyPart = bodyPart.getNextBodyPart();
            if(temporaryBodyPart == null)
                break;
            bodyPart = temporaryBodyPart;
        }
        return bodyPart;
    }
    boolean isDead(){
        return head.isDead();
    }

    public Direction getCurrentDirection() {
        return head.getCurrentDirection();
    }
    void setCurrentDirection(Direction newDirection) {
        head.setCurrentDirection(newDirection);
    }

    int getScore() {
        return _score;
    }
    void incrementScore(int value) {
        if (value < 0) {
            throw new UnsupportedOperationException("How are you gonna win if you" +
                    " are adding negative amount points to score?!");
        }
        _score += value;
    }

    CreatureType getLastBoost() {
        return _lastBoost;
    }
    void setLastBoost(CreatureType boost){
        _lastBoost = boost;
    }
    void resetLastBoost(){
        _lastBoost = null;
    }
    void incrementScore(int value) {
        if (value < 0) {
            throw new UnsupportedOperationException("How are you gonna win if you" +
                    " are adding negative amount points to score?!");
        }
        _score += value;
    }


}
