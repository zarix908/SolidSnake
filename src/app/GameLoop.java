package app;

import app.Painter;
import models.Direction;
import models.Game;
import javafx.scene.canvas.GraphicsContext;

public class GameLoop implements Runnable {
    private final Game _game;
    private final GraphicsContext _gc;

    private int _frameRate;
    private float _interval;
    private boolean _running;
    private boolean _paused;
    private boolean _keyIsPressed;
    private Direction _direction;

    private GameFrame _frame;


    public GameLoop(final Game game, final  GraphicsContext gc){
        _game = game;
        _gc = gc;

        //TODO: framerate is for speed control and therefore should be initiated and changed in "Game" (eg: boosts slow/speed up the game)
        _frameRate = 20;

        _interval = 1000.0f / _frameRate;
        _running = true;
        _paused = false;
        _keyIsPressed = false;
        _direction = Direction.None;
    }

    public void stop() {
        _running = false;
    }
    public void resume() {
        _paused = false;
    }
    public void pause() {
        _paused = true;
    }

    public boolean isKeyPressed() {
        return _keyIsPressed;
    }
    public void setKeyPressed() {
        _keyIsPressed = true;
    }

    public boolean isPaused() {
        return _paused;
    }

    public int getFrameRate() {
        return _frameRate;
    }
    public void setFrameRate(int frameRate) {
        _frameRate = frameRate;
    }

    public void setDirection(Direction direction) {
        _direction = direction;
    }

    @Override
    public void run() {
        while (_running && !_paused) {
            float time = System.currentTimeMillis();
            _keyIsPressed = false;

            //NOTICE: game class now supports multiple snakes (for possible future multiplayer)
            Direction[] directions = {_direction};
            _frame = _game.makeTurn(directions);
            Painter.paint(_frame, _gc);

            //TODO: check if snake is dead, as for know consider that gameframe is null
            if (_frame == null) {
                pause();
                Painter.paintResetMessage(_gc);
                break;
            }

            // fps management
            time = System.currentTimeMillis() - time;
            if (time < _interval) {
                try {
                    Thread.sleep((long) (_interval - time));
                } catch (InterruptedException ignore) {
                }
            }
        }
    }
}
