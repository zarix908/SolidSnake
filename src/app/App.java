package app;

import javafx.animation.AnimationTimer;
import javafx.animation.FadeTransition;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;
import models.Direction;
import models.Game;

public class App extends Application {
    private Game _game;
    private GameFrame _frame;
    private GraphicsContext _context;
    private boolean _isPaused;
    private Direction _currDir;

     static Stage _theStage;
     static Scene _mainMenuScene;
     static Scene _gamePlayScene;

    private static int _width = 800;
    private static int _height = 600;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Snake Reborn");
        primaryStage.setResizable(false);
        primaryStage.setFullScreen(false);
        primaryStage.setOnCloseRequest(e -> System.exit(0));

        _theStage = primaryStage;
        _mainMenuScene = new Scene(createMainMenu());
        _gamePlayScene = new Scene(createGamePlay());

        primaryStage.setScene(_mainMenuScene);
        primaryStage.show();
    }

    private Parent createGamePlay(){
        StackPane root = new StackPane();
        Canvas canvas = new Canvas(_width, _height);
        canvas.setFocusTraversable(true);
        root.getChildren().add(canvas);

        _context = canvas.getGraphicsContext2D();

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

        AnimationTimer gameLoop = new AnimationTimer(){

            private long _prevTime = 0;

            @Override
            public void handle(long now) {
                if ((now - _prevTime) >= 100 * 1000000) {
                    _prevTime = now;
                    _frame = _game.makeTurn(new Direction[]{_currDir});
                }

                if (!_isPaused) {
                    Painter.paint(_frame, _context);
                    if (_frame == null) {
                        _isPaused = true;
                    }
                }
            }
        };

        gameLoop.start();
        return root;
    }

    private Parent createMainMenu(){
        StackPane root = new StackPane();
        root.setPrefSize(_width, _height);
        root.setBackground(
                new Background(
                        new BackgroundFill(
                                Color.BLACK,
                                CornerRadii.EMPTY,
                                Insets.EMPTY)
                )
        );

        ImageView snakeLogo = new ImageView(
                new Image(
                        "file:res/images/snakeLogoHD.png",
                        600,
                        0,
                        true,
                        true
                )
        );
        FadeTransition logoFade = new FadeTransition(Duration.millis(2000), snakeLogo);
        logoFade.setFromValue(0);
        logoFade.setToValue(1);

        root.getChildren().add(snakeLogo);
        StackPane.setAlignment(snakeLogo, Pos.TOP_CENTER);

        MainMenu mainMenu = new MainMenu();
//        Pane mainMenu = mainMenuCreator.getMenu();

        FadeTransition startFade = new FadeTransition(Duration.millis(2000), mainMenu);
        startFade.setFromValue(0);
        startFade.setToValue(1);
        logoFade.setOnFinished(event -> {
            startFade.play();
            root.getChildren().add(mainMenu);
        });
        logoFade.play();

        return root;
    }

    private void reset() {
        _isPaused = false;
        _currDir = Direction.None;
        _game = new Game(Settings.getCols(), Settings.getRows(), 1);
        _frame = _game.makeTurn(new Direction[]{_currDir});
//        Painter.paint(_frame, _context);
    }

    static Stage getStage(){
        return _theStage;
    }
}
