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

import java.util.Map;

public class App extends Application {

    //TODO: how to get rid of static?
    private static Game _game;
    private static GameFrame _frame;
    private static GraphicsContext _context;
    private static boolean _isGameOver;
    private static boolean _isPaused = false;
    private static Direction _currDir;
    private static int _snakeCount = 1;
    private static Stage _theStage;
    private static AnimationTimer _gameLoop;

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
        _theStage.setScene(new Scene(createMainMenu(), Color.BLACK));
        _theStage.show();
    }

     private void playSnake(int snakeCount){
        _snakeCount = snakeCount;
        reset(_snakeCount);
        _theStage.setScene(new Scene(createGamePlay(), Color.BLACK));
        _gameLoop.start();
    }

    private Parent createGamePlay(){
        StackPane root = new StackPane();
        root.setPrefSize(_width, _height);

        PauseMenu pauseMenu = new PauseMenu();
        Map<String, MenuButton> bm = pauseMenu.getButtonsMap();
        bm.get("pauseResume").setOnMouseClicked(event -> {
            _isPaused = false;
            root.getChildren().remove(pauseMenu);
            pauseMenu.reload();
        });
        bm.get("pauseRestart").setOnMouseClicked(event -> {
            _isPaused = false;
//            _gameLoop.stop();
            root.getChildren().remove(pauseMenu);
            pauseMenu.reload();
            reset(_snakeCount);
        });
        bm.get("quitYes").setOnMouseClicked(event -> {
            _isPaused = false;
            _gameLoop.stop();
            FadeTransition fade = new FadeTransition(Duration.millis(300), root);
            fade.setFromValue(1);
            fade.setToValue(0);
            fade.setOnFinished(e -> {
                _theStage.setScene(new Scene(createMainMenu(), Color.BLACK));
            });
            fade.play();
        });

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
                    if (_isGameOver) {
                        reset(_snakeCount);
                    }
                    break;
                case ESCAPE:
                    if (_isPaused) {
                        _isPaused = false;
                        root.getChildren().remove(pauseMenu);
                        pauseMenu.reload();
                    } else {
                        _isPaused = true;
                        root.getChildren().add(pauseMenu);
                    }
            }
        });

        _gameLoop = new AnimationTimer(){

            private long _prevTime = 0;

            @Override
            public void handle(long now) {
                if (!_isGameOver && !_isPaused) {
                    if ((now - _prevTime) >= 100 * 1000000) {
                        _prevTime = now;
                        _frame = _game.makeTurn(new Direction[]{_currDir});
                        if (_frame == null) {
                            _isGameOver = true;
                        }
                    }
                }
                Painter.paint(_frame, _context);

            }
        };

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
                new Image(getClass()
                            .getClassLoader()
                            .getResourceAsStream("Images/snakeLogoHD.png"),
                        600,
                        0,
                        true,
                        true)
        );
        FadeTransition logoFade = new FadeTransition(Duration.millis(2000), snakeLogo);
        logoFade.setFromValue(0);
        logoFade.setToValue(1);

        snakeLogo.setOpacity(0);
        root.getChildren().add(snakeLogo);
        StackPane.setAlignment(snakeLogo, Pos.TOP_CENTER);

        MainMenu mainMenu = new MainMenu();
        Map<String, MenuButton> mb = mainMenu.getButtonsMap();
        mb.get("playSolo").setOnMouseClicked(event -> {
            FadeTransition fade = new FadeTransition(Duration.millis(300), root);
            fade.setFromValue(1);
            fade.setToValue(0);
            fade.setOnFinished(e -> playSnake(1));
            fade.play();
        });

        FadeTransition startFade = new FadeTransition(Duration.millis(2000), mainMenu);
        startFade.setFromValue(0);
        startFade.setToValue(1);
        logoFade.setOnFinished(event -> {
            startFade.play();
            mainMenu.setOpacity(0);
            root.getChildren().add(mainMenu);
        });
        logoFade.play();

        return root;
    }

    private static void reset(int snakeCount) {
        _isGameOver = false;
        _currDir = Direction.None;
        _game = new Game(Settings.getCols(), Settings.getRows(), snakeCount);
        _frame = _game.makeTurn(new Direction[]{_currDir});
//        Painter.paint(_frame, _context);
    }

    static void setSnakeCount(int count){
        _snakeCount = count;
    }

    static Stage getStage(){
        return _theStage;
    }
}
