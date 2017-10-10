package app;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import models.Direction;
import models.Game;

public class App extends Application {
    private OldGameLoop _loop;
    private Game _game;
    private GameFrame _frame;
    private GraphicsContext _context;
    private boolean _isPaused;
    private Direction _currDir;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        StackPane root = new StackPane();

        Canvas canvas = new Canvas(Settings.getWidth(), Settings.getHeight());
        canvas.setFocusTraversable(true);
        root.getChildren().add(canvas);

        _context = canvas.getGraphicsContext2D();

        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.setTitle("Snake Reborn");
        primaryStage.setOnCloseRequest(e -> System.exit(0));

        canvas.setOnKeyPressed(e -> {
            switch (e.getCode()) {
                case UP:
                    _currDir = Direction.Up;
                    break;
                case DOWN:
                    _currDir = Direction.Down;
                    break;
                case LEFT:
                    _currDir = Direction.Left;
                    break;
                case RIGHT:
                    _currDir = Direction.Right;
                    break;
                case ENTER:
                    if (_isPaused) {
                        reset();
                    }
                    break;
            }
        });

        reset();

        AnimationTimerExt gameLoop = new AnimationTimerExt(50){

            @Override
            public void handle() {
                if (!_isPaused) _frame = _game.makeTurn(new Direction[]{_currDir});

                if (_frame == null)
                    _isPaused = true;

                Painter.paint(_frame, _context);
            }
        };
        primaryStage.show();
        gameLoop.start();
    }



    private void reset() {
        _isPaused = false;
        _currDir = Direction.None;
        _game = new Game(Settings.getCols(), Settings.getRows(), 1);
        _frame = _game.makeTurn(new Direction[]{_currDir});
//        _loop = new OldGameLoop(_game, _context);
        Painter.paint(_frame, _context);
    }
}
