package models;

public abstract class Snake {
    //TODO: death of snake and etc.
    protected SnakeBodyPart head;

    public SnakeBodyPart getHead() {
        return head;
    }

    //TODO: public abstract void move(KeyStroke keyStroke);

    protected void attachNewBodyPart(SnakeBodyPart bodyPart){
        head.attachNewBodyPart(bodyPart);
        //TODO: bodyPart.setSnake(this)?????
    }
}
