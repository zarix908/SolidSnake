package visualisation;

import models.Direction;
import models.ICreature;
import models.ISnakeBodyPart;
import models.Point;
import java.util.HashMap;
import java.util.Map;

public class GameFrame{

    public GameFrame(int width,
                     int height,
                     Map<Point, ICreature> creatures){
        _width = width;
        _height = height;
        _textureInfoMap = new HashMap<>();
    }

    private Map<Point, TextureInfo> convertICreatureToTextureInfo(Map<Point, ICreature> creatures){
        Map<Point, TextureInfo> textures = new HashMap<>();
        for (Point location : creatures.keySet()) {
            ICreature creature = creatures.get(location);
            ISnakeBodyPart snakeBodyPart = creature instanceof ISnakeBodyPart
                    ? ((ISnakeBodyPart) creature)
                    : null;
            boolean isHead = snakeBodyPart == null || snakeBodyPart.isHead();
            TextureInfo texture = new TextureInfo(
                    CreatureToTextureConverter.converters.get(creature.getCreatureType())
                            .apply(isHead),
                    creature.getDirection(), location);
            textures.put(location, texture);
        }
        return textures;
    }

    class TextureInfo{
        private TextureType _type;
        private Direction _direction;
        private Point _location;

        TextureInfo(TextureType type,
                           Direction direction,
                           Point location) {
            _type = type;
            _direction = direction;
            if(!location.isInBounds(_width, _height)){
                throw new IndexOutOfBoundsException(
                        "It's not index, but let's pretend it's a good type for exception here");
            }
            _location = location;
        }
    }
    private int _width;

    public int getWidth() {
        return _width;
    }

    private int _height;

    public int getHeight() {
        return _height;
    }

    private Map<Point, TextureInfo> _textureInfoMap;

    Map<Point, TextureInfo> getTexturesInfo(){
        return _textureInfoMap;
    }

    /*
    * GameFrame -> enumerate through (name, direction, location)
    *
    *
    * */
}
