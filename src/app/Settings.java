package app;

import java.util.HashMap;
import java.util.Map;
import javafx.scene.paint.Color;

//TODO: It's a placeholder for settings configuration, something should be implemented in "models" in order to get everything working
// Note: shitty implementation
public final class Settings {

    private static int _size = 20;
    private static int _cols = 30;
    private static int _rows = 30;

    private static final Map<TextureType, Color> _colorDict = new HashMap<TextureType, Color>(){{
        put(TextureType.SimpleSnakeBodyPart, Color.LIGHTBLUE);
        put(TextureType.SnakeHead, Color.BLUE);
        put(TextureType.TailDiscardSnakeBodyPart, Color.AQUAMARINE);
        put(TextureType.Apple, Color.FORESTGREEN);
        put(TextureType.Mushroom, Color.ORANGERED);
        put(TextureType.Wall, Color.BLACK);
    } };

    // Note: we can get this info from GameFrame class --> are these getters unnecessary?
    static int getSize() {
        return _size;
    }
    static int getCols() {
        return _cols;
    }
    static int getRows() {
        return _rows;
    }

    static int getHeight() {
        return _cols * _size;
    }
    static int getWidth() {
        return _rows * _size;
    }

    public static void setSize(int size) {
        _size = size;
    }
    public static void setCols(int cols) {
        _cols = cols;
    }
    public static void setRows(int rows) {
        _rows = rows;
    }

    static Map<TextureType, Color> getColorDict(){
        return _colorDict;
    }
}
