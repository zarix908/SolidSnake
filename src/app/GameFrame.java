package app;

import models.CreatureType;
import models.Direction;
import models.Creature;
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
        _types = extractTypes(creatures);
        _textureInfoMap = convertICreatureToTextureInfo(creatures);
        _scores = scores;
    }

    private Map<Point, CreatureType> extractTypes(Map<Point, Creature> creatures){
        Map<Point, CreatureType> typeMap = new HashMap<>();
        for (Point location : creatures.keySet()) {
            Creature creature = creatures.get(location);
            typeMap.put(location, creature.getCreatureType());
        }
        return typeMap;
    }

    private Map<Point, TextureInfo> convertICreatureToTextureInfo(Map<Point, Creature> creatures){
        Map<Point, TextureInfo> textures = new HashMap<>();
        for (Point location : creatures.keySet()) {
            Creature creature = creatures.get(location);
            TextureInfo texture = new TextureInfo(
                    CreatureToTextureConverter.converters
                            .get(creature.getCreatureType()),
                    creature.getCurrentDirection());
            textures.put(location, texture);
        }
        return textures;
    }



    class TextureInfo{
        private TextureType _type;
        private Direction _direction;

        TextureInfo(TextureType type,
                    Direction direction){
            _type = type;
            _direction = direction;
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

    public Map<Point, TextureInfo> getTexturesInfo(){
        return _textureInfoMap;
    }

    private final int[] _scores;

    public int[] getScores() {
        return _scores;
    }

    private final Map<Point, CreatureType> _types;

    public Map<Point, CreatureType> getTypes() {
        return _types;
    }
}
