package app;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import models.Game;

public class App extends Application {
    private GameLoop _loop;
    private Game _game;
    private GameFrame _frame;
    private GraphicsContext _context;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        StackPane root = new StackPane();

        //TODO: const resolution? (in that case const rows/cols)
        Canvas canvas = new Canvas(Settings.getWidth(), Settings.getHeight());
        _context = canvas.getGraphicsContext2D();

        canvas.setFocusTraversable(true);
        //TODO: controller

        reset();
        root.getChildren().add(canvas);
        Scene scene = new Scene(root);

        primaryStage.setResizable(false);
        primaryStage.setTitle("Snake Reborn");
        primaryStage.setOnCloseRequest(e -> System.exit(0));
        primaryStage.setScene(scene);
        primaryStage.show();

        new Thread(_loop).start();
    }

    private void reset() {
        _game = new Game(Settings.getCols(), Settings.getRows());
        _loop = new GameLoop(_game, _context);

        //TODO: initiate the game (get first GameFrame)
        Painter.paint(_frame, _context);
    }
}
