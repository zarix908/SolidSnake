package models;

class Snake {
    public Snake(Point location, Direction startingDirection) {
        this.head = new SimpleSnakeBodyPart(true, startingDirection, location, this);
        _score = 0;
    }

    private SnakeBodyPart head;

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

    private int _score;

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


}
