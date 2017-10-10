package app;

import models.CreatureType;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

final class CreatureToTextureConverter {
    static final Map<CreatureType, Function<Boolean, TextureType>> converters = new HashMap<>() {
        {
            put(CreatureType.SimpleSnakeBodyPart, b -> b ? TextureType.SimpleSnakeHead : TextureType.SimpleSnakeBodyPart);
            put(CreatureType.Apple, b -> TextureType.Apple);
            put(CreatureType.Mushroom, b -> TextureType.Mushroom);
            put(CreatureType.TailDiscardBodyPart, b -> TextureType.TailDiscardSnakeBodyPart);
        }
    };
}