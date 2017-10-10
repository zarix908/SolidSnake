package app;

import models.Direction;
import models.Creature;
import models.SnakeBodyPart;
import models.Point;
import java.util.HashMap;
import java.util.Map;

public class GameFrame {

    public GameFrame(int width,
                     int height,
                     Map<Point, Creature> creatures,
                     int[] scores){
        _width = width;
        _height = height;
        _textureInfoMap = convertICreatureToTextureInfo(creatures);
        _scores = scores;
    }

    private Map<Point, TextureInfo> convertICreatureToTextureInfo(Map<Point, Creature> creatures){
        Map<Point, TextureInfo> textures = new HashMap<>();
        for (Point location : creatures.keySet()) {
            Creature creature = creatures.get(location);
            SnakeBodyPart snakeBodyPart = creature instanceof SnakeBodyPart
                    ? ((SnakeBodyPart) creature)
                    : null;
            boolean isHead = snakeBodyPart == null || snakeBodyPart.isHead();
            TextureInfo texture = new TextureInfo(
                    CreatureToTextureConverter.converters
                            .get(creature.getCreatureType())
                            .apply(isHead),
                    creature.getDirection());
            textures.put(location, texture);
        }
        return textures;
    }



    class TextureInfo{
        private TextureType _type;
        private Direction _direction;
        private Point _location;

        TextureInfo(TextureType type,
                    Direction direction){
                    //Point location)
            _type = type;
            _direction = direction;

            // Is it necessary?
//            if(!location.isInBounds(_width, _height)){
//                throw new IndexOutOfBoundsException(
//                        "It's not index, but let's pretend it's a good type for exception here");
//            }
//            _location = location;
        }

        TextureType getType() {
            return _type;
        }
        Direction getDirection() {
            return _direction;
        }
    }

    private final int _width;
    public int getWidth() {
        return _width;
    }

    private final int _height;
    public int getHeight() {
        return _height;
    }

    private final Map<Point, TextureInfo> _textureInfoMap;

    Map<Point, TextureInfo> getTexturesInfo(){
        return _textureInfoMap;
    }

    private final int[] _scores;

    public int[] getScores() {
        return _scores;
    }

}
