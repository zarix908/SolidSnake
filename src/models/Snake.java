package models;

public abstract class Snake {
    //TODO: death of snake and etc.
    protected SnakeBodyPartSkeleton head;

    public SnakeBodyPartSkeleton getHead() {
        return head;
    }

    //TODO: public abstract void move(KeyStroke keyStroke);

    protected void attachNewBodyPart(SnakeBodyPartSkeleton bodyPart){
        head.attachNewBodyPart(bodyPart);
        //TODO: bodyPart.setSnake(this)?????
    }
}
