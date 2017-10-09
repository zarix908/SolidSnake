package models;

class Snake {
    public Snake(ISnakeBodyPart head, Direction startingDirection) {
        this.head = head;
        head.setDirection(startingDirection);
        _score = 0;
    }

    private ISnakeBodyPart head;

    ISnakeBodyPart getHead() {
        return head;
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

    void attachNewBodyPart(ISnakeBodyPart bodyPart){
        head.attachNewBodyPart(bodyPart);
    }

    int getScore() {
        return _score;
    }

    void incrementScore(int value) {
        _score += value;
    }


}
