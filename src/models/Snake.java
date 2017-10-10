package models;

class Snake {
    public Snake(Point location, Direction startingDirection) {
        this.head = new SimpleSnakeBodyPart(true, startingDirection, location, this);
        _score = 0;
    }

    private ISnakeBodyPart head;

    ISnakeBodyPart getHead() {
        return head;
    }

    ISnakeBodyPart getTail(){
        ISnakeBodyPart bodyPart = head;
        while (true){
            ISnakeBodyPart temporaryBodyPart = bodyPart.getNextBodyPart();
            if(temporaryBodyPart == null)
                break;
            bodyPart = temporaryBodyPart;
        }
        return bodyPart;
    }

    boolean isDead(){
        return head.isDead();
    }

    private int _score;

    public Direction getCurrentDirection() {
        return head.getDirection();
    }

    void setCurrentDirection(Direction newDirection) {
        head.setDirection(newDirection);
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


}
