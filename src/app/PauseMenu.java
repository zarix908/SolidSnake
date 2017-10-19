package app;

import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import java.util.Map;

class PauseMenu extends Menu {

    private Map<String, MenuButton> _buttons;
    private Rectangle _bg;
    private MenuBox _menuPause;

    PauseMenu(){
        //TODO: get width and height from App
        _bg = new Rectangle(800, 600);
        _bg.setFill(Color.GREY);
        _bg.setOpacity(0.4);

        MenuButton pauseResume = new PauseMenuButton("RESUME", 22);
        MenuButton pauseRestart = new PauseMenuButton("RESTART", 22);
        MenuButton pauseQuit = new PauseMenuButton("QUIT", 22);
        _menuPause = new PauseMenuBox(
                pauseResume,
                pauseRestart,
                pauseQuit
        );

        StackPane quitText = new StackPane();
        quitText.setAlignment(Pos.CENTER);
        Text text = new Text("ARE YOU SURE YOU WANT TO QUIT TO MAIN MENU?");
        text.setFill(Color.BLACK);
        text.setFont(Font.font("Calibri", FontWeight.SEMI_BOLD, 22));
        quitText.getChildren().add(text);

        HBox quitConfirm = new HBox();
        quitConfirm.setAlignment(Pos.CENTER);
        MenuButton quitYes = new PauseMenuButton("YES", 22);
        MenuButton quitNo = new PauseMenuButton("NO", 22);
        quitConfirm.getChildren().addAll(quitYes, quitNo);

        VBox menuQuit = new VBox();
        menuQuit.setAlignment(Pos.CENTER);
        menuQuit.getChildren().addAll(quitText, quitConfirm);

        pauseResume.setOnMouseClicked(event -> {});
        pauseRestart.setOnMouseClicked(event -> {});
        pauseQuit.setOnMouseClicked(event -> {
            getChildren().remove(_menuPause);
            getChildren().add(menuQuit);
        });

        quitYes.setOnMouseClicked(event -> {});
        quitNo.setOnMouseClicked(event -> {
            getChildren().remove(menuQuit);
            getChildren().add(_menuPause);
        });

        _menuPause.setAlignment(Pos.CENTER);
        setAlignment(Pos.CENTER);

        getChildren().addAll(_bg, _menuPause);

        //TODO: Java9 ONLY!!!!!
        _buttons = Map.of("pauseResume", pauseResume, "pauseRestart", pauseRestart, "quitYes", quitYes);
    }

    void reload() {
        getChildren().clear();
        getChildren().addAll(_bg, _menuPause);
    }

    Map<String, MenuButton> getButtonsMap(){
        return _buttons;
    }
}
