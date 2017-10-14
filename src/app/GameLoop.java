package app;

import javafx.scene.canvas.GraphicsContext;
import javafx.stage.Stage;
import models.Direction;
import models.Game;

class GameLoop {

    private Stage theStage;

    private Game _game;
    private GameFrame _frame;
    private GraphicsContext _context;
    private boolean _isPaused;
    private Direction _currDir;
    private int _playerCount;

    GameLoop(int playerCount){
        _playerCount = playerCount;
        theStage = App.getStage();
    }

    void run(){

    }
}
