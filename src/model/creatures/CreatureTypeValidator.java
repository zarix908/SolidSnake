package model.creatures;

import static model.creatures.CreatureType.*;

public final class CreatureTypeValidator {
    public static boolean isSnake(CreatureType type){
        return type == SnakeHead || type == SimpleSnakeBodyPart || type == TailDiscardBodyPart;
    }
}
