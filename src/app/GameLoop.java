package app;

import javafx.scene.canvas.GraphicsContext;
import javafx.stage.Stage;
import model.utils.Direction;
import model.game.Game;
import model.game.GameFrame;

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
