package visualisation;

import models.Direction;
import models.ICreature;
import models.Point;
import visualisation.TextureType;

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

    class TextureInfo{
        private TextureType _type;
        private Direction _direction;
        private Point _location;

        public TextureInfo(TextureType type,
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
